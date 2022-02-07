package service.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 退出系统，删除session
 */
public class Exit_System extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String exit = request.getParameter("exit");
        if (exit.equals("退出系统")) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            //跳转至登录界面，并删除所有session
            session.invalidate();
        }
    }
}
