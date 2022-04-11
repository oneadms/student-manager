package com.example.studentmanager.servlet;

import com.example.studentmanager.model.vo.RespPageBean;
import com.example.studentmanager.service.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/10
 **/
@WebServlet(urlPatterns = "/teacher")
public class TeacherServlet extends HttpServlet {

  private TeacherService teacherService = new TeacherService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action= req.getParameter("action");
    if ("page".equals(action)) {
      req.getRequestDispatcher("/WEB-INF/jsp/teacher/teacherList.jsp").forward(req, resp);
    } else if ("data".equals(action)) {
      String page = req.getParameter("page");
      String rows = req.getParameter("rows");
      String sort = req.getParameter("sort");
      String order = req.getParameter("order");
      resp.setContentType("application/json;charset=utf-8");

      RespPageBean respPageBean = teacherService.getTeacherByPage(page, rows, sort, order);
      resp.getWriter().write(new ObjectMapper().writeValueAsString(respPageBean));
    }

  }
}
