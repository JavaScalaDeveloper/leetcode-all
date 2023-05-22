# [794. 有效的井字游戏](https://leetcode.cn/problems/valid-tic-tac-toe-state)

## 题目描述

<p>给你一个字符串数组 <code>board</code> 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 <code>board</code> 所显示的状态时，才返回 <code>true</code> 。</p>

<p>井字游戏的棋盘是一个 <code>3 x 3</code> 数组，由字符 <code>' '</code>，<code>'X'</code> 和 <code>'O'</code> 组成。字符 <code>' '</code> 代表一个空位。</p>

<p>以下是井字游戏的规则：</p>

<ul>
	<li>玩家轮流将字符放入空位（<code>' '</code>）中。</li>
	<li>玩家 1 总是放字符 <code>'X'</code> ，而玩家 2 总是放字符 <code>'O'</code> 。</li>
	<li><code>'X'</code> 和 <code>'O'</code> 只允许放置在空位中，不允许对已放有字符的位置进行填充。</li>
	<li>当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。</li>
	<li>当所有位置非空时，也算为游戏结束。</li>
	<li>如果游戏结束，玩家不允许再放置字符。</li>
</ul>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe1-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>board = ["O  ","   ","   "]
<strong>输出：</strong>false
<strong>解释：</strong>玩家 1 总是放字符 "X" 。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe2-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>board = ["XOX"," X ","   "]
<strong>输出：</strong>false
<strong>解释：</strong>玩家应该轮流放字符。
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0794.Valid%20Tic-Tac-Toe%20State/images/tictactoe4-grid.jpg" style="width: 253px; height: 253px;" />
<pre>
<strong>输入：</strong>board = ["XOX","O O","XOX"]
<strong>输出：</strong>true
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>board.length == 3</code></li>
	<li><code>board[i].length == 3</code></li>
	<li><code>board[i][j]</code> 为 <code>'X'</code>、<code>'O'</code> 或 <code>' '</code></li>
</ul>

## 解法

**方法一：分类讨论**

我们先统计当前棋盘上 `'X'` 和 `'O'` 的数量，记为x和o。接下来，我们分情况讨论：

-   如果x \neq o且x - 1 \neq o，则当前棋盘不可能是有效棋盘，返回 `false`。
-   如果当前棋盘上玩家 1 获胜，但x-1 \neq o，则当前棋盘不可能是有效棋盘，返回 `false`。
-   如果当前棋盘上玩家 2 获胜，但x \neq o，则当前棋盘不可能是有效棋盘，返回 `false`。
-   其他情况下，当前棋盘是有效棋盘，返回 `true`。

时间复杂度O(C)，空间复杂度O(1)。其中C是棋盘上的格子数。本题中C = 9。

### **Java**

```java
class Solution {
    private String[] board;

    public boolean validTicTacToe(String[] board) {
        this.board = board;
        int x = count('X'), o = count('O');
        if (x != o && x - 1 != o) {
            return false;
        }
        if (win('X') && x - 1 != o) {
            return false;
        }
        return !(win('O') && x != o);
    }

    private boolean win(char x) {
        for (int i = 0; i < 3; ++i) {
            if (board[i].charAt(0) == x && board[i].charAt(1) == x && board[i].charAt(2) == x) {
                return true;
            }
            if (board[0].charAt(i) == x && board[1].charAt(i) == x && board[2].charAt(i) == x) {
                return true;
            }
        }
        if (board[0].charAt(0) == x && board[1].charAt(1) == x && board[2].charAt(2) == x) {
            return true;
        }
        return board[0].charAt(2) == x && board[1].charAt(1) == x && board[2].charAt(0) == x;
    }

    private int count(char x) {
        int cnt = 0;
        for (var row : board) {
            for (var c : row.toCharArray()) {
                if (c == x) {
                    ++cnt;
                }
            }
        }
        return cnt;
    }
}
```
