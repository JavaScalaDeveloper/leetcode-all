/*
// Definition for Employee.
package com.solution._0690;
import change.datastructure.*;
import java.util.*;
public class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

package com.solution._0690;
import change.datastructure.*;
import java.util.*;
public class Solution {

    private final Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        int sum = employee.importance;
        for (Integer subordinate : employee.subordinates) {
            sum += dfs(subordinate);
        }
        return sum;
    }
}
