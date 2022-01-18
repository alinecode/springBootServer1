package com.hello.store.test.webservice.testpackage;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class SSUserVo {
    /**
     * 经办人姓名
     */
    private String aae011;
    /**
     * 经办人所属部门
     */
    private String aae017;
    /**
     * 经办时间
     */
    private Timestamp aae036;

    public String getAae011() {
        return aae011;
    }

    public void setAae011(String aae011) {
        this.aae011 = aae011;
    }

    public String getAae017() {
        return aae017;
    }

    public void setAae017(String aae017) {
        this.aae017 = aae017;
    }

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public Timestamp getAae036() {
        return aae036;
    }

    public void setAae036(Timestamp aae036) {
        this.aae036 = aae036;
    }

    /**
     * 转换为map对象
     *
     * @return Map
     */
    public java.util.Map toMap() {
        java.util.Map map = new java.util.HashMap();
        // 数据来源批次流水号
        map.put("aae011", getAae011());
        // 接收总人次
        map.put("aae017", getAae017());
        // 接收总金额
        map.put("aae036", getAae036());
        return map;
    }
}

