package com.example.myapplication.leetcode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Test {

    public static final Object o1 = new Object();
    public static final Object o2 = new Object();
    public static final String TAG = "Test";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        new Test().f();
    }


    /**
     * 死锁示例, 结合f3和f4
     */
    public void f() {
        Test test = new Test();
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                test.f1(3000);
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                test.f1(6000);
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }

    public void f1(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (o1){
            try {
                System.out.println(Thread.currentThread().getName() + "o1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread() + "o2");
                    o2.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void f3(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (o1){
            try {
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "o1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread() + "o2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void f4(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (o2){
            try {
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "o2");
                synchronized (o1) {
                    System.out.println(Thread.currentThread() + "o1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fun1(String str){
        if (str == null || str.length() == 0) {
            System.out.println(0);
            return;
        }
        int n = 0;
        int max = 0;
        char cur = str.charAt(0);
        if (cur - '0' <= 9){
            n = 1;
            max = 1;
        }
        for (int i = 1; i < str.length(); i++){
            char c = str.charAt(i);
            if (c - '0' > 9){
                n = 0;
                i++;
                if (i < str.length()){
                    cur = str.charAt(i);
                    if (cur - '0' < 9){
                        n = 1;
                        max = Math.max(max,n);
                    }
                    continue;
                }else {
                    break;
                }

            }
            if (cur - c > 0){
                n = 0;
            }
            cur = c;
            n++;
            max = Math.max(max, n);
        }
        System.out.println(max);
    }

    public static void fun2(int[] ints){
        int length = ints.length;
        //保存有多少种数
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int a : ints) {
            int tem;
            if (map.containsKey(a)) {
                tem = map.get(a) + 1;
            } else {
                stack.push(a);
                tem = 1;
            }
            map.put(a, tem);
            max = Math.max(max, tem);
        }
        int b = stack.size();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < b; i++){
            int a = stack.pop();
            if (map.get(a) == max){
                list.add(a);
            }
        }
        int c = list.size();
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            nums[i] = list.get(i);
        }
        Arrays.sort(nums);
        if (c % 2 == 0){
            System.out.println((nums[c / 2] + nums[c/2 - 1]) / 2);
        }else {
            System.out.println(nums[c / 2]);
        }
    }



    public static void fun3(String string){
        if (string == null || string.length() == 0){
            System.out.println("");
            return;
        }
        if (string.length() == 1){
            System.out.println(string.charAt(0));
            return;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++){
            char a = string.charAt(i);
            //遇到{，则说明无左子树，需要直接弹出根节点
            //遇到单词，则说明左子树无子节点，此时弹出左子树和根节点
            //遇到}跳过
            //
            if (fun4(a)){
                char b = string.charAt(++i);
                if (b == '{'){
                    stack.push(a);
                }else if (b == ','){
                    stringBuilder.append(a);
                    stringBuilder.append(stack.pop());
                }else if (b == '}'){
                    stringBuilder.append(a);
                }
            }else if (a == ','){
                stringBuilder.append(stack.pop());
            }else {
                continue;
            }
        }
        while (stack.size() != 0){
            stringBuilder.append(stack.pop());
        }
        System.out.println(stringBuilder.toString());
    }

    public static boolean fun4(char c){
        return c != '{' && c != '}' && c != ',';
    }




    public static volatile Test INSTANCE;

    public Test getInstance(){
        if (INSTANCE == null){
            synchronized (Test.class){
                if (INSTANCE == null){
                    INSTANCE = new Test();
                }
            }
        }
        return INSTANCE;
    }

    public void fun(int a, int b){
        a = a^b;
        b = a^b;
        a = a^b;
    }

    public static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }

    public static List<Integer> res = new LinkedList<>();
    public static void fun(TreeNode root){
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = root;
        TreeNode cur = root.left;
        stack.push(pre);
        while (true){
            if (cur != null){
                pre = cur;
                cur = cur.left;
                stack.push(pre);
            }else {
                cur = pre;
                res.add(cur.val);
                if (cur.right != null){
                    pre = cur.right;
                    cur = cur.right.left;
                }else {
                    cur = null;
                    pre = null;
                }
            }
            if (stack.size() == 0) return;
        }
    }

    public static int fun(String s){
        //存放数字
        Stack<Integer> stack1 = new Stack<>();
        //存放算符
        Stack<Character> stack2 = new Stack<>();
        boolean b = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c - '0' >= 0 && '9' - c >= 0){
                stringBuilder.append(c);
            }else {
                stack1.push(Integer.parseInt(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
                if (b){
                    int num1 = stack1.pop();
                    int num2 = stack1.pop();
                    char temC = stack2.pop();
                    if (temC == '*'){
                        stack1.push(num1 * num2);
                    }else {
                        stack1.push(num2 / num1);
                    }
                    b = false;
                }
                if (c == '*' || c == '/'){
                    b = true;
                }
                stack2.push(c);
            }
        }
        if (stringBuilder.length() != 0){
            stack1.push(Integer.parseInt(stringBuilder.toString()));
        }

        int aNum = stack1.pop();
        int bNum;
        while (stack2.size() != 0){
            bNum = stack1.pop();
            char c = stack2.pop();
            if (c == '+'){
                aNum = aNum + bNum;
            }else if (c == '-'){
                aNum = bNum - aNum;
            }else if (c == '*'){
                aNum = bNum * aNum;
            }else {
                aNum = bNum / aNum;
            }
        }
        return aNum;
    }

    public static int[] fun(int[] ints){
        for (int i = 0; i < ints.length; i++){
            for (int j = ints.length - 1; j > i; j--){
                if (ints[j] < ints[j - 1]){
                    swap(ints, i ,j);
                }
            }
        }
        return ints;
    }

    public static void swap(int[] ints, int i, int j){
        int tem = ints[i];
        ints[i] = ints[j];
        ints[j] = tem;
    }

    public static int numIsland(int[][] nums){
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < nums[0].length; j++){
                if (nums[i][j] == 1){
                    res++;
                    dfs(nums, i, j);
                }
            }
        }
        return res;
    }

    public static void dfs(int[][] nums, int i, int j){
        if (i < 0 || j < 0 || i > nums.length || j > nums[0].length) return;
        if (nums[i][j] == 1){
            nums[i][j] = 0;
            dfs(nums, i - 1, j);
            dfs(nums, i + 1, j);
            dfs(nums, i, j - 1);
            dfs(nums, i, j + 1);
        }
    }

    /**
     * 销售价值减少的颜色球
     * @param inventory
     * @param orders
     * @return
     */
    public int maxProfit(int[] inventory, int orders) {
        int sum = 0;
        Arrays.sort(inventory);
        int n = 1;
        int m = inventory.length - 1;
        int res = orders;
        int cur = inventory[m];
        while (res > 0) {
            while (m >= 1 && inventory[m - 1] == cur) {
                n++;
                m--;
            }
            //与上一个的差
            int delta;
            int left;
            if (m == 0) {
                left = inventory[0];
            } else {
                left = inventory[m - 1];
            }
            delta = cur - left;
            if (res > n * delta) {
                sum = (sum + n * (delta) * (inventory[m - 1] + 1 + cur) / 2) % 1000000007;
                res -= delta * n;
                cur = inventory[m - 1];
                m--;
                n++;
            } else {
                int tem = res / n;
                if (tem > 0) {
                    sum = (sum + n * (tem) * (cur - tem + 1 + cur) / 2) % 1000000007;
                }
                res -= tem * n;
                sum = (sum + res * (cur - tem)) % 1000000007;
                return sum;
            }
        }
        return sum;
    }

    /**
     * 二叉树的最大深度
     * @param root 二叉树的根
     * @return 二叉树的深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }


    /**
     * 平衡二叉树，平衡二叉树定义：任何一棵子树的左右节点高度相差不超过1
     * @param root 二叉树的根
     * @return 是否是平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalanced(root.left) && isBalanced(root.right) &&
                Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
    }

    /**
     * 二叉树的直径。
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的长度。这条路径可能经过也可能不经过根节点 root。
     * 两节点之间路径的 长度 由它们之间边数表示。
     * @param root 二叉树的根节点
     * @return 二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        return Math.max(Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)),
                maxDepth(root.left) + maxDepth(root.right));
    }

    /**
     * 用rand7实现rand10 ,rand7 返回[1,7]之间的随机整数
     * @return [1,10]之间的随机整数
     */
    public int rand10() {
        while (true) {
            int a = rand7();
            int b = rand7() - 1;
            int num = a + b * 7;
            if (num <= 40) return num % 10 + 1;
            //上面那一步已经可以求解完成，但是由于舍弃了41-49这9各数字，所以生成随机数的效率低，后面的代码为优化代码

            a = num - 40; // rand9
            b = rand7() - 1;
            num = a + b * 9; // [1,63]
            if (num <= 60) return num % 10 + 1;

            a = num - 60; //rand 3
            b = rand7() - 1;
            num = a + b * 3;
            if (num <= 20) return num % 10 + 1;
            //最后剩余21则不使用，舍弃
        }

    }

    //用于rand10中，代替系统预定义的rand7使其编译通过，此函数无意义
    private int rand7() {
        return 7;
    }

    /**
     * 旋转图像，需要在原矩阵上修改
     * @param matrix 输入矩阵
     */
    public void rotate(int[][] matrix) {

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> current = new LinkedList<>();
        dfs(0, current, result, candidates, target);
        return result;
    }

    public void dfs(int begin, LinkedList<Integer> current, List<List<Integer>> res, int[] candidates, int target) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new LinkedList<>(current));
        }
        for (int i = begin; i < candidates.length; i++) {
            current.addLast(candidates[i]);
            dfs(i, current, res, candidates, target - candidates[i]);
            current.removeLast();
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 删除排序列表中的重复元素
     * @param head 链表头节点
     * @return 删除后的列表
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
        } else {
            ListNode next = head.next.next;
            while (next != null && next.val == head.val) {
                next = next.next;
            }
            return deleteDuplicates(next);
        }
        return head;
    }

    /**
     * 字符串转整数
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int len = s.length();
        // str.charAt(i) 方法回去检查下标的合法性，一般先转换成字符数组
        char[] charArray = s.toCharArray();

        // 1、去除前导空格
        int index = 0;
        while (index < len && charArray[index] == ' ') {
            index++;
        }

        // 2、如果已经遍历完成（针对极端用例 "      "）
        if (index == len) {
            return 0;
        }

        // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        int sign = 1;
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // 4、将后续出现的数字字符进行转换
        // 不能使用 long 类型，这是题目说的
        int res = 0;
        while (index < len) {
            char currChar = charArray[index];
            // 4.1 先判断不合法的情况
            if (currChar > '9' || currChar < '0') {
                break;
            }

            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++;
        }
        return res;
    }

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            int res = partition(nums, left, right);
            if (res < nums.length - k) {
                left = res + 1;
            } else if (res > nums.length - k) {
                right = res - 1;
            } else {
                return nums[res];
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        Random random = new Random();
        int randomIndex = random.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);
        int pivot = nums[left];
        int lt = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, lt, left);
        return lt;
    }

    /**
     * 有序数组中的单一元素
     * @param nums 输入数组
     * @return 唯一的那个数
     * 解题思路，二分法且保持每次nums分半后是奇数个数的数组，
     * 每次取中间数时比较左右两个值，需要考虑数组长度除以2之后的奇偶值对计算过程的影响
     * 11223
     * 1123344
     * 1122344
     * 112334455
     * 112233445
     */
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            if (left == right) return nums[left];
            int tem = ((right - left) / 2) % 2;
            mid = left + (right - left) / 2;
            if (tem == 1) {
                if (nums[mid] == nums[mid + 1]) {
                    right = mid - 1;
                } else if (nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else {
                    return nums[mid];
                }
            } else {
                if (nums[mid] == nums[mid + 1]) {
                    left = mid;
                } else if (nums[mid] == nums[mid - 1]) {
                    right = mid;
                } else {
                    return nums[mid];
                }
            }
        }
        return -1;
    }


    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * @param nums 输入数组
     * @return 多数元素
     */
    public int majorityElement(int[] nums) {
        int max = nums[0];
        int maxNums = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == max) {
                maxNums++;
            } else {
                if (maxNums > 0) {
                    maxNums--;
                } else {
                    max = nums[i];
                    maxNums = 1;
                }
            }
        }
        return max;
    }

    /**
     * 解题思路是递归
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
     * @param root 根节点
     * @return 反转后的二叉树的根节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temLeft = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temLeft);
        return root;
    }

    /**
     * 树的子结构，思路是递归加上辅助函数判两棵树相等
     * @param A 树A
     * @param B 树B
     * @return B树是否是A的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;
        List<TreeNode> list = new LinkedList<>();
        list.add(A);
        while (list.size() != 0) {
            TreeNode root = list.remove(0);
            if (root.left != null) {
                list.add(root.left);
            }
            if (root.right != null) {
                list.add(root.right);
            }
            if (isEqualTree(root, B)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqualTree(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        } else if (A == null) {
            return false;
        }
        return isEqualTree(A.left, B.left) && isEqualTree(A.right, B.right) && A.val == B.val;
    }



















}
