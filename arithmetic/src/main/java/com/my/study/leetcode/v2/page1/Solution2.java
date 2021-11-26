package com.my.study.leetcode.v2.page1;

import java.util.*;

/**
 * @author : Pan Yingting
 * @date : 2021/7/25 10:27 上午
 */
class Solution2 {
    public int trap(int[] height) {
        if(height.length <= 2) {
            return 0;
        }
        int count = 0;
        int secondsHeight = secondsHeight(height);
        for(int i=1; i<secondsHeight; i++)
        {
            boolean start = false;
            int thisCount = 0;
            for(int left = 0; left < height.length; left++) {
                if(!start) {
                    if(height[left] >= i) {
                        start = true;
                    }
                } else {
                    if(height[left] >= i) {
                        count += thisCount ;
                        thisCount = 0;
                    } else{
                        thisCount ++;
                    }
                }
            }
        }
        return count;
    }

    private int secondsHeight(int[] height){
        int height1 = height[0];
        int height2 = height[1];

        for(int i=2; i<height.length; i++) {
            if(height[i] > height1 || height[i] > height2) {
                if(height1 > height2) {
                    height2 = height[i];
                } else {
                    height1 = height[i];
                }
            }
        }
        return Math.min(height1, height2);
    }

    public String multiply(String num1, String num2) {

        StringBuilder sbSum = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        for(int i=num1.length()-1; i>=0; i--) {
            if (sb.length() > 0) {
                sb.delete(0, sb.length());
            }
            int j=num2.length()-1;
            for(;j >= 0; j--) {
                int number1 = num1.charAt(i) - '0';
                int number2 = num2.charAt(j) - '0';
                int sum = number1 * number2 + flag;
                sb.append(sum%10);
                flag = sum/10;
            }
            if(flag > 0) {
                sb.append(flag);
                flag = 0;
            }

            if(sbSum.length() == 0) {
                String subNum = sb.reverse().toString();
                sbSum.append(subNum);
            } else{
                sb.reverse();
                for (int n =0; n <num1.length()-i-1; n++) {
                    sb.append(0);
                }
                String subNum =sb.toString();
                int subCount = 0;
                int thisNum;
                sb.delete(0, sb.length());
                int k = 0;
                for(; k< sbSum.length() && k < subNum.length(); k++) {
                    thisNum = sbSum.charAt(sbSum.length()-k-1) + subNum.charAt(subNum.length()-k-1) - 2*'0' + subCount;
                    if(thisNum >= 10) {
                        sb.append(thisNum-10);
                        subCount = 1;
                    } else{
                        sb.append(thisNum);
                        subCount = 0;
                    }
                }
                while(k < sbSum.length()) {
                    thisNum =sbSum.charAt(sbSum.length()-k-1)  + subCount - '0';
                    sb.append( thisNum %10);
                    subCount = thisNum/10;
                    k++;
                }
                while(k < subNum.length()) {
                    thisNum = subNum.charAt(subNum.length()-k-1) + subCount - '0';
                    sb.append( thisNum %10);
                    subCount = thisNum/10;
                    k++;
                }
                if(subCount == 1) {
                    sb.append(subCount);
                }
                sbSum = new StringBuilder(sb.reverse().toString());
            }
        }

        String myNum =  sbSum.toString();
        String ret = "";
        boolean have = false;
        for (int i = 0; i < myNum.length(); i++) {
            if (myNum.charAt(i) != '0') {
                have = true;
            }
            if(have) {
                ret += myNum.charAt(i);
            }
        }
        return  ret == "" ? "0" : ret;
    }

    public List<List<String>> solveNQueens(int n) {
        int[] queen = new int[n];
        boolean [] colFlag = new boolean[n];
        boolean [] leftFlag = new boolean[2*n-1];
        boolean [] rightFlag = new boolean[2*n-1];
        List<List<String>> lists = new ArrayList<>();
        return lists;
    }

    public int totalNQueens(int n) {
        int[] queen = new int[n];
        boolean [] colFlag = new boolean[n];
        boolean [] leftFlag = new boolean[2*n-1];
        boolean [] rightFlag = new boolean[2*n-1];
        List<List<String>> lists = new ArrayList<>();
        return lists.size();
    }

