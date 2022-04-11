package com.example.studentmanager.model.dto;

import com.example.studentmanager.model.entity.Teacher;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO extends Teacher {

  /**
   * Teacher t=new Teacher();
   * t.setName("张三");
   * t.setXXX
   * List<Map<String,Object> courses=new ArrayList();
   *
   */

   private List<Map<String, Object>> courses;
}
