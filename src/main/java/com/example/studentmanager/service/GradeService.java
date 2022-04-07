package com.example.studentmanager.service;

import com.example.studentmanager.model.dao.GradeDao;
import com.example.studentmanager.model.dto.GradeDTO;
import java.util.List;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/7
 **/
public class GradeService extends BaseService<GradeDao>{
  public List<GradeDTO> getALlGrade() {
    return baseDao.getAllGrade();
  }
}
