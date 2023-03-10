package com.turulin.WebFlux.Models;

public class MyMessage {
    private String msg;
    private String recipient;

    public MyMessage(String msg, String recipient) {
        this.msg = msg;
        this.recipient = recipient;
    }

    public String getMsg() {
        return msg;
    }

    public String getRecipient() {
        return recipient;
    }
}
