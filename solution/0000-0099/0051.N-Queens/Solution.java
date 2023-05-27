package com.solution._0051;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> res = solution.solveNQueens(8);
        System.out.println(res);
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        String[][] g = new String[n][n];
        //填满格子上所有的点
        for (int i = 0; i < n; ++i) {
            String[] t = new String[n];
            Arrays.fill(t, ".");
            g[i] = t;
        }
        boolean[] col = new boolean[n];
        boolean[] dg = new boolean[2 * n];
        boolean[] udg = new boolean[2 * n];
        dfs(0, n, col, dg, udg, g, res);
        return res;
    }

    private void dfs(int u, int n, boolean[] col, boolean[] dg, boolean[] udg, String[][] g,
                     List<List<String>> res) {
        if (u == n) {
            List<String> t = new ArrayList<>();
            for (String[] e : g) {
                t.add(String.join("", e));
            }
            res.add(t);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!col[i] && !dg[u + i] && !udg[n - u + i]) {
                g[u][i] = "Q";
                col[i] = dg[u + i] = udg[n - u + i] = true;
                dfs(u + 1, n, col, dg, udg, g, res);
                g[u][i] = ".";
                col[i] = dg[u + i] = udg[n - u + i] = false;
            }
        }
    }

    /*
    解法一：位运算
    这种解法使用了位运算技巧，将二进制数中 1 所在的位置表示成棋盘上的位置。具体来说，我们用三个整数分别表示列、左对角线和右对角线上已放置皇后的情况。
    例如，cols=0b1001 表示第 1 和第 4 列上已有皇后，leftDiagonal=0b10000 表示第 5 条左对角线上已有皇后。
    在 dfs() 方法中，我们用一个整型变量 bits 表示当前行可用的位置，其中 '1' 表示可用，'0' 表示不可用。使用位运算技巧可以方便地计算出可用的位置。例如，~(cols | leftDiagonal | rightDiagonal) 中的 ~ 表示按位取反，| 表示按位或运算，& 表示按位与运算，<< 和 >> 分别表示左移和右移运算。
    在每个可用的位置上递归调用 dfs() 方法继续搜索下一行。当找到一组解时，我们将其转换为字符串并保存到结果集中。
     */
    public static List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        dfs2(n, 0, 0, 0, 0, new ArrayList<>(), res);
        return res;
    }

    private static void dfs2(int n, int row, int cols, int leftDiagonal, int rightDiagonal, List<String> path, List<List<String>> res) {
        if (row == n) { // 已找到一组解
            res.add(new ArrayList<>(path));
        } else {
            // 计算可用的位置，'1' 表示可用，'0' 表示不可用
            int bits = (~(cols | leftDiagonal | rightDiagonal)) & ((1 << n) - 1);
            while (bits != 0) {
                int pos = bits & -bits; // 取最低位的 1
                bits = bits & (bits - 1); // 将最低位的 1 置为 0，表示在该位置放置皇后
                int col = Integer.bitCount(pos - 1); // 皇后所在的列号
                char[] rowChars = new char[n];
                Arrays.fill(rowChars, '.');
                rowChars[col] = 'Q';
                path.add(new String(rowChars)); // 将解转换为字符串
                dfs2(n, row + 1, cols | pos, (leftDiagonal | pos) << 1, (rightDiagonal | pos) >> 1, path, res);
                path.remove(path.size() - 1); // 回溯到上一步
            }
        }
    }

    public static List<List<String>> solveNQueens3(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean[] cols = new boolean[n]; // 列
        boolean[] leftDiagonal = new boolean[2 * n - 1]; // 左对角线
        boolean[] rightDiagonal = new boolean[2 * n - 1]; // 右对角线
        dfs3(n, 0, cols, leftDiagonal, rightDiagonal, new ArrayList<>(), res);
        return res;
    }

    /*
    解法二：递归+回溯
    这种解法使用递归和回溯方法，通过枚举每一行，选择合适列上放置皇后。在 dfs() 方法中，我们用一个 boolean 型数组表示列、左对角线和右对角线上已放置皇后的情况。
    在 dfs() 方法中，我们遍历每一列，判断当前位置是否可以放置皇后。如果可以放置，我们将皇后所在的位置标记为已占用，并递归调用 dfs() 方法继续搜索下一行。
    当找到一组解时，我们将其转换为字符串并保存到结果集中。
    在回溯时，我们需要清除当前行皇后的位置，并将其所在的列、左对角线和右对角线标记为未占用。
     */
    private static void dfs3(int n, int row, boolean[] cols, boolean[] leftDiagonal, boolean[] rightDiagonal, List<String> path, List<List<String>> res) {
        if (row == n) { // 已找到一组解
            res.add(new ArrayList<>(path));
        } else {
            for (int col = 0; col < n; col++) { // 遍历每一列
                if (!cols[col] && !leftDiagonal[row - col + n - 1] && !rightDiagonal[row + col]) {
                    cols[col] = true; // 在该位置放置皇后
                    leftDiagonal[row - col + n - 1] = true;
                    rightDiagonal[row + col] = true;
                    char[] rowChars = new char[n];
                    Arrays.fill(rowChars, '.');
                    rowChars[col] = 'Q';
                    path.add(new String(rowChars)); // 将解转换为字符串
                    dfs3(n, row + 1, cols, leftDiagonal, rightDiagonal, path, res);
                    path.remove(path.size() - 1); // 回溯到上一步
                    cols[col] = false; // 回溯到上一步，清除当前行的皇后位置
                    leftDiagonal[row - col + n - 1] = false;
                    rightDiagonal[row + col] = false;
                }
            }
        }
    }
}
