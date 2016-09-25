#!/bin/bash
set -e

: ${EXT_DIR:="/bi-ext"}

: ${BI_JAVA_OPTS:='-Djava.security.egd=file:/dev/./urandom -Xms4096m -Xmx4096m -Djava.awt.headless=true -Dpentaho.karaf.root.transient=true -XX:+HeapDumpOnOutOfMemoryError -XX:ErrorFile=../logs/jvm_error.log -XX:HeapDumpPath=../logs/ -verbose:gc -Xloggc:../logs/gc.log -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -Dfile.encoding=utf8 -DDI_HOME=\"$DI_HOME\"'}

: ${PDI_HADOOP_CONFIG:="hdp23"}

: ${PDI_MAX_LOG_LINES:="10000"}
: ${PDI_MAX_LOG_TIMEOUT:="1440"}
: ${PDI_MAX_OBJ_TIMEOUT:="240"}

: ${SERVER_NAME:="bi-server"}
: ${SERVER_HOST:="`hostname`"}
: ${SERVER_PORT:="443"}
: ${SERVER_URL:="https://${SERVER_HOST}/pentaho/"}
: ${LOCALE_LANGUAGE:="en"}
: ${LOCALE_COUNTRY:="US"}

: ${DOCKER_GROUP:="pentaho"}
: ${DOCKER_USER:="pentaho"}

map_user() {
	# shamelessly copied from https://github.com/schmidigital/permission-fix/blob/master/tools/permission_fix
	UNUSED_USER_ID=21338
	UNUSED_GROUP_ID=21337

	echo "Fixing permissions..."

	# Setting Group Permissions
	DOCKER_GROUP_CURRENT_ID=`id -g $DOCKER_GROUP`

	if [ $DOCKER_GROUP_CURRENT_ID -eq $HOST_GROUP_ID ]; then
	  echo "Group $DOCKER_GROUP is already mapped to $DOCKER_GROUP_CURRENT_ID. Nice!"
	else
	  echo "Check if group with ID $HOST_GROUP_ID already exists"
	  DOCKER_GROUP_OLD=`getent group $HOST_GROUP_ID | cut -d: -f1`

	  if [ -z "$DOCKER_GROUP_OLD" ]; then
		echo "Group ID is free. Good."
	  else
		echo "Group ID is already taken by group: $DOCKER_GROUP_OLD"

		echo "Changing the ID of $DOCKER_GROUP_OLD group to UNUSED_GROUP_ID"
		groupmod -o -g $UNUSED_GROUP_ID $DOCKER_GROUP_OLD
	  fi

	  echo "Changing the ID of $DOCKER_GROUP group to $HOST_GROUP_ID"
	  groupmod -o -g $HOST_GROUP_ID $DOCKER_GROUP || true
	  echo "Finished"
	  echo "-- -- -- -- --"
	fi

	# Setting User Permissions
	DOCKER_USER_CURRENT_ID=`id -u $DOCKER_USER`

	if [ $DOCKER_USER_CURRENT_ID -eq $HOST_USER_ID ]; then
	  echo "User $DOCKER_USER is already mapped to $DOCKER_USER_CURRENT_ID. Nice!"

	else
	  echo "Check if user with ID $HOST_USER_ID already exists"
	  DOCKER_USER_OLD=`getent passwd $HOST_USER_ID | cut -d: -f1`

	  if [ -z "$DOCKER_USER_OLD" ]; then
		echo "User ID is free. Good."
	  else
		echo "User ID is already taken by user: $DOCKER_USER_OLD"

		echo "Changing the ID of $DOCKER_USER_OLD to $UNUSED_USER_ID"
		usermod -o -u $UNUSED_USER_ID $DOCKER_USER_OLD
	  fi

	  echo "Changing the ID of $DOCKER_USER user to $HOST_USER_ID"
	  usermod -o -u $HOST_USER_ID $DOCKER_USER || true
	  echo "Finished"
	fi
}

init_biserver() {
	if [ ! -d $BISERVER_HOME/tomcat/logs/audit ]; then
		map_user
		
		echo "Creating temporary directories for tomcat..."
		rm -rf tomcat/temp tomcat/work \
			&& mkdir -p /tmp/tomcat/temp /tmp/tomcat/work tomcat/logs/audit pentaho-solutions/system/logs \
			&& ln -s /tmp/tomcat/temp tomcat/temp \
			&& ln -s /tmp/tomcat/work tomcat/work \
			&& ln -s $BISERVER_HOME/tomcat/logs/audit $BISERVER_HOME/pentaho-solutions/system/logs/audit
	fi

	# only useful for testing / development purpose
	# on production, you'll need to use external database like MySQL
	if [ ! -f data/hsqldb/hibernate.properties ]; then
		/bin/cp -rf data/.hsqldb/* data/hsqldb/. \
			&& mkdir -p pentaho-solutions/system/karaf/data/log pentaho-solutions/system/karaf/data/tmp \
			&& sed -i -e 's|\(CATALINA_OPTS=\)\(.*\)|# http://wiki.apache.org/tomcat/HowTo/FasterStartUp#Entropy_Source\n\1" -DKETTLE_HOME='"$KETTLE_HOME $BI_JAVA_OPTS"'"|' start-pentaho.sh \
			&& sed -i -e 's|\(fully-qualified-server-url=\).*|\1'"$SERVER_URL"'|' pentaho-solutions/system/server.properties \
			&& sed -i -e 's|\(locale-language=\).*|\1'"$LOCALE_LANGUAGE"'|' pentaho-solutions/system/server.properties \
			&& sed -i -e 's|\(locale-country=\).*|\1'"$LOCALE_COUNTRY"'|' pentaho-solutions/system/server.properties \
			&& sed -i -e 's|\(<value>\)false\(</value>\)|\1true\2|' pentaho-solutions/system/systemListeners.xml \
			&& sed -i 's/^\(active.hadoop.configuration=\).*/\1'"$PDI_HADOOP_CONFIG"'/' $KETTLE_HOME/plugins/pentaho-big-data-plugin/plugin.properties
			#&& sed -i -e 's|\(,mvn:pentaho-karaf-features/pentaho-big-data-plugin-osgi/6.1.0.1-196/xml/features\)||' pentaho-solutions/system/karaf/etc/org.apache.karaf.features.cfg \
			#&& sed -i -e 's|\(respectStartLvlDuringFeatureStartup=\).*|\1true|' pentaho-solutions/system/karaf/etc/org.apache.karaf.features.cfg \
			#&& sed -i -e 's|\(featuresBootAsynchronous=\).*|\1false|' pentaho-solutions/system/karaf/etc/org.apache.karaf.features.cfg \
			#&& sed -i -e 's|\(,pdi-dataservice,pentaho-marketplace\)||' pentaho-solutions/system/karaf/etc/org.apache.karaf.features.cfg \
	
	fi
}

