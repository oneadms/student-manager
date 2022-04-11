package com.example.studentmanager.cmmon;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/8
 **/
public interface GradeStatus {

  int EXISTS_REPEAT_GRADE = 2;
  int ADD_GRADE_SUCCESS = 1;
  int ADD_GRADE_FAILURE = 2;
  int DELETE_SUCCESS = 1;
  int DELETE_FAILURE = 2;
  int DELETE_GRADE_NOT_EXISTS = 3;
}
