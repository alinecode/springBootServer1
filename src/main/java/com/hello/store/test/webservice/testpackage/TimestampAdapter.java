package com.hello.store.test.webservice.testpackage;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * description:
 * author: xiongtao
 * date: 2020-5-22
 * Version 1.0
 **/
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
    @Override
    public Timestamp unmarshal(Date v) throws Exception {
        return new Timestamp(v.getTime());
    }

    @Override
    public Date marshal(Timestamp v) throws Exception {
        return new Date(v.getTime());
    }
}