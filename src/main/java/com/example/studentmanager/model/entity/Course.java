package com.example.studentmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/7
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Course {
  private Integer cid;
  private String courseName;
}
