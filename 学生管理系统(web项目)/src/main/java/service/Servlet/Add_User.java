package service.Servlet;

import web.UserSelect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 添加用户模块
 */
public class Add_User extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("UTF-8");
        //获取用户填入信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password_again = request.getParameter("password_again");
        String checkCode = request.getParameter("CheckCode");

        //将验证码信息填入session中
        HttpSession session = request.getSession();
        String check = (String) session.getAttribute("check");

        //保存用户注册信息
        session.setAttribute("user_new", username);
        session.setAttribute("password_new", password);
        session.setAttribute("password_again_Login", password_again);


        //判断用户是否输入
        if (username.equals("") || password.equals("") || password_again.equals("")) {
            request.setAttribute("login", "请输入完整信息!");
            request.getRequestDispatcher("Add_User.jsp").forward(request, response);
            return;
        }


        //判断验证码是否正确
        if (!check.equalsIgnoreCase(checkCode)) {
            request.setAttribute("login", "验证码错误!");
            request.getRequestDispatcher("Add_User.jsp").forward(request, response);
            return;
        }

        //判断两次密码输入是否一致
        if (!password.equals(password_again)) {
            request.setAttribute("login", "两次密码输入不一致!");
            request.getRequestDispatcher("Add_User.jsp").forward(request, response);
        }

        //判断注册是否成功
        if (UserSelect.Select(username, password)) {
            request.setAttribute("login", UserSelect.state);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            request.setAttribute("login", UserSelect.state);
            request.getRequestDispatcher("Add_User.jsp").forward(request, response);
        }
    }
}
