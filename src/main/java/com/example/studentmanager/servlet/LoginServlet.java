package com.example.studentmanager.servlet;

import com.example.studentmanager.cmmon.LoginStatus;
import com.example.studentmanager.cmmon.Result;
import com.example.studentmanager.service.UserService;
import com.example.studentmanager.util.VerificationCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/login","/LoginServlet"})
public class LoginServlet  extends HttpServlet {

  private UserService userService = new UserService();


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String captcha = ((String) req.getSession().getAttribute("captcha"));
    String account = req.getParameter("account");
    String password = req.getParameter("password");
    String vcode = req.getParameter("vcode");
    ObjectMapper objectMapper = new ObjectMapper();
    resp.setContentType("application/json;charset=utf-8");
    String res=null;
    PrintWriter writer = resp.getWriter();
    if (!captcha.equalsIgnoreCase(vcode)) {
      res = objectMapper.writeValueAsString(Result.fail("验证码错误"));
      writer.write(res);
      return;
    }
    int login = userService.login(account,password);

    switch (login) {
      case LoginStatus.LOGIN_SUCCESS:

        res = objectMapper.writeValueAsString(Result.success("登录成功"));
        req.getSession().setAttribute("username", account);
        break;
      case LoginStatus.USER_NOT_FOUND:
        res = objectMapper.writeValueAsString(Result.fail("用户名不存在"));
        break;
      case LoginStatus.LOGIN_FAILURE:
        res = objectMapper.writeValueAsString(Result.fail("登录失败"));
        break;
      default:
        res = objectMapper.writeValueAsString(Result.fail("登录失败"));
    }
    writer.write(res);

  }

  private void initCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    VerificationCode verificationCode = new VerificationCode();
//    必须先获取image

    BufferedImage image = verificationCode.getImage();
    String text = verificationCode.getText();
    req.getSession().setAttribute("captcha", text);

    VerificationCode.output(image, resp.getOutputStream());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");

    if ("captcha".equals(action)) {
      initCaptcha(req, resp);
    }else{
      req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
    }
  }
}
