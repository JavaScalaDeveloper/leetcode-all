# [78. 子集](https://leetcode.cn/problems/subsets)

## 题目描述

<p>给你一个整数数组 <code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的子集（幂集）。</p>

<p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[[],[0]]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10</code></li>
	<li><code>-10 <= nums[i] <= 10</code></li>
	<li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：递归枚举**

我们设计一个递归函数dfs(u, t)，它的参数为当前枚举到的元素的下标u，以及当前的子集t。

当前枚举到的元素下标为u，我们可以选择将其加入子集t中，也可以选择不加入子集t中。递归这两种选择，即可得到所有的子集。

时间复杂度O(n\times 2^n)，空间复杂度O(n)。其中n为数组的长度。数组中每个元素有两种状态，即选择或不选择，共2^n种状态，每种状态需要O(n)的时间来构造子集。

**方法二：二进制枚举**

我们可以将方法一中的递归过程改写成迭代的形式，即使用二进制枚举的方法来枚举所有的子集。

我们可以使用2^n个二进制数来表示n个元素的所有子集，若某个二进制数 `mask` 的第i位为1，表示子集中包含数组第i个元素v；若为0，表示子集中不包含数组第i个元素v。

时间复杂度O(n\times 2^n)，空间复杂度O(n)。其中n为数组的长度。一共有2^n个子集，每个子集需要O(n)的时间来构造。

### **Java**

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, List<Integer> t) {
        if (u == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        dfs(u + 1, t);
        t.add(nums[u]);
        dfs(u + 1, t);
        t.remove(t.size() - 1);
    }
}
```

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    t.add(nums[i]);
                }
            }
            ans.add(t);
        }
        return ans;
    }
}
```