    private void backtrack(boolean [] colFlag, boolean [] leftFlag, boolean [] rightFlag, int rowIdx, int n, int[] queen, List<List<String>> lists){
        if(rowIdx == n) {
            printQueen(queen, lists, n);
            return;
        }
        for(int i=0; i<n; i++) {
            if(colFlag[i] || leftFlag[i] || rightFlag[i]) {
                continue;
            }
            colFlag[i] = true; leftFlag[i] = true; rightFlag[i] = true;
            queen[rowIdx] = i;
            backtrack(colFlag, leftFlag, rightFlag, rowIdx+1, n, queen, lists);
            colFlag[i] = false; leftFlag[i] = false; rightFlag[i] = false;

        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        addNum(0, matrix.length-1, 0,  matrix[0].length-1,  matrix, list);
        return list;
    }

    private void addNum(int topIdx, int buttomIdx, int leftIdx, int rightIdx, int[][]matrix,  List<Integer> list){
        if(topIdx > buttomIdx || leftIdx > rightIdx) {
            return;
        }
        for(int i=leftIdx; i<= rightIdx; i++) {
            list.add(matrix[topIdx][i]);
        }
        for(int i=topIdx+1; i<= buttomIdx; i++) {
            list.add(matrix[i][rightIdx]);
        }
        if(buttomIdx != topIdx) {
            for(int i=rightIdx-1; i>=leftIdx; i--) {
                list.add(matrix[buttomIdx][i]);
            }
        }

        if(leftIdx != rightIdx) {
            for(int i=buttomIdx-1; i>topIdx; i--) {
                list.add(matrix[i][leftIdx]);
            }
        }
        addNum(topIdx+1, buttomIdx-1, leftIdx+1, rightIdx-1, matrix,  list);
    }

    private void printQueen(int[] queen, List<List<String>> lists, int n){
        List<String> list = new ArrayList<>();
        lists.add(list);
        for (int i=0; i<n; i++) {
            char[] arr = new char[n];
            Arrays.fill(arr, '.');
            arr[queen[i]] = 'Q';
            list.add(new String(arr));
        }
    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length <=1) return intervals;
        int[] firstNum = new int[intervals.length];
        Map<Integer, Integer> map = new HashMap<>(intervals.length+2);
        for(int i=0; i<intervals.length; i++) {
            firstNum[i] = intervals[i][0];
            map.put(intervals[i][0], intervals[i][1]);
        }
        List<int[]> list = new ArrayList<>(intervals.length);
        Arrays.sort(firstNum);
        int[] preArr = null;
        for(int num: firstNum) {
            if(preArr == null) {
                preArr = new int[2];
                preArr[0] = num;
                preArr[1] = map.get(num);
            } else {
                if(num <= preArr[1]) {
                    preArr[1] = map.get(num);
                } else {
                    list.add(preArr);
                    preArr = null;
                }
            }
        }
        return null;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> arrList = new ArrayList<>(intervals.length+1);
        int[] preArr;
        for(int i=0; i<intervals.length; i++) {
            int[] arr1 = intervals[i];
            if(newInterval != null) {
                if(arr1[0] <= newInterval[0] && arr1[1] >= newInterval[0] || arr1[0] <= newInterval[1] && arr1[1] >= newInterval[1]
                || arr1[0] < newInterval[0] && arr1[1] > newInterval[1] || arr1[0] > newInterval[0] && arr1[1] < newInterval[1]) {
                    arr1[0] = Math.min(arr1[0], newInterval[0]);
                    arr1[1] = Math.max(arr1[1], newInterval[1]);
                    newInterval = null;
                } else {
                    if (newInterval[1] < arr1[0]) {
                        arrList.add(newInterval);
                        newInterval = null;
                    }
                }
                arrList.add(arr1);
            } else {
                preArr = arrList.get(arrList.size()-1);
                if(preArr[1] >= arr1[0]) {
                    preArr[1] = Math.max(preArr[1], arr1[1]);
                } else {
                    arrList.add(arr1);
                }
            }
        }
        if(newInterval != null) {
            arrList.add(newInterval);
        }
        int [][] ret = new int[arrList.size()][2];
        for (int i=0; i<arrList.size(); i++) {
            ret[i][0] = arrList.get(i)[0];
            ret[i][1] = arrList.get(i)[1];
        }
        return ret;

    }

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        int idx = 1;
        String word;
        int pos = -1;
        while (pos < path.length()-1){
            idx = pos+1;
            pos = path.indexOf('/', idx);
            if(pos == -1) {
                word = path.substring(idx);
                pos = path.length()-1;
            } else {
                word = path.substring(idx, pos);
            }
            if ("".equals(word) || ".".equals(word)){
                continue;
            }
            if ("..".equals(word)){
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(word);
        }

        String newPath = "";
        while(!stack.isEmpty()) {
            newPath = "/" + stack.pop() + newPath;
        }
        return newPath.isEmpty()? "/" : newPath;
    }

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int[][] heights = new int[matrix.length][matrix[0].length];
        int max = 0;

        Stack<Integer> stack = new Stack<Integer>();
        int[] heightLeft = new int[matrix[0].length];
        int[] heightRight = new int[matrix[0].length];

