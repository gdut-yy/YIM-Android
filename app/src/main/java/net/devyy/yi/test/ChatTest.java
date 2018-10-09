package net.devyy.yi.test;

import java.io.Serializable;

/**
 * 对话 Bean（测试用）
 */
public class ChatTest implements Serializable {

    private int chatImgId;
    private String userName;
    private String lastMessage;
    private String time;

    public ChatTest(String name, String lastMessage, String time) {
        this.userName = name;
        this.lastMessage = lastMessage;
        this.time = time;
    }

    public String getUserName( ) {
        return userName;
    }

    public String getLastMessage( ) {
        return lastMessage;
    }

    public String getTime( ) {
        return time;
    }
}
