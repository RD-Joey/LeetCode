package com.stu.zyj;

public class DetectCapital520 {
    public boolean detectCapitalUse(String word) {
        if(word.length() < 2) return true;
        if(word.charAt(0) >= 97) return word.toLowerCase().equals(word);
        if(word.charAt(1) >= 97) return word.substring(1).toLowerCase().equals(word.substring(1));
        return word.toUpperCase().equals(word);
    }
}
