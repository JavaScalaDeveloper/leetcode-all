# [剑指 Offer II 007. 数组中和为 0 的三个数](https://leetcode.cn/problems/1fGaJU)

## 题目描述



<p>给定一个包含 <code>n</code> 个整数的数组&nbsp;<code>nums</code>，判断&nbsp;<code>nums</code>&nbsp;中是否存在三个元素&nbsp;<code>a</code> ，<code>b</code> ，<code>c</code> <em>，</em>使得&nbsp;<code>a + b + c = 0</code> ？请找出所有和为 <code>0</code> 且&nbsp;<strong>不重复&nbsp;</strong>的三元组。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
<strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>



<p><meta charset="UTF-8" />注意：本题与主站 15&nbsp;题相同：<a href="https://leetcode.cn/problems/3sum/">https://leetcode.cn/problems/3sum/</a></p>

## 解法

枚举第一个数，然后用双指针确定另外两个数

### **Java**

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int cur = nums[i] + nums[left] + nums[right];
                if (cur < 0) {
                    left++;
                } else if (cur > 0) {
                    right--;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }
}
```
