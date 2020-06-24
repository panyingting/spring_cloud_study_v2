package com.my.study.leetcode.arithmetic.page1;


/**
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RomanToInt {

    public int romanToInt(String s) {
        int total = 0;
        int tmp = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            switch (ch){
                case 'I':
                    if(tmp >= 10){
                        total += tmp;
                        tmp = 0;
                    }
                    tmp +=1;
                    break;
                case 'V':                           // 5
                    total += tmp == 1? 4 : tmp+5;
                    tmp = 0;
                    break;
                case 'X':                           // 10
                    if(tmp >= 100){
                        total += tmp;
                        tmp = 0;
                    }
                    if(tmp == 1){
                        total += 9;
                        tmp = 0;
                    }
                    else{
                        tmp +=10;
                    }
                    break;
                case 'L':                           // 50
                    total += tmp == 10 ? 40 : 50+tmp;
                    tmp = 0;
                    break;
                case 'C':                           // 100
                    if(tmp == 10){
                        total += 90;
                        tmp = 0;
                    }else {
                        tmp += 100;
                    }
                    break;
                case 'D':                           // 500
                    total += tmp == 100 ? 400 : 500;
                    tmp = 0;
                    break;
                case 'M':
                    total += tmp == 100 ? 900 : 1000;
                    tmp = 0;
            }
        }
        return total+tmp;
    }


}
