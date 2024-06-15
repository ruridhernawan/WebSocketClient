package com.example.demo;

import org.springframework.web.socket.WebSocketSession;

public class VariableStatic {
public static WebSocketSession session;
public static final String fileLocationLinux  = "/var/log/secure";
public static final String fileLocationFreebsd  = "/var/log/auth.log";
public static final String fileLocationNonUnix  = "D:\\secure.log";
}
