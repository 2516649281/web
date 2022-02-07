package service.Servlet;

import dao.pojo.StudentDemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 删除学生模块
 */
public class Delete_Student extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        try {
            StudentDemo.DeleteStudent(id);
            session.setAttribute("del", id);
            System.out.println("已将" + id + "号学生移出表格");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除学生失败");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
