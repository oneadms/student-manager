package com.example.studentmanager.model.dao;

import com.example.studentmanager.model.dao.base.BaseDao;
import com.example.studentmanager.model.dto.ClazzDTO;
import com.example.studentmanager.model.entity.Clazz;
import com.example.studentmanager.model.entity.Grade;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import sun.security.krb5.internal.crypto.CksumType;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/8
 **/
public class ClazzDao extends BaseDao {
//  select c.*,g.gradeName from clazz c left join grade g on c.gid=g.gid where gid= ? GROUP BY sort order limit 0,10;
  public List<ClazzDTO> getClazzByPage(String gid, int start,int size, String sort, String order) {
    StringBuffer sql = new StringBuffer(
        "select c.*,g.gradeName from clazz c left join grade g on c.gid=g.gid ");
    Object[] params = null;
    if (gid != null && !gid.equals("")) {
      sql.append("where c.gid= ? group by " + sort + " " + order + " limit ?,?");
      params = new Object[3];
      params[0] = gid;
      params[1] = start;
      params[2] = size;
    } else {
      sql.append("group by " + sort + " " + order + " limit ?,?");
      params = new Object[2];
      params[0] = start;
      params[1] = size;
    }
    try {
      return queryRunner.query(sql.toString(), new ResultSetHandler<List<ClazzDTO>>() {
        @Override
        public List<ClazzDTO> handle(ResultSet resultSet) throws SQLException {
          ArrayList<ClazzDTO> clazzDTOS = new ArrayList<>();
          while (resultSet.next()) {
            int cid = resultSet.getInt("cid");
            String clazzName = resultSet.getString("clazzName");
            int gid1 = resultSet.getInt("gid");
            String gradeName = resultSet.getString("gradeName");
            Grade grade = new Grade(gid1, gradeName);
            ClazzDTO clazzDto = new ClazzDTO(grade);
            clazzDto.setClazzName(clazzName);
            clazzDto.setGid(gid1);
            clazzDto.setCid(cid);
            clazzDTOS.add(clazzDto);
          }
          return clazzDTOS;
        }

      },params);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Long getTotal(String gid) {
    if (gid != null && !"".equals(gid)) {
      try {
        return queryRunner.query("select count(*) from clazz where gid=?;", new ScalarHandler<>(),gid);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      //说明用户传递了 gid 过来
    }else{
      try {
        return queryRunner.query("select count(*) from clazz;", new ScalarHandler<>());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return null;
  }

  public int addClazz(String clazzName, String gid) {
    try {
      return queryRunner.update("insert into clazz (clazzName, gid) values (?,?);", clazzName, gid);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public int deleteClazz(String clazzid) {
    try {
      return queryRunner.update("delete from clazz where cid=?;", clazzid);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public Clazz getClazzByClazzName(String clazzName) {
    try {
      return queryRunner.query("select * from clazz c where c.clazzName-?;", new BeanHandler<>(
          Clazz.class), clazzName);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Clazz getClazzByClazzId(String clazzid) {
    try {
      return queryRunner.query("select * from clazz c where c.cid-?;", new BeanHandler<>(
          Clazz.class), clazzid);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
