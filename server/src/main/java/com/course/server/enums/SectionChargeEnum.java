package com.course.server.enums;

/**
 * @author 田付成
 * @date 2021/3/18 13:59
 */
public enum SectionChargeEnum {

    CHARGE("C", "收费"),
    FREE("F", "免费");

    private String code;

    private String desc;

    SectionChargeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
