/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.37
 * Generated at: 2022-02-14 04:38:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.mantle.home;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.pentaho.platform.api.engine.IAuthorizationPolicy;
import org.pentaho.platform.api.engine.IPluginManager;
import org.pentaho.platform.engine.core.system.PentahoSessionHolder;
import org.pentaho.platform.engine.core.system.PentahoSystem;
import org.pentaho.platform.security.policy.rolebased.actions.AdministerSecurityAction;
import org.pentaho.platform.security.policy.rolebased.actions.RepositoryReadAction;
import org.pentaho.platform.security.policy.rolebased.actions.RepositoryCreateAction;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("org.pentaho.platform.security.policy.rolebased.actions.RepositoryCreateAction");
    _jspx_imports_classes.add("org.pentaho.platform.security.policy.rolebased.actions.AdministerSecurityAction");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("javax.servlet.http.HttpServletRequest");
    _jspx_imports_classes.add("org.pentaho.platform.api.engine.IAuthorizationPolicy");
    _jspx_imports_classes.add("java.util.Locale");
    _jspx_imports_classes.add("org.pentaho.platform.api.engine.IPluginManager");
    _jspx_imports_classes.add("org.pentaho.platform.security.policy.rolebased.actions.RepositoryReadAction");
    _jspx_imports_classes.add("org.pentaho.platform.engine.core.system.PentahoSystem");
    _jspx_imports_classes.add("org.pentaho.platform.engine.core.system.PentahoSessionHolder");
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

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

  boolean canReadContent = PentahoSystem.get( IAuthorizationPolicy.class, PentahoSessionHolder.getSession() )
      .isAllowed( RepositoryReadAction.NAME );
  boolean canCreateContent = PentahoSystem.get( IAuthorizationPolicy.class, PentahoSessionHolder.getSession() )
      .isAllowed( RepositoryCreateAction.NAME );
  boolean canAdminister = PentahoSystem.get( IAuthorizationPolicy.class, PentahoSessionHolder.getSession() )
      .isAllowed( AdministerSecurityAction.NAME );
  List<String> pluginIds =
      PentahoSystem.get( IPluginManager.class, PentahoSessionHolder.getSession() ).getRegisteredPlugins();
  Locale locale = request.getLocale();

      out.write("\n");
      out.write("<html lang=\"en\" class=\"bootstrap\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <title>Home Page</title>\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("  <meta name=\"locale\" content=\"");
      out.print(locale.toString());
      out.write("\">\n");
      out.write("\n");
      out.write("  <!-- Le styles -->\n");
      out.write("  <link href=\"css/home.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("  <!-- We need web context for requirejs and css -->\n");
      out.write("  <script type=\"text/javascript\" src=\"webcontext.js?context=mantle&cssOnly=true\"></script>\n");

// For consistency, we're using the same method as PentahoWebContextFilter to get scheme
if ( PentahoSystem.getApplicationContext().getFullyQualifiedServerURL().toLowerCase().startsWith( "https:" ) ) {

      out.write("\n");
      out.write("  \n");
 } else { 
      out.write("\n");
      out.write("  \n");
 } 
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("  <!-- Avoid 'console' errors in browsers that lack a console. -->\n");
      out.write("  <script type=\"text/javascript\">\n");
      out.write("    if (!(window.console && console.log)) {\n");
      out.write("      (function () {\n");
      out.write("        var noop = function () {\n");
      out.write("        };\n");
      out.write("        var methods = ['assert', 'debug', 'error', 'info', 'log', 'trace', 'warn'];\n");
      out.write("        var length = methods.length;\n");
      out.write("        var console = window.console = {};\n");
      out.write("        while (length--) {\n");
      out.write("          console[methods[length]] = noop;\n");
      out.write("        }\n");
      out.write("      }());\n");
      out.write("    }\n");
      out.write("  </script>\n");
      out.write("\n");
      out.write("  <!-- Require Home -->\n");
      out.write("  <script type=\"text/javascript\">\n");
      out.write("    var Home = null;\n");
      out.write("    require([\"home/home\", \n");
      out.write("      \"common-ui/util/ContextProvider\"], function (pentahoHome, ContextProvider) {\n");
      out.write("      Home = pentahoHome;\n");
      out.write("\n");
      out.write("      // Define properties for loading context\n");
      out.write("      var contextConfig = [\n");
      out.write("        {\n");
      out.write("          path: \"properties/config\",\n");
      out.write("          post: function (context, loadedMap) {\n");
      out.write("            context.config = loadedMap;\n");
      out.write("          }\n");
      out.write("        },\n");
      out.write("        {\n");
      out.write("          path: \"properties/messages\",\n");
      out.write("          post: function (context, loadedMap) {\n");
      out.write("            context.i18n = loadedMap;\n");
      out.write("          }\n");
      out.write("      }];\n");
      out.write("\n");
      out.write("      // Define permissions\n");
      out.write("      ContextProvider.addProperty(\"canReadContent\", ");
      out.print(canReadContent);
      out.write(");\n");
      out.write("      ContextProvider.addProperty(\"canCreateContent\", ");
      out.print(canCreateContent);
      out.write(");\n");
      out.write("      ContextProvider.addProperty(\"canAdminister\", ");
      out.print(canAdminister);
      out.write(");\n");
      out.write("      ContextProvider.addProperty(\"hasAnalyzerPlugin\", ");
      out.print(pluginIds.contains("analyzer"));
      out.write(");\n");
      out.write("      ContextProvider.addProperty(\"hasIRPlugin\", ");
      out.print(pluginIds.contains("pentaho-interactive-reporting"));
      out.write(");\n");
      out.write("      ContextProvider.addProperty(\"hasDashBoardsPlugin\", ");
      out.print(pluginIds.contains("dashboards"));
      out.write(");\n");
      out.write("      ContextProvider.addProperty(\"hasMarketplacePlugin\", ");
      out.print(pluginIds.contains("marketplace"));
      out.write(");\n");
      out.write("      ContextProvider.addProperty(\"hasDataAccess\", false); // default\n");
      out.write("\n");
      out.write("      // BISERVER-8631 - Manage datasources only available to roles/users with appropriate permissions\n");
      out.write("      var serviceUrl = Home.getUrlBase() + \"plugin/data-access/api/permissions/hasDataAccess\";\n");
      out.write("      Home.getContent(serviceUrl, function (result) {\n");
      out.write("        ContextProvider.addProperty(\"hasDataAccess\", result);\n");
      out.write("        ContextProvider.get(Home.init, contextConfig); // initialize\n");
      out.write("      }, function (error) {\n");
      out.write("        console.log(error);\n");
      out.write("        ContextProvider.get(Home.init, contextConfig); // log error and initialize anyway\n");
      out.write("      });\n");
      out.write("\n");
      out.write("    });\n");
      out.write("  </script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body data-spy=\"scroll\" data-target=\".sidebar\">\n");
      out.write("<div class=\"container-fluid main-container\">\n");
      out.write("  <div class=\"row-fluid\">\n");
      out.write("    <div class=\"span3\" id=\"buttonWrapper\">\n");
      out.write("\n");
      out.write("\n");
      out.write("      <div class='row-fluid'>\n");
      out.write("        <script type=\"text/x-handlebars-template\">\n");
      out.write("          <div class=\"well sidebar\">\n");
      out.write("            {{#if canReadContent}}\n");
      out.write("            <button class=\"btn btn-large btn-block\" onclick=\"window.parent.mantle_setPerspective('browser.perspective')\">\n");
      out.write("              {{i18n.browse}}\n");
      out.write("            </button>\n");
      out.write("            {{/if}}\n");
      out.write("\n");
      out.write("            <!-- Only show create button if user is allowed -->\n");
      out.write("\n");
      out.write("            {{#if canCreateContent}}\n");
      out.write("            <button id=\"btnCreateNew\" class=\"btn btn-large btn-block popover-source\" data-toggle=\"dropdown\"\n");
      out.write("                data-toggle=\"popover\" data-placement=\"right\" data-html=\"true\" data-id=\"my_hid\" data-container=\"body\" onclick=\"preCreatePopover();\">\n");
      out.write("              {{i18n.create_new}}\n");
      out.write("            </button>\n");
      out.write("            {{/if}}\n");
      out.write("\n");
      out.write("            {{#if hasDataAccess}}\n");
      out.write("            <button class=\"btn btn-large btn-block\" onclick=\"window.parent.executeCommand('ManageDatasourcesCommand')\">\n");
      out.write("              {{i18n.manage_datasources}}\n");
      out.write("            </button>\n");
      out.write("            {{/if}}\n");
      out.write("\n");
      out.write("            <button class=\"btn btn-large btn-block\" onclick=\"window.parent.executeCommand('OpenDocCommand')\">\n");
      out.write("              {{i18n.documentation}}\n");
      out.write("            </button>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("          <div style=\"display:none\" id=\"btnCreateNewContent\"></div>\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("      <div class=\"row-fluid\">\n");
      out.write("        <div class='span12'>\n");
      out.write("          <script id=\"recentsTemplate\" type=\"text/x-handlebars-template\" delayCompile=\"true\">\n");
      out.write("            <div id=\"recents\" class=\"well widget-panel\">\n");
      out.write("              <h3>\n");
      out.write("                {{i18n.recents}}\n");
      out.write("              </h3>\n");
      out.write("\n");
      out.write("              <div id=\"recentsSpinner\"></div>\n");
      out.write("              {{#if isEmpty}}\n");
      out.write("              <div class=\"empty-panel content-panel\">\n");
      out.write("                <div class=\"centered\">\n");
      out.write("                  <div class=\"empty-message\">{{i18n.empty_recents_panel_message}}</div>\n");
      out.write("                  <button class=\"pentaho-button\" onclick=\"window.parent.mantle_setPerspective('browser.perspective');\">{{i18n.browse}}</button>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("              {{else}}\n");
      out.write("              <div id=\"recents-content-panel\" class=\"content-panel\">\n");
      out.write("                <ul class=\"nav nav-tabs nav-stacked\">\n");
      out.write("                  {{#eachRecent recent}}\n");
      out.write("                  <li>\n");
      out.write("                    <a href=\"javascript:Home.openRepositoryFile('{{escapeQuotes fullPath}}', 'run')\" title='{{title}}'>\n");
      out.write("                      <div class=\"row-fluid\">\n");
      out.write("                        <div class=\"span10 ellipsis\">\n");
      out.write("                          {{#if xanalyzer}} <i class=\"pull-left content-icon file-xanalyzer\"/> {{/if}}\n");
      out.write("                          {{#if xdash}} <i class=\"pull-left content-icon file-xdash\"/> {{/if}}\n");
      out.write("                          {{#if xcdf}} <i class=\"pull-left content-icon file-xcdf\"/> {{/if}}\n");
      out.write("                          {{#if prpti}} <i class=\"pull-left content-icon file-prpti\"/> {{/if}}\n");
      out.write("                          {{#if ktr}} <i class=\"pull-left content-icon file-ktr\"/> {{/if}}\n");
      out.write("                          {{#if prpt}} <i class=\"pull-left content-icon file-prpt\"/> {{/if}}\n");
      out.write("                          {{#if xaction}} <i class=\"pull-left content-icon file-xaction\"/> {{/if}}\n");
      out.write("                          {{#if url}} <i class=\"pull-left content-icon file-url\"/> {{/if}}\n");
      out.write("                          {{#if html}} <i class=\"pull-left content-icon file-html\"/> {{/if}}\n");
      out.write("                          {{#if cda}} <i class=\"pull-left content-icon file-cda\"/> {{/if}}\n");
      out.write("                          {{#if wcdf}} <i class=\"pull-left content-icon file-wcdf\"/> {{/if}}\n");
      out.write("                          {{#if unknownType}} <i class=\"pull-left content-icon file-unknown\"/> {{/if}}\n");
      out.write("                          <span class=\"pad-left\">{{title}}</span>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"span2\">\n");
      out.write("                          {{#unless isEmpty}}\n");
      out.write("                          {{#if isFavorite}}\n");
      out.write("                          <i title=\"{{../../../i18n.remove_favorite_tooltip}}\" class=\"pull-right favorite-on\" onclick=\"controller.unmarkRecentAsFavorite('{{escapeQuotes fullPath}}'); return false;\"/>\n");
      out.write("                          {{else}}\n");
      out.write("                          <i title=\"{{../../../i18n.add_favorite_tooltip}}\" class=\"pull-right favorite-off\" onclick=\"controller.markRecentAsFavorite('{{escapeQuotes fullPath}}', '{{escapeQuotes title}}'); return false;\"/>\n");
      out.write("                          {{/if}}\n");
      out.write("                          {{/unless}}\n");
      out.write("                        </div>\n");
      out.write("                      </div>\n");
      out.write("                    </a>\n");
      out.write("                  </li>\n");
      out.write("                  {{/eachRecent}}\n");
      out.write("                </ul>\n");
      out.write("              </div>\n");
      out.write("              {{/if}}\n");
      out.write("            </div>\n");
      out.write("          </script>\n");
      out.write("\n");
      out.write("          <div id=\"recentsContianer\"></div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("      <div class=\"row-fluid\">\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"span12\">\n");
      out.write("          <script id=\"favoritesTemplate\" type=\"text/x-handlebars-template\" delayCompile=\"true\">\n");
      out.write("            <div id=\"favorites\" class=\"well widget-panel\">\n");
      out.write("              <h3>\n");
      out.write("                {{i18n.favorites}}\n");
      out.write("              </h3>\n");
      out.write("\n");
      out.write("              <div id=\"favoritesSpinner\"></div>\n");
      out.write("              {{#if isEmpty}}\n");
      out.write("              <div class=\"empty-panel content-panel\">\n");
      out.write("                <div class=\"centered\">\n");
      out.write("                  <div class=\"empty-message\">{{i18n.empty_favorites_panel_message}}</div>\n");
      out.write("                  <button class=\"pentaho-button\" onclick=\"window.parent.mantle_setPerspective('browser.perspective')\">{{i18n.browse}}</button>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("              {{else}}\n");
      out.write("              <div id=\"favorites-content-panel\" class=\"content-panel\">\n");
      out.write("                <ul class=\"nav nav-tabs nav-stacked\">\n");
      out.write("                  {{#eachFavorite favorites}}\n");
      out.write("                  <li>\n");
      out.write("                    <a href=\"javascript:Home.openRepositoryFile('{{escapeQuotes fullPath}}', 'run')\" title='{{title}}'>\n");
      out.write("                      <div class=\"row-fluid\">\n");
      out.write("                        <div class=\"span10 ellipsis\">\n");
      out.write("                          {{#if xanalyzer}} <i class=\"pull-left content-icon file-xanalyzer\"/> {{/if}}\n");
      out.write("                          {{#if xdash}} <i class=\"pull-left content-icon file-xdash\"/> {{/if}}\n");
      out.write("                          {{#if xcdf}} <i class=\"pull-left content-icon file-xcdf\"/> {{/if}}\n");
      out.write("                          {{#if prpti}} <i class=\"pull-left content-icon file-prpti\"/> {{/if}}\n");
      out.write("                          {{#if prpt}} <i class=\"pull-left content-icon file-prpt\"/> {{/if}}\n");
      out.write("                          {{#if ktr}} <i class=\"pull-left content-icon file-ktr\"/> {{/if}}\n");
      out.write("                          {{#if xaction}} <i class=\"pull-left content-icon file-xaction\"/> {{/if}}\n");
      out.write("                          {{#if url}} <i class=\"pull-left content-icon file-url\"/> {{/if}}\n");
      out.write("                          {{#if html}} <i class=\"pull-left content-icon file-html\"/> {{/if}}\n");
      out.write("                          {{#if unknownType}} <i class=\"pull-left content-icon file-unknown\"/> {{/if}}\n");
      out.write("                          <span class=\"pad-left\">{{title}}</span>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"span2\">\n");
      out.write("                          {{#unless isEmpty}}\n");
      out.write("                          <i title=\"{{../../../i18n.remove_favorite_tooltip}}\" class=\"pull-right favorite-on\" onclick=\"controller.unmarkRecentAsFavorite('{{escapeQuotes fullPath}}'); return false;\"/>\n");
      out.write("                          {{/unless}}\n");
      out.write("                        </div>\n");
      out.write("                      </div>\n");
      out.write("                    </a>\n");
      out.write("                  </li>\n");
      out.write("                  {{/eachFavorite}}\n");
      out.write("                </ul>\n");
      out.write("              </div>\n");
      out.write("              {{/if}}\n");
      out.write("            </div>\n");
      out.write("          </script>\n");
      out.write("\n");
      out.write("          <div id=\"favoritesContianer\"></div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <div class=\"span9\" style=\"overflow:visible\">\n");
      out.write("\n");
      out.write("      <div class=\"row-fluid welcome-container\">\n");
      out.write("\n");
      out.write("        <iframe src=\"content/welcome/index.html\" class='welcome-frame' frameborder=\"0\" scrolling=\"no\"></iframe>\n");
      out.write("\n");
      out.write("      </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("</div>\n");
      out.write("  <script type=\"text/javascript\">\n");
      out.write("\t\t\n");
      out.write("\t\tvar popup_init = false;\n");
      out.write("\t\t\n");
      out.write("\t\tfunction preCreatePopover(){\n");
      out.write("\t\t\tif(!popup_init){\n");
      out.write("\t\t\t\tvar tmp = $.fn.popover.Constructor.prototype.show; \n");
      out.write("\t\t\t\t$.fn.popover.Constructor.prototype.show = function () {\n");
      out.write("\t\t\t\t  tmp.call(this);\n");
      out.write("\n");
      out.write("\t\t\t\t  //Keep the popover from running off the screen\n");
      out.write("\t\t\t\t  var offset = 5;\n");
      out.write("\t\t\t\t  var top = this.$element.offset().top;\n");
      out.write("\t\t\t\t  var height =  this.$element.outerHeight();\n");
      out.write("\t\t\t\t  var topOffset = top-offset;\n");
      out.write("\t\t\t\t  $('.popover').css('top', topOffset+\"px\");\n");
      out.write("\t\t\t\t  $('.arrow').css('top', offset + height / 2);\n");
      out.write("\n");
      out.write("\t\t\t\t  if (!$('.popover-title').html()) \n");
      out.write("\t\t\t\t\t\t$('.popover-title').hide();\n");
      out.write("\t\t\t\t}; \n");
      out.write("\t\t\t\tpopup_init = true;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}\n");
      out.write("  \n");
      out.write("  </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
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