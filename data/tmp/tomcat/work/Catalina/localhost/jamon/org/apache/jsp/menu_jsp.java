/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.37
 * Generated at: 2022-02-14 04:29:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.fdsapi.*;
import com.fdsapi.arrays.*;
import com.jamonapi.*;
import com.jamonapi.proxy.*;
import com.jamonapi.utils.*;
import com.jamonapi.distributed.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {


private static String enabled(boolean isEnabled) {
   return isEnabled ? "enabled" : "disabled";
}

private static String getTotalKeySize() {
    long totalKeySize=MonitorFactory.getTotalKeySize();
    return  totalKeySize>0 ? "Total number of characters in Monitor keys: "+totalKeySize : "";
}

private static String enabled(String prefix, boolean isEnabled) {
   return prefix+enabled(isEnabled);
}




  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("com.jamonapi.utils");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("com.fdsapi");
    _jspx_imports_packages.add("com.jamonapi");
    _jspx_imports_packages.add("com.fdsapi.arrays");
    _jspx_imports_packages.add("com.jamonapi.distributed");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("com.jamonapi.proxy");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

String enableAll = request.getParameter("enableAll");

// Enable/Disable jamon as a whole and the proxy capability.  ResultSets will be disabled by default.
if ("Enable Monitoring!".equals(enableAll)) {
  MonitorFactory.enable();
  MonProxyFactory.enableAll(true);
  MonProxyFactory.enableResultSet(false); 

} else if ("Disable Monitoring!".equals(enableAll)) {
  MonitorFactory.disable();
  MonProxyFactory.enableAll(false);

} 

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/JAMonStyles.css\"> \n");
      out.write("<title>JAMon ");
      out.print(MonitorFactory.getVersion());
      out.write(" Menu - Support Pages</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div align=\"center\">\n");
      out.write("<a href=\"http://www.jamonapi.com\"><img src=\"images/jamon_banner3.jpg\" id=\"monLink\" border=\"0\" /></a>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<br><br>\n");
      out.write("<hr width=\"75%\">  \n");
      out.write("<br><br>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div align=\"center\">\n");
      out.write("<form action=\"menu.jsp\" method=\"post\">  \n");
      out.write("    <input type=\"submit\" name=\"enableAll\" value=\"Enable Monitoring!\" >\n");
      out.write("    <input type=\"submit\" name=\"enableAll\" value=\"Disable Monitoring!\" >\n");
      out.write("</form>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<br><br>\n");
      out.write("\n");
      out.write("<div align=\"center\">\n");
      out.write(" <table border=\"1\" class=\"layoutMain\" cellpadding=\"2\" cellspacing=\"0\" width=\"450\">\n");
      out.write("<tr align=\"left\">\n");
      out.write("<th class=\"sectHead\">JAMon ");
      out.print(MonitorFactory.getVersion());
      out.write(" Support Pages</th>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td><a href=\"jamonadmin.jsp\">JAMon Admin Page</a> - Manage the JAMon summary statistics (Currently \n");
      out.print(enabled("JAMon = ",MonitorFactory.isEnabled()));
      out.write(',');
      out.write(' ');
      out.write('\n');
      out.print(enabled("SQL Summary = ",MonProxyFactory.isSQLSummaryEnabled()));
      out.write(',');
      out.write(' ');
      out.write('\n');
      out.print(enabled("Exception Summary = ",MonProxyFactory.isExceptionSummaryEnabled()));
      out.write(',');
      out.write(' ');
      out.write('\n');
      out.print(enabled("Interface = ",MonProxyFactory.isInterfaceEnabled()));
      out.write(',');
      out.write(' ');
      out.write('\n');
      out.print(enabled("ResultSet = ",MonProxyFactory.isResultSetEnabled()));
      out.write(',');
      out.write('\n');
      out.print(enabled("Activity Tracking = ",MonitorFactory.isActivityTrackingEnabled()));
      out.write(" \n");
      out.write("\n");
      out.write(")</td>\n");
      out.write("\n");
      out.write("\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td><a href=\"sql.jsp\">SQL Details page </a> - Manage the most recent ");
      out.print(MonProxyFactory.getSQLBufferSize() );
      out.write(" SQL commands executed. (currently ");
      out.print(enabled(MonProxyFactory.isSQLDetailEnabled()));
      out.write(")</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td><a href=\"exceptions.jsp\">Exception Details page </a> - Manage the most recent ");
      out.print(MonProxyFactory.getExceptionBufferSize() );
      out.write(" exceptions. (currently ");
      out.print(enabled(MonProxyFactory.isExceptionDetailEnabled()));
      out.write(")</td>\n");
      out.write("</tr>\n");
      out.write("<tr>\n");
      out.write("<td><a href=\"query.jsp\">Generate Monitoring Data</a> - Generate JAMon data using a JDBC proxied connection. This is useful to run when learning JAMon.</td>\n");
      out.write("</tr></table>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<hr width=\"75%\">  \n");
      out.write("\n");
      out.write("\n");
      out.write("<td><table border='0' align='center' width='25%'>\n");
      out.write("    <tr>\n");
      out.write("    <th nowrap>JAMon ");
      out.print(MonitorFactory.getVersion());
      out.write("</th>\n");
      out.write("    <th nowrap>");
      out.print(getTotalKeySize());
      out.write("</th>\n");
      out.write("    </tr>\n");
      out.write("</table></td>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}