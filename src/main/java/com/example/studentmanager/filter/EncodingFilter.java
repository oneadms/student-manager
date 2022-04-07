package com.example.studentmanager.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
@WebFilter(initParams =@WebInitParam(name="charset",value = "UTF-8"),urlPatterns = "/*")
public class EncodingFilter implements Filter {

  private String charset;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    charset = filterConfig.getInitParameter("charset");
    System.out.println("charset = " + charset);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    request.setCharacterEncoding(charset);
    response.setCharacterEncoding(charset);
    chain.doFilter(request, response);

  }

  @Override
  public void destroy() {
  }
}
