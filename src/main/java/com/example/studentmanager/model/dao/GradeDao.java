package com.example.studentmanager.model.dao;

import com.example.studentmanager.model.dao.base.BaseDao;
import com.example.studentmanager.model.dto.GradeDTO;
import com.example.studentmanager.model.entity.Course;
import com.sun.corba.se.spi.activation.BadServerDefinitionHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;

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

                  String gradeName = rs.getString("gradeName");
                  Course course = new Course(cid, courseName);
                  gradeDTO.setGradeName(gradeName);
                  gradeDTO.setGid(gid);
                  ArrayList<Course> courses = new ArrayList<>();
                  courses.add(course);
                  gradeDTO.setCourses(courses);
                  lastGid = gid;
                }
                result.add(gradeDTO);
              }
              return result;
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
