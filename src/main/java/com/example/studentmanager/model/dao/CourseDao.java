package com.example.studentmanager.model.dao;

import com.example.studentmanager.model.dao.base.BaseDao;
import com.example.studentmanager.model.entity.Course;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/7
 **/
public class CourseDao extends BaseDao {

  public static final String STRING = new String();

  public List<Course> queryAllCourse() {
    try {
      return queryRunner.query("select * from course;", new BeanListHandler<>(Course.class));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public int addCourse(String courseName) {
    int res=-1;
    try {

      res = queryRunner.update("insert into course (courseName) values (?);", courseName);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return res;
  }

  public Long getCourseCount(String courseName) {
    Long count = null;
    try {
      count = queryRunner.query("select count(1)from course c where c.courseName=?;",
          new ScalarHandler<Long>(),courseName);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return count;
  }

  public boolean deleteCourse(List<String> cidList) {
    AtomicBoolean flag= new AtomicBoolean(false);
    cidList.parallelStream().forEach(cid -> {
      try {
        int row = queryRunner.update("delete from course where cid=?", cid);
        if (row > 0) {
          flag.set(true);
        }
      } catch (SQLException e) {

        e.printStackTrace();
      }

    });
    return flag.get();
  }


}
