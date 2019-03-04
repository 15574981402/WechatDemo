package org.apache.jsp.WEB_002dINF.jsp.sbyh;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class yh_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html >\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t\t<title>Insert title here</title>\r\n");
      out.write("\t\t<!-- head 中 -->\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/we/weui.min.css\">\r\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/we/jquery-weui.min.css\">\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- body 最后 -->\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/we/jquery-2.1.4.js\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/we/jquery-weui.min.js\"></script>\r\n");
      out.write("\t\t<!-- 如果使用了某些拓展插件还需要额外的JS -->\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/we/swiper.min.js\"></script>\r\n");
      out.write("\t\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/we/city-picker.min.js\"></script>\r\n");
      out.write("\t\t<!-- JSSDK -->\r\n");
      out.write("\t\t<script src=\"http://res.wx.qq.com/open/js/jweixin-1.4.0.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t$(document).ready(function() {\r\n");
      out.write("\t\t\t\tyhsystemList();\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t//\t\t\t\t\t$(document).ready(function() {\r\n");
      out.write("\r\n");
      out.write("\t\t\t//\t\t\t\tconsole.debug('调用ajax加载config配置');\r\n");
      out.write("\t\t\t//\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t//\t\t\t\t\tasync: 'false',\r\n");
      out.write("\t\t\t//\t\t\t\t\ttype: 'GET',\r\n");
      out.write("\t\t\t//\t\t\t\t\turl: 'http://127.0.0.1:8080/cn.yckj.crm/getTicket.action',\r\n");
      out.write("\t\t\t//\t\t\t\t\tdataType: 'json',\r\n");
      out.write("\t\t\t//\t\t\t\t\tsuccess: function(res) {\r\n");
      out.write("\t\t\t//\t\t\t\t\t\tconsole.debug(res);\r\n");
      out.write("\t\t\t//\t\t\t\t\t\twx.config({\r\n");
      out.write("\t\t\t//\t\t\t\t\t\t\tdebug: false,\r\n");
      out.write("\t\t\t//\t\t\t\t\t\t\tappId: res.appId,\r\n");
      out.write("\t\t\t//\t\t\t\t\t\t\ttimestamp: res.timestamp,\r\n");
      out.write("\t\t\t//\t\t\t\t\t\t\tnonceStr: res.noncestr,\r\n");
      out.write("\t\t\t//\t\t\t\t\t\t\tsignature: res.signature,\r\n");
      out.write("\t\t\t//\t\t\t\t\t\t\tjsApiList: res.jsApiList\r\n");
      out.write("\t\t\t//\t\t\t\t\t\t}); //end_config\r\n");
      out.write("\t\t\t//\t\t\t\t\t},\r\n");
      out.write("\t\t\t//\t\t\t\t\terror: function(res) {\r\n");
      out.write("\t\t\t//\t\t\t\t\t\tconsole.debug(res);\r\n");
      out.write("\t\t\t//\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction changeLenyh() {\r\n");
      out.write("\t\t\t\t$(\"#yhlength\").html($(\"#yhpdesc\").val().length);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction yhsystemList() {\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/system/list.action\", {}, function(data) {\r\n");
      out.write("\t\t\t\t\tvar vlist = JSON.parse(data);\r\n");
      out.write("\t\t\t\t\t$(\"#yhsystemtype\").html(\"\"); //清空 \r\n");
      out.write("\t\t\t\t\tvar optionList = \"<option >请选择优化系统</option>\";\r\n");
      out.write("\t\t\t\t\t$.each(vlist, function(i, system) {\r\n");
      out.write("\t\t\t\t\t\toptionList += \"<option value='\" + system.systemtype + \"'>\" + system.systemname + \"</option>\"\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t$(\"#yhsystemtype\").html(optionList);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction yhmodeList() {\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/mode/received.action\", {\r\n");
      out.write("\t\t\t\t\t\"systemtype\": $(\"#yhsystemtype\").val()\r\n");
      out.write("\t\t\t\t}, function(data) {\r\n");
      out.write("\t\t\t\t\tvar vlist = JSON.parse(data);\r\n");
      out.write("\t\t\t\t\tvar optionList = \"<option >请选择优化模块</option>\";\r\n");
      out.write("\t\t\t\t\t$(\"#yhpmodule\").html(\"\");\r\n");
      out.write("\t\t\t\t\t$.each(vlist, function(i, mode) {\r\n");
      out.write("\t\t\t\t\t\toptionList += \"<option value='\" +mode.moduleid + \"'>\" + mode.modulename + \"</option>\"\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t$(\"#yhpmodule\").html(optionList);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t//\t\t\t\t\t});\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<!-- 容器 -->\r\n");
      out.write("\t\t<div class=\"weui-tab\">\r\n");
      out.write("\t\t\t<div class=\"weui-navbar\">\r\n");
      out.write("\t\t\t\t<a class=\"weui-navbar__item weui-bar__item--on weui-tab__bd-item--active\" href=\"#tab1\">\r\n");
      out.write("\t\t\t\t\t系统优化\r\n");
      out.write("\t\t\t\t</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"weui-tab__bd\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div id=\"tab1\" class=\"weui-tab__bd-item weui-tab__bd-item--active\">\r\n");
      out.write("\t\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/problem/yhreceived.action\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" name=\"puser\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${openid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t\t<div class=\"weui-cells weui-cells_form\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"weui-cell\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"weui-cell__hd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"weui-label\"><h2>系统</h2></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"weui-cell__bd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<select name=\"systemtype\" id=\"yhsystemtype\" class=\"weui-input\" onchange=\"yhmodeList()\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<option >请选择优化系统</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"weui-cell\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"weui-cell__hd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"weui-label\"><h2>模块</h2></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"weui-cell__bd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<select name=\"pmodule\" id=\"yhpmodule\" class=\"weui-input\"  >\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<option >请选择优化模块</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"weui-cells__title\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<h2>优化描述</h2></div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"weui-cell\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"weui-cell__bd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<textarea id=\"yhpdesc\" name=\"pdesc\" class=\"weui-textarea\" placeholder=\"请输入文本\" rows=\"3\" maxlength=\"200\" onkeyup=\"changeLenyh()\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"weui-textarea-counter\"><span id=\"yhlength\">0</span>/200</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"weui-cells weui-cells_form\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"weui-cell\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"weui-cell__bd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"weui-uploader\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"weui-uploader__hd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<p class=\"weui-uploader__title\">附件上传</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"weui-uploader__info\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"weui-uploader__bd\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<ul class=\"weui-uploader__files\" id=\"uploadImage\"></ul>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"weui-uploader__input-box\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div><input type=\"file\" name=\"upfiles\">可选附件1</input>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div><input type=\"file\" name=\"upfiles\">可选附件2</input>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div><input type=\"file\" name=\"upfiles\">可选附件3</input>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"submit\" value=\"优化提交\" />\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
