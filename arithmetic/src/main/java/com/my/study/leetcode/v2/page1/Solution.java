package com.my.study.leetcode.v2.page1;

import com.google.common.collect.ImmutableList;
import com.my.study.leetcode.arithmetic.page1.MergeTwoLists;
import com.my.study.leetcode.arithmetic.page2.RotateRight;
import org.checkerframework.checker.units.qual.A;

import javax.xml.soap.Node;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Pan Yingting
 * @date : 2021/7/5 8:01 上午
 */
public class Solution {

    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        int maxDiff = (numRows - 1) * 2;
        for (int i = 0; i < numRows; i++) {
            int idx = i;
            int diff = maxDiff - (i * 2);
            if (diff == 0) {
                diff = maxDiff;
            }
            while (idx < s.length()) {
                sb.append(s.charAt(idx));
                idx += diff;
                if (diff != maxDiff) {
                    diff = maxDiff - diff;
                }
            }
        }
        return sb.toString();
    }

    public int reverse(int x) {
        int max = Integer.MAX_VALUE / 10;
        int maxSub = Integer.MAX_VALUE % 10;
        int min = Integer.MIN_VALUE / 10;
        int minSub = - (Integer.MIN_VALUE % 10);
        int base = x >= 0 ? x % 10 : (x % 10);
        x = x/10;
        while (x != 0) {
            int remain = x % 10;
            if (base > max || ( base == max && remain > maxSub)) {
                return 0;
            }
            if (base < min || (base == min && remain < minSub)) {
                return 0;
            }
            base = base * 10 + remain;
            x = x/10;
        }
        return base;
    }

    public int myAtoi(String s) {
        int idx = 0;
        while (idx < s.length()) {
            if (s.charAt(idx) != ' ') {
                break;
            }
            idx++;
        }
        int base = 1;
        if (idx < s.length()) {
            if (s.charAt(idx) == '+' || s.charAt(idx) == '-') {
                base = s.charAt(idx) == '+' ? 1 : -1;
                idx++;
            }
            int num = 0;
            int max = Integer.MAX_VALUE / 10;
            int maxRemain = Integer.MAX_VALUE % 10;
            int min = Integer.MIN_VALUE / -10;
            int minRemain = -(Integer.MIN_VALUE % 10);

            int bitNum;
            for (int i = idx; i < s.length(); i++) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    bitNum = s.charAt(i) - '0';
                    if (idx == i) {
                        num = bitNum;
                    } else {
                        if (base > 0 && ( max < num || (num == max && bitNum >= maxRemain)) ) {
                            return Integer.MAX_VALUE;
                        }
                        if (base < 0 && (min < num || (num == min && bitNum >= minRemain)) ) {
                            return Integer.MIN_VALUE;
                        }
                        num = num * 10 + bitNum;
                    }
                } else {
                    break;
                }
            }
            return base < 0 ? num*base : num;
        }
        return 0;
    }

    /**
     * 9. 回文数
     */
    public boolean isPalindrome(int x) {
        if (x < 10){
            return x >= 0;
        }
        int num1 = x;
        int num2 = x %10;
        x = x / 10;
        while (x > 0) {
            num2 = num2 * 10 + x %10;
            x = x /10;
        }
        return num1 == num2;
    }

    /**
     * 10. 正则表达式匹配
     */
    public boolean isMatch0(String s, String p) {
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        dp[0][0] = 1;

        for (int i = 0; i<dp.length; i++) {
            for (int j=1; j<dp[0].length; j++) {
                char pChar = p.charAt(j-1);
                if (pChar == '*') {
                     dp[i][j] = dp[i][j-2];
                     if (dp[i][j] == 0 && i > 0) {
                         if(p.charAt(j-2) == '.' || (p.charAt(j-2) == s.charAt(i-1))) {
                             dp[i][j] = dp[i-1][j];
                         }
                     }
                } else {
                    if ( i > 0) {
                        if ((p.charAt(j-1) == '.' || (p.charAt(j-1) == s.charAt(i-1)))) {
                            dp[i][j] = dp[i-1][j-1];
                        }
                    }
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1] == 1;

    }

    /**
     * 11. 盛最多水的容器
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int leftIdx = 0; leftIdx < height.length; leftIdx++) {
            int leftHeight = height[leftIdx];
            int maxHeight = 0;
            int currMax;
            for (int rightIdx = height.length - 1; rightIdx > leftIdx; rightIdx--) {
                if (height[rightIdx] >= leftHeight) {
                    currMax = (rightIdx - leftIdx) * leftHeight;
                    max = Math.max(currMax, max);
                    break;
                }
                if (height[rightIdx] > maxHeight) {
                    currMax = (rightIdx - leftIdx) * height[rightIdx];
                    maxHeight = height[rightIdx];
                    max = Math.max(currMax, max);
                }
            }
        }
        return max;
    }

    /**
     * 11. 盛最多水的容器 - 双指针
     */
    public int maxArea2(int[] height) {
        int leftIdx = 0;
        int rightIdx = height.length-1;
        int maxHeight = Math.min(height[rightIdx], height[leftIdx]);
        int max = height.length * maxHeight;
        while (leftIdx < rightIdx) {
            if (height[leftIdx] <= height[rightIdx]) {
                leftIdx ++;
            } else {
                rightIdx --;
            }
            if (height[leftIdx] > maxHeight && maxHeight < height[rightIdx]) {
                maxHeight = Math.min(height[rightIdx], height[leftIdx]);
                int currArea = (rightIdx - leftIdx) * maxHeight;
                max = Math.max(currArea, max);
            }

        }
        return max;
    }

    /**
     * 12. 整数转罗马数字
     *
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/integer-to-roman
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int count = 1;
            if (num >= 1000) {
                count = num / 1000;
                num = num % 1000;
                sb.append("M");
            } else if (num >= 900) {
                sb.append("CM");
                num = num -900;
            } else if (num >= 500) {
                sb.append("D");
                num = num -500;
            } else if (num >= 400) {
                sb.append("CD");
                num = num - 400;
            } else if (num >= 100) {
                sb.append("C");
                count = num / 100;
                num = num % 100;
            } else if (num >= 90) {
                sb.append("XC");
                num = num - 90;
            } else if (num >= 50) {
                sb.append("L");
                num = num - 50;
            } else if (num >= 40) {
                sb.append("XL");
                num = num - 40;
            } else if (num >= 10) {
                sb.append("X");
                count = num /10;
                num = num % 10;
            } else if (num == 9) {
                num = num - 9;
                sb.append("IX");
            } else if (num >= 5) {
                sb.append("V");
                num = num - 5;
            } else if (num == 4) {
                num = num - 4;
                sb.append("IV");
            } else {
                sb.append("I");
                count = num;
                num = 0;
            }
            while (count > 1) {
                sb.append(sb.charAt(sb.length()-1));
                count--;
            }
        }
        return sb.toString();
    }

    /**
     * 14. 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }
        int len = strs[0].length();
        for (int i=1; i<strs.length; i++) {
            len = Math.min(strs[i].length(), len);
            for (int idx = 0; idx < len; idx ++) {
                if (strs[0].charAt(idx) != strs[i].charAt(idx)) {
                    len = idx;
                }
            }
        }
        return strs[0].substring(0, len);
    }

    /**
     * 15. 三数之和
     */
    public List<List<Integer>> threeSumV1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i==0 || nums[i] != nums[i-1]) {

                int maxIdx = nums.length-1;
                for (int j = i+1; j < nums.length; j++) {
                    if (j == i+1 || nums[j] != nums[j-1]) {
                        int findNum = -(nums[i] + nums[j]);
                        int minIdx =  j+1;
                        while (minIdx <= maxIdx) {
                            int midIdx = (minIdx + maxIdx)/2;
                            if (nums[midIdx] == findNum) {
                                List<Integer> find = new ArrayList<>(3);
                                find.add(nums[i]);
                                find.add(nums[j]);
                                find.add(nums[midIdx]);
                                result.add(find);
                                break;
                            }else {
                                if (nums[midIdx] < findNum) {
                                    minIdx = midIdx + 1;
                                } else {
                                    maxIdx = midIdx - 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 15. 三数之和 双指针
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();


        for (int i = 0; i < nums.length-2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i==0 || nums[i] != nums[i-1]) {
                int right = nums.length-1;
                int left = i+1;
                while (right > left) {
                    int sum = nums[right] + nums[left];
                    if (sum == -nums[i]) {
                        List<Integer> find = new ArrayList<>(3);
                        find.add(nums[i]);
                        find.add(nums[right]);
                        find.add(nums[left]);
                        result.add(find);
                        right --;
                        left++;
                        while ( left != i+1 && left < right && nums[left] == nums[left-1]) {
                            left++;
                        }
                    } else {
                        if (sum > -nums[i]) {
                            right--;
                        } else {
                            left++;
                            while ( left != i+1 && nums[left] == nums[left-1]) {
                                left++;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     * 示例：
     *
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * 提示：
     * 3 <= nums.length <= 10^3
     * -10^3 <= nums[i] <= 10^3
     * -10^4 <= target <= 10^4
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int diff = target - nums[i];
            int left = i+1;
            int right = nums.length-1;
            while (left < right) {
                int minDiff = diff - nums[left] - nums[right];
                if (minDiff > 0) {
                    left ++;
                    if (min > minDiff) {
                        sum = target - minDiff;
                        min = minDiff;
                    }
                } else if (minDiff < 0){
                    right --;
                    if (min > -minDiff) {
                        sum = target - minDiff;
                        min = -minDiff;
                    }
                } else {
                    return target;
                }
            }
        }
        return sum;
    }

    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
     * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
     * 找出所有满足条件且不重复的四元组。
     * 注意：答案中不可以包含重复的四元组。
     * 示例 1：
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * 示例 2：
     * 输入：nums = [], target = 0
     * 输出：[]
     * 提示：
     * 0 <= nums.length <= 200
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 4) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int i1 = i+1; i1 < nums.length - 2; i1++) {
                if ( i1 != i+1 && nums[i1] == nums[i1-1]) {
                    continue;
                }
                int diff = target - nums[i] - nums[i1];
                int left = i1 + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right];

                    if (sum < diff) {
                        left ++;
                    } else if (sum > diff) {
                        right --;
                    } else {
                        List<Integer> ele = new ArrayList<>(4);
                        ele.add(nums[i]);
                        ele.add(nums[i1]);
                        ele.add(nums[left]);
                        ele.add(nums[right]);
                        list.add(ele);
                        left ++;
                        right --;
                        while (left < right && nums[left] == nums[left-1]) {
                            left ++;
                        }
                    }
                }
            }
        }
        return list;
    }


    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * 进阶：你能尝试使用一趟扫描实现吗？
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     *
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     *
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     *  
     *
     * 提示：
     *
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;
        int count = 0;
        while (right.next != null) {
            right = right.next;
            if (count != n) {
                count++;
            } else {
                left = left.next;
            }
        }
        if (n > count) {
            return head.next;
        }
        left.next = left.next.next;
        return head;
    }

    public boolean isValid(String s) {
        Set<Character> left = new HashSet<>();
        Set<Character> right = new HashSet<>();
        left.add('(');
        left.add('[');
        left.add('{');
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(left.contains(ch)) {
                stack.push(ch);
            } else if (stack.isEmpty()){
                return false;
            } else {
                char ch2 = stack.pop();
                boolean ok = (ch == ')' && ch2 == '(') || (ch2 == '[' && ch == ']') || (ch2 == '{' && ch == '}');

                if(!ok) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head;
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode pos = head;
        while ( l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pos.next = l1;
                l1 = l1.next;
            } else {
                pos.next = l2;
                l2 = l2.next;
            }
            pos = pos.next;
        }
        if (l1 != null) {
            pos.next = l1;
        } else{
            pos.next = l2;
        }

        return head;
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>(10);
        StringBuilder builder = new StringBuilder(2*n);
        doGenerateParenthesis(n, n, builder, list);
        return list;
    }

    private void doGenerateParenthesis(int leftNum, int rightNum, StringBuilder builder, List<String> ret) {
        if (leftNum == 0) {
            if (rightNum == 0) {
                ret.add(builder.toString());
            }
            return;
        }
        int len = builder.length();
        leftNum--;
        builder.append("(");
        while (rightNum >= leftNum) {
            doGenerateParenthesis(leftNum, rightNum, builder, ret);
            builder.append(")");
            rightNum--;
        }
        builder.delete(len, builder.length());
    }

    /**
     * 26. 删除有序数组中的重复项
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <=1 ) {
            return nums.length;
        }
        int left = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }

    /**
     * 23. 合并K个升序链表
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length-1);

    }

    private ListNode mergeKLists(ListNode[] lists, int begin, int end) {
        if (begin == end) {
            return lists[begin];
        }
        if (begin == end - 1) {
            return mergeTwoList(lists[begin], lists[end]);
        }
        int midIdx = (begin + end) / 2;
        ListNode l1 = mergeKLists(lists, begin, midIdx);
        ListNode l2 = mergeKLists(lists, midIdx + 1, end);
        return mergeTwoList(l1, l2);
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2){

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode ahead = new ListNode(0);
        ListNode pos = ahead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pos.next = l1;
                l1 = l1.next;
            } else {
                pos.next = l2;
                l2 = l2.next;
            }
            pos = pos.next;
        }
        pos.next = l1 != null ? l1 : l2;

        return ahead.next;

    }

    /**
     * 23. 合并K个升序链表
     */
    public ListNode mergeKLists2(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));

        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.offer(list);
            }
        }

        ListNode ahead = new ListNode(0);
        ListNode pos = ahead;

        while (!priorityQueue.isEmpty()) {
            pos.next = priorityQueue.poll();
            if (pos.next.next != null) {
                priorityQueue.offer(pos.next.next);
            }
            pos = pos.next;
        }
        return ahead.next;
    }

    /**
     * 24. 两两交换链表中的节点
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ahead = new ListNode(1);
        ahead.next = head;
        ListNode pre0 = ahead;
        ListNode pre1 = head;
        ListNode pre2 = head.next;

        while (pre1 != null && pre2 != null) {
            pre1.next = pre2.next;
            pre2.next = pre1;
            pre0.next = pre2;

            pre0 = pre1;
            pre1 = pre1.next;
            if (pre1 != null) {
                pre2 = pre1.next;
            }
        }
        return ahead.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode ahead = new ListNode(1);
        ahead.next = head;
        ListNode pre = ahead;

        while (head != null) {
            int num = k;
            while (head != null && num > 0) {
                stack.push(head);
                head = head.next;
                num -- ;
            }
            if (num != 0) {
                break;
            }
            while (!stack.isEmpty()) {
                pre.next = stack.pop();
                pre = pre.next;
            }
            pre.next = head;

        }
        return ahead.next;

    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode ahead = new ListNode(1);
        ahead.next = head;
        ListNode pre = ahead;
        ListNode start = head;
        ListNode end ;

        while (head != null) {
            int i=1;
            while (i < k && head.next != null) {
                head = head.next;
                i++;
            }
            if (i < k) {
                break;
            }
            end =  head.next;
            head.next = null;

            head = reverseListNode(start);
            start.next = end;
            pre.next = head;
            pre = start;
            head = end;
            start = head;
        }
        return ahead.next;
    }


    public ListNode reverseListNode(ListNode head) {
        ListNode newHead = doReverseListNode(head);
        head.next = null;
        return newHead;
    }

    /**
     * 没有处理最后节点为空，也就是会出现循环链表，需要调用方处理
     * @param head
     * @return
     */
    public ListNode doReverseListNode(ListNode head) {
        if (head.next == null){
            return head;
        }
        ListNode node = reverseListNode(head.next);
        head.next.next = head;
        return node;
    }

    /**
     * 27. 移除元素
     */
    public int removeElement(int[] nums, int val) {
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[pos++] = nums[i];
            }
        }
        return pos;
    }

    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int minMid = Integer.MIN_VALUE / 2;
        int maxMid = Integer.MAX_VALUE / 2;
        int flag = 1;
        if (dividend > 0) {
            flag = -flag;
            dividend = -dividend;
        }
        if (divisor > 0) {
            flag = -flag;
            divisor = -divisor;
        }
        if (dividend > divisor) {
            return 0;
        }
        int sum = divisor;
        int result = 1;
        while ((minMid < sum && maxMid > sum) && (sum + sum) > dividend) {
            result = result + result;
            sum = sum + sum;
        }
        result += divide(dividend - sum, divisor);
        return flag > 0 ? result : -result;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>(10);
        int wordLen = words[0].length();
        Map<String, Integer> wordCountMap = new HashMap<>(words.length);
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0)+1);
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int matchCount = 0;
            Map<String, Integer> matchWordsMap = new HashMap<>(wordCountMap.size());

            while (right + wordLen <= s.length()) {
                String findWord = s.substring(right, right+wordLen);
                right += wordLen;
                if (!wordCountMap.containsKey(findWord)) {
                    left = right;
                    matchCount = 0;
                    matchWordsMap.clear();
                } else {
                    matchCount ++;
                    matchWordsMap.put(findWord, matchWordsMap.getOrDefault(findWord, 0) + 1);
                    while (matchWordsMap.get(findWord) > wordCountMap.get(findWord)) {
                        matchCount--;
                        String backWord = s.substring(left, left+wordLen);
                        matchWordsMap.put(backWord, matchWordsMap.get(backWord) - 1);
                        left += wordLen;
                    }
                    if (matchCount == words.length) {
                        ret.add(left);
                    }
                }
            }
        }
        return ret;
    }

    public void nextPermutation(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        for (int i=nums.length-2; i>=0; i--) {
            if (nums[i] < nums[i+1]) {
                int idx = findIdxNum(nums[i] + 1, nums, i+1, nums.length-1);
                swap(nums, i, idx);
                left = i+1;
                break;
            }
        }
        while (left < right) {
            swap(nums, left, right);
            left ++;
            right--;
        }
    }

    private void swap(int[]nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    private int findIdxNum(int min, int[]nums, int begin, int end) {
        if (begin == end) {
            return begin;
        }
        int midIdx = (begin + end) / 2;

        if (nums[midIdx] < min) {
            return findIdxNum(min, nums, begin, midIdx-1);
        }
        if (nums[midIdx] == min) {
            while ( midIdx <nums.length && nums[midIdx] == min) {
                midIdx ++;
            }
            return midIdx-1;
        }
        if (nums[midIdx+1] < min) {
            return midIdx;
        }
        return findIdxNum(min, nums, midIdx+1, end);
    }

    public int climbStairs(int n) {
        int dp[] = new int[n];
        if (n==1){
            return 1;
        }
        dp[0] = 1;
        dp[1] = 2;
        for(int i=2;i<dp.length;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    public boolean isValidSudoku(char[][] board) {
        boolean [][] row = new boolean[9][9];
        boolean [][] col = new boolean[9][9];
        boolean [][] box = new boolean[9][9];

        for (int i = 0; i < board.length; i++) {
            for (int i1 = 0; i1 < board[0].length; i1++) {
                int boxIdx = i/3*3 + i1/3;
                char ch = board[i][i1];
                if(ch != '.') {
                    ch -= '1';
                    if (row[i][ch] || col[i1][ch] || box[boxIdx][ch]) {
                        return false;
                    }
                    row[i][ch] = true;
                    col[i1][ch] = true;
                    box[boxIdx][ch] = true;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    row[i][num] = true;
                    col[j][num] = true;
                    box[i/3 *3 + j/3][num] = true;
                }
            }
        }
        boolean ok = solveSudoku(board, row, col, box, 0, 0);
        System.out.println(ok);
    }

    private  boolean solveSudoku(char[][] board, boolean[][] row, boolean[][] col, boolean[][] box, int rowIdx ,int colIdx) {
        if (rowIdx == 9) {
            return true;
        }
        boolean changeRow = colIdx == 8;

        if (board[rowIdx][colIdx] != '.') {
            return solveSudoku(board, row, col, box, changeRow ? rowIdx + 1 : rowIdx , changeRow ? 0 : colIdx+1);
        }
        for (int i=0; i<9; i++) {
            if(row[rowIdx][i] || col[colIdx][i] || box[rowIdx/3*3 + colIdx/3][i]) {
                continue;
            }
            row[rowIdx][i] = true;
            col[colIdx][i] = true;
            box[rowIdx/3*3 + colIdx/3][i] = true;
            board[rowIdx][colIdx] = (char)('1'+i);

            boolean ok = solveSudoku(board, row, col, box, changeRow ? rowIdx + 1 : rowIdx , changeRow ? 0 : colIdx+1);
            if (ok) {
                return true;
            }
            row[rowIdx][i] = false;
            col[colIdx][i] = false;
            box[rowIdx/3*3 + colIdx/3][i] = false;
            board[rowIdx][colIdx] = '.';
        }
        return false;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        findNum(candidates, target, list, subList, 0);
        return list;

    }

    private void findNum(int[] candidates, int target, List<List<Integer>> list, List<Integer> subList, int idx){
        if(idx == candidates.length || candidates[idx] > target) {
            return;
        }
        for (int i = idx; i<candidates.length && candidates[i] <= target; i++) {
            subList.add(candidates[i]);
            if (candidates[i] == target) {
                List<Integer> tmp = new ArrayList<>(subList);
                list.add(tmp);
                subList.remove(subList.size()-1);
                return;
            }
            findNum(candidates, target - candidates[i], list, subList, i);
            subList.remove(subList.size()-1);
        }

    }

    /**
     * 40. 组合总和 II
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        findNum(candidates, target, list, subList, 0);
        return list;

    }

    private void findNum2(int[] candidates, int target, List<List<Integer>> list, List<Integer> subList, int idx){
        if(idx == candidates.length || candidates[idx] > target) {
            return;
        }
        for (int i = idx; i<candidates.length && candidates[i] <= target; i++) {
            subList.add(candidates[i]);
            if (candidates[i] == target) {
                List<Integer> tmp = new ArrayList<>(subList);
                list.add(tmp);
                subList.remove(subList.size()-1);
                return;
            }
            findNum2(candidates, target - candidates[i], list, subList, i+1);
            subList.remove(subList.size()-1);
            while (i < candidates.length - 1 && candidates[i+1] == candidates[i]) {
                i++;
            }
        }

    }

    /**
     * 42. 接雨水
     */
    public int trap(int[] height) {

        int maxLeftHeightIdx = 0;
        int count = 0;
        boolean hasMax = false;
        int maxRightHeight = 0;
        for(int i=1; i<height.length; i++){
            if(!hasMax){
                if(height[i] >= height[i-1]) {
                    continue;
                }
                hasMax = true;
                maxLeftHeightIdx = i-1;
                continue;
            }
            if(height[i] > height[i-1]) {
                int posRightHeight = i;
                maxRightHeight = height[i];
                for(int j=i+1; j<height.length && maxRightHeight < height[maxLeftHeightIdx]; j++) {
                    if(height[j] > maxRightHeight) {
                        posRightHeight = j;
                        maxRightHeight = height[j];
                    }
                }
                if(maxRightHeight < height[maxLeftHeightIdx]) {
                    int k=posRightHeight-2;
                    while (height[k] < maxRightHeight) {
                        k--;
                    }
                    maxLeftHeightIdx = k;
                }
                count += count(height,maxLeftHeightIdx, posRightHeight);
                hasMax = false;
                i = posRightHeight;
            }
        }
        return count;
    }

    public String multiply(String num1, String num2) {
        int [] nums = new int[num1.length() + num2.length()];
        for (int i=num1.length()-1; i>= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length()-1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int idx = num1.length()-1 - i + num2.length()-1 - j;
                int sum = n1 * n2 + nums[idx];
                nums[idx] = sum %10;
                int remain =  sum /10;
                int rIdx = 1;
                while (remain > 0) {
                    sum = nums[idx+rIdx] + remain;
                    nums[idx+rIdx] = sum%10;
                    remain =  sum /10;
                    rIdx++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int end = nums.length-1;
        while (nums[end] == 0 && end > 0) {
            end--;
        }
        for(int i=0; i<= end; i++) {
            sb.append(nums[end-i]);
        }
        return sb.length() > 0 ? sb.toString() : "0";
    }

    private int count(int[] height, int leftIdx, int rightIdx){
        int maxHeight = Math.min(height[leftIdx], height[rightIdx]);
        int count = maxHeight * (rightIdx - leftIdx-1);
        for(int i=leftIdx+1; i<rightIdx; i++) {
            count -= height[i];
        }
        return count;
    }

    /**
     * adceb *a*b
     * 44. 通配符匹配
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for(int i=0; i<p.length() && p.charAt(i) == '*'; i++) {
            dp[0][i+1] = true;
        }
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            for (int j = 0; j < p.length(); j++) {
                char chP = p.charAt(j);
                if (chP == '*') {
                    dp[i+1][j+1] = dp[i][j] || dp[i+1][j] || dp[i][j+1];
                } else if(chP == '?' || chP == ch){
                    dp[i+1][j+1] = dp[i][j];
                    if(j > 0 && p.charAt(j-1) == '*') {
                        dp[i+1][j+1] = dp[i+1][j+1] || dp[i][j-1];
                    }
                }
            }
        }
        return dp[s.length()+1][p.length()+1];

    }

    public int jump(int[] nums) {
        if(nums.length == 1) {
            return 0;
        }
        int i=1;
        int maxLength = nums[0] + 1;
        int steps = nums[0];
        int begin = 0;
        int nextBegin;
        while(maxLength < nums.length) {
            nextBegin = begin + steps;
            steps = nextMax(nums, begin, steps);
            maxLength += steps;
            begin = nextBegin;
            i++;
        }
        return i;
    }

    private int nextMax(int[] nums, int begin, int steps){
        int nextSteps = 0;
        for(int i=begin + 1; i<=begin+steps; i++) {
            nextSteps = Math.max(nextSteps, nums[i] - (steps+begin-i));
        }
        return nextSteps;



    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean [] flag = new boolean[nums.length];
        List<List<Integer>> lists = new ArrayList<>(nums.length*2);
        List<Integer> list = new ArrayList<>(nums.length);
        find(nums, lists, list, flag);
        return lists;
    }

    private void find(int[] nums, List<List<Integer>> lists, List<Integer> list, boolean [] flag){
        if(list.size() == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        Integer preNum = null;
        for (int i=0; i<nums.length; i++) {
            if(!flag[i]){
                if(preNum == null || preNum != nums[i]) {
                    list.add(nums[i]);
                    preNum = nums[i];
                    flag[i] = true;
                    find(nums, lists, list, flag);
                    list.remove(list.size()-1);
                    flag[i] = false;
                }
            }
        }
    }

    public void rotate(int[][] matrix) {

        int endRow = matrix.length/2;
        for (int i = 0; i < endRow; i++) {
            int endCol = matrix[0].length-1-i;
            int thisEndRow = matrix.length-1-i;
            for (int j = i; j<endCol; j++) {
                int tmp = matrix[i][j];
                int diffIdx = endCol - j;
                matrix[i][j] = matrix[i+diffIdx][i];
                matrix[i+diffIdx][i] = matrix[thisEndRow][i+diffIdx];
                matrix[thisEndRow][i+diffIdx] = matrix[thisEndRow-diffIdx][endCol];
                matrix[thisEndRow-diffIdx][endCol] = tmp;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        boolean [] colFlag = new boolean[n];
        boolean [] seqFlag = new boolean[2*n-1];
        boolean [] seq2Flag = new boolean[2*n-1];
        List<List<String>> lists = new ArrayList<>();

        List<String> list = new ArrayList<>(n);
        fill(colFlag, seqFlag, seq2Flag, lists, list, 0, n);
        return lists;
    }

    private void fill(
            boolean [] colFlag,
            boolean [] seqFlag,
            boolean [] seq2Flag,
                      List<List<String>> lists, List<String> list, int row, int n){

        StringBuilder stringBuilder = new StringBuilder(n);
        int pos;
        for(int i=0; i<n; i++) {
            if(colFlag[i] || seqFlag[row+i] || seq2Flag[n-1 - row + i]) {
                stringBuilder.append(".");
            } else {
                pos = i;
                stringBuilder.append("Q");
                while (stringBuilder.length() < n) {
                    stringBuilder.append(".");
                }
                list.add(stringBuilder.toString());
                if(n -1 == row) {
                    lists.add(new ArrayList<>(list));
                } else {
                    colFlag[i] = true;
                    seqFlag[row+i] = true;
                    seq2Flag[n-1 - row + i] = true;
                    fill(colFlag, seqFlag, seq2Flag, lists, list, row+1, n);
                    colFlag[i] = false;
                    seqFlag[row+i] = false;
                    seq2Flag[n-1 - row + i] = false;
                }
                stringBuilder.delete(pos, stringBuilder.length());
                stringBuilder.append(".");
                list.remove(list.size()-1);
            }
        }
    }

    /**
     * 60. 排列序列
     */
    public String getPermutation(int n, int k) {
        int sum = 1;
        for(int i=1; i<=n; i++) {
            sum *=i;
        }
        int [] bit = new int[n];
        for(int i=n; i>=1; i--) {
            int num = (k-1)*i/sum+1;
            k = k - (num-1) * sum/i;
            sum = sum / i;
            bit[n-i] = num;
        }
        StringBuilder builder = new StringBuilder(n);
        boolean [] flag = new boolean[n];
        for(int i=0; i<n;i++) {
            int num = bit[i];
            int count = 0;
            for(int j=0; j<flag.length; j++) {
                if(!flag[j]) {
                    count++;
                    if(count == num) {
                        builder.append(j+1);
                        flag[j] = true;
                    }
                }
            }
        }
        return builder.toString();
    }

    public String addBinary(String a, String b) {
        int r = 0;
        int len = Math.max(a.length(), b.length());
        StringBuilder sb = new StringBuilder(len+1);
        for(int i=0; i< len; i++) {
           if(a.length() > i) {
               r += a.charAt(a.length()-1-i) - '0';
           }
           if(b.length() > i) {
               r+= b.charAt(b.length()-1-i) - '0';
           }
            if(r >=2) {
                sb.append(r-2);
                r = 1;
            } else {
                sb.append( r);
                r = 0;
            }
        }
        if(r > 0) {
            sb.append(r);
        }
        return sb.reverse().toString();
    }

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> list = new ArrayList<>();
        int width = 0;
        int preIdx = 0;
        StringBuilder buidler = new StringBuilder();
        for(int i=0; i<words.length; i++) {
            if(width + words[i].length() > maxWidth) {
                String str = getLine(words, preIdx, i-1, maxWidth, buidler);
                list.add(str);
                buidler.delete(0, buidler.length() -1);
                preIdx = i;
                width = words[i].length()+1;
            } else {
                width += words[i].length()+1;
            }
        }
        String str = getLine(words, preIdx, words.length-1, maxWidth, buidler);
        list.add(str);
        return list;
    }

    private String getLine(String[] words, int start, int end, int maxWidth, StringBuilder buidler){

        if(end == words.length-1) {
            int width = 0;
            for(int i=start; i<= end; i++) {
                buidler.append(words[i]);
                width += words[i].length();
                if(i < end) {
                    buidler.append(' ');
                    width ++;
                }
            }
            while(width< maxWidth) {
                buidler.append(' ');
                width ++;
            }
            return buidler.toString();
        }
        int len = 0;
        for(int i=start; i<= end; i++) {
            len += words[i].length();
        }

        int remainder = maxWidth - len;
        int perBlank = remainder/(start-end);
        int remainderBlank = remainder%(start-end);

        for(int i=start; i<= end; i++) {
            buidler.append(words[i]);
            if(i < end) {
                for(int j=0; j<perBlank; j++) {
                    buidler.append(' ');
                }
                if(remainderBlank > 0) {
                    buidler.append(' ');
                    remainderBlank--;
                }
            }
        }
        return buidler.toString();

    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        int len = s1.length() + s2.length();
        if(len != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for (int i=1; i<=s1.length(); i++) {
            dp[i][0] =  dp[i-1][0] && s1.charAt(i) == s3.charAt(i-1);
        }
        for (int i=1; i<=s2.length(); i++) {
            dp[0][i] = dp[0][i-1] && s2.charAt(i) == s3.charAt(i-1);
        }

        for(int i=1; i<=s1.length(); i++) {
            for (int j=1; j<=s2.length(); j++) {
                char chS3 = s3.charAt(i+j - 1);
                dp[i][j] = (dp[i][j-1] && chS3 == s2.charAt(j-1))
                        || (dp[i-1][j] && chS3 == s1.charAt(i-1));
            }
        }
        return dp[s1.length()][s2.length()];
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            idxMap.put(inorder[i], i);
        }
        return buildRoot(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, idxMap);
    }

    private TreeNode buildRoot(int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRgith, Map<Integer, Integer> idxMap){
        if (iLeft > iRgith) {
            return null;
        }
        int rootVal = preorder[pLeft];
        int iRootIdx = idxMap.get(rootVal);
        int pLenftNum = iRootIdx - iLeft;
        TreeNode treeNode = new TreeNode(rootVal);
        treeNode.left = buildRoot(preorder, pLeft+1, pLeft + pLenftNum, inorder, iLeft, iRootIdx-1, idxMap);
        treeNode.right = buildRoot(preorder, pLeft + pLenftNum+1, pRight, inorder, iRootIdx+1, iRgith, idxMap);
        return treeNode;
    }

    // ["This", "is", "an", "example", "of", "text", "justification."]
    //16
    public static void main(String[] args) throws ParseException {
        Solution solution = new Solution();

        solution.rotate(new int[][]{{1,2,3,4}, {1,2,3,4}, {1,2,3,4}, {1,2,3,4}});
        System.out.println("num");
        Object buid = solution.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        Map<String, Solution> map = new HashMap<>();
        String str = "";
        str.toCharArray();
        System.out.println(buid);

        StringBuilder sb = new StringBuilder();
        sb.append(20);
        sb.append(0);
        System.out.println(sb.toString());

        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01").getTime());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-02").getTime()/1000);
    }
}
