package com.my.study.leetcode.v2.page1;

/**
 * @author : Pan Yingting
 * @date : 2021/6/28 9:26 上午
 */
public class FindMedianSortedArrays {

    int nums1LeftIdx = 0;
    int nums2LeftIdx = 0;
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        int len = nums1.length + nums2.length;
        int k = (len - 1) / 2;
        int k1 = len%2;


        int apartK ;

        while (k > 1) {
            if (nums1LeftIdx == nums1.length) {
                if (k1 == 0) {
                    return (nums2[nums2LeftIdx + k] + nums2[nums2LeftIdx + k + 1]) / 2.0;
                }else {
                    return nums2[nums2LeftIdx + k];
                }
            } else if (nums2LeftIdx == nums2.length) {
                if (k1 == 0) {
                    return (nums1[nums1LeftIdx + k] + nums1[nums1LeftIdx + k + 1]) / 2.0;
                }else {
                    return nums1[nums1LeftIdx + k];
                }
            }

            apartK = k/2;
            int nums1Nk = nums1LeftIdx + apartK;
            int nums2Nk = nums2LeftIdx + apartK;
            int nums1Idx = nums1.length > nums1Nk ? nums1Nk : nums1.length-1;
            int nums2Idx = nums2.length > nums2Nk ?  nums2Nk : nums2.length-1;
            if (nums1[nums1Idx] <= nums2[nums2Idx]) {
                k = k - (nums1Idx - nums1LeftIdx) -1;
                nums1LeftIdx = nums1Idx + 1;
            } else {
                k = k - (nums2Idx - nums2LeftIdx + 1);
                nums2LeftIdx = nums2Idx + 1;
            }
        }
        int num1 = getNum(nums1, nums2, 0);
        if (k1 > 0) {
            return num1;
        }
        int num2 = getNum(nums1, nums2, 1);
        return (num1 + num2)/ 2.0;
    }

    private int getNum(int[] nums1, int[] nums2, int type){
        if (nums1LeftIdx == nums1.length) {
            if (type == 0) {
                nums2LeftIdx ++;
            }
            return nums2[nums2LeftIdx++];
        } else if (nums2LeftIdx == nums2.length) {
            if (type == 0) {
                nums1LeftIdx ++;
            }
            return nums1[nums1LeftIdx++];
        } else {
            if (type == 0) {
                int num = Math.max(nums1[nums1LeftIdx], nums2[nums2LeftIdx]);
                nums1LeftIdx++;
                nums2LeftIdx++;
                return num;
            } else {
                return Math.min(nums1[nums1LeftIdx], nums2[nums2LeftIdx]);
            }
        }
    }

    public static void main(String[] args) {
        int[] num1 = {9};
        int[] num2 = {1,3,3,4,5,56};

        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        double midNum = findMedianSortedArrays.findMedianSortedArrays(num1, num2);
        System.out.println(midNum);
    }
}
