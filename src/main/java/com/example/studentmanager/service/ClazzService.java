package com.example.studentmanager.service;

import com.example.studentmanager.cmmon.ClazzStatus;
import com.example.studentmanager.model.dao.ClazzDao;
import com.example.studentmanager.model.dto.ClazzDTO;
import com.example.studentmanager.model.vo.RespPageBean;
import java.util.List;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/8
 **/
public class ClazzService extends BaseService<ClazzDao> {

  public RespPageBean getClazzByPage(String gid, String page, String rows, String sort,
      String order) {
    Long total = baseDao.getTotal(gid);
    int size = Integer.parseInt(rows);
    int start = (Integer.parseInt(page) - 1) * size;
    List<ClazzDTO> clazz = this.baseDao.getClazzByPage(gid, start, size, sort, order);
    return new RespPageBean().setTotal(total).setRows(clazz);
  }

  public int addClazz(String clazzName, String gid) {
    if (baseDao.getClazzByClazzName(clazzName) != null) {
      return ClazzStatus.CLAZZ_IN_EXISTS;
    }
    int r = baseDao.addClazz(clazzName, gid);
    return r > 0 ? ClazzStatus.ADD_SUCCESS : ClazzStatus.ADD_FAILURE;
  }

  public int deleteClazz(String clazzid) {
    if (baseDao.getClazzByClazzId(clazzid) == null) {
      return ClazzStatus.CLAZZ_NOT_EXITS;
    }
    int res = baseDao.deleteClazz(clazzid);
    return res > 0 ? ClazzStatus.DELETE_SUCCESS : ClazzStatus.DELETE_FAILRFE;
  }
}
