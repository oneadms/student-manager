package com.example.studentmanager.service;

import com.example.studentmanager.model.dao.TeacherDao;
import com.example.studentmanager.model.vo.RespPageBean;
import java.sql.SQLException;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/11
 **/
public class TeacherService extends BaseService<TeacherDao>{

  public RespPageBean getTeacherByPage(String page, String rows, String sort, String order) {
    Long total = baseDao.getTotal();
    int size = Integer.parseInt(rows);
    int offset = Integer.parseInt(page) - 1;
    int start= offset * size;
    try {
      return new RespPageBean(total, baseDao.getTeacherByPage(start, size, sort, order));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
// 根据tid删除教师

}
