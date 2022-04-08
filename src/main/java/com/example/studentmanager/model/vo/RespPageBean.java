package com.example.studentmanager.model.vo;

import com.example.studentmanager.model.dto.ClazzDTO;
import java.util.List;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class RespPageBean {
    private Long total;
    private List<ClazzDTO> rows;
}
