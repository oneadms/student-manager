package com.example.studentmanager.model.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  implements Serializable {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private boolean enabled;
    private int role;

}
