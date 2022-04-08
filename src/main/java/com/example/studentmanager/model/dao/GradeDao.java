package com.example.studentmanager.model.dao;

import com.example.studentmanager.model.dao.base.BaseDao;
import com.example.studentmanager.model.dto.GradeDTO;
import com.example.studentmanager.model.entity.Course;
import com.example.studentmanager.model.entity.Grade;
import com.example.studentmanager.util.DBUtils;
import com.sun.corba.se.spi.activation.BadServerDefinitionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/7
 **/
public class GradeDao extends BaseDao {
  public List<GradeDTO> getAllGrade() {
    try {
      return queryRunner.query(
          "SELECT g.*,c.* FROM  grade g LEFT JOIN grade_course gc on g.gid=gc.gid LEFT JOIN course c on gc.cid=c.cid ORDER BY g.gid ASC",
          new ResultSetHandler<List<GradeDTO>>() {
            @Override
            public List<GradeDTO> handle(ResultSet rs) throws SQLException {
              GradeDTO gradeDTO = null;
              int lastGid = -1;
              List<GradeDTO> result = new ArrayList<>();
              while (rs.next()) {
                int cid = rs.getInt("cid");
                String courseName = rs.getString("courseName");
                int gid = rs.getInt("gid");
                if (gid == lastGid) {
                  gradeDTO.getCourses().add(new Course(cid, courseName));
                } else {
                  gradeDTO = new GradeDTO();
                  result.add(gradeDTO);
                  String gradeName = rs.getString("gradeName");
                  Course course = new Course(cid, courseName);
                  gradeDTO.setGradeName(gradeName);
                  gradeDTO.setGid(gid);
                  ArrayList<Course> courses = new ArrayList<>();
                  courses.add(course);
                  gradeDTO.setCourses(courses);
                  lastGid = gid;

                }

              }
              return result;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public Grade getGradeByGradeName(String gradeName) {
    try {
      return queryRunner.query("select * from grade g where g.gradename=?;", new BeanHandler<>(Grade.class), gradeName);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public int addGrade(Grade grade) throws SQLException {
    Connection con = DBUtils.getCon();
    PreparedStatement ps = con.prepareStatement(
        "insert into grade (gradeName) values (?);", Statement.RETURN_GENERATED_KEYS);
    ps.setString(1, grade.getGradeName());
    int i = ps.executeUpdate();
    ResultSet rs = ps.getGeneratedKeys();
    if (rs.next()) {

      int gid = rs.getInt(1);
      grade.setGid(gid);
    }

    DBUtils.close(rs);
    DBUtils.close(ps);

    return i;

  }

  public int addGradeCourse(Grade grade, List<Integer> cids) throws SQLException {
    StringBuffer sb = new StringBuffer("insert into grade_course (gid,cid) values");

    Object[] params = new Object[cids.size() * 2];
    for (int i = 0; i < cids.size(); i++) {
      if (i == cids.size() - 1) {
        sb.append("(?,?)");
      } else {
        sb.append("(?,?),");
      }
      params[2*i]=grade.getGid();
      params[2 * i +1] = cids.get(i);

    }
    return queryRunner.update(sb.toString(), params);
  }

  public int deleteGrade(String gid) throws SQLException {
    return queryRunner.update(DBUtils.getCon(), "delete from grade where gid=?;", gid);
  }

  public int deleteGradeCourse(String gid) throws SQLException {
    return queryRunner.update(DBUtils.getCon(),"delete from grade_course where gid=?;",gid);
  }

  public Grade getGradeByGid(String gid) {
    try {
      return queryRunner.query("select * from grade g where g.gid=?;",new BeanHandler<>(Grade.class),gid);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
