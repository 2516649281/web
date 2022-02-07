package web;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码展示模块
 */
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //生成验证码
        ServletOutputStream outputStream = response.getOutputStream();
        String s = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);
        //存入session中
        HttpSession session = request.getSession();
        session.setAttribute("check", s);
    }
}
