package service.Servlet;

import dao.pojo.UserDemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登录模块
 */
public class Login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String jsp = "Login.jsp";
        //获取用户登录信息
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        try {
            if (UserDemo.SelectUser(user, password).equals(password)) {
                session.setAttribute("user_login", user);
                session.setAttribute("password_login", password);
                jsp = "index.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(jsp).forward(request, response);
    }
}
