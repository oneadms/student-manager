package com.example.studentmanager.service;

import com.example.studentmanager.model.dao.base.BaseDao;
import java.lang.reflect.ParameterizedType;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
public class BaseService<D extends BaseDao> {
  protected D baseDao;
  {
    Class<D> baseDaoClasses = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    try {
      baseDao = (D) baseDaoClasses.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
