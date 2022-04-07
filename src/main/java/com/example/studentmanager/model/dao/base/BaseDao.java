package com.example.studentmanager.model.dao.base;

import com.example.studentmanager.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
public class BaseDao {

  protected QueryRunner queryRunner = new QueryRunner(DBUtils.getDs());


}
