package com.example.studentmanager.servlet;

import com.example.studentmanager.cmmon.CourseStatus;
import com.example.studentmanager.cmmon.Result;
import com.example.studentmanager.model.entity.Course;
import com.example.studentmanager.service.CourseService;
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
@WebServlet(urlPatterns = "/course")
public class CourseServlet extends HttpServlet {
  private CourseService courseService=new CourseService();
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");
    resp.setContentType("application/json;charset=utf-8");
    if ("page".equals(action)) {
      req.getRequestDispatcher("/WEB-INF/jsp/course/courseList.jsp").forward(req, resp);
    }else if ("data".equals(action)){
      List<Course> courses = courseService.queryAllCourse();
      ObjectMapper objectMapper = new ObjectMapper();
      String s = objectMapper.writeValueAsString(courses);
      resp.getWriter().write(s);
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("application/json;charset=utf-8");
    final String method = req.getParameter("method");
    String courseName = req.getParameter("name");

    String msg;
    int code;
    switch (method) {
      case "AddCourse":
        final int courseStatus = courseService.addCourse(courseName);
        switch (courseStatus) {
          case CourseStatus.ADD_SUCCESS:
            code = 200;
            msg = "添加成功";
            break;
          case CourseStatus.COURSE_REPEAT:
            code = 500;
            msg = "课程名重复";
          break;
          case CourseStatus.ADD_FAILURE:
            code=500;
            msg = "添加失败";
            break;
          case CourseStatus.PARAM_NULL:
            code=500;
            msg = "传入参数不能为空";
            break;
          default:
            code=500;
            msg = "添加失败,未知错误";
        }
        resp.getWriter().write(new ObjectMapper().writeValueAsString(new Result(code, msg, null)));
        break;
      case "DeleteCourse":
        String[] cids = req.getParameterValues("cid[]");
        List<String> cidList = Arrays.stream(cids).collect(Collectors.toList());
        System.out.println(Arrays.toString(cids));
        int res = courseService.deleteCourse(cidList);
        switch (res) {
          case CourseStatus.DELETE_SUCCESS:
            resp.getWriter().write(new ObjectMapper().writeValueAsString(Result.success("删除成功")));
            break;
          case CourseStatus.DELETE_FAILURE:
            resp.getWriter().write(new ObjectMapper().writeValueAsString(Result.fail("删除失败")));
            break;
          default:
            resp.getWriter().write(new ObjectMapper().writeValueAsString(Result.fail("删除失败")));
            break;
        }
        break;

      default:
        break;
    }
  }
}
