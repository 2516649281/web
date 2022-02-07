package service.Servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 非法用户拦截模块
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //设置白名单
        String[] url = {"/Login.jsp", "/Add_User.jsp"};
        String s = httpServletRequest.getRequestURL().toString();
        for (String i : url) {
            if (s.contains(i)) {
                chain.doFilter(request, response);
                return;
            }
        }
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user_login");
        Object password = session.getAttribute("password_login");
        if (user == null || password == null) {
            System.out.println("Filter已拦截");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            session.invalidate();
        } else {
            chain.doFilter(request, response);
            System.out.println("Filter已放行");
        }
    }
}
