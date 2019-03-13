import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shaoyijiong
 * @date 2019/3/13
 */
@WebServlet("/HelloForm")
public class HelloWorld extends HttpServlet {

    private String message;

    @Override
    public void init() {
        // 执行必需的初始化
        message = "Hello World";
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
        //request 相关

        //拿到参数  如果中文的话需要转码
        String name = new String(request.getParameter("name").getBytes("ISO8859-1"),
            StandardCharsets.UTF_8);
        //cookie
        Cookie[] cookies = request.getCookies();
        //请求路径 /hello
        String requestURI = request.getRequestURI();

        response.setStatus(200);
        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    /**
     * 处理 POST 方法请求的方法
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        // 什么也不做
    }
}
