package com.solution._0134;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] gas = new int[]{1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};
        int res = solution.canCompleteCircuit(gas, cost);
        int res2 = solution.canCompleteCircuit2(gas, cost);
        System.out.println(res);
        System.out.println(res2);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = n - 1, j = n - 1;
        int cnt = 0, s = 0;//步行距离和剩余油量
        while (cnt < n) {
            s += gas[j] - cost[j];
            ++cnt;
            j = (j + 1) % n;
            while (s < 0 && cnt < n) {
                --i;
                s += gas[i] - cost[i];
                ++cnt;
            }
        }
        return s < 0 ? -1 : i;
    }

    /*
    对于每个节点 i，我们计算从该节点出发能否到达节点 i+1。如果能够到达，则转移到 i+1 继续判断；如果不能到达，则从节点 i+1 重新开始计算。
    此外，我们还需要维护一个变量 sum，记录从当前节点出发到下一站剩余的油量。如果 sum 小于 0，则说明从当前节点出发无法到达下一站，需要重新选择起点。
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;  // 起点
        while (i < n) {
            int sum = 0;  // 从当前节点出发到下一站剩余的油量
            int j = i;
            while (true) {
                sum += gas[j] - cost[j];
                if (sum < 0) {  // 从当前节点出发无法到达下一站
                    break;
                }
                j = (j + 1) % n;//j为终点
                if (j == i) {  // 能够绕一圈回到起点
                    return i;
                }
            }
            i = j + 1;  // 从下一个节点重新开始
            if (i >= n) {
                break;
            }
        }
        return -1;
    }

}
