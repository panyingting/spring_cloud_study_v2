package com.my.study.leetcode.arithmetic;

import org.junit.Test;

public class FindMedianNumber {

    @Test
    public void test(){
        int nums1[] = {1,6};
        int nums2[] = new int[]{-7,8};

        double median = findMedianSortedArrays(nums1, nums2);

        System.out.println(median);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int count = nums1.length + nums2.length;

        int medianIdx = (count+1)/2 - 1; // 也可以根据count奇偶性来分开赋值， 基数 ：  medianIdx = (count)/2 ； 偶数（不会为0） ：  medianIdx = (count)/2 - 1；
        int medianIdx2 = (count+2)/2 - 1; // 也可以根据count奇偶性来分开赋值， 基数 ：  medianIdx = (count)/2 ； 偶数（不会为0） ：  medianIdx = (count)/2 - 1；

        if(nums1.length == 0)
            return (nums2[medianIdx] + nums2[medianIdx2])/2f;
        if(nums2.length == 0)
            return (nums1[medianIdx] + nums1[medianIdx2])/2f;

        if((count & 1) > 0){
            return findMedianNum(nums1, 0, nums2, 0, medianIdx);
        }

        return (findMedianNum(nums1, 0, nums2, 0, medianIdx) + findMedianNum(nums1, 0, nums2, 0, medianIdx+1))/2f;
    }
    private double findMedianNum(int[] nums1, int begin1, int[] nums2, int begin2, int kIndex){

        if(kIndex == 0){
            if( begin1 >= nums1.length){
                return nums2[begin2];
            }else if (begin2 >= nums2.length){
                return nums1[begin1];
            }
            return Math.min(nums1[ begin1], nums2[begin2]);
        }
        // 去除法，是两个数组，所以去掉kIndex 之前的一半数据（去掉小的一部分）
        int partIdx = (kIndex - 1)/2;
        int val1 = partIdx + begin1 >= nums1.length ? Integer.MAX_VALUE : nums1[begin1+partIdx];
        int val2 = partIdx + begin2 >= nums2.length ? Integer.MAX_VALUE : nums2[begin2+partIdx];

        if(val1 < val2){
            return findMedianNum(nums1, begin1+partIdx+1, nums2, begin2, kIndex - partIdx-1);
        }
        return findMedianNum(nums1, begin1, nums2, begin2+partIdx+1, kIndex - partIdx-1);
    }

}
