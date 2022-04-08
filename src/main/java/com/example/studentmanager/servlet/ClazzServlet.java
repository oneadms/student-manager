package com.example.studentmanager.servlet;

import com.example.studentmanager.cmmon.ClazzStatus;
import com.example.studentmanager.cmmon.Result;
import com.example.studentmanager.model.vo.RespPageBean;
import com.example.studentmanager.service.ClazzService;
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
 * @date 2022/4/8
 **/
@WebServlet(urlPatterns = "/clazz")
public class ClazzServlet extends HttpServlet {

  private ClazzService clazzService = new ClazzService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    if ("page".equals(action)) {
      req.getRequestDispatcher("/WEB-INF/jsp/class/clazzList.jsp").forward(req,resp);
    } else if ("data".equals(action)) {
      //获取年级的id
      String gid = req.getParameter("gid");
      //分页页码
      String page = req.getParameter("page");
      //每页查询多少条
        String rows = req.getParameter("rows");
      String sort = req.getParameter("sort");
      String order = req.getParameter("order");
      RespPageBean respPageBean = clazzService.getClazzByPage(gid, page, rows, sort, order);
      resp.setContentType("application/json;charset=utf-8");
      resp.getWriter().write(new ObjectMapper().writeValueAsString(respPageBean));

    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    if ("addClazz".equals(action)) {
      String gid = req.getParameter("gradeid");
      String clazzName = req.getParameter("name");
      int res = clazzService.addClazz(clazzName, gid);
      Result result;
      if (res == ClazzStatus.ADD_SUCCESS) {
        result = Result.success("添加班级成功");
      }else if (res == ClazzStatus.CLAZZ_IN_EXISTS) {
        result = Result.fail("该班级已经存在，无法添加");
      }  else {
        result = Result.fail("添加班级失败");
      }
      resp.setContentType("application/json;charset=utf-8");
      resp.getWriter().write(new ObjectMapper().writeValueAsString(result));

    } else if ("deleteClazz".equals(action)) {
      String clazzid = req.getParameter("clazzid");

      Result result;
      int res = clazzService.deleteClazz(clazzid);
      if (res == ClazzStatus.DELETE_SUCCESS) {
        result = Result.success("删除成功");
      } else if(res==ClazzStatus.CLAZZ_NOT_EXITS) {
        result = Result.success("删除失败，该数据不存在");
      }else {
        result = Result.fail("删除失败");
      }
      resp.setContentType("application/json;charset=utf-8");
      resp.getWriter().write(new ObjectMapper().writeValueAsString(result));

    }
  }
}
