package com.example.studentmanager.model.dto;

import com.example.studentmanager.model.entity.Course;
import java.util.List;
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
@Builder
@ToString
public class GradeDTO {
    private Integer gid;
    private String gradeName;
    private List<Course> courses;
}
