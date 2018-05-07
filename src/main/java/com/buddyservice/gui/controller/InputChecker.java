package com.buddyservice.gui.controller;

public class InputChecker {

    public static boolean checkStringWithoutNumbers(String input) {
        if (input.matches(".*\\d+.*") || input.length() < 3) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkEmail(String input) {
        if (input.matches("\\w+@\\w+\\.\\w+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPhoneNumber(String input) {
        if (input.matches("\\d{9}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDate(String input) {
        if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkRodneCislo(String input) {
        if (input.matches("\\d{10}")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumber(String input) {
        if (input.matches("\\d*")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkTime(String input) {
        if (input.matches("(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDouble(String input) {
        if (input.matches("\\d+((\\.)?\\d{1,2})?")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPassword(String input) {
        if (!input.matches(".*\\s.*") && input.length() <= 16) {
            return true;
        } else {
            return false;
        }
    }
}
