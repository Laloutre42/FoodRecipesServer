package com.zed.foodrecipes.service;

public class Operations {

	public static double parseDouble(String s){
        return s.isEmpty() ? 0.0 : Double.parseDouble(s);
    }
	public static int parseInt(String s){
        return s.isEmpty() ? 0 : Integer.parseInt(s);
    }

    public static boolean parseBoolean(String s) {
        return s.isEmpty() ? false : Boolean.parseBoolean(s);
    }
}
