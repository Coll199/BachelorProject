package com.transport.smart.message;

public class GenericMessage {
    private String msg;

    public GenericMessage(){};

    public GenericMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
