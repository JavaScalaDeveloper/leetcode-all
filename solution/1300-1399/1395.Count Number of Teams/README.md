# [1395. 统计作战单位数](https://leetcode.cn/problems/count-number-of-teams)

## 题目描述

<p> <code>n</code> 名士兵站成一排。每个士兵都有一个 <strong>独一无二</strong> 的评分 <code>rating</code> 。</p>

<p>每 <strong>3</strong> 个士兵可以组成一个作战单位，分组规则如下：</p>

<ul>
	<li>从队伍中选出下标分别为 <code>i</code>、<code>j</code>、<code>k</code> 的 3 名士兵，他们的评分分别为 <code>rating[i]</code>、<code>rating[j]</code>、<code>rating[k]</code></li>
	<li>作战单位需满足： <code>rating[i] < rating[j] < rating[k]</code> 或者 <code>rating[i] > rating[j] > rating[k]</code> ，其中  <code>0 <= i < j < k < n</code></li>
</ul>

<p>请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rating = [2,5,3,4,1]
<strong>输出：</strong>3
<strong>解释：</strong>我们可以组建三个作战单位 (2,3,4)、(5,4,1)、(5,3,1) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rating = [2,1,3]
<strong>输出：</strong>0
<strong>解释：</strong>根据题目条件，我们无法组建作战单位。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rating = [1,2,3,4]
<strong>输出：</strong>4
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>n == rating.length</code></li>
	<li><code>3 <= n <= 1000</code></li>
	<li><code>1 <= rating[i] <= 10^5</code></li>
	<li><code>rating</code> 中的元素都是唯一的</li>
</ul>

## 解法

**方法一：枚举中间元素**

我们可以枚举数组rating中每个元素rating[i]作为中间元素，然后统计左边比它小的元素的个数l，右边比它大的元素的个数r，那么以该元素为中间元素的作战单位的个数为l × r + (i - l) × (n - i - 1 - r)，累加到答案中即可。

时间复杂度O(n^2)，空间复杂度O(1)。其中n为数组rating的长度。

**方法二：树状数组**

我们可以用两个树状数组分别维护数组rating中每个元素的左边比它小的元素的个数l，右边比它大的元素的个数r，然后统计以该元素为中间元素的作战单位的个数为l × r + (i - l) × (n - i - 1 - r)，累加到答案中即可。

时间复杂度O(n × log n)，空间复杂度O(n)。其中n为数组rating的长度。

### **Java**

```java
class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = 0, r = 0;
            for (int j = 0; j < i; ++j) {
                if (rating[j] < rating[i]) {
                    ++l;
                }
            }
            for (int j = i + 1; j < n; ++j) {
                if (rating[j] > rating[i]) {
                    ++r;
                }
            }
            ans += l * r;
            ans += (i - l) * (n - i - 1 - r);
        }
        return ans;
    }
}
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int[] nums = rating.clone();
        Arrays.sort(nums);
        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        BinaryIndexedTree tree1 = new BinaryIndexedTree(m);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(m);
        for (int v : rating) {
            int x = search(nums, v);
            tree2.update(x, 1);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = search(nums, rating[i]);
            tree1.update(x, 1);
            tree2.update(x, -1);
            int l = tree1.query(x - 1);
            int r = n - i - 1 - tree2.query(x);
            ans += l * r;
            ans += (i - l) * (n - i - 1 - r);
        }
        return ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}
```
