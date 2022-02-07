package service.Servlet;

import dao.pojo.StudentDemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 修改学生模块
 */
public class Edit_Student extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        try {
            StudentDemo.EditStudent(id, name, age, sex, address);
            session.setAttribute("edit", id);
            System.out.println("修改学生成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("修改学生失败");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
