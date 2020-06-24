package com.my.study.leetcode.arithmetic.page1;

public class StringToInteger {

    public int myAtoi(String str) {
        if(str == null || str.length() == 0)
            return 0;

        int idx = 0;
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == ' '){
                idx++;
                continue;
            }
            else if(ch != '-' && ch != '+' && (ch > '9' || ch < '0')){
                return 0;
            }
            break;
        }

        if(str.length() == idx)
            return 0;

        int chIdx = 0;
        char[] chArr = new char[12];
        char idxCh = str.charAt(idx);
        if( idxCh == '-' || idxCh == '+'){
            if(idx == str.length() - 1 || str.charAt(idx+1) > '9' || str.charAt(idx+1) < '0')
                return 0;
            if(idxCh == '+')
                idx++;
            if(idxCh == '-'){
                chArr[chIdx++] = idxCh;
                idx++;
            }
        }
        while (idx < str.length() && str.charAt(idx) == '0'){
            idx ++;
        }

        if(str.length() == idx || str.charAt(idx) > '9' || str.charAt(idx) < '0'){
            return 0;
        }

        chArr[chIdx++] = str.charAt(idx++);
        for(; idx < str.length() && str.charAt(idx) <= '9' && str.charAt(idx) >= '0' ; idx++){

            if(chIdx == 12)
                return chArr[0] == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            chArr[chIdx ++] = str.charAt(idx);

        }

        String newNum = new String(chArr, 0, chIdx);
        String extrInt;
        if(chArr[0] == '-'){
            extrInt = String.valueOf(Integer.MIN_VALUE);
        }
        else{
            extrInt = String.valueOf( Integer.MAX_VALUE);
        }
        if(newNum.length() < extrInt.length() || (newNum.length() == extrInt.length() && newNum.compareTo(extrInt) < 0)){
            return Integer.valueOf(newNum);
        }
        return Integer.valueOf(extrInt);
    }

    public static void main(String[] args) {

        System.out.println(new StringToInteger().myAtoi("2147483647"));
    }

}
