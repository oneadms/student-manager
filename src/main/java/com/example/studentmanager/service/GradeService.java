package com.example.studentmanager.service;

import com.example.studentmanager.cmmon.GradeStatus;
import com.example.studentmanager.model.dao.GradeDao;
import com.example.studentmanager.model.dto.GradeDTO;
import com.example.studentmanager.model.entity.Grade;
import com.example.studentmanager.util.DBUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/7
 **/
public class GradeService extends BaseService<GradeDao> {

  public List<GradeDTO> getALlGrade() {
    return baseDao.getAllGrade();
  }

  public int addGrade(String gradeName, List<Integer> cids) {
//    开启事务
    Connection con = DBUtils.getCon();
    try {
      con.setAutoCommit(false);
      if (baseDao.getGradeByGradeName(gradeName) != null) {
//        存在重复年级无法添加
        return GradeStatus.EXISTS_REPEAT_GRADE;
      }
//      开始添加
//      先添加Grade表在添加GradeCourse表
      Grade grade = new Grade().setGradeName(gradeName);
      int i = baseDao.addGrade(grade);
      int i2 = baseDao.addGradeCourse(grade, cids);
      con.commit();
      return i == 1 && i2 == cids.size() ? GradeStatus.ADD_GRADE_SUCCESS : GradeStatus.ADD_GRADE_FAILURE;


    } catch (SQLException e) {
      try {
        con.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally {
      DBUtils.close(con);
    }

    return 0;
  }

  public int deleteGradeByGid(String gid) {
//      grade和grade_course都要删除 所以要开启事务

    Connection con = DBUtils.getCon();
    try {
      con.setAutoCommit(false);
      if (baseDao.getGradeByGid(gid) == null) {
        return GradeStatus.DELETE_GRADE_NOT_EXISTS;
      }
      int r1=baseDao.deleteGrade(gid);
      int r2=baseDao.deleteGradeCourse(gid);
      con.commit();
      return r1>0&r2>0?GradeStatus.DELETE_SUCCESS:GradeStatus.DELETE_FAILURE;
    } catch (SQLException e) {
      try {
        con.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      e.printStackTrace();
    } finally {
      DBUtils.close(con);
    }
    return GradeStatus.DELETE_FAILURE;
  }


}
