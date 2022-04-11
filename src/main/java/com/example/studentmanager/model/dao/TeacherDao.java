package com.example.studentmanager.model.dao;

import com.example.studentmanager.model.dao.base.BaseDao;
import com.example.studentmanager.model.dto.TeacherDTO;
import com.example.studentmanager.model.entity.Clazz;
import com.example.studentmanager.model.entity.Course;
import com.example.studentmanager.model.entity.Grade;
import com.example.studentmanager.model.entity.Teacher;
import com.sun.xml.internal.ws.streaming.TidyXMLStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/11
 **/
public class TeacherDao extends BaseDao {

  public Long getTotal() {
    try {
      return queryRunner.query("select count(*) from  teacher;", new ScalarHandler<>());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;

  }

  public List<TeacherDTO> getTeacherByPage(int start, int size, String sort, String order)
      throws SQLException {
    return queryRunner.query(
        "SELECT t.*,cl.*,co.cid as coid, co.courseName ,g.gradeName FROM (SELECT * FROM teacher ORDER BY "
            + sort + " " + order
            + " LIMIT ?,?) t left join  teacher_course tc on tc.tid=t.tid left join grade g on g.gid=tc.gid left join "
            + "clazz cl on cl.cid=tc.clazzId left join course co on co.cid=tc.courseId ",
        new ResultSetHandler<List<TeacherDTO>>() {
          @Override
          public List<TeacherDTO> handle(ResultSet rs) throws SQLException {
            List<TeacherDTO> list = new ArrayList<>();
            int lastTid = -1;
            TeacherDTO teacherDTO=null;

            while (rs.next()) {
              int tid = rs.getInt("tid");
              if (lastTid != tid) {
                String number = rs.getString("number");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                String qq = rs.getString("qq");
                int cid = rs.getInt("cid");
                String clazzName = rs.getString("clazzName");
                int gid = rs.getInt("gid");
                int coid = rs.getInt("coid");
                String courseName = rs.getString("courseName");
                String gradeName = rs.getString("gradeName");
                teacherDTO = new TeacherDTO();
                teacherDTO.setTid(tid);
                teacherDTO.setName(name);
                teacherDTO.setNumber(number);
                teacherDTO.setGender(gender);
                teacherDTO.setPhone(phone);
                teacherDTO.setQq(qq);

                HashMap<String, Object> courses = new HashMap<>();
                if (gradeName != null && !"".equals(gradeName)) {

                  Grade grade = new Grade().setGradeName(gradeName).setGid(gid);
                  Clazz clazz = new Clazz().setClazzName(clazzName).setCid(cid).setGid(gid);
                  Course course = new Course().setCourseName(courseName).setCid(coid);
                  courses.put("grade", grade);
                  courses.put("clazz", clazz);
                  courses.put("course", course);
                }
                ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
                arrayList.add(courses);
                teacherDTO.setCourses(arrayList);
                lastTid = tid;
              }else{
                int cid = rs.getInt("cid");
                String clazzName = rs.getString("clazzName");
                int gid = rs.getInt("gid");
                int coid = rs.getInt("coid");
                String courseName = rs.getString("courseName");
                String gradeName = rs.getString("gradeName");
                HashMap<String, Object> courses = new HashMap<>();

                Grade grade = new Grade().setGradeName(gradeName).setGid(gid);
                Clazz clazz = new Clazz().setClazzName(clazzName).setCid(cid).setGid(gid);
                Course course = new Course().setCourseName(courseName).setCid(coid);
                courses.put("grade", grade);
                courses.put("clazz", clazz);
                courses.put("course", course);

                teacherDTO.getCourses().add(courses);
                list.add(teacherDTO);
              }
            }
            return list;
          }
        }, start, size);

  }
}
