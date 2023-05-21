# [1419. 数青蛙](https://leetcode.cn/problems/minimum-number-of-frogs-croaking)

## 题目描述

<p>给你一个字符串 <code>croakOfFrogs</code>，它表示不同青蛙发出的蛙鸣声（字符串 <code>"croak"</code> ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以&nbsp;<code>croakOfFrogs</code> 中会混合多个 <code>“croak”</code> <em>。</em></p>

<p>请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。</p>

<p>要想发出蛙鸣 "croak"，青蛙必须 <strong>依序</strong> 输出 <code>‘c’, ’r’, ’o’, ’a’, ’k’</code> 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。如果字符串 <code>croakOfFrogs</code> 不是由若干有效的 "croak" 字符混合而成，请返回 <code>-1</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "croakcroak"
<strong>输出：</strong>1 
<strong>解释：</strong>一只青蛙 “呱呱” 两次
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "crcoakroak"
<strong>输出：</strong>2 
<strong>解释：</strong>最少需要两只青蛙，“呱呱” 声用黑体标注
第一只青蛙 "<strong>cr</strong>c<strong>oak</strong>roak"
第二只青蛙 "cr<strong>c</strong>oak<strong>roak</strong>"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>croakOfFrogs = "croakcrook"
<strong>输出：</strong>-1
<strong>解释：</strong>给出的字符串不是 "croak<strong>"</strong> 的有效组合。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= croakOfFrogs.length &lt;= 10<sup>5</sup></code></li>
	<li>字符串中的字符只有 <code>'c'</code>, <code>'r'</code>, <code>'o'</code>, <code>'a'</code> 或者 <code>'k'</code></li>
</ul>

## 解法

**方法一：计数 + 模拟**

我们注意到，如果字符串 `croakOfFrogs` 是由若干有效的 `"croak"` 字符混合而成，那么它的长度一定是5的倍数。因此，如果字符串的长度不是5的倍数，可以直接返回-1。

接下来，我们将 `'c'`, `'r'`, `'o'`, `'a'`, `'k'` 这5个字母分别对应下标0到4，用一个长度为5的数组cnt记录字符串 `croakOfFrogs` 中每个字母出现的次数，其中cnt[i]表示当前下标为i的字母出现的次数。另外，我们定义一个整数变量x表示当前未完成蛙鸣的青蛙的数目，需要的青蛙的最少数目ans即为x的最大值。

我们遍历字符串 `croakOfFrogs` 中的每个字母c，找到c对应的下标i，然后将cnt[i]加1。接下来，根据i值的不同，我们分别进行如下操作：

-   如果i=0，那么当前有一个新的青蛙开始蛙鸣，因此令x的值加1，然后我们更新ans = max(ans, x)；
-   否则，如果cnt[i-1]=0，那么表示当前没有青蛙可以发出字母c，无法完成蛙鸣，返回-1，否则我们令cnt[i-1]减1。如果i=4，那么表示青蛙已经完成了一个蛙鸣，因此令x的值减1。

遍历结束后，如果x=0，那么说明青蛙已经完成了所有的蛙鸣，返回ans，否则返回-1。

时间复杂度O(n)，空间复杂度O(C)。其中n是字符串 `croakOfFrogs` 的长度；而C是字符集的大小，本题中C=26。

### **Java**

```java
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int n = croakOfFrogs.length();
        if (n % 5 != 0) {
            return -1;
        }
        int[] idx = new int[26];
        String s = "croak";
        for (int i = 0; i < 5; ++i) {
            idx[s.charAt(i) - 'a'] = i;
        }
        int[] cnt = new int[5];
        int ans = 0, x = 0;
        for (int k = 0; k < n; ++k) {
            int i = idx[croakOfFrogs.charAt(k) - 'a'];
            ++cnt[i];
            if (i == 0) {
                ans = Math.max(ans, ++x);
            } else {
                if (--cnt[i - 1] < 0) {
                    return -1;
                }
                if (i == 4) {
                    --x;
                }
            }
        }
        return x > 0 ? -1 : ans;
    }
}
```
