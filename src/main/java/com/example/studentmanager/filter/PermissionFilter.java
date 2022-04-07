package com.example.studentmanager.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
//TODO 开发时不需要登录
//@WebFilter(urlPatterns = "/*")

public class PermissionFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    System.out.println("req.getRequestURI() = " + req.getRequestURI());
    if (req.getRequestURI().contains("login")||req.getRequestURI().contains("/Login")||req.getRequestURI().contains("easyui")||req.getRequestURI().contains("h-ui")) {
      chain.doFilter(request, response);
    }else{
      HttpSession session = req.getSession();
      String username = ((String) session.getAttribute("username"));
      if (username == null) {
        ((HttpServletResponse) response).sendRedirect("/login");
      }else{
        chain.doFilter(req, response);
      }
    }
  }
}
