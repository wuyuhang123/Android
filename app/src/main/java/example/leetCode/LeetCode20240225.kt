package example.leetCode

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap

/**
 * @Author wuyuhang
 * @Date 2024/2/25 11:25
 * @Describe
 */

/**
 * 两数之和
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        if (map.containsKey(num)) {
            val result = IntArray(2)
            result[0] = index;
            result[1] = map[num]!!
            return result
        }
        map[target - num] = index
    }
    return IntArray(2)
}

class ListNode(val `val`: Int) {
    var next: ListNode? = null
}

/**
 * 合并有序链表，可以使用递归，更简单
 */
//fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
//    val res = ListNode(0)
//    var cur = res
//    var l1 = list1
//    var l2 = list2
//    while (l1 != null || l2 != null) {
//        if (l1 == null) {
//            cur.next = l2
//            l2 = l2?.next
//        } else if (l2 == null) {
//            cur.next = l1
//            l1 = l1.next
//        } else {
//            if (l1.value < l2.value) {
//                cur.next = l1
//                l1 = l1.next
//            } else {
//                cur.next = l2
//
//                l2 = l2.next
//            }
//        }
//        cur = cur.next!!
//    }
//    return res.next
//}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null) {
        return list2
    }
    if (list2 == null) {
        return list1
    }
    return if (list1.`val` < list2.`val`) {
        val l1 = mergeTwoLists(list1.next, list2)
        list1.next = l1
        list1
    } else {
        val l2 = mergeTwoLists(list1, list2.next)
        list2.next = l2
        list2
    }
}

/**
 * 合并有序数组,由于最终结果存在num1中，所以反过来从大到小从尾部放
 */
fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var n1 = m - 1
    var n2 = n - 1
    for (i in m + n - 1 downTo 0) {
        if (n1 == -1) {
            nums1[i] = nums2[n2]
            n2--
        } else if (n2 == -1) {
            nums1[i] = nums1[n1]
            n1--
        } else {
            if (nums1[n1] > nums2[n2]) {
                nums1[i] = nums1[n1]
                n1--
            } else {
                nums1[i] = nums2[n2]
                n2--
            }
        }
    }
}

/**
 * 岛屿数量
 */
fun numIslands(grid: Array<CharArray>): Int {
    var result = 0
    for (i in grid.indices) {
        for(j in 0 until grid[0].size) {
            if (grid[i][j] == '1') {
                result++
                dfs(i, j, grid)
            }
        }
    }
    return result
}

fun dfs(x: Int, y: Int, grid: Array<CharArray>) {
    if (x < 0 || x >= grid.size || y < 0 || y >= grid[0].size) {
        return
    }
    if (grid[x][y] == '1') {
        grid[x][y] = '0'
        dfs(x - 1, y, grid)
        dfs(x + 1, y, grid)
        dfs(x, y - 1, grid)
        dfs(x, y + 1, grid)
    }
}

/**
 * 环形链表
 */
fun hasCycle(head: ListNode?): Boolean {
    if (head == null) {
        return false
    }
    var p = head
    var q = head.next
    while (p != null && q != null && p != q) {
        p = p.next
        q = q.next?.next
    }
    return p != null && q != null
}

/**
 * 有效的括号
 */
fun isValid(s: String): Boolean {
    val stack = LinkedList<Char>()
    for (c in s) {
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c)
        } else {
            if (stack.size == 0) {
                return false
            } else {
                if (!(c == ')' && stack.pop() == '('
                    || c == ']' && stack.pop()== '['
                    || c == '}' && stack.pop() == '{')) {
                    return false
                }
            }
        }
    }
    return stack.size == 0
}

/**
 * 买卖股票最佳时机
 */
fun maxProfit(prices: IntArray): Int {
    var min = prices[0]
    var res = 0
    for (i in 1 until prices.size) {
        if (prices[i] < min) {
            min = prices[i]
        } else {
            res = res.coerceAtLeast(prices[i] - min)
        }
    }
    return res
}

/**
 * 搜索旋转排序数组，二分变种,使用递归使其变成纯二分
 */
fun search(nums: IntArray, target: Int): Int {
    return search(nums, target, 0, nums.size - 1)
}

fun search(nums: IntArray, target: Int, left: Int, right: Int): Int {
    var temLeft = left
    var temRight = right
    while (temLeft <= temRight) {
        val mid = (temLeft + temRight) / 2
        if (nums[temLeft] < nums[temRight]) {
            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] < target) {
                temLeft = mid + 1
            } else {
                temRight = mid
            }
        } else if (nums[temRight] < nums[temLeft]){
            val result1 = search(nums, target, mid + 1, right)
            val result2 = search(nums, target, temLeft, mid)
            if (result1 != -1) {
                return result1
            } else if (result2 != -1) {
                return result2
            }
            return -1
        } else {
            // 注意处理左右下标相等的情况
            return if(nums[temLeft] == target) {
                temLeft
            } else {
                -1
            }
        }
    }
    return -1
}

/**
 * 最大子数组和
 */
fun maxSubArray(nums: IntArray): Int {
    var dp1 = nums[0]
    var dp2 = dp1
    var max = dp1
    for (index in 1 until nums.size) {
        dp2 = if (dp1 > 0) {
            dp1 + nums[index]
        } else {
            nums[index]
        }
        max = Math.max(max, dp2)
        dp1 = dp2
    }
    return max
}

// 需要一个全局的变量来记录下一次遍历从哪开始,总体思路是递归
var ww = -1

fun decodeString(s: String): String {
    return decodeString(0, s)
}

fun decodeString(int: Int, s: String): String {
    val res = StringBuilder()
    var num = 0
    for (i in int until s.length) {
        if (i <= ww) {
          continue
        }
        val c = s[i]
        val temNum = c - '0'
        if (temNum in 0..9) {
            num *= 10
            num += temNum
        } else if (c == '[') {
            val repeatString = decodeString(i + 1, s)
            for (j in 0 until num) {
                res.append(repeatString)
            }
            num = 0
        } else if (c== ']') {
            ww = i
            return res.toString()
        } else {
            res.append(c)
        }
    }
    return res.toString()
}

/**
 * 二维搜索数组
 */
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    var i = 0
    var j = matrix[0].size - 1
    while (i < matrix.size && j >= 0) {
        when {
            matrix[i][j] < target -> {
                i++
            }
            matrix[i][j] > target -> {
                j--
            }
            else -> {
                return true
            }
        }
    }
    return false
}

/**
 * 接雨水
 */
fun trap(height: IntArray): Int {
    val left: IntArray = IntArray(height.size)
    val right: IntArray = IntArray(height.size)
    var max = 0
    for (i in 1 until height.size) {
        max = Math.max(max, height[i - 1])
        left[i] = max
    }
    max = 0
    for (i in height.size - 1 downTo 1 ) {
        max = Math.max(max, height[i - 1])
        right[i] = max
    }
    var result = 0
    for (i in height.indices) {
        if (height[i] < left[i] && height[i] < right[i]) {
            result += Math.min(left[i], right[i]) - height[i]
        }
    }
    return result
}

/**
 * 复原Ip地址
 */
fun restoreIpAddresses(s: String): List<String> {

}

/**
 * 环形链表2，返回入环的第一个节点
 * 思路是先算出n，然后两个指针全部回到起点，其中一个先走n，然后同时移动两个指针，下次相遇时一个是a，一个是a+n，即同时停在路口
 */
fun detectCycle(head: ListNode?): ListNode? {

}

/**
 * 基本计算器
 */
fun calculate(s: String): Int {

}