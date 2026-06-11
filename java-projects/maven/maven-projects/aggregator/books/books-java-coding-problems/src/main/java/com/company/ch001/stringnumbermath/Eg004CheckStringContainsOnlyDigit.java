package com.company.ch001.stringnumbermath;

public class Eg004CheckStringContainsOnlyDigit {

    public static void main(String[] args) {
        Eg004CheckStringContainsOnlyDigit obj = new Eg004CheckStringContainsOnlyDigit();
        System.out.println(obj.checkStringContainsOnlyDigits("1234567890"));
        System.out.println(obj.checkStringContainsOnlyDigits("12345abc67890"));
        System.out.println(obj.checkStringContainsOnlyDigitsUsingStream("1234567890"));
        System.out.println(obj.checkStringContainsOnlyDigitsUsingStream("12345abc67890"));
    }

    public boolean checkStringContainsOnlyDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkStringContainsOnlyDigitsUsingStream(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .allMatch(Character::isDigit);
    }
}
