# [47. 全排列 II](https://leetcode.cn/problems/permutations-ii)

## 题目描述

<p>给定一个可包含重复数字的序列 <code>nums</code> ，<em><strong>按任意顺序</strong></em> 返回所有不重复的全排列。</p>

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

## 解法

**方法一：排序 + 回溯**

我们可以先对数组进行排序，这样就可以将重复的数字放在一起，方便我们进行去重。

然后，我们设计一个函数 $dfs(i)$，表示当前需要填写第 $i$ 个位置的数。函数的具体实现如下：

-   如果 $i = n$，说明我们已经填写完毕，将当前排列加入答案数组中，然后返回。
-   否则，我们枚举第 $i$ 个位置的数 $nums[j]$，其中 $j$ 的范围是 $[0, n - 1]$。我们需要保证 $nums[j]$ 没有被使用过，并且与前面枚举的数不同，这样才能保证当前排列不重复。如果满足条件，我们就可以填写 $nums[j]$，并继续递归地填写下一个位置，即调用 $dfs(i + 1)$。在递归调用结束后，我们需要将 $nums[j]$ 标记为未使用，以便于进行后面的枚举。

在主函数中，我们首先对数组进行排序，然后调用 $dfs(0)$，即从第 0 个位置开始填写，最终返回答案数组即可。

时间复杂度 $O(n \times n!)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。需要进行 $n!$ 次枚举，每次枚举需要 $O(n)$ 的时间来判断是否重复。另外，我们需要一个标记数组来标记每个位置是否被使用过，因此空间复杂度为 $O(n)$。

相似题目：

-   [46. 全排列](/solution/0000-0099/0046.Permutations/README.md)

### **Java**

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] nums;
    private boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        vis = new boolean[nums.length];
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = 0; j < nums.length; ++j) {
            if (vis[j] || (j > 0 && nums[j] == nums[j - 1] && !vis[j - 1])) {
                continue;
            }
            t.add(nums[j]);
            vis[j] = true;
            dfs(i + 1);
            vis[j] = false;
            t.remove(t.size() - 1);
        }
    }
}
```
