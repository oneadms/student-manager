package com.example.studentmanager.cmmon;

/**
 * @author cnmgb
 * @version 1.0
 * @date 2022/4/6
 **/
public class Result {

  public Result(Integer code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  private Integer code;
  private String msg;
  private Object data;

  public Result() {
  }
  public static Result success(String msg) {
    return new Result(200, msg, null);
  }
  public static Result success(String msg,Object data) {
    return new Result(200, msg, data);
  }
  public static Result fail(String msg) {
    return new Result(500, msg, null);
  }
  public static Result fail(String msg,Object data) {
    return new Result(500, msg, data);
  }
}
