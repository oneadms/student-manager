package com.example.studentmanager.util;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
public class DBUtils {

  private static Properties properties;

  private static DataSource dataSource;


  public  static DataSource getDs() {




    try {
      properties = new Properties();

      properties.load(DBUtils.class.getResourceAsStream("/db.properties"));

      if (dataSource == null) {
        synchronized (DBUtils.class) {
          if (dataSource == null) {
            dataSource = DruidDataSourceFactory.createDataSource(properties);

          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return dataSource;
  }
}
