package org.apache.jsp.WEB_002dINF.jsp.session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sessionview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
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
      out.write("\t\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/we/jquery.form.js\">\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tfunction sessionviewaction(sid) {\r\n");
      out.write("\t\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/sessionview/list.action\", {\r\n");
      out.write("\t\t\t\t\t'sid': sid\r\n");
      out.write("\t\t\t\t}, function(result) {\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\tvar vlist = JSON.parse(result);\r\n");
      out.write("\t\t\t\t\talert(vlist);\r\n");
      out.write("\t\t\t\t\t$(\"#viewList\").html(\"\");\r\n");
      out.write("\t\t\t\t\tvar s = \"\";\r\n");
      out.write("\t\t\t\t\t$.each(vlist, function(i, item) {\r\n");
      out.write("\t\t\t\t\t\tif(\"receive\" == item.aremake) {\r\n");
      out.write("\t\t\t\t\t\t\ts += \"<div align='left' style='border: 1px solid #adcd3c; background: #f2fddb'>\"\r\n");
      out.write("\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\ts += \"<div align='right' style='border: 1px solid #adcd3c; background: #e8f5fe'>\"\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\ts += item.stime;\r\n");
      out.write("\t\t\t\t\t\tif(\"text\" == item.msgtype) {\r\n");
      out.write("\t\t\t\t\t\t\ts += \"<h2>\" + item.msgcontent + \"</h2>\";\r\n");
      out.write("\t\t\t\t\t\t} else if(\"image\" == item.msgtype) {\r\n");
      out.write("\t\t\t\t\t\t\ts += \"<img width='300px' height='300px' src='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/\" + item.msgcontent + \"'</>\";\r\n");
      out.write("\t\t\t\t\t\t} else if(\"voice\" == item.msgtype) {\r\n");
      out.write("\t\t\t\t\t\t\ts += \"<audio controls src='\" + item.msgcontent + \"'/>\";\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\ts += \"</div>\";\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\talert(s)\r\n");
      out.write("\t\t\t\t\t$(\"#viewList\").html(s);\r\n");
      out.write("\t\t\t\t\t$(\"#replysid\").html(sid);\r\n");
      out.write("\t\t\t\t\t$(\"#replysid\").attr('value', sid);\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction changeLen() {\r\n");
      out.write("\t\t\t\t$(\"#length\").html($(\"#msgcontent\").val().length);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\tfunction formajax() {\r\n");
      out.write("\t\t\t\tvar value = $(\"#replysid\").attr('value');\r\n");
      out.write("\t\t\t\tif(value == \"\" || value == null) {\r\n");
      out.write("\t\t\t\t\talert(\"请选择回复的会话及点击[查看会话]\");\r\n");
      out.write("\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$('#replyform').ajaxSubmit(function(data) {\r\n");
      out.write("\t\t\t\t\t\tsessionviewaction($(\"#replysid\").attr(\"value\"));\r\n");
      out.write("\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t//\t\t\t\tvar s = $('#replyform').serialize();\r\n");
      out.write("\t\t\t\t//\t\t\t\talert(s);\r\n");
      out.write("\t\t\t\t//\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t//\t\t\t\t\t//几个参数需要注意一下\r\n");
      out.write("\t\t\t\t//\t\t\t\t\ttype: \"POST\", //方法类型\r\n");
      out.write("\t\t\t\t//\t\t\t\t\tdataType: \"json\", //预期服务器返回的数据类型\r\n");
      out.write("\t\t\t\t//\t\t\t\t\turl: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/custom/reply.action\", //url\r\n");
      out.write("\t\t\t\t//\t\t\t\t\tdata: s,\r\n");
      out.write("\t\t\t\t//\t\t\t\t\tsuccess: function(result) {\r\n");
      out.write("\t\t\t\t//\t\t\t\t\t\tsessionviewaction($(\"#replysid\").attr(\"value\")); //更新会话列表\r\n");
      out.write("\t\t\t\t//\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t//\t\t\t\t\terror: function() {\r\n");
      out.write("\t\t\t\t//\t\t\t\t\t\talert(\"回复失败异常！\");\r\n");
      out.write("\t\t\t\t//\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t//\t\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<div><span>客服编号:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${data.custom.customid}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span><span>客服状态:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${data.custom.cstate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<h2>会话列表</h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t<table id='wesessionTab' align=\"center\" style=\"border: 1px solid #a9c9e2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>操作</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>微信号</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>时间</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<th>状态</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t<h2>会话详情列表</h2>\r\n");
      out.write("\t\t\t\t\t\t\t<div id=\"viewList\" align=\"right\" style=\"border: 1px solid #adcd3c; background: #f2fddb\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<!--<div align=\"left\" style=\"border: 1px solid #adcd3c; background: #f2fddb\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t用户openid12346号码 <span>2019/1/14 17:05:30</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t</h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<font>发送内容:我发现数据库系统的文件删除不上去我发现数据库系统的文件删除不上去 </br>我发现数据库系统的文件删除不上去我发现数据库系统的文件删除不上去\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</br>我发现数据库系统的文件删除不上去\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</font>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t一段语音\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<audio controls src=\"http://ra01.sycdn.kuwo.cn/resource/n3/32/56/3260586875.mp3\"></audio>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div align=\"right\" style=\"border: 1px solid #adcd3c; background: #e8f5fe\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t客服人员用户名 <span>2019/1/14 17:05:40</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t</h2> 解决方案如下图所示:\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div align=\"right\" style=\"border: 1px solid #adcd3c; background: #e8f5fe\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t客服人员用户名 <span>2019/1/14 17:05:41</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t</h2>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/img/aa.png\" width=\"300px\" height=\"300px\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>-->\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<div align=\"right\">\r\n");
      out.write("\t\t\t\t<form id=\"replyform\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/custom/reply.action\" method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"sid\" id=\"replysid\" align=\"right\" />\r\n");
      out.write("\t\t\t\t\t<div class=\"weui-cell\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"weui-cell__bd\">\r\n");
      out.write("\t\t\t\t\t\t\t<textarea id=\"msgcontent\" class=\"weui-textarea\" placeholder=\"请输入回复内容\" maxlength=\"200\" name=\"msgcontent\" rows=\"3\" onkeyup=\"changeLen()\"></textarea>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"weui-textarea-counter\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<span id=\"length\">0</span>/200\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"file\" name=\"file\" value=\"发送图片\" align=\"right\" />\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"回复\" align=\"right\" onclick=\"formajax()\" />\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/jsp/session/sessionview.jsp(104,9) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/session/sessionview.jsp(104,9) '${data.slist }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${data.slist }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/session/sessionview.jsp(104,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("session");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type='button' value='关闭' />\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type='button' value='查看详情' onclick='sessionviewaction(");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(")' />\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(" </td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session.openid }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session.opentime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${session.cstate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}
