package com.example.studentmanager.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

  private Properties properties;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    properties = new Properties();
    properties.load(AdminServlet.class.getResourceAsStream("/system-info.properties"));
    req.setAttribute("systemInfo",properties);

    req.getRequestDispatcher("/WEB-INF/jsp/admin/admin.jsp").forward(req,resp);

  }
}
