package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

public class Main {
    public static void main(String[] args) {
        toList();
    }

    public static void toList() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                System.out.println("1");
                Thread.sleep(2000);
                emitter.onNext(2);
                System.out.println("2");
                Thread.sleep(2000);
                emitter.onNext(3);
                System.out.println("3");
                emitter.onNext(4);
                emitter.onComplete();
            }
        });
        observable.toList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> list) throws Throwable {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i);
                }
            }
        });
    }

    public static void combineLatest() {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        List<Character> characters = new ArrayList<Character>();
        for (int i = 0; i < 8; i++) {
            characters.add((char)('a' + i));
        }
        Observable<Integer> observable1 = Observable.fromIterable(integers).delay(2000, TimeUnit.MILLISECONDS);
        Observable<Character> observable2 = Observable.fromIterable(characters);
        Observable.combineLatest(observable1, observable2, new BiFunction<Integer, Character, String>() {
            @Override
            public String apply(Integer integer, Character character) throws Throwable {
                return character + "" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        });
        while (true) {

        }
    }

    public static void zip() {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        List<Character> characters = new ArrayList<Character>();
        for (int i = 0; i < 8; i++) {
            characters.add((char)('a' + i));
        }
        Observable<Integer> observable1 = Observable.fromIterable(integers);
        Observable<Character> observable2 = Observable.fromIterable(characters);
        Observable.zip(observable1, observable2, new BiFunction<Integer, Character, String>() {
            @Override
            public String apply(Integer integer, Character character) throws Throwable {
                return character + "" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        });
    }

    private static void testMap() {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }

        //map
        Observable.fromIterable(integers).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Throwable {
                if (integer % 2 == 0) {
                    return "lfs_" + integer;
                }
                return "wyh_" + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        });
    }

    private static void testFlatMap() {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }

        //flatMap
        Observable.fromIterable(integers).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                if (integer % 2 == 0) {
                    return Observable.just("wyh_" + integer).delay(2000, TimeUnit.MILLISECONDS);
                }
                return Observable.just("wyh_odd_" + integer).delay(200, TimeUnit.MILLISECONDS);
            }
        }).cast(String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        });

        while (true) {

        }
    }

    private static void testConcatMap() {
        List<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }

        //concatMap
        Observable.fromIterable(integers).concatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                if (integer % 2 == 0) {
                    return Observable.just("wyh_" + integer).delay(2000, TimeUnit.MILLISECONDS);
                }
                return Observable.just("wyh_odd_" + integer).delay(200, TimeUnit.MILLISECONDS);
            }
        }).cast(String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        });

        //flatMap
        Observable.fromIterable(integers).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                if (integer % 2 == 0) {
                    return Observable.just("wyh_" + integer).delay(2000, TimeUnit.MILLISECONDS);
                }
                return Observable.just("wyh_odd_" + integer).delay(200, TimeUnit.MILLISECONDS);
            }
        }).cast(String.class).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println(s);
            }
        });

        while (true) {

        }
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

    /**
     * 二分
     * @param nums
     * @param left
     * @param right
     * @return
     */
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
        int length2 = matrix[0].length;
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

    public ListNode detectCycle(ListNode head) {
        //快慢指针，指针f，s分别为2，1，设环前为a，环为b，则两指针第一次相遇时，会有f = 2bn，s = bn，
        //此时将f速度改为一并使其重置为首部，那么两指针再次相遇时的节点即为入口节点
        if (head == null) return null;
        ListNode s = head;
        ListNode f = head.next;
        while (f != s){
            if (f == null || f.next == null) return null;
            s = s.next;
            f = f.next.next;
        }
        f = head;
        s = s.next;
        while (f != s){
            s = s.next;
            f = f.next;
        }
        return f;
    }

    public int findNumberOfLIS(int[] nums) {
        //定义dp，为以i结尾的最长子序列长度，之后排序，整体时间复杂度为n^2
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int[] gp = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            dp[i] = 1;
            gp[i] = 1;
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        //此处不能是gp[i] = 1
                        gp[i] = gp[j];
                    }else if (dp[j] + 1 == dp[i]){
                        //此处不能是++
                        gp[i] += gp[j];
                    }
                }
            }
        }
        int res = 0;
        int max = 1;
        for (int i = 0; i < nums.length; i ++){
            if (max < dp[i]){
                res = gp[i];
                max = dp[i];
            }else if (max == dp[i]){
                res += gp[i];
            }
        }
        return res;
    }

    public int fun(String str){
        int length = str.length();
        int res = 0;
        for (int i = length - 1; i > 1; i--){
            char c = str.charAt(i);
            int tem = c - '0';
            if (tem >= 10) {
                tem = c - 'A' + 1;
            }
            res += tem;
        }
        return res;
    }

    //
    public static int[] findTwoNumber(int number){
        int n1 = number / 2;
        int n2 = number - n1;
        while (!f(n1) || !f(n2) && n1 > 0){
            n1 -= 1;
            n2 += 1;
        }
        return new int[]{n1,n2};
    }

    //判断一个数是否为素数
    public static boolean f(int num){
        if(num == 1 || num == 2) return true;
        int f = num / 2;
        int n1 = f;
        int n2 = 2;
        while (n1 * n2 != num){
            if (n2 > f){
                return true;
            }
            n2++;
            n1 = num / n2;
        }
        return false;
    }

    //字符倒序
    public String revert(String s){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--){
            stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }

    //ip地址转为整数
    public String revertIp(String s){
        String[] strings = s.split("\\.");
        String res = null;
        if (strings.length == 1){
            StringBuilder stringBuilder = new StringBuilder();
            long i = Long.parseLong(s);
            long i1 = (i & 0xff000000) >>> 24;
            long i2 = (i & 0x00ff0000) >>> 16;
            long i3 = (i & 0x0000ff00) >>> 8;
            long i4 = i & 0x000000ff;
            stringBuilder.append(i1).append('.').append(i2).append('.').append(i3).append('.').append(i4);
            res = stringBuilder.toString();
        }else {
            long i1 = Long.parseLong(strings[0]);
            long i2 = Long.parseLong(strings[1]);
            long i3 = Long.parseLong(strings[2]);
            long i4 = Long.parseLong(strings[3]);
            i1 = i1 << 24;
            i2 = i2 << 16;
            i3 = i3 << 8;
            res = Long.toString(i1 | i2 | i3 | i4);
        }

        return res;
    }

    //统计Ascii码种数
    public int count(String s){
        int length = s.length();
        int[] dp = new int[127];
        int res = 0;
        for (int i = 0; i < length; i++){
            char c = s.charAt(i);
            if (c == '\n' || c > 127){
                continue;
            }
            if (dp[c] == 0){
                dp[c] = 1;
                res++;
            }
        }
        return res;
    }

    //验证密码合规程序
    /**
     * 密码要求:
     *
     * 1.长度超过8位
     *
     * 2.包括大小写字母.数字.其它符号,以上四种至少三种
     *
     * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
     */

    public static boolean passwordVerify(String s){
        int length = s.length();
        if (length <= 8) return false;
        int sum = 0;
        int[] dp = new int[4];
        for (int i = 0; i < length; i++){
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z' ){
                if (dp[0] == 0){
                    dp[0] = 1;
                    sum++;
                }
            }else if (c >= 'A' && c <= 'Z'){
                if (dp[1] == 0){
                    dp[1] = 1;
                    sum++;
                }
            }else if (c >= '0' && c <= '9'){
                if (dp[2] == 0){
                    dp[2] = 1;
                    sum++;
                }
            }else if (dp[3] == 0){
                dp[3] = 1;
                sum++;
            }
        }
        if (sum < 3) return false;
        int left = 0;
        int right = 3;
        HashMap<String, Integer> hashSet = new HashMap<>();
        while (right < length){
            String s1 = s.substring(left, right);
            if (hashSet.containsKey(s1) && left - hashSet.get(s1) > 3){
                return false;
            }
            if (!hashSet.containsKey(s1)){
                hashSet.put(s1, left);
            }
            left++;
            right++;
        }
        return true;
    }

    //华为机试，明明的随机数，输入的随机数为
    public List<Integer> random(List<Integer> list){
        int[] nums = new int[500];
        for (int i = 0; i < list.size(); i++){
            int a = list.get(i);
            if (nums[a] == 0){
                nums[a] = 1;
            }
        }
        List<Integer> res = new LinkedList<>();
        for (int i = 1; i < 500; i++){
            if (nums[i] == 1){
                res.add(i);
            }
        }
        return res;
    }

    //华为计试，成绩排名
    public static class Student{
        public String name;
        public int record;
        public Student(String name, int record){
            this.name = name;
            this.record = record;
        }
    }

    public static void sort(Student[] students, boolean b){
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (b){
                    return Integer.compare(o1.record, o2.record);
                }else {
                    return Integer.compare(o2.record, o1.record);
                }

            }
        });
        int length = students.length;
        for (Student student : students) {
            System.out.print(student.name + ' ');
            System.out.println(student.record);
        }
    }

    //华为机试，坐标移动
    public void zuobiao(String[] list){
        int length = list.length;
        Pattern pattern = Pattern.compile("[AWSD]\\d[\\d]?");
        Matcher matcher = null;
        int x = 0;
        int y = 0;
        for (String str : list) {
            matcher = pattern.matcher(str);
            if (matcher.matches()) {
                char c = str.charAt(0);
                int num = Integer.parseInt(str.substring(1));
                if (c == 'A') {
                    x -= num;
                } else if (c == 'D') {
                    x += num;
                } else if (c == 'W') {
                    y += num;
                } else {
                    y -= num;
                }
            }
        }
        System.out.println(x + "," + y);
    }


    public static List<List<Integer>> baidu(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<Integer>();
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int n = target - nums[i];
            if (list.size() != 0 && n == list.get(0)) {
                continue;
            }
            if (list.size() != 0) {
                list.remove(0);
            }
            list.add(n);
            for (int j = i + 1; j < nums.length; j++) {
                int m = nums[j];
                if (m == n) {
                    List<Integer> linkedList = new LinkedList<>();
                    linkedList.add(nums[i]);
                    linkedList.add(m);
                    result.add(linkedList);
                    break;
                } else if (m > n) {
                    break;
                }
            }
        }
        return result;
    }
























}
