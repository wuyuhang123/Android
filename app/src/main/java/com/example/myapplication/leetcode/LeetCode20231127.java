package com.example.myapplication.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

/**
 * @Author wuyuhang
 * @Date 2023/11/27 18:25
 * @Describe
 */
public class LeetCode20231127 {

    public static void main(String[] args) {
        new LeetCode20231127().search(new int[]{4,5,6,7,0,1,2}, 0);
    }

    public void fun() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("3");
                emitter.onError(new Exception());
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Throwable {
                return Observable.just("1", s);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Throwable {

            }
        }, throwable -> {

        });
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(target - nums[i], i);
            } else {
                int[] result = new int[2];
                result[0] = i;
                result[1] = map.get(nums[i]);
                return result;
            }
        }
        return new int[0];
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

//      迭代法
//        ListNode pre = null;
//        ListNode cur = head;
//        while (cur != null) {
//            ListNode tem = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = tem;
//        }
//        return pre;

        //递归法
        return reverseListRecursion(null, head);
    }

    public ListNode reverseListRecursion(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode node = reverseListRecursion(cur, cur.next);
        cur.next = pre;
        return node;
    }

    /**
     * 买卖股票的最佳时机1
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        int cur = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < cur) {
                cur = prices[i];
            } else if (prices[i] > cur) {
                result = Math.max(prices[i] - cur, result);
            }
        }
        return result;
    }

    /**
     * 买卖股票的最佳时机2
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        // 贪心算法，上涨就买，开始下跌则卖出，找到下一个上涨起点再买入，从图像上看就是取每一个上升的小段然后求和
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit += tmp;
        }
        return profit;
    }

    /**
     * 买卖股票的最佳时机3
     * 动态规划
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];
        dp[0][3] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]); //最晚第i天第一次买入时的成本取负数
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i][0]); //最晚第i天第一次卖出时的收益
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);//最晚第i天第二次买入时的成本取负数
            dp[i][3] = Math.max(dp[i - 1][3], prices[i] + dp[i - 1][2]);//最晚第i天第二次卖出时的收益
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][3]);
    }


    /**
     * K个一组反转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode result = new ListNode();
        ListNode pre = result;
        pre.next = head;
        ListNode cur = head;
        ListNode tail = head;
        while (true) {
            boolean b = true;
            for (int i = 0; i < k; i++) {
                if (tail == null) {
                    b = false;
                    break;
                }
                tail = tail.next;
            }
            if (b) {
                ListNode node = reversKGroupInner(pre, cur, k);
                pre.next = node;
                cur.next = tail;
                pre = cur;
                cur = tail;
            } else {
                return result.next;
            }
        }
    }

    public ListNode reversKGroupInner(ListNode pre, ListNode cur, int k) {
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length -1;
        List<Integer> result = new LinkedList<>();
        while (left <= right && top <= bottom) {
            if (top <= bottom && left <= right) {
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                top++;
            }
            if (top <= bottom && left <= right) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                right--;
            }
            if (top <= bottom && left <= right) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (top <= bottom && left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }


    /**
     * 二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int[] right = new int[height.length];
        right[height.length - 1] = 0;
        int maxRight = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight = Math.max(height[i + 1], maxRight);
            right[i] = maxRight;
        }
        int result = 0;
        int maxLeft = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            if (height[i] < maxLeft && height[i] < right[i]) {
                result += (Math.min(maxLeft, right[i]) - height[i]);
            }
            maxLeft = Math.max(maxLeft, height[i]);
        }
        return result;
    }

    /**
     * 相交链表
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        //不需要两个额外的布尔变量来控制是不是需要停止，因为双指针交叉走的话一定会同时为null或者相交
        while (p != q) {
            if (p == null) {
                p = headB;
            } else {
                p = p.next;
            }
            if (q == null) {
                q = headA;
            } else {
                q = q.next;
            }
        }
        return q;
    }

    /**
     * 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String result = s.substring(0,1);
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (max < right - left - 1) {
                max = right - left - 1;
                result = s.substring(left + 1, right);
            }
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (max < right - left - 1) {
                max = right - left - 1;
                result = s.substring(left + 1, right);
            }
        }
        return result;
    }

    /**
     * 无重复字符的最长子串
     * 可以用hashmap保存下标来进一步优化时间
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        HashSet<Character> hashSet = new HashSet<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            if (hashSet.contains(c)) {
                max = Math.max(right - left, max);
                while (left < right) {
                    hashSet.remove(s.charAt(left));
                    left++;
                    if (s.charAt(left - 1) == c) {
                        break;
                    }
                }
            }
            hashSet.add(c);
            right++;
        }
        return Math.max(right - left, max);
    }

    /**
     * 三数之和
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int count = nums[left] + nums[right];
                if (target == count) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (target > count) {
                    left++;
                } else  {
                    right--;
                }
            }
            while (i < nums.length - 2 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return res;
    }

    /**
     * 搜索旋转排序数组
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    public int search(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int tem = (right + left) / 2;
            if (nums[left] < nums[right]) {
                if (nums[tem] < target) {
                    left = tem + 1;
                } else if (nums[tem] > target) {
                    right = tem;
                } else {
                    return tem;
                }
            } else if (nums[left] > nums[right]) {
                int result1 = search(nums, target, left, tem);
                int result2 = search(nums, target, tem + 1, right);
                if (result1 != -1) {
                    return result1;
                } else if (result2 != -1) {
                    return result2;
                } else {
                    return -1;
                }
            } else {
                return nums[left] == target ? left : -1;
            }
        }
        return -1;
    }

    /**
     * LRUCache implementation
     */
    class LRUCache {
        private List<Integer> list = new LinkedList<>();
        private int count = 0;
        private int capacity;
        private final Map<Integer, Integer> map = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            for(int i = 0; i < list.size(); i++) {
                if (list.get(i) == key) {
                    list.remove(i);
                    break;
                }
            }
            list.add(key);
            return map.get(key);
        }

        public void put(int key, int value) {
            if (count == capacity) {
                if (map.containsKey(key)) {
                    for(int i = 0; i < list.size(); i++) {
                        if (list.get(i) == key) {
                            list.remove(i);
                            break;
                        }
                    }
                } else {
                    int oldKey = list.remove(0);
                    map.remove(oldKey);
                }
                count--;
            }
            list.add(key);
            map.put(key, value);
            count++;
        }
    }


    /**
     * 全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        fun(nums, new boolean[nums.length], new LinkedList<>(), result, nums.length);
        return result;
    }

    public void fun(int[] origin, boolean[] ints, List<Integer> list, List<List<Integer>> result, int k) {
        if (k == 0) {
            List<Integer> tem = new LinkedList<>();
            tem.addAll(list);
            result.add(tem);
            return;
        }
        for (int i = 0; i < origin.length; i++) {
            if (!ints[i]) {
                list.add(origin[i]);
                ints[i] = true;
                fun(origin, ints, list, result, k - 1);
                ints[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 查找数组中第k大的元素，要求时间复杂度为n，使用快排的快速选择算法
     * ,在数组中重复元素比较多的情况下会退化为on2的复杂度，一种解决方式是用三路划分的方式，牺牲空间换时间
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return nums[partition(nums, 0, nums.length - 1, k)];
    }

    public int partition(int[] nums, int left, int right, int k) {
        int lt = new Random().nextInt(right - left + 1) + left;
        swap(nums, left, lt);
        int pivot = nums[left];
        lt = left;
        for (int i = left + 1; i < right + 1; i++) {
            if (nums[i] > pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        if (lt == k - 1) {
            return lt;
        } else if (lt < k - 1) {
            return partition(nums, lt + 1, right, k);
        } else {
            return partition(nums, left, lt - 1, k);
        }
    }

    public void swap(int[] nums, int left, int right) {
        int tem = nums[left];
        nums[left] = nums[right];
        nums[right] = tem;
    }



}
