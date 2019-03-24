package com.cxy.favourite.domain.enums;

/**
 * 错误原因枚举
 */
public enum ExceptionEnums {
    SUCCESS(000000, "操作成功"),
    FAILED(999999,"操作失败"),
    ParamError(000001, "参数错误！"),
    BACKLOGIN(0000111,"重新登录"),
    LoginNameOrPassWordError(000100, "用户名或者密码错误！"),
    EmailUsed(000101,"该邮箱已被注册"),
    UserNameUsed(000102,"该登录名已存在"),
    EmailNotRegister(000103,"该邮箱地址未注册"),
    LinkOutdated(000104,"该链接已过期，请重新请求"),
    PassWordError(000105,"密码输入错误"),
    UserNameLengthLimit(000106,"用户名长度超限"),
    LoginNameNotExists(000107,"该用户未注册"),
    UserNameSame(000200,"新用户名与原用户名一致"),

    FavoritesNameIsNull(000201,"收藏夹名称不能为空"),
    FavoritesNameUsed(000202,"收藏夹名称已被创建"),

    CollectExist(000300,"该文章已被收藏"),

    FileEmpty(000400,"上传文件为空"),
    LimitPictureSize(000401,"图片大小必须小于2M"),
    LimitPictureType(000402,"图片格式只能是'jpg'、'png'、'jpge'、'gif'、'bmp'")
    ;
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    ExceptionEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ExceptionEnums get(Integer code){
        for(ExceptionEnums e :values()){
            if(code.equals(e.getCode())){
                return e;
            }
        }
        return null;
    }

}
