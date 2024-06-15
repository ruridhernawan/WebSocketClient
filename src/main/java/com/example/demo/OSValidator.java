package com.example.demo;

public class OSValidator {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isUnix() {
    	
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix") || OS.contains("linux"));
    }

    public static boolean isFreeBSD() {
    
        return OS.contains("freebsd");
    }

    public static void main(String[] args) {
        if (isUnix()) {
            System.out.println("Sistem Operasi: Unix/Linux");
        } else if (isFreeBSD()) {
            System.out.println("Sistem Operasi: FreeBSD");
        } else {
            System.out.println("Sistem Operasi: Bukan Unix/Linux/FreeBSD");
        }
    }
}
