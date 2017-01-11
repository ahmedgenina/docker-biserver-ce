#!/bin/bash
[[ "$TRACE" ]] && set -x

log() {
  [[ "$2" ]] && echo "[`date +'%Y-%m-%d %H:%M:%S.%N'`] - $1 - $2"
}

log "INFO" "Removing temporary files created one day before under $BISERVER_HOME/tomcat/temp directory..."
find $BISERVER_HOME/tomcat/temp/* -maxdepth 0 -name "*.*" -mtime +1 | xargs rm -f
log "INFO" "Removing log files created 21 days before under /tmp/kettle directory..."
find /tmp/kettle -name "*.log" -mtime +21 | xargs rm -f
log "INFO" "Done"