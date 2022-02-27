/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.37
 * Generated at: 2022-02-14 04:29:18 UTC
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
import net.sf.xsshtmlfilter.HTMLFilter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jamonapi.*;
import com.jamonapi.proxy.*;
import com.jamonapi.utils.*;
import com.jamonapi.distributed.*;

public final class sql_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

    


String[] outputTypeHeader={"outputTypeValue","outputType"}; 
Object[][] outputTypeBody={ {"html", "HTML"},{"xml", "XML"}, {"csv", "Comma Separated"}, {"excel","MS Excel"},};   
Template jamonTemplate;  
 
String[] actionHeader={"action","actionDisplay"};
Object[][] actionBody={
                 {"Refresh", "Refresh"}, 
                 {"Reset", "Reset"}, 
	         {"Enable","Enable"}, 
	         {"Disable","Disable"}, 
                };

String[] bufferSizeHeader={"bufferSize","bufferSizeDisplay"};
Object[][] bufferSizeBody={
                 {"No Action", "No Action"}, 
                 {"100", "100 rows"}, 
                 {"250", "250 rows"}, 
	         {"500","500 rows"}, 
	         {"1000","1000 rows"}, 
               };


private static String enabled(boolean isEnabled) {
   return isEnabled ? "enabled" : "disabled";
}

// Format time String in current locale. 
private String now() {   return LocaleContext.getDateFormatter().format(new Date()); }    

// if the value is null then return the passed in default else return the value 
private static String getValue(String value, String defaultValue) {
    HTMLFilter  vFilter = new HTMLFilter();
    return (value==null || "".equals(value.trim())) ? defaultValue: vFilter.filter(value);
}

private static void executeAction(String action) {

  if ("Reset".equals(action))
    MonProxyFactory.resetSQLDetail();
  else if ("Enable".equals(action)) 
    MonProxyFactory.enableSQLDetail(true);
  else if ("Disable".equals(action))  
    MonProxyFactory.enableSQLDetail(false);

}

// There is no technical limit to buffer size, so other possibilities could be added.
private static void setBufferSize(String bufferSize) {

  if ("100".equals(bufferSize))
    MonProxyFactory.setSQLBufferSize(100);
  else if ("250".equals(bufferSize)) 
    MonProxyFactory.setSQLBufferSize(250);
  else if ("500".equals(bufferSize)) 
    MonProxyFactory.setSQLBufferSize(500);
  else if ("1000".equals(bufferSize)) 
    MonProxyFactory.setSQLBufferSize(1000);

}

// convert arg to an int or return the default 
private static int getNum(String value, String defaultValue) {

  String retValue=getValue(value, defaultValue);
  char[] digits=retValue.toCharArray();
  boolean isDigit=true;
  for (int i=0;i<digits.length;i++) {
     if (!Character.isDigit(digits[i]))
       isDigit=false;
  }

  if (!isDigit)
    retValue=defaultValue;    

 return Integer.parseInt(retValue); 
}  

private static ResultSetConverter getResultSetConverter(String[] header, Object[][] data, String arraySQLExec) {      
  ArraySQL asql=new ArraySQL(header, arraySQLExec );       
  ResultSetConverter rsc = new ResultSetConverter(header, asql.execute(data));      
  return rsc;
}    

private static ArrayConverter getArrayConverter(int textSize, String highlightMe) {    
  // used to format the data in the report   
  ArrayConverter ac=new ArrayConverter();
  // replace the normal extreme values of default min,max, and acccess date with an empty default.   
  // If the key is in the data stream the value will be used instead of the key.    
  DecimalFormat decimalFormat=LocaleContext.getFloatingPointFormatter();   
  decimalFormat.applyPattern("#,###");   
  DateFormat dateFormat=LocaleContext.getDateFormatter();    
  // Set the converter to take action on data passed to the FormattedDataSet
  ac.setDefaultConverter(new ConverterNumToString(decimalFormat, new ConverterDateToString(dateFormat, new TruncateString(textSize,highlightMe,"#00ff99;"))));     
  return ac;  
}