        for(int i=0; i<matrix.length; i++) {
            stack.clear();
            Arrays.fill(heightLeft, 0);
            Arrays.fill(heightRight, 0);
            int begin = -1;
            for(int j=0; j<matrix[0].length; j++) {
                if(matrix[i][j] != '0') {
                    heights[i][j] = i==0 ? 1 :  heights[i-1][j] + 1;
                    while(!stack.isEmpty() && heights[i][j] <= heights[i][stack.peek()]) {
                        heightRight[stack.peek()] = j;
                        stack.pop();
                    }
                    heightLeft[j] = stack.isEmpty() ? begin : stack.peek();
                    stack.push(j);
                }
                if(matrix[i][j] == '0' || ( matrix[i][j] == '1' && j == matrix[0].length-1)) {
                    if( matrix[i][j] == '1' && j == matrix[0].length-1) {
                        j = matrix[0].length;
                    }
                    for(int k=begin+1; k<j; k++) {
                        if(heightRight[k] == 0) {
                            heightRight[k] = j;
                        }
                        max = Math.max(max, (heightRight[k] - heightLeft[k] -1) * heights[i][k]);
                    }
                    begin = j;
                    stack.clear();
                }
            }
        }
        return max;
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;

        for(int i=1; i<s.length(); i++) {
            if(dp[i-1] == 0) {
                return 0;
            }

            char ch = s.charAt(i);
            char ch0 = s.charAt(i-1);
            if(ch0 == '0') {
                if(ch == '0') {
                    return 0;
                }
                dp[i] = dp[i-1];
            }
            else if(ch0 == '1') {

                if(ch == '0' ) {
                    dp[i] = i > 1 ? dp[i-2] : 1;
                } else {
                    dp[i] += dp[i-1] + (i > 1 ? dp[i-2] : 1);
                }
            }
            else if(ch0 == '2') {

                if(ch != '0' && ch <= '6' ) {
                    dp[i] += dp[i-1] + (i > 1 ? dp[i-2] : 1);
                } else {
                    if(ch == '0') {
                        dp[i] = i > 1 ? dp[i-2] : 1;
                    } else {
                        dp[i] = dp[i-1];
                    }
                }
            }
            else {
                if(ch == '0') {
                    return 0;
                }
                dp[i] = dp[i-1];
            }
        }
        return dp[dp.length-1];
    }

    public int strStr(String haystack, String needle) {
        if(haystack == needle) {
            return 0;
        }
        if(haystack == null || needle == null) {
            return -1;
        }
        if (haystack.length() == 0 || needle.length() == 0) {
            return needle.length() == 0 ? 0 : -1;
        }

        int needleIdx = 0;
        int[] next = next(needle);

        for (int i=0; i<haystack.length(); i++) {
            while (needleIdx > 0 && needle.charAt(needleIdx) != haystack.charAt(i)) {
                needleIdx = next[needleIdx-1] + 1;
            }
            if (i + needle.length() - needleIdx - 1 >= haystack.length()) {
                return -1;
            }
            if (needle.charAt(needleIdx) == haystack.charAt(i)) {
                needleIdx++;
            }
            if (needleIdx == needle.length()) {
                return i - needleIdx+1;
            }

        }
        return -1;
    }

    private int[] next(String needle){
        char[] chArr = needle.toCharArray();
        int[] next = new int[chArr.length];
        next[0] = -1;
        int idx = 1;
        int findIdx = -1;
        while (idx < chArr.length) {
            while (findIdx != -1 && chArr[findIdx+1] != chArr[idx]) {
                findIdx = next[findIdx];
            }
            if(chArr[findIdx+1] == chArr[idx]) {
                findIdx++;
            }
            next[idx++] = findIdx;
        }
        return next;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.addFirst(root);

        LinkedList<List<Integer>> list = new LinkedList<>();

        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            List<Integer> valList = new ArrayList<>(size);
            list.addFirst(valList);
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = linkedList.pollLast();
                valList.add(treeNode.val);
                if (treeNode.left != null) {
                    linkedList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    linkedList.add(treeNode.right);
                }
            }
        }
        return list;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int num = num(head);
        return sortedListToBST(head, num);
    }

    private TreeNode sortedListToBST(ListNode head, int num) {
        if (num <= 0) {
            return null;
        }
        if (num == 1) {
            return new TreeNode(head.val);
        }
        int midNum = (num+1)/2;
        ListNode pos = head;
        for (int i = 2; i < midNum; i++) {
            pos = pos.next;
        }
        TreeNode treeNode = new TreeNode( midNum == 1 ? pos.val : pos.next.val);
        treeNode.left = sortedListToBST(head, midNum-1);
        treeNode.right = sortedListToBST( midNum == 1 ? pos.next : pos.next.next, num - midNum);
        return treeNode;
    }

    private int num(ListNode head){
        int num = 0;
        while (head != null) {
            num ++;
            head = head.next;
        }
        return num;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[] arr = {-10,-3,0,5,9};
        ListNode listNode = createNode(arr);
        Object str = solution2.sortedListToBST(listNode);
        System.out.println(123*456);
        System.out.println(str);
    }

    private static ListNode createNode(int[] arr){
        ListNode node = new ListNode(1);
        ListNode pos = node;
        for (int i : arr) {
            pos.next = new ListNode(i);
            pos = pos.next;
        }
        return node.next;
    }
}