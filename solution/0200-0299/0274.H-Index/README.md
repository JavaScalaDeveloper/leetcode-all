# [274. H 指数](https://leetcode.cn/problems/h-index)

## 题目描述

<p>给你一个整数数组 <code>citations</code> ，其中 <code>citations[i]</code> 表示研究者的第 <code>i</code> 篇论文被引用的次数。计算并返回该研究者的 <strong><code>h</code><em>&nbsp;</em>指数</strong>。</p>

<p>根据维基百科上&nbsp;<a href="https://baike.baidu.com/item/h-index/3991452?fr=aladdin" target="_blank">h 指数的定义</a>：h 代表“高引用次数”，一名科研人员的 <code>h</code><strong>指数</strong>是指他（她）的 （<code>n</code> 篇论文中）<strong>总共</strong>有 <code>h</code> 篇论文分别被引用了<strong>至少</strong> <code>h</code> 次。且其余的 <em><code>n - h</code>&nbsp;</em>篇论文每篇被引用次数&nbsp;<strong>不超过 </strong><em><code>h</code> </em>次。</p>

<p>如果 <code>h</code><em> </em>有多种可能的值，<strong><code>h</code> 指数 </strong>是其中最大的那个。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>citations = [3,0,6,1,5]</code>
<strong>输出：</strong>3 
<strong>解释：</strong>给定数组表示研究者总共有 <code>5</code> 篇论文，每篇论文相应的被引用了 <code>3, 0, 6, 1, 5</code> 次。
&nbsp;    由于研究者有 <code>3 </code>篇论文每篇 <strong>至少 </strong>被引用了 <code>3</code> 次，其余两篇论文每篇被引用 <strong>不多于</strong> <code>3</code> 次，所以她的 <em>h </em>指数是 <code>3</code>。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>citations = [1,3,1]
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == citations.length</code></li>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>0 &lt;= citations[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：排序**

我们可以先对数组 `citations` 按照元素值从大到小进行排序。然后我们从大到小枚举h值，如果某个h值满足citations[h-1] ≥ h，则说明有至少h篇论文分别被引用了至少h次，直接返回h即可。如果没有找到这样的h值，说明所有的论文都没有被引用，返回0。

时间复杂度O(n × log n)，空间复杂度O(log n)。其中n是数组 `citations` 的长度。

**方法二：计数 + 求和**

我们可以使用一个长度为n+1的数组cnt，其中cnt[i]表示引用次数为i的论文的篇数。我们遍历数组 `citations`，将引用次数大于n的论文都当作引用次数为n的论文，然后将每篇论文的引用次数作为下标，将cnt中对应的元素值加1。这样我们就统计出了每个引用次数对应的论文篇数。

接下来，我们从大到小枚举h值，将cnt中下标为h的元素值加到变量s中，其中s表示引用次数大于等于h的论文篇数。如果s ≥ h，说明至少有h篇论文分别被引用了至少h次，直接返回h即可。

时间复杂度O(n)，空间复杂度O(n)。其中n是数组 `citations` 的长度。

**方法三：二分查找**

我们注意到，如果存在一个h值满足至少有h篇论文至少被引用h次，那么对于任意一个h' < h，都有至少h'篇论文至少被引用h'次。因此我们可以使用二分查找的方法，找到最大的h值，使得至少有h篇论文至少被引用h次。

我们定义二分查找的左边界l=0，右边界r=n。每次我们取mid = \lfloor \frac{l + r + 1}{2} \rfloor，其中\lfloor x \rfloor表示对x向下取整。然后我们统计数组 `citations` 中大于等于mid的元素的个数，记为s。如果s ≥ mid，说明至少有mid篇论文至少被引用mid次，此时我们将左边界l变为mid，否则我们将右边界r变为mid-1。当左边界l等于右边界r时，我们找到了最大的h值，即为l或r。

时间复杂度O(n × log n)，其中n是数组 `citations` 的长度。空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int h = n; h > 0; --h) {
            if (citations[n - h] >= h) {
                return h;
            }
        }
        return 0;
    }
}
```

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] cnt = new int[n + 1];
        for (int x : citations) {
            ++cnt[Math.min(x, n)];
        }
        for (int h = n, s = 0; ; --h) {
            s += cnt[h];
            if (s >= h) {
                return h;
            }
        }
    }
}
```

```java
class Solution {
    public int hIndex(int[] citations) {
        int l = 0, r = citations.length;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int s = 0;
            for (int x : citations) {
                if (x >= mid) {
                    ++s;
                }
            }
            if (s >= mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
```
