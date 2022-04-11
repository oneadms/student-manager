package com.example.studentmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {
  private Integer tid;
  private String number;
  private String name;
  private String gender;
  private String phone;
  private String qq;

}
