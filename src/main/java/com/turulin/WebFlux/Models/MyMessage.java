package com.turulin.WebFlux.Models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMessage myMessage = (MyMessage) o;
        return Objects.equals(msg, myMessage.msg) && Objects.equals(recipient, myMessage.recipient);
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "msg='" + msg + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}
