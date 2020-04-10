package com.hello.store.test.web;

public class Rtn<T> {
    public Rtn() {
    }

    /**
     * 成功默认
     * @return
     */
    public static Data ok() {
        return new ResponseBaseData(true, 200, "操作成功", (Object)null);
    }

    public static Data success() {
        return new ResponseBaseData(true, 200, "操作成功", (Object)null);
    }

    /**
     * 指定成功的信息
     * @param message
     * @return
     */
    public static Data successmsg(String message) {
        return new ResponseBaseData(true, 200, message, (Object)null);
    }

    /**
     * 返回数据
     * @param data
     * @return
     */
    public Data success(T data) {
        return new ResponseBaseData(true, 200, "操作成功", data);
    }

    /**
     * 指定信息和数据
     * @param message
     * @param data
     * @return
     */
    public Data success(String message, T data) {
        return new ResponseBaseData(true, 200, message, data);
    }

    public static Data error() {
        return new ResponseBaseData(false, 200, "操作失败", (Object)null);
    }

    public static Data error(String message) {
        return new ResponseBaseData(false, 200, message, (Object)null);
    }

    public Data error(String message, T data) {
        return new ResponseBaseData(false, 200, message, data);
    }

    public static Data errorPage() {
        return new ResponseBaseData(false, 200, "查询失败", (Object)null);
    }

    public static Data errorPage(String message) {
        return new ResponseBaseData(false, 200, message, (Object)null);
    }
}
