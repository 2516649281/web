package service.Servlet;

import dao.pojo.StudentDemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 添加学生模块
 */
public class Add_Student extends HttpServlet {
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
            StudentDemo.AddStudent(id, name, age, sex, address);
            System.out.println("已将" + id + "号学生添加至表格");
            session.setAttribute("add", id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
