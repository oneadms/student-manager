package com.example.studentmanager.servlet;

import com.example.studentmanager.cmmon.Result;
import com.example.studentmanager.model.dto.GradeDTO;
import com.example.studentmanager.service.GradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/7
 **/
@WebServlet(urlPatterns ="/grade")
public class GradeServlet extends HttpServlet {

  private GradeService gradeService = new GradeService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    if ("query".equals(action)) {
      resp.setContentType("application/json;charset=utf-8");
      List<GradeDTO> aLlGrade = gradeService.getALlGrade();
      ObjectMapper objectMapper = new ObjectMapper();
      resp.getWriter().write(objectMapper.writeValueAsString(aLlGrade));
    } else if ("page".equals(action)) {
      req.getRequestDispatcher("/WEB-INF/jsp/grade/gradeList.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getParameter("AddGrade");
  }
}
