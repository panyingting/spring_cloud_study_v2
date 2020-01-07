package com.study.arithmetic.letcode;

public class ZShapeSort {

    public String convert(String s, int numRows) {

        if(numRows <=1 || s == null || s.length() < numRows)
            return s;
        char[] buff = new char[s.length()];
        int buffIdx = 0;
        int farthest = (numRows-1)*2;
        for(int i=0; i<numRows; i++){
            buff[buffIdx++] = s.charAt(i);

            int currGap = farthest - i*2;

            for(int next = i+ currGap; next < s.length(); next += currGap){
                if(currGap != 0){
                    buff[buffIdx++] = s.charAt(next);
                }
                currGap = farthest - currGap;
            }
        }
        return new String(buff);
    }

    public static void main(String[] args) {

        System.out.println(new ZShapeSort().convert("LEETCODEISHIRING", 3));
    }

}
