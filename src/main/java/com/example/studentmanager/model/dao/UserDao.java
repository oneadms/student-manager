package com.example.studentmanager.model.dao;

import com.example.studentmanager.model.dao.base.BaseDao;
import com.example.studentmanager.model.entity.User;
import java.sql.SQLException;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
public class UserDao extends BaseDao {
    public User getUserByUsername(String username) {
      try {

        return queryRunner.query("select * from user where username= ?;",
            new BeanHandler<User>(User.class),username);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return null;
    }
}
