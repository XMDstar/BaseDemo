package com.zcc.frame.tools.event;

/**
 * @creator : yangshuai
 * @create-time : 2018/8/23 上午10:15
 * @description :所有发送的Event，T实现message
 */
public class MessageEvent<T> {
    private int code;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MessageEvent(int code, T data) {
        this.code = code;

        this.data = data;
    }
}
