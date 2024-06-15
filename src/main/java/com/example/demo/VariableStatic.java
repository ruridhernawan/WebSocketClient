package com.example.demo;

import org.springframework.web.socket.WebSocketSession;

public class VariableStatic {
public static WebSocketSession session;


public static final String fileLocationLinux  = "/home/live/log/apps.log"; 
//public static final String fileLocationLinux  = "/Users/user/Documents/log.apps"; 
public static final String fileLocationFreebsd  = "/home/live/log/apps.log";
public static final String fileLocationNonUnix  = "D:\\secure.log";
}
