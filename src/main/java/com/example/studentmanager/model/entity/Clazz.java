package com.example.studentmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/8
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Clazz {
  private Integer cid;
  private String clazzName;
  private Integer gid;
}