apply_changes() {
	# you can mount a volume pointing to /pdi-ext for customization
	if [ -d $EXT_DIR ]; then
		# if you have custom scripts to run, let's do it
		if [ -f $EXT_DIR/custom_install.sh ]; then
			echo "Running custom installation script..."
			. $EXT_DIR/custom_install.sh
		# otherwise, simply override files based what we have under ext directory
		elif [ "$(ls -A $EXT_DIR)" ]; then
			echo "Copying files from $EXT_DIR to $BISERVER_HOME..."
			/bin/cp -Rf $EXT_DIR/* .
		fi
	fi

#	&& sed -i -e 's|\(<!-- \[BEGIN HSQLDB DATABASES\]\).*|\1|' tomcat/webapps/pentaho/WEB-INF/web.xml \
#	&& sed -i -e 's|.*\(\[END HSQLDB DATABASES\] -->\)|\1|' tomcat/webapps/pentaho/WEB-INF/web.xml \
#	&& sed -i -e 's|\(<!-- \[BEGIN HSQLDB STARTER\]\).*|\1|' tomcat/webapps/pentaho/WEB-INF/web.xml \
#	&& sed -i -e 's|.*\(\[END HSQLDB STARTER\] -->\)|\1|' tomcat/webapps/pentaho/WEB-INF/web.xml
}

gen_kettle_config() {
	if [ ! -f $KETTLE_HOME/.kettle/kettle.properties ]; then
		echo "Generating kettle.properties..."
		mkdir -p $KETTLE_HOME/.kettle
		cat <<< "# This file was generated by Pentaho Data Integration.
#
# Here are a few examples of variables to set:
#
# PRODUCTION_SERVER = hercules
# TEST_SERVER = zeus
# DEVELOPMENT_SERVER = thor
#
# Note: lines like these with a # in front of it are comments
#
# Read more at https://github.com/pentaho/pentaho-kettle/blob/6.1.0.1-R/engine/src/kettle-variables.xml
KETTLE_EMPTY_STRING_DIFFERS_FROM_NULL=Y
KETTLE_FORCED_SSL=Y

# Master Detector ( start in 1 second, and repeat detection every 10 seconds)
#KETTLE_MASTER_DETECTOR_INITIAL_DELAY=1000
#KETTLE_MASTER_DETECTOR_REFRESH_INTERVAL=10000

#KETTLE_REDIRECT_STDERR=Y
#KETTLE_REDIRECT_STDOUT=Y
#KETTLE_SYSTEM_HOSTNAME=${SERVER_HOST}

# Tracing
#KETTLE_TRACING_ENABLED=Y
#KETTLE_TRACING_HTTP_URL=http://localhost:9411
" > $KETTLE_HOME/.kettle/kettle.properties
	fi

	if [ ! -f $KETTLE_HOME/slave-server-config.xml ]; then
		echo "Generating master server configuration..."
		cat <<< "<slave_config>
        <slaveserver>
            <name>${SERVER_NAME}</name>
            <hostname>${SERVER_HOST}</hostname>
            <port>${SERVER_PORT}</port>
            <webAppName>pentaho</webAppName>
            <master>Y</master>
            <sslMode>Y</sslMode>
        </slaveserver>

        <max_log_lines>${PDI_MAX_LOG_LINES}</max_log_lines>
        <max_log_timeout_minutes>${PDI_MAX_LOG_TIMEOUT}</max_log_timeout_minutes>
        <object_timeout_minutes>${PDI_MAX_OBJ_TIMEOUT}</object_timeout_minutes>
</slave_config>" > $KETTLE_HOME/slave-server-config.xml
	fi
}

# start BI server
if [ "$1" = 'biserver' ]; then
	init_biserver
	gen_kettle_config
	apply_changes

	# update configuration based on environment variables
	# send log output to stdout
	#sed -i 's/^\(.*rootLogger.*\), *out *,/\1, stdout,/' system/karaf/etc/org.ops4j.pax.logging.cfg
	#sed -i -e 's|.*\(runtimeFeatures=\).*|\1'"ssh,http,war,kar,cxf"'|' system/karaf/etc-carte/org.pentaho.features.cfg 

	# now start the bi server
	./start-pentaho.sh
fi

exec "$@"
