package com.my.study.leetcode.arithmetic.page2;

import org.junit.Test;

public class GetPermutation {


    @Test
    public void test(){
        getPermutation(3,3);
    }

    public String getPermutation(int n, int k) {

        // 标记已被添加的数字
        boolean[] flag = new boolean[n+1];

        // 存储组合的数字
        StringBuilder nums = new StringBuilder(n);

        // 递归获取下一个数字
        generate(n, k, flag, nums);

        return nums.toString();
    }

    private void generate(int n, int k, boolean[] flag, StringBuilder nums){

        int remainNums = n - nums.length();
        // 如果获取完毕则可以返回了
        if(remainNums == 0) return;
        int no = 1;

        // 计算当前数字个数后面有多少个组合（也就是当前数字个数-1有多少个组合）， 比如 1，2，3 ，得出2个组合
        for(int i=1; i< remainNums; i++){
            no *= i;
        }

        // 根据组合数以及 K 获取对应数字
        int remain = k % no;
        int currNo = k/no ;

        if(remain != 0){
            currNo +=1;
            k = remain;
        }else {
            k = no;
        }

        int count = 0;
        for(int i=1; i<flag.length; i++){
            if(!flag[i]){       // 只有数字未被加入时才计算
                count ++;
            }
            if(count == currNo){
                currNo = i;
                break;
            }
        }

        flag[currNo] = true;
        nums.append(currNo);

        generate(n, k, flag, nums);

    }

}
