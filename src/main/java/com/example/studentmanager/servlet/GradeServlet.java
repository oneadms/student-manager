package com.example.studentmanager.servlet;

import com.example.studentmanager.cmmon.GradeStatus;
import com.example.studentmanager.cmmon.Result;
import com.example.studentmanager.model.dto.GradeDTO;
import com.example.studentmanager.service.GradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(urlPatterns = "/grade")
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
    } else if ("data_from_clazz".equals(action)) {
      resp.setContentType("application/json;charset=utf-8");
      List<GradeDTO> aLlGrade = gradeService.getALlGrade();
      aLlGrade.add(0, new GradeDTO().setGid(-1).setGradeName("全部"));
      ObjectMapper objectMapper = new ObjectMapper();
      resp.getWriter().write(objectMapper.writeValueAsString(aLlGrade));
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    if ("AddGrade".equals(action)) {
//      年级名称
      String name = req.getParameter("name");
//    课程id cid
      String[] cids = req.getParameterValues("clazzid");
      Result result =
          gradeService.addGrade(name, Arrays.stream(cids).map(Integer::parseInt).collect(
              Collectors.toList())) == GradeStatus.ADD_GRADE_SUCCESS ? Result.success("添加成功")
              : Result.fail("添加失败");
      resp.setContentType("application/json;charset=utf-8");
      resp.getWriter().write(new ObjectMapper().writeValueAsString(result));

    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String gid = req.getParameter("gid");
    int res = gradeService.deleteGradeByGid(gid);
    Result result=null;
    if (res == GradeStatus.DELETE_GRADE_NOT_EXISTS) {
      result = Result.fail("删除用户不存在");
    } else if (res == GradeStatus.DELETE_FAILURE) {
      result = Result.fail("删除失败");
    } else if (res == GradeStatus.DELETE_SUCCESS) {
      result = Result.success("删除成功");
    }
    resp.setContentType("application/json;charset=utf-8");
    resp.getWriter().write(new ObjectMapper().writeValueAsString(result));

  }
}
