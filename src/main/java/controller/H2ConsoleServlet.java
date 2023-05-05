package controller;

public class H2ConsoleServlet extends org.h2.server.web.WebServlet {
    @Override
    public void init() {
        super.init();
        // Altere as configurações conforme necessário
        getServletContext().setAttribute("org.h2.server.web.WebAllowOthers", "true");
        getServletContext().setAttribute("org.h2.server.web.WebPort", "8082");
    }
}
