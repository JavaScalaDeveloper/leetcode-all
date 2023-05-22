# [剑指 Offer II 084. 含有重复元素集合的全排列](https://leetcode.cn/problems/7p8L0Z)

## 题目描述



<p>给定一个可包含重复数字的整数集合&nbsp;<code>nums</code> ，<strong>按任意顺序</strong> 返回它所有不重复的全排列。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,2]
<strong>输出：</strong>
[[1,1,2],
 [1,2,1],
 [2,1,1]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 8</code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
</ul>



<p><meta charset="UTF-8" />注意：本题与主站 47&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/permutations-ii/">https://leetcode.cn/problems/permutations-ii/</a></p>

## 解法

排序 + 深度优先搜索。

### **Java**

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        boolean[] used = new boolean[n];
        Arrays.sort(nums);
        dfs(0, n, nums, used, path, res);
        return res;
    }

    private void dfs(
        int u, int n, int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (u == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(u + 1, n, nums, used, path, res);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
```
