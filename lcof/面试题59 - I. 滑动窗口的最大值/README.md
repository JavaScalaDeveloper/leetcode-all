# [面试题 59 - I. 滑动窗口的最大值](https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)

## 题目描述



<p>给定一个数组 <code>nums</code> 和滑动窗口的大小 <code>k</code>，请找出所有滑动窗口里的最大值。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <em>nums</em> = <code>[1,3,-1,-3,5,3,6,7]</code>, 和 <em>k</em> = 3
<strong>输出: </strong><code>[3,3,5,5,6,7] 
<strong>解释: 
</strong></code>
  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7</pre>



<p><strong>提示：</strong></p>

<p>你可以假设 <em>k </em>总是有效的，在输入数组不为空的情况下，1 &le; k &le;&nbsp;输入数组的大小。</p>

<p>注意：本题与主站 239 题相同：<a href="https://leetcode.cn/problems/sliding-window-maximum/">https://leetcode.cn/problems/sliding-window-maximum/</a></p>

## 解法

**方法一：单调队列**

单调队列常见模型：找出滑动窗口中的最大值/最小值。模板：

时间复杂度 $O(n)$，空间复杂度 $O(k)$。其中 $n$ 为数组长度。

### **Java**

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            if (!q.isEmpty() && i - q.peek() + 1 > k) {
                q.poll();
            }
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.offer(i);
            if (i >= k - 1) {
                ans[i - k + 1] = nums[q.peek()];
            }
        }
        return ans;
    }
}
```
