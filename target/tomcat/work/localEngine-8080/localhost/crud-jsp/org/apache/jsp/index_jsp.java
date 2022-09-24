package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=utf-8");
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
      out.write("<!doctype html>\n");
      out.write("<html lang=\"pt-br\">\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"utf-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <title>Eventos</title>\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n");
      out.write("          integrity=\"sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT\" crossorigin=\"anonymous\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container mt-5\">\n");
      out.write("    <h1>Event</h1>\n");
      out.write("    <button type=\"button\" class=\"btn btn-success\" data-bs-toggle=\"modal\" data-bs-target=\"#salvarModal\">\n");
      out.write("        Save Event\n");
      out.write("    </button>\n");
      out.write("    <table class=\"table\">\n");
      out.write("        <thead>\n");
      out.write("        <tr>\n");
      out.write("            <th scope=\"col\">#</th>\n");
      out.write("            <th scope=\"col\">Nome</th>\n");
      out.write("            <th scope=\"col\">Data</th>\n");
      out.write("            <th scope=\"col\">Local</th>\n");
      out.write("        </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody id=\"tbody\">\n");
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"modal\" tabindex=\"-1\" id=\"editarModal\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <h5 class=\"modal-title\">Editar</h5>\n");
      out.write("                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <form name=\"formEdit\" METHOD=\"POST\" class=\"row g-3\">\n");
      out.write("                    <div class=\"col-md-6\">\n");
      out.write("                        <label for=\"inputNomeEditar\" class=\"form-label\">Evento</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"inputNomeEditar\" name=\"inputNomeEditar\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-6\">\n");
      out.write("                        <label for=\"inputDataEditar\" class=\"form-label\">Data</label>\n");
      out.write("                        <input type=\"date\" class=\"form-control\" id=\"inputDataEditar\" name=\"inputDataEditar\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-12\">\n");
      out.write("                        <label for=\"inputLocalEditar\" class=\"form-label\">Local</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"inputLocalEditar\" name=\"inputLocalEditar\" required>\n");
      out.write("                    </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Fechar</button>\n");
      out.write("                <button type=\"submit\" class=\"btn btn-primary\" id=\"btnEditar\">Editar</button>\n");
      out.write("            </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"modal\" tabindex=\"-1\" id=\"salvarModal\">\n");
      out.write("    <div class=\"modal-dialog\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\">\n");
      out.write("                <h5 class=\"modal-title\">Salvar</h5>\n");
      out.write("                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <form action=\"formSave\" METHOD=\"POST\" class=\"row g-3\">\n");
      out.write("                    <div class=\"col-md-6\">\n");
      out.write("                        <label for=\"inputNome\" class=\"form-label\">Evento</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"inputNome\" name=\"inputNome\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-md-6\">\n");
      out.write("                        <label for=\"inputData\" class=\"form-label\">Data</label>\n");
      out.write("                        <input type=\"date\" class=\"form-control\" id=\"inputData\" name=\"inputData\" required>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"col-12\">\n");
      out.write("                        <label for=\"inputLocal\" class=\"form-label\">Local</label>\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"inputLocal\" name=\"inputLocal\" required>\n");
      out.write("                    </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\">\n");
      out.write("                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Fechar</button>\n");
      out.write("                <button id=\"btnSalvar\" type=\"submit\" class=\"btn btn-primary\">Salvar</button>\n");
      out.write("            </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js\"></script>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js\"\n");
      out.write("        integrity=\"sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8\"\n");
      out.write("        crossorigin=\"anonymous\"></script>\n");
      out.write("<script src=\"js/script.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
