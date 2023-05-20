package com.solution._2651;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }
}
