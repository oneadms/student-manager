package com.example.studentmanager.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
  private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
  public static Connection getCon() {
    Connection connection = threadLocal.get();
    if (connection == null) {
      synchronized (DBUtils.class) {
        if (connection == null) {
          try {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return threadLocal.get();
  }
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

  public static void close(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void close(PreparedStatement ps) {
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }
  }

  public static void close(Connection con) {
    if (con != null) {
      try {
        con.close();
        threadLocal.remove();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
