package com.example.studentmanager.service;

import com.example.studentmanager.cmmon.CourseStatus;
import com.example.studentmanager.model.dao.CourseDao;
import com.example.studentmanager.model.dto.GradeDTO;
import com.example.studentmanager.model.entity.Course;
import com.example.studentmanager.service.BaseService;
import java.util.List;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/7
 **/
public class CourseService extends BaseService<CourseDao> {

  public List<Course> queryAllCourse() {
    return baseDao.queryAllCourse();
  }

  public int addCourse(String courseName) {
    if (courseName == null) {
      return CourseStatus.PARAM_NULL;
    }
    Long courseCount = baseDao.getCourseCount(courseName);
    if (courseCount >= 1) {
      return CourseStatus.COURSE_REPEAT;
    }
    return baseDao.addCourse(courseName) > 0 ? CourseStatus.ADD_SUCCESS : CourseStatus.ADD_FAILURE;
  }

  public int deleteCourse(List<String> cidList) {
    return baseDao.deleteCourse(cidList) ? CourseStatus.DELETE_SUCCESS
        : CourseStatus.DELETE_FAILURE;
  }


}
