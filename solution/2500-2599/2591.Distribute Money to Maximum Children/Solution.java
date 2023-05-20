package com.solution._2591;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        if (money > 8 * children) {
            return children - 1;
        }
        if (money == 8 * children - 4) {
            return children - 2;
        }
        // money-8x >= children-x, x <= (money-children)/7
        return (money - children) / 7;
    }
}
