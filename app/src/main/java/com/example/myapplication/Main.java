package com.example.myapplication;

import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        Main main = new Main();
        main.maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'},{'1', '1', '1', '1', '1'},{'1', '0', '0', '1', '0'}});

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public int maxProfit(int[] prices) {
        int res = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++){
            if (prices[i] <= min){
                min = prices[i];
            }else {
                int tem = prices[i] - min;
                res = Math.max(tem,res);
            }
        }
        return res;
    }

    public int maxSubArray(int[] nums) {
        int b = nums[0];
        int max = b;
        for (int i = 1; i < nums.length; i++){
            b = Math.max(nums[i] + b, nums[i]);
            max = Math.max(max, b);
        }
        return max;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        int length = s.length();
        int max = 1;
        for (int i = 1; i < length; i++){
            char c = s.charAt(i);
            if (map.containsKey(c)){
                if (map.get(c) >= left){
                    left = map.get(c) + 1;
                }
            }
            map.put(c, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int pos = 0;
        while (true){
            pos = fun(nums, left, right);
            if (pos == k - 1){
                return nums[pos];
            }else if (pos < k - 1){
                left = pos + 1;
            }else {
                right = pos - 1;
            }
        }
    }

    public int fun(int[] nums, int left, int right){
        if (left == right){
            return left;
        }
        int tem = left;
        int target = nums[tem];
        while (left < right){
            while (left < right && nums[right] <= target){
                right--;
            }
            while (left < right && nums[left] >= target){
                left++;
            }
            swap(nums, left, right);
        }
        swap(nums, tem, left);
        return left;
    }

    public void swap(int[] nums, int left, int right){
        int tem = nums[left];
        nums[left] = nums[right];
        nums[right] = tem;
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0 ; i < nums.length; i++){
            dp[i] = 1;
        }
        for (int i = 1; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }

    public int trap(int[] height) {
        int sum = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        for (int i = 1; i < height.length; i++){
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--){
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }
        for (int i = 0; i < height.length; i++){
            int tem = Math.min(left[i], right[i]) - height[i];
            if (tem > 0){
                sum += tem;
            }
        }
        return sum;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        boolean b = true;
        int count = 1;
        while (list.size() != 0){
            int tem = count;
            count = 0;
            List<Integer> integerList = new LinkedList<>();
            if (b){
                while (tem > 0){
                    TreeNode node = list.removeFirst();
                    if (node.right != null){
                        list.addLast(node.right);
                        count++;
                    }
                    if (node.left != null){
                        list.addLast(node.left);
                        count++;
                    }
                    integerList.add(node.val);
                    tem--;
                }
            } else {
                while (tem > 0){
                    TreeNode node = list.removeLast();
                    if (node.left != null){
                        list.addFirst(node.left);
                        count++;
                    }
                    if (node.right != null){
                        list.addFirst(node.right);
                        count++;
                    }
                    integerList.add(node.val);
                    tem--;
                }
            }
            b = !b;
            res.add(integerList);
        }
        return res;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    public void dfs(char[][] grid, int i, int j){
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        List<Integer> res = new LinkedList<>();
        while (left <= right || top <= bottom){
            if (top <= bottom){
                for (int i = left; i <= right; i++){
                    res.add(matrix[top][i]);
                }
                top++;
            }
            if (left <= right){
                for (int i = top; i <= bottom; i++){
                    res.add(matrix[i][right]);
                }
                right--;
            }
            if (top <= bottom){
                for (int i = right; i >= left; i--){
                    res.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right){
                for (int i = bottom; i >= top; i--){
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                if (nums[left] + nums[right] == -nums[i]){
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    while (nums[++left] == nums[left - 1]){
                        if (left >= right) break;
                    }
                    while (nums[--right] == nums[right + 1]){
                        if (left >= right) break;
                    }
                }else if (nums[left] + nums[right] < -nums[i]){
                    while (nums[++left] == nums[left - 1]){
                        if (left >= right) break;
                    }
                }else {
                    while (nums[--right] == nums[right + 1]){
                        if (left >= right) break;
                    }
                }
            }
        }
        return res;
    }

    public String longestPalindrome(String s) {
        String res = s.substring(0,1);
        int length = s.length();
        int max = 1;
        for (int i = 0; i < s.length(); i++){
            int left = i - 1;
            int right = i + 1;
            int count = 1;
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)){
                count += 2;
                if (count > max){
                    max = count;
                    res = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
            if (i != length - 1 && s.charAt(i) == s.charAt(i + 1)){
                count = 2;
                if (count > max){
                    max = count;
                    res = s.substring(i, i + 2);
                }
                left = i - 1;
                right = i + 2;
                while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)){
                    count += 2;
                    if (count > max){
                        max = count;
                        res = s.substring(left, right + 1);
                    }
                    left--;
                    right++;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> permute(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new LinkedList<>();
        fun(res, list, nums, dp, 0);
        return res;
    }

    public void fun(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] dp, int deep){
        if (deep == nums.length){
            res.add(copyArray(list));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (!dp[i]){
                list.add(nums[i]);
                dp[i] = true;
                fun(res, list, nums, dp, deep + 1);
                list.remove(list.size() - 1);
                dp[i] = false;
            }
        }
    }

    public List<Integer> copyArray(List<Integer> list){
        int size = list.size();
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < size; i++){
            res.add(list.get(i));
        }
        return res;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return null;
        }
        ListNode listNode = lists[0];
        for (int i = 1; i < lists.length; i++){
            listNode = mergeTwoLists(listNode, lists[i]);
        }
        return listNode;
    }

    public ListNode mergeTwoLists(ListNode listNode1, ListNode listNode2){
        ListNode res = new ListNode();
        ListNode tem = res;
        while (listNode1 != null && listNode2 != null){
            if (listNode1.val < listNode2.val){
                tem.next = listNode1;
                tem = listNode1;
                listNode1 = listNode1.next;
            }else {
                tem.next = listNode2;
                tem = listNode2;
                listNode2 = listNode2.next;
            }
        }
        if (listNode1 == null){
            tem.next = listNode2;
        }else {
            tem.next = listNode1;
        }
        return res.next;
    }

    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        int size = 1;
        list.addLast(root);
        while (size > 0){
            int tem = size;
            size = 0;
            for(int i = 0; i < tem; i++){
                TreeNode node = list.removeFirst();
                if (node.left != null){
                    list.addLast(node.left);
                    size++;
                }
                if (node.right != null){
                    list.addLast(node.right);
                    size++;
                }
                if (i == tem - 1){
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    public boolean isValid(String s) {
        int length = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++){
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else if (stack.size() != 0 && (c == ')' && stack.peek() == '(' || c == ']' && stack.peek() == '[' || c == '}' && stack.peek() == '{')){
                stack.pop();
            }else {
                return false;
            }
        }
        return stack.size() == 0;
    }

    public boolean hasCycle(ListNode head) {
        ListNode h1 = head;
        ListNode h2 = head;
        while (true){
            if (h1 == null) break;
            h1 = h1.next;
            if (h2 == null) break;
            h2 = h2.next;
            if (h2 == null) break;
            h2 = h2.next;
            if (h1 == h2) return true;
        }
        return false;
    }

    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        int length = 0;
        ListNode tem = head;
        while (tem != null){
            length++;
            tem = tem.next;
        }
        boolean b = length % 2 == 1;
        int offset = b ? length / 2 + 1 : length / 2;
        tem = head;
        for (int i = 0; i < offset; i++){
            tem = tem.next;
        }
        while (tem != null){
            stack.push(tem);
            tem = tem.next;
        }
        tem = head;
        ListNode listNode = null;
        while (stack.size() != 0){
            listNode = stack.pop();
            listNode.next = tem.next;
            tem.next = listNode;
            tem = tem.next.next;
        }
        if (b){
            tem.next = null;
        }else {
            if (listNode != null){
                listNode.next = null;
            }
        }
    }

    public String addStrings(String num1, String num2) {
        int count = 0;
        int length1 = num1.length();
        int length2 = num2.length();
        int length = Math.max(length1, length2);
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++){
            int n1 = 0;
            int n2 = 0;
            if (i < length1){
                n1 = num1.charAt(length1 - i - 1) - '0';
            }
            if (i < length2){
                n2 = num2.charAt(length2 - i - 1) - '0';
            }
            stack.push((char) ((n1 + n2 + count) % 10 + '0'));
            count = (n1 + n2 + count) / 10;

        }
        StringBuilder stringBuilder = new StringBuilder();
        while (stack.size() != 0){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }

    public int sumNumbers(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        return fun(root, stringBuilder);
    }

    public int fun(TreeNode node, StringBuilder stringBuilder){
        stringBuilder.append(node.val);
        if (node.left == null && node.right == null){
            int res = Integer.parseInt(stringBuilder.toString());
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return res;
        }
        int n1 = 0;
        int n2 = 0;
        if (node.left != null){
            n1 = fun(node.left, stringBuilder);
        }
        if (node.right != null){
            n2 = fun(node.right, stringBuilder);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return n1 + n2;
    }

    public int maximalSquare(char[][] matrix) {
        int length1 = matrix.length;
        if (length1 == 0){
            return 0;
        }
        int length2 = matrix[0].length + 1;
        int[] dp = new int[length2];
        int res = 0;
        for (char[] chars : matrix) {
            //代表三个正方形的左上角那个值
            int tem1 = 0;
            int last = 0;
            for (int j = 0; j < length2; j++) {
                int tem = dp[j];
                if (chars[j] == '1') {
                    dp[j] = Math.min(dp[j], Math.min(tem1, last)) + 1;
                } else {
                    dp[j] = 0;
                }

                res = Math.max(dp[j], res);
                last = dp[j];
                tem1 = tem;
            }
        }
        return res * res;
    }

    public void nextPermutation(int[] nums) {
        int length = nums.length;
        int i = length - 2;
        while (i >= 0 && nums[i] > nums[i + 1]){
            i--;
        }
        if (i < 0){
            fun(nums, 0);
        }else {
            int j = length - 1;
            while (nums[j] < nums[i]){
                j--;
            }
            int tem = nums[i];
            nums[i] = nums[j];
            nums[j] = tem;
            fun(nums, i);
        }
    }

    public void fun(int[] nums, int i){
        int left = i;
        int right = nums.length - 1;
        while (left < right){
            int tem = nums[left];
            nums[left] = nums[right];
            nums[right] = tem;
            left++;
            right--;
        }
    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                int tem = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tem;
            }
        }
        int i = 0;
        for (; i < nums.length; i++){
            if (nums[i] - 1 != i){
                break;
            }
        }
        return i + 1;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        return fun(preorder, inorder, 0, preorder.length - 1, 0, preorder.length - 1);
    }

    public TreeNode fun(int[] preorder, int[] inorder, int left1, int right1, int left2, int right2){
        if (left2 > right2) return null;
        int p1 = preorder[left1];
        int p = left2;
        while (p <= right2 && inorder[p] != p1){
            p++;
        }
        TreeNode treeNode = new TreeNode();
        treeNode.val = p1;
        treeNode.left = fun(preorder, inorder, left1 + 1, left1 + p - left2, left2, p - 1);
        treeNode.right = fun(preorder, inorder, left1 + 1 + p - left2, right1, p + 1, right2);
        return treeNode;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && right > intervals[i + 1][0]){
                right = intervals[i + 1][1];
                i++;
            }
            list.addLast(new int[]{left, right});
        }
        return list.toArray(new int[][]{});
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new LinkedList<>();
        fun(list, new StringBuilder(), 2 * n, n, 0);
        return list;
    }

    public void fun(List<String> list, StringBuilder stringBuilder, int n, int c1, int c2){
        if (n == 0){
            list.add(stringBuilder.toString());
            return;
        }
        //c1为左括号剩余量，c2为右括号剩余量
        if (c2 == 0){
            stringBuilder.append('(');
            fun(list, stringBuilder, n - 1, c1 - 1, c2 + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }else if (c1 == 0){
            stringBuilder.append(')');
            fun(list, stringBuilder, n - 1, c1, c2 - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }else {
            stringBuilder.append('(');
            fun(list, stringBuilder, n - 1, c1 - 1, c2 + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(')');
            fun(list, stringBuilder, n - 1, c1, c2 - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode headDump = new ListNode();
        headDump.next = head;
        ListNode pre = headDump;
        ListNode tail = head;

        //找到前后需要倒置的子链表的前后两个节点，然后倒置子链表，再把子链表接入前后分段链表中
        for (int i = 0; i < left - 1; i++){
            pre = pre.next;
        }
        ListNode leftNode = pre.next;
        for (int i = 0; i < right; i++){
            tail = tail.next;
        }
        pre.next = reverseListNode(leftNode, right - left);
        leftNode.next = tail;
        return headDump.next;
    }

    public ListNode reverseListNode(ListNode head, int k){
        ListNode res = head;
        head = head.next;
        for (int i = 0; i < k; i++){
            ListNode next = head.next;
            head.next = res;
            res = head;
            head = next;
        }
        return res;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode headDump = new ListNode();
        headDump.next = head;
        ListNode pre = headDump;
        ListNode left = pre.next;
        ListNode tail = head;
        while (left != null){
            int n = 0;
            for (int i = 0; i < k; i++, n++){
                if (tail == null) break;
                tail = tail.next;
            }
            if (n < k){
                break;
            }
            pre.next = reverseListNode(left, n - 1);
            left.next = tail;
            pre = left;
            left = pre.next;
        }
        return headDump.next;
    }

    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left) || root.val <= pre){
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }

    List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return null;
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }

}
