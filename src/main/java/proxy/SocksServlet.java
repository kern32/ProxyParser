package proxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Created by kernel32 on 20.10.2017.
 */
@WebServlet(name = "SocksServlet", urlPatterns = {"/get/socks"})
public class SocksServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printSocksProxy(response);
    }

    private static void printSocksProxy(HttpServletResponse response) throws IOException {
        Set<Proxy> proxySet = ControllerServlet.getProxySet();
        for (Proxy proxy : proxySet) {
            if (proxy.isSocks()) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(proxy + "<br/>");
            }
        }
    }
}
