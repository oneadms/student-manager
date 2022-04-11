package com.example.studentmanager.service;

import com.example.studentmanager.cmmon.LoginStatus;
import com.example.studentmanager.model.dao.UserDao;
import com.example.studentmanager.model.entity.User;
import javax.servlet.http.HttpServletRequest;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/

public class UserService extends BaseService<UserDao> {

    public int login(String username, String password, HttpServletRequest req) {
        User user = baseDao.getUserByUsername(username);
        if (user == null) {
            return LoginStatus.USER_NOT_FOUND;
        }else{
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("username", username);
                req.getSession().setAttribute("nickname", user.getNickname());
                return LoginStatus.LOGIN_SUCCESS;
            }
        }
        return LoginStatus.LOGIN_FAILURE;
    }
}
