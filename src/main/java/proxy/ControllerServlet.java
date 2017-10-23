package proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@WebServlet(name = "ControllerServlet", urlPatterns = {"/index"})
public class ControllerServlet extends HttpServlet {

    private static Set<Proxy> proxySet = new HashSet<>();

    public static Set<Proxy> getProxySet() {
        return proxySet;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //www.socks-proxy.net
        getProxyFromSocksProxyNet();
        //best-proxies.ru
        //getFromBestProxies();

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    public static void getProxyFromSocksProxyNet() throws IOException {
        Document doc = Jsoup.connect("https://www.socks-proxy.net/").userAgent("Mozilla").get();
        Elements rows = doc.select("table#proxylisttable tr");

        for (int i = 1; i < rows.size()-1; i++) {
            Element row = rows.get(i);
            String ip = row.select("td").get(0).text();
            long port = Long.valueOf(row.select("td").get(1).text());
            Type proxyType = getProxyType(row.select("td").get(4).text());
            proxySet.add(new Proxy(ip, port, proxyType ));
        }
    }

    private static Type getProxyType(String proxyType) {
        Type type = null;
        if (proxyType.toUpperCase().startsWith("HTTP")) {
            type = Type.HTTP;
        } else if (proxyType.toUpperCase().startsWith("SOCKS")) {
            type = Type.SOCKS;
        }
        return type;
    }

    public static void getFromBestProxies() throws IOException {
        /*The path to the driver executable must be set by the webdriver.gecko.driver system property; for more information, see https://github.com/mozilla/geckodriver. The latest version can be downloaded from https://github.com/mozilla/geckodriver/releases*/
        /*System.setProperty("webdriver.gecko.driver","resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://best-proxies.ru/proxylist/free/");
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements rows = doc.select("table#proxylist");
        System.out.println(rows);
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Element td = row.select("td ul li a").get(1);
            String ip = td.attr("data-ip");
            long port = Long.valueOf(td.attr("data-port"));
            Type proxyType = getProxyType(td.attr("data-types"));
            proxySet.add(new Proxy(ip, port, proxyType));
        }*/
    }
}