private synchronized Template getJAMonTemplate(FormattedDataSet fds) {
   // start from sortedHTMLTable template and add some jamon display capabilities (highlighting and descriptive text appearing where the mouse is)
   if (jamonTemplate==null) {
     jamonTemplate=fds.getTemplate("sortedHTMLTable").copy();

     // highlighting for odd and even rows of the JAMon report.  Affects coloring, 
     // and what happens when the rows is highlighted with the mouse.
     String odd="Odd==   <tr class='odd' onMouseOver='rollOnRow(this, \"\")' onMouseOut='rollOffRow(this)'>\n";// label was removed from rollover due to red hightlighting messing it up
     String even="Even==   <tr class='even' onMouseOver='rollOnRow(this, \"\")' onMouseOut='rollOffRow(this)'>\n";
     jamonTemplate.initialize("BODY_ROW_PREFIX",0,0,"Type==Alternating "+odd+" "+even);
   }

   return jamonTemplate;
}

 public static class TruncateString implements Converter {
    Pattern pattern;

    int size;
    String stringToColorize;
    String color;
    public TruncateString(int size, String stringToColorize, String color) {
       this.size=size;
       this.stringToColorize=stringToColorize;
       this.color=color;
       if (stringToColorize!=null && !stringToColorize.equals(""))
         pattern=Pattern.compile("(?i)"+stringToColorize);

    }

    public Object convert(Object inputObj) {


       // Data input validation - if the passed object is null it is ok, but no logic needs to be performed. 
       if (inputObj==null)
         return null;
       else if (inputObj instanceof String && size>=1  && inputObj.toString().length()>size) { // prevent end of array bounds exception
        // truncate string and replace returns with returns/<br> for readability in browser and view source 
        String returnStr=inputObj.toString().substring(0,size).replaceAll("\n","\n<br>"); 
        if (pattern!=null) {
          Matcher matcher=pattern.matcher(returnStr);
          returnStr=matcher.replaceAll("<b style='color:black;background-color:"+color+"'>"+stringToColorize+"</b>");
        }
       
        return returnStr;
       } else if (inputObj instanceof String) {
        String returnStr=inputObj.toString().replaceAll("\n","\n<br>"); 
        if (pattern!=null) {
          Matcher matcher=pattern.matcher(returnStr);
          returnStr=matcher.replaceAll("<b style='color:black;background-color:"+color+"'>"+stringToColorize+"</b>");
        }
       
        return returnStr;    

       } else 
         return inputObj;

     }


  public Converter createInstance() {
    return new TruncateString(size, stringToColorize, color);
  } 

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
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("net.sf.xsshtmlfilter.HTMLFilter");
    _jspx_imports_classes.add("java.util.regex.Matcher");
    _jspx_imports_classes.add("java.util.Date");
    _jspx_imports_classes.add("java.util.Map");
    _jspx_imports_classes.add("java.text.DecimalFormat");
    _jspx_imports_classes.add("java.util.HashMap");
    _jspx_imports_classes.add("java.util.regex.Pattern");
    _jspx_imports_classes.add("java.text.DateFormat");
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
  
// Set formatting rules per the requests Locale (as opposed to the servers locale). 
// This will format data per the users preference (note this sets it for the given thread/servlet) 
FormattedDataSet fds=new FormattedDataSet(); 

LocaleContext.setLocale(request.getLocale());  
// Assign request parameters to local variables.  
String action    = getValue(request.getParameter("action"),"Refresh");
String outputType= getValue(request.getParameter("outputTypeValue"),"html"); 
String bufferSize    = getValue(request.getParameter("bufferSize"),"No Action");
String arraySQL  = getValue(request.getParameter("ArraySQL"),""); 
String sortOrder = getValue(request.getParameter("sortOrder"), "desc"); 
int sortCol      = getNum(request.getParameter("sortCol"), "1");
int textSize     = getNum(request.getParameter("TextSize"), "0");   
// Assign defaults for arraysql.  If nothing is provided use 'select * from array'.  If the first word is not  
// 'select' assume they want to do a like on the first column (label). 
String arraySQLExec = getValue(arraySQL,"select * from array");   
String highlightString=getValue(request.getParameter("highlight"),"");

if (arraySQLExec.trim().toLowerCase().startsWith("select"))    
  arraySQLExec=arraySQLExec;// noop all is ok full select entered 
else if (arraySQLExec.trim().toLowerCase().startsWith("where"))     
  arraySQLExec="select * from array "+arraySQL;// where clause entered:  where hits>100 and total<50000 
else    {
  arraySQLExec="select * from array where sql like '"+arraySQL+"'";
} 

arraySQLExec = (arraySQLExec.trim().toLowerCase().startsWith("select")) ? arraySQLExec : "select * from array where sql like '"+arraySQL+"'";  
// Build the request parameter query string that will be part of every clickable column. 
String query=""; 
query+="&outputTypeValue="+outputType; 
query+="&ArraySQL="+java.net.URLEncoder.encode(arraySQL);
query+="&TextSize="+textSize;   
query+="&highlight="+highlightString;   

executeAction(action);
setBufferSize(bufferSize);

Map map=new HashMap(); // used for html page 
map.put("sortPageName", "sql.jsp"); 
map.put("query", query); 
map.put("imagesDir","images/");   
// used for xml1 page. 
map.put("rootElement", "JAMonSQLXML");   

String outputText=""; 
ResultSetConverter rsc=getResultSetConverter(MonProxyFactory.getSQLDetailHeader(), MonProxyFactory.getSQLDetail(), arraySQLExec);      
ArrayConverter ac=getArrayConverter(textSize,highlightString );     
fds.setArrayConverter(ac);



if (rsc.isEmpty())
  outputText="<div align='center'><br><br><b>No data was returned</b></div>";
else {      
  if ("xml".equalsIgnoreCase(outputType)) {       
    rsc=new ResultSetConverter(rsc.getMetaData(), rsc.getResultSet());       
    outputText=fds.getFormattedDataSet(rsc, map, "xml1");     
  } else if  ("csv".equalsIgnoreCase(outputType)) {
      rsc=new ResultSetConverter(rsc.getMetaData(), ac.convert(rsc.getResultSet()));
      outputText=fds.getFormattedDataSet(rsc, map, "csv");
 } else if ("excel".equalsIgnoreCase(outputType) || "spreadsheet".equalsIgnoreCase(outputType)) {       
    rsc=new ResultSetConverter(rsc.getMetaData(), ac.convert(rsc.getResultSet()));       
    outputText=fds.getFormattedDataSet(rsc, map, "basicHtmlTable");     
  } else        
    outputText=fds.getSortedText(rsc.getMetaData(), rsc.getResultSet(), map, sortCol, sortOrder, getJAMonTemplate(fds));   

}

      out.write("  \n");
      out.write("\n");
 if ("html".equalsIgnoreCase(outputType)) { 
      out.write(" \n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"> \n");
      out.write("<html> \n");
      out.write("<head> \n");
      out.write("<META http-equiv=\"Content-Type\" content=\"text/html\"; charset=ISO-8859-1\"> \n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/JAMonStyles.css\"> \n");
      out.write("<title>JAMon - SQL Detail (");
      out.print(MonProxyFactory.getSQLBufferSize());
      out.write(" rows are ");
      out.print(enabled(MonProxyFactory.isSQLDetailEnabled()));
      out.write(") - ");
      out.print(now());
      out.write("</title> \n");
      out.write("<script type=\"text/javascript\"> \n");
      out.write("\n");
      out.write("<!--\n");
      out.write("// Row highlighter\n");
      out.write("var objClass\n");
      out.write("\n");
      out.write("function rollOnRow(obj, txt) {\n");
      out.write("    objClass = obj.className\n");
      out.write("    obj.className = \"rowon\";\n");
      out.write("    obj.title = txt;\n");
      out.write("}\n");
      out.write("\n");
      out.write("function rollOffRow(obj) {\n");
      out.write("    obj.className = objClass;\n");
      out.write("}\n");
      out.write("\n");
      out.write("function selectAll(obj, numRows) {\n");
      out.write("    state = (obj.checked) ? true : false;\n");
      out.write("\n");
      out.write("    for (var i = 1; i < numRows + 1; i ++) {\n");
      out.write("        currRow = eval(\"obj.form.row_\" + i);\n");
      out.write("        currRow.checked = state;\n");
      out.write("    }\n");
      out.write("}\n");
      out.write("\n");
      out.write("function helpWin() {\n");
      out.write("    newWin = window.open('sql.htm', 'helpWin', 'resizable=no,scrollbars=yes,height=550,width=450,screenX=100,screenY=100');\n");
      out.write("    if (newWin.opener == null) newWin.opener = self;\n");
      out.write("}\n");
      out.write("// -->\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("\n");
      out.write("</head> \n");
      out.write("<body>  \n");
      out.write("<!-- arraySQLExec=");
      out.print(arraySQLExec);
      out.write("-->  \n");
      out.write("<form action=\"sql.jsp\" method=\"post\">  \n");
      out.write("<table border=\"0\" cellpadding=\"1\" cellspacing=\"0\" align=\"center\"> \n");
      out.write("  <tr> <td><h2 style=\"color:#03487F;\">JAMon - SQL Detail (");
      out.print(MonProxyFactory.getSQLBufferSize());
      out.write(" rows are ");
      out.print(enabled(MonProxyFactory.isSQLDetailEnabled()));
      out.write(") - ");
      out.print(now());
      out.write(" - <a href=\"menu.jsp\">Home</a></h2></td> </tr> \n");
      out.write("  <tr> <td><table class=\"layoutmain\" border=\"0\" cellpadding=\"4\" cellspacing=\"0\" width=\"750\" align=\"left\">     \n");
      out.write("    <tr class=\"sectHead\">     \n");
      out.write("    <th>SQL Detail Action</th>\n");
      out.write("    <th>Output</th>     \n");
      out.write("    <th>Set Buffer Size (optional)</th>     \n");
      out.write("    <th>Text Display Length (optional)</th>     \n");
      out.write("    <th>Highlight (optional)</th>     \n");
      out.write("    <th>Filter (optional)</th>     \n");
      out.write("    <th align=\"right\"><a href=\"javascript:helpWin();\" style=\"color:#C5D4E4;\">Help</a></th>     \n");
      out.write("    </tr>     \n");
      out.write("   <tr class=\"even\">     \n");
      out.write("    <th>");
      out.print(fds.getDropDownListBox(actionHeader, actionBody, ""));
      out.write("</th>\n");
      out.write("    <th>");
      out.print(fds.getDropDownListBox(outputTypeHeader, outputTypeBody, outputType));
      out.write("</th>     \n");
      out.write("    <th>");
      out.print(fds.getDropDownListBox(bufferSizeHeader, bufferSizeBody, ""));
      out.write("</th>     \n");
      out.write("    <th><input type='text' name='TextSize' value=\"");
      out.print((textSize<=0) ? "" : ""+textSize);
      out.write("\" size=\"15\"></th>\n");
      out.write("    <th><input type='text' name='highlight' value=\"");
      out.print(highlightString);
      out.write("\" size=\"20\"></th>     \n");
      out.write("    <th><input type='text' name='ArraySQL' value=\"");
      out.print(arraySQL);
      out.write("\" size=\"45\"></th>    \n");
      out.write("    <td><input type=\"submit\" name=\"actionSbmt\" value=\"Go !\" ></td>     \n");
      out.write("  </tr> \n");
      out.write(" </table></td> \n");
      out.write("</tr> \n");
      out.write("<tr> <td><table border=\"0\" cellpadding='0' cellspacing='0' align=\"center\">     \n");
      out.write("   <tr>     <td>");
      out.print(outputText);
      out.write("</td>     </tr> \n");
      out.write("   </table></td> \n");
      out.write(" </tr> \n");
      out.write("</table>  \n");
      out.write("</form>  \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<br>\n");
      out.write("<div align=\"center\" style=\"padding-top : 30px;\">\n");
      out.write("<hr width=\"580\" align=\"center\" />\n");
      out.write("<a href=\"menu.jsp\">Home</a> | <a href=\"jamonadmin.jsp\">JAMonAdmin</a> | SQL Details | <a href=\"exceptions.jsp\">Exception Details</a>\n");
      out.write("<hr width=\"580\" align=\"center\" />\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<br><br>\n");
      out.write("<td><table border='0' align='center' width='25%'>\n");
      out.write("    <tr>\n");
      out.write("    <th nowrap><a href=\"http://www.jamonapi.com\"><img src=\"images/jamon_small.jpg\" id=\"monLink\" border=\"0\" /></a></th>\n");
      out.write("    <th nowrap>JAMon ");
      out.print(MonitorFactory.getVersion());
      out.write("</th>\n");
      out.write("    <th nowrap><a href=\"http://www.fdsapi.com\"><img height=40 width=80 src=\"images/fds_logo_small.jpg\" id=\"monLink\" border=\"0\" /></a></th>\n");
      out.write("    </tr>\n");
      out.write("</table></td>\n");
      out.write("  \n");
      out.write("</body> \n");
      out.write("</html>  \n");
 
} else if ("xml".equalsIgnoreCase(outputType)) { 

      out.write(" \n");
      out.write("<?xml version=\"1.0\"?> ");
      out.print(outputText);
      out.write(' ');
      out.write('\n');
 } else {   
  response.setContentType("application/vnd.ms-excel"); 

      out.write("   \n");
      out.print(outputText);
      out.write(' ');
      out.write(' ');
      out.write('\n');
 } 
      out.write(' ');
      out.write(' ');
      out.write('\n');
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