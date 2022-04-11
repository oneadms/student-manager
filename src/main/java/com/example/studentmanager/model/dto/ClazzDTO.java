package com.example.studentmanager.model.dto;

import com.example.studentmanager.model.entity.Clazz;
import com.example.studentmanager.model.entity.Grade;
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
public class ClazzDTO extends Clazz {
    private Grade grade;
}
