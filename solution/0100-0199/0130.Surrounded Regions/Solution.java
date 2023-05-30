package com.solution._0130;

public class Solution {
    private char[][] board;
    private int m;
    private int n;

    public static void main(String[] args) {
        char[][] chars = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
//        Solution solution = new Solution();
//        solution.solve(chars);
//        for (char[] aChar : chars) {
//            for (int j = 0; j < chars[0].length; j++) {
//                System.out.println(aChar[j]);
//            }
//        }
        Solution2 solution2 = new Solution2();
        solution2.solve(chars);
        for (char[] aChar : chars) {
            for (int j = 0; j < chars[0].length; j++) {
                System.out.println(aChar[j]);
            }
        }
    }

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && board[i][j] == 'O') {
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '.') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(int i, int j) {
        board[i][j] = '.';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                dfs(x, y);
            }
        }
    }

    private static class Solution2 {
        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0) {
                return;
            }
            int m = board.length, n = board[0].length;
            // 标记所有与边界相连的 'O'
            for (int i = 0; i < m; i++) {
                dfs(board, i, 0);
                dfs(board, i, n - 1);
            }
            for (int j = 0; j < n; j++) {
                dfs(board, 0, j);
                dfs(board, m - 1, j);
            }
            // 处理未被标记的 'O'
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    } else if (board[i][j] == '#') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private void dfs(char[][] board, int i, int j) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
                return;
            }
            board[i][j] = '#';  // 标记为特殊字符
            dfs(board, i - 1, j);
            dfs(board, i + 1, j);
            dfs(board, i, j - 1);
            dfs(board, i, j + 1);
        }

    }
}
