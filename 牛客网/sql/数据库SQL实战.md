# 牛客数据库SQL实战题(1-10题)

## 1、查找最晚入职员工的所有信息

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select *
from employees
where hire_date=(select max(hire_date) from employees);
```

这里主要的争论点是，最晚入职的是一个人，还是最晚时间对应的多个人。上述答案是以时间为准，筛选出最晚时间，然后找到对应最晚时间的所有人。

## 2、查找入职员工时间排名倒数第三的员工所有信息

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select *
from employees
where hire_date=(select distinct hire_date 
                 from employees 
                 order by hire_date desc 
                 limit 2,1);
```

这里主要的技巧是使用limit，Limit接受一个或两个数字参数。参数必须是一个整数常量。如果给定两个参数，第一个参数指定第一个返回记录行的偏移量，第二个参数指定返回记录行的最大数目。

```sql
select * from table limit 5,10;
select * from table limit 5,-1;
```

上面的代码第一个是检索第6行到15行，包括第15行，也就是返回从第6行开始的接下来的10行。
第二个是检索从第6行开始直到结束的所有行。-1表示倒数第一个。

## 3、查找各个部门当前(to_date='9999-01-01')领导当前薪水详情以及其对应部门编号dept_no

```sql
CREATE TABLE dept_manager (
dept_no char(4) NOT NULL,
emp_no int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select sa.*, de.dept_no
from (select * from salaries where to_date='9999-01-01') as sa
join (select * from dept_manager where to_date='9999-01-01') as de
on sa.emp_no=de.emp_no;
```

这题主要的点是dept_manager和salaries哪个是主表，答案是salaries表是主表，也就是说，当我们join表时，salaries表在左侧。
先在salaries和dept_no表中，选中所有to_date='9999-01-01'
的行，也就是过滤掉失效信息，筛选出当前薪水和领导信息。然后题目想要获取的from_date是关于薪水的from_date，而不是dept_manager表的from_date(面向答案编程)
。我们要筛选的列是salaries表的所有列，和dept_manager表中的dept_no列。
这题的`join`是内连接，写`inner join`也可以。`outer join`、`left join`、`right join`不行。
另外，讨论区有人谈到可以用自然连接`natural join`。自然连接是一种特殊的等值连接，自然连接在结果中会把重复的属性列去掉。一般的连接操作是从行的角度进行运算，但是自然连接还需要取消重复列，所以是同时从行和列的角度进行运算。

## 其他答案

```sql
select sa.* ,de.dept_no
from salaries as sa 
join dept_manager as de 
on sa.emp_no=de.emp_no
where sa.to_date ='9999-01-01' and de.to_date='9999-01-01';
```

## 4、查找所有已经分配部门的员工的last_name和first_name

```sql
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select em.last_name, em.first_name, de.dept_no
from dept_emp as de 
join employees as em
on de.emp_no=em.emp_no;
```

因为dept_emp表中的dept_no非空，dept_emp表保存的一定是已经分配了部门的人。

## 5、查找所有员工的last_name和first_name以及对应部门编号dept_no，也包括展示没有分配具体部门的员工

```sql
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select em.last_name, em.first_name, de.dept_no
from employees as em 
left join dept_emp as de
on em.emp_no=de.emp_no;
```

这题主要是`left join`。

## 6、查找所有员工入职时候的薪水情况，给出emp_no以及salary， 并按照emp_no进行逆序

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select em.emp_no, sa.salary
from employees as em
join salaries as sa
on em.emp_no=sa.emp_no and em.hire_date=sa.from_date
order by em.emp_no desc;
```

emplyees表中的主键是emp_no，也就是说一个员工只有一个号码。但是salaries表中的主键是emp_no和from_date，也就是说员工可能变更薪水，所以同一个emp_no可能有多条记录。要查找员工入职时候的薪水，表达式为`em.hire_date=sa.from_date`
。
上述sql语句写left join也是正确的。

## 其他答案

```sql
select em.emp_no, sa.salary
from employees as em, salaries as sa
where em.emp_no=sa.emp_no and em.hire_date=sa.from_date
order by em.emp_no desc;
```

这里是直接查询两张表，并没有将表进行join，直接在where中进行筛选。

## 7、查找薪水涨幅超过15次的员工号emp_no以及其对应的涨幅次数t

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select emp_no, count(from_date) as t
from salaries
group by emp_no
having t>15;
```

这题主要是where和having的区别：
他们的本质的区别是where筛选的是数据库表里面本来就有的字段，而having筛选的字段是从前筛选的字段筛选的。
例如：

```sql
select a, b from table where a>5;
select a, b from table having a>5;
//以上两种都正确
select a, b from table where c>3;
select a, b from table having c>3;//错误
//用where正确，但用having错误。
//用where是先筛选行，再select列；但having是先select列，再having筛选行。
//也就是说，如果用having，select的列中a,b没有c，则用having错误。
select a, avg(b) as avg_b from table where avg_b>2 group by a;//错误
select a, avg(b) as avg_b from table group by a having avg_b>2;
//我们根据a分组聚合，并将聚合的列命名为avg_b。
//原表中没有avg_b，所以不能用where对avg_b进行行筛选
//只能用having，在筛选了列、聚合之后，再用having进行筛选行。
```

# 8、找出所有员工当前(to_date='9999-01-01')具体的薪水salary情况，对于相同的薪水只显示一次,并按照逆序显示

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select distinct salary
from salaries
where to_date='9999-01-01'
order by salary desc;
```

排序用order by，这里去重用的是distinct，也可以用group by。group by的写法在下面的其他答案中。
这里主要的问题是distince和group by的性能区别：
1）当重复量非常巨大，如1000w条中有300w条重复数据，用distinct比较好。
2）当重复量较小，如1000w条中有1w条重复数据，用group by比较好。

## 其他答案

```sql
select salary
from salaries
where to_date='9999-01-01'
group by salary
order by salary desc;
```

## 9、获取所有部门当前manager的当前薪水情况，给出dept_no, emp_no以及salary，当前表示to_date='9999-01-01'

```sql
CREATE TABLE dept_manager (
dept_no char(4) NOT NULL,
emp_no int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select de.dept_no, de.emp_no, sa.salary
from dept_manager as de
join salaries as sa
on de.emp_no=sa.emp_no 
and de.to_date='9999-01-01' 
and sa.to_date='9999-01-01';
```

此题讨论区有个争论点是，有人认为一个人可能同时任两个部门的领导，这里我们假设只能在一个部门工作，也就是如果换了部门，则to_date会变为当前时间而不是\`9999-01-01\`。

## 其他答案

```sql
select de.dept_no, de.emp_no, sa.salary
from dept_manager as de
join salaries as sa
on de.emp_no=sa.emp_no 
where de.to_date='9999-01-01' 
and sa.to_date='9999-01-01';
```

将时间筛选放到where中也可以通过。

## 10、获取所有非manager的员工emp_no

```sql
CREATE TABLE dept_manager (
dept_no char(4) NOT NULL,
emp_no int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select emp_no
from employees
where emp_no not in 
(select distinct emp_no
from dept_manager
where to_date='9999-01-01');
```

这题主要考察'not in'。

# 牛客数据库SQL实战题(11-20题)

## 11、获取所有员工当前的manager

如果当前的manager是自己的话结果不显示，当前表示to_date='9999-01-01'。结果第一列给出当前员工的emp_no,第二列给出其manager对应的manager_no。

```sql
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE dept_manager (
dept_no char(4) NOT NULL,
emp_no int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));
```

### 答案

```sql
select emp.emp_no, ma.emp_no as manager_no
from dept_emp as emp
join dept_manager as ma
on ma.dept_no=emp.dept_no
where ma.to_date='9999-01-01' and emp.to_date='9999-01-01'
and emp.emp_no <> ma.emp_no;
```

如果当前的manager是自己的话不显示该条，这个条件用不等号来表示就可以。

## 12、获取所有部门中当前员工薪水最高的相关信息，给出dept_no, emp_no以及其对应的salary

```sql
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select de.dept_no, de.emp_no, max(sa.salary) as salary
from dept_emp as de 
join salaries as sa
on de.emp_no=sa.emp_no
where de.to_date='9999-01-01' and sa.to_date='9999-01-01'
group by de.dept_no;
```

根据de.dept_no分组，选择sa.salary最大的行，de.emp_no也会选择相应的行。

## 13、从titles表获取按照title进行分组，每组个数大于等于2，给出title以及对应的数目t

```sql
CREATE TABLE IF NOT EXISTS titles (
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```sql
select title, count(title) as t
from titles
group by title
having t>=2;
```

select title之后还可以count(title)。

## 14、从titles表获取按照title进行分组，每组个数大于等于2，给出title以及对应的数目t。

注意对于重复的emp_no进行忽略。

```sql
CREATE TABLE IF NOT EXISTS titles (
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```sql
select title, count(distinct emp_no) as t
from titles
group by title
having t>=2;
```

要去除重复的emp_no可以在count中对distinct emp_no进行计数。

## 15、查找employees表所有emp_no为奇数，且last_name不为Mary的员工信息，并按照hire_date逆序排列

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select *
from employees
where emp_no%2=1 and last_name<>'Mary'
order by hire_date desc;
```

mysql中取余用%除数，在oracle中取余是mod(被除数, 除数)。

## 16、统计出当前各个title类型对应的员工当前薪水对应的平均工资。结果给出title以及平均工资avg。

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));

CREATE TABLE IF NOT EXISTS titles (
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```sql
select ti.title as title, avg(sa.salary) as avg
from titles as ti
join salaries as sa
on ti.emp_no=sa.emp_no
where ti.to_date='9999-01-01' and sa.to_date='9999-01-01'
group by ti.title;
```

## 17、获取当前（to_date='9999-01-01'）薪水第二多的员工的emp_no以及其对应的薪水salary

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select emp_no, salary
from salaries
where to_date='9999-01-01' and salary=
(select distinct salary 
 from salaries 
 order by salary desc
 limit 1,1);
```

薪水第二多的人可能有多个

## 18、查找当前薪水(to_date='9999-01-01')排名第二多的员工编号emp_no、薪水salary、last_name以及first_name，不准使用order by

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select em.emp_no, max(sa.salary) as salary, em.last_name, em.first_name
from employees as em
join salaries as sa
on em.emp_no=sa.emp_no
where sa.to_date='9999-01-01' and sa.salary<
(select max(salary) from salaries where to_date='9999-01-01');
```

不用order by选择次高，则先选出最高，然后排出最高，选出剩下数据中的最高。

## 19、查找所有员工的last_name和first_name以及对应的dept_name，也包括暂时没有分配部门的员工

```sql
CREATE TABLE departments (
dept_no char(4) NOT NULL,
dept_name varchar(40) NOT NULL,
PRIMARY KEY (dept_no));

CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select em.last_name, em.first_name, dename.dept_name
from (employees as em 
      left join dept_emp as de 
      on em.emp_no=de.emp_no)
left join departments as dename
on de.dept_no=dename.dept_no;
```

连接三张表，先将employees表和dept_emp表左连接，employees中的有的员工没有分配部门，所以要用left join。也会有员工有多个部门的记录，这题并没有要求筛选当前。

## 20、查找员工编号emp_no为10001其自入职以来的薪水salary涨幅值growth

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

##答案

```sql
select (select salary 
        from salaries 
        where emp_no='10001' 
        order by from_date desc 
        limit 1) -
       (select salary 
        from salaries 
        where emp_no='10001' 
        order by from_date 
        limit 1) as growth;
```

另外，在MS Access中支持first()函数和last()函数，分别可以获取列的第一个值和最后一个值。但是其他数据库不支持，一般用limit来选择。

# 牛客数据库SQL实战题(21-30题)

## 21、查找所有员工自入职以来的薪水涨幅情况，给出员工编号emp_no以及其对应的薪水涨幅growth，并按照growth进行升序

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select now.emp_no, (now.salary-pre.salary) as growth
from (select em.emp_no, sa.salary 
      from employees as em
      join salaries as sa
      on em.emp_no=sa.emp_no
      where sa.to_date='9999-01-01') as now
join (select em.emp_no, sa.salary
      from employees as em 
      join salaries as sa 
      on em.emp_no=sa.emp_no 
      where em.hire_date=sa.from_date) as pre
on now.emp_no=pre.emp_no
order by growth;
```

分别建两张表，一张表是emp_no和对应的开始工资，一张表是emp_no和对应的当前工资。然后将两张表连接起来，做减法。

## 22、统计各个部门对应员工涨幅的次数总和，给出部门编码dept_no、部门名称dept_name以及次数sum

```sql
CREATE TABLE departments (
dept_no char(4) NOT NULL,
dept_name varchar(40) NOT NULL,
PRIMARY KEY (dept_no));

CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select de.dept_no, dename.dept_name, count(sa.salary) as sum
from (dept_emp as de
join departments as dename on dename.dept_no=de.dept_no)
join salaries as sa on de.emp_no=sa.emp_no
group by de.dept_no;
```

## 23、对所有员工的当前(to_date='9999-01-01')薪水按照salary进行按照1-N的排名，相同salary并列且按照emp_no升序排列

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select sa1.emp_no, sa1.salary, count(distinct sa2.salary) as rank
from salaries as sa1
left join salaries as sa2
on sa2.salary>=sa1.salary
where sa1.to_date='9999-01-01' and sa2.to_date='9999-01-01'
group by sa1.emp_no
order by sa1.salary desc, sa1.emp_no;
```

排序这个字段需要造出一个字段并count，为了构造出rank，我们可以复用salaries表，对salaries1表中的每一个salary，对大于或等于s1.salary的sa.salary进行count。
参考[牛客网](https://www.nowcoder.com/questionTerminal/b9068bfe5df74276bd015b9729eec4bf)，在支持ROW_NUMBER、RANK、DENSE_RANK等函数的SQL
Server数据库中，有以下参考代码，可惜在本题的SQLite数据库中不支持。

```sql
SELECT emp_no, salaries, DENSE_RANK() OVER(ORDER BY salary DESC) AS rank
WHERE to_date ='9999-01-01'
ORDER BY salary DESC, emp_no ASC
```

## 24、获取所有非manager员工当前的薪水情况，给出dept_no、emp_no以及salary ，当前表示to_date='9999-01-01'

```sql
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE dept_manager (
dept_no char(4) NOT NULL,
emp_no int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select dema.dept_no, em.emp_no, sa.salary
from employees as em
join dept_emp as de
on em.emp_no=de.emp_no
join dept_manager as dema
on de.dept_no=dema.dept_no
join salaries as sa
on em.emp_no=sa.emp_no
where sa.to_date='9999-01-01' and em.emp_no<>dema.emp_no
```

## 25、获取员工其当前的薪水比其manager当前薪水还高的相关信息，当前表示to_date='9999-01-01'

结果第一列给出员工的emp_no，
第二列给出其manager的manager_no，
第三列给出该员工当前的薪水emp_salary,
第四列给该员工对应的manager当前的薪水manager_salary

```sql
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE dept_manager (
dept_no char(4) NOT NULL,
emp_no int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select em.emp_no, ma.emp_no as manager_no, sa1.salary as emp_salary, sa2.salary as manager_salary
from dept_emp as em
join dept_manager as ma
on em.dept_no=ma.dept_no 
    and em.to_date='9999-01-01' 
    and ma.to_date='9999-01-01'
    and em.emp_no<>ma.emp_no
join salaries as sa1
on em.emp_no=sa1.emp_no and sa1.to_date='9999-01-01'
join salaries as sa2
on ma.emp_no=sa2.emp_no and sa2.to_date='9999-01-01' 
where sa1.salary>sa2.salary
```

重复利用salary。

## 26、汇总各个部门当前员工的title类型的分配数目

结果给出部门编号dept_no、dept_name、其当前员工所有的title以及该类型title对应的数目count

```sql
CREATE TABLE departments (
dept_no char(4) NOT NULL,
dept_name varchar(40) NOT NULL,
PRIMARY KEY (dept_no));

CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE IF NOT EXISTS titles (
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```sql
select de.dept_no, name.dept_name, ti.title, count(ti.title)
from dept_emp as de
join departments as name
on de.dept_no=name.dept_no and de.to_date='9999-01-01'
join titles as ti
on de.emp_no=ti.emp_no and ti.to_date='9999-01-01'
group by de.dept_no, ti.title
```

## 27、给出每个员工每年薪水涨幅超过5000的员工编号emp_no

薪水变更开始日期from_date以及薪水涨幅值salary_growth，并按照salary_growth逆序排列。
提示：在sqlite中获取datetime时间对应的年份函数为strftime('%Y', to_date)

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select s2.emp_no, s2.from_date, (s2.salary - s1.salary) as salary_growth
from salaries as s1, salaries as s2
where s1.emp_no = s2.emp_no 
and salary_growth > 5000
and (strftime("%Y",s2.to_date) - strftime("%Y",s1.to_date) = 1 
     or strftime("%Y",s2.from_date) - strftime("%Y",s1.from_date) = 1 )
order by salary_growth desc
```

每年薪水涨幅的定义并不是非常明确。

## 28、查找描述信息中包括robot的电影对应的分类名称以及电影数目，而且还需要该分类对应电影数量>=5部

```sql
CREATE TABLE IF NOT EXISTS film (
film_id smallint(5)  NOT NULL DEFAULT '0',
title varchar(255) NOT NULL,
description text,
PRIMARY KEY (film_id));

CREATE TABLE category  (
category_id  tinyint(3)  NOT NULL ,
name  varchar(25) NOT NULL, 
last_update timestamp,
PRIMARY KEY ( category_id ));

CREATE TABLE film_category  (
film_id  smallint(5)  NOT NULL,
category_id  tinyint(3)  NOT NULL, 
last_update timestamp);
```

### 答案

```sql
select c.name, count(fc.film_id)
from (select category_id, count(film_id) as amount
      from film_category
      group by category_id
      having amount>=5) as cc, 
      film as f, category as c, film_category as fc
where f.description like '%robot%'
and f.film_id=fc.film_id
and fc.category_id=c.category_id
and c.category_id=cc.category_id;
```

首先，此题所说的该分类对应电影数量>=5，是该分类下所有电影的数量大>=5，而不是筛选了描述中有robot字段之后电影数量依然>=5的分类。
其次，这题的代码还是让人摸不着头脑，第一行的select c.name, count(fc.film_id)中的count(fc.film_id)很奇怪，还没有group
by。用join各表的方法写的代码无法通过。作者才疏学浅，如果你有好的想法请联系我。

## 29、使用join查询方式找出没有分类的电影id以及名称

```sql
CREATE TABLE IF NOT EXISTS film (
film_id smallint(5)  NOT NULL DEFAULT '0',
title varchar(255) NOT NULL,
description text,
PRIMARY KEY (film_id));

CREATE TABLE category  (
category_id  tinyint(3)  NOT NULL ,
name  varchar(25) NOT NULL,
last_update timestamp,
PRIMARY KEY ( category_id ));

CREATE TABLE film_category  (
film_id  smallint(5)  NOT NULL,
category_id  tinyint(3)  NOT NULL, 
last_update timestamp);
```

### 答案

```sql
select f.film_id, f.title
from film as f
left join film_category as fc
on f.film_id=fc.film_id
where fc.category_id is null;
```

## 30、使用子查询的方式找出属于Action分类的所有电影对应的title,description

```sql
CREATE TABLE IF NOT EXISTS film (
film_id smallint(5)  NOT NULL DEFAULT '0',
title varchar(255) NOT NULL,
description text,
PRIMARY KEY (film_id));

CREATE TABLE category  (
category_id  tinyint(3)  NOT NULL ,
name  varchar(25) NOT NULL, `last_update` timestamp,
PRIMARY KEY ( category_id ));

CREATE TABLE film_category  (
film_id  smallint(5)  NOT NULL,
category_id  tinyint(3)  NOT NULL, `last_update` timestamp);
```

### 答案

子查询方式：

```sql
select f.title, f.description
from film as f
where f.film_id 
in (select fc.film_id 
    from film_category as fc 
    where fc.category_id 
    in (select c.category_id 
        from category as c 
        where name='Action'))
```

非子查询方式：

```sql
select f.title,f.description
from film as f 
inner join film_category as fc 
on f.film_id = fc.film_id
inner join category as c 
on c.category_id = fc.category_id
where c.name = 'Action';
```

# 牛客数据库SQL实战题(31-40题)

## 31、获取select * from employees对应的执行计划

牛客这个用的是SQLite，在SQLite数据库中，可以用 "EXPLAIN" 关键字或 "EXPLAIN QUERY PLAN" 短语，用于描述表的细节。
参考：[牛客讨论区](https://www.nowcoder.com/questionTerminal/18f30bb19fd34abebcf7e6397c7fb5d8)

```sql
explain select * from employees;
```

## 32、将employees表的所有员工的last_name和first_name拼接起来作为Name，中间以一个空格区分

```sql
CREATE TABLE employees ( emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

不同数据库连接字符串的方法不完全相同，MySQL、SQL Server、Oracle等数据库支持CONCAT方法，而本题所用的SQLite数据库只支持用连接符号"||"来连接字符串。
参考：[牛客讨论区](https://www.nowcoder.com/questionTerminal/6744b90bbdde40209f8ecaac0b0516fe)

```sql
select last_name||" "||first_name as Name
from employees
```

## 33、创建一个actor表，包含如下列信息


| 列表        | 类型        | 是否为NULL | 含义                               |
| ----------- | ----------- | ---------- | ---------------------------------- |
| actor_id    | smallint(5) | not null   | 主键id                             |
| first_name  | varchar(45) | not null   | 名字                               |
| last_name   | varchar(45) | not null   | 姓氏                               |
| last_update | timestamp   | not null   | 最后更新时间，默认是系统的当前时间 |

### 答案

```sql
create table if not exists actor(
actor_id smallint(5) not null primary key,
first_name varchar(45) not null,
last_name varchar(45) not null,
last_update timestamp not null default(datetime('now','localtime')));
```

primary key也可以写在后面，写primary key(actor_id)。在last_update末尾加上DEFAULT设置默认值，默认值为(datetime('now','localtime'))，即获得系统时间。

## 34、批量插入数据

对于表actor批量插入如下数据

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')))
```


| actor_id | first_name | last_name | last_update         |
| -------- | ---------- | --------- | ------------------- |
| 1        | PENELOPE   | GUINESS   | 2006-02-15 12:34:33 |
| 2        | NICK       | WAHLBERG  | 2006-02-15 12:34:33 |

### 答案

利用values：

```sql
insert into actor
values(1,'PENELOPE','GUINESS','2006-02-15 12:34:33'),
(2,'NICK','WAHLBERG','2006-02-15 12:34:33');
```

利用UNION SELECT批量插入：

```sql
insert into actor
select 1,'PENELOPE','GUINESS','2006-02-15 12:34:33'
union select 2,'NICK','WAHLBERG','2006-02-15 12:34:33';
```

## 35、批量插入数据，不使用replace操作

对于表actor批量插入如下数据,如果数据已经存在，请忽略，不使用replace操作。

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')))
```


| actor_id | first_name | last_name | last_update           |
| -------- | ---------- | --------- | --------------------- |
| 3        | 'ED'       | 'CHASE'   | '2006-02-15 12:34:33' |

### 答案

在SQLite中，insert or ignore表示如果不存在则插入，如果存在则忽略；insert or replace表示如果不存在则插入，如果存在则替换。

```sql
insert or ignore into actor
values(3, 'ED', 'CHASE', '2006-02-15 12:34:33')
```

```sql
insert or replace into actor
values(3, 'ED', 'CHASE', '2006-02-15 12:34:33')
```

在MySQL中则是使用insert ingnore和insert replace。

## 36、创建一个actor_name表

对于如下表actor，其对应的数据为:


| actor_id                                                                                       | first_name  | last_name  | last_update         |
| ---------------------------------------------------------------------------------------------- | ----------- | ---------- | ------------------- |
| 1                                                                                              | PENELOPE    | GUINESS    | 2006-02-15 12:34:33 |
| 2                                                                                              | NICK        | WAHLBERG   | 2006-02-15 12:34:33 |

创建一个actor_name表，将actor表中的所有first_name以及last_name导入改表。 actor_name表结构如下： 
                   

| 列表                                                                                           | 类型        | 是否为NULL | 含义                |
| -                                                                                              | -           | -          | -                   |
| first_name                                                                                     | varchar(45) | not null   | 名字                |
| last_name                                                                                      | varchar(45) | not null   | 姓氏                |

### 答案

创建表和插入数据合并在一条语句中：

```sql
create table actor_name as
select first_name, last_name from actor;
```

先创建表再插入数据：

```sql
create table actor_name(
first_name varchar(45) not null,
last_name varchar(45) not null
);
insert into actor_name
select first_name, last_name from actor;
```

## 37、对first_name创建唯一索引uniq_idx_firstname

针对如下表actor结构创建索引：

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')))
```

对first_name创建唯一索引uniq_idx_firstname，对last_name创建普通索引idx_lastname

### 答案

```sql
create unique index uniq_idx_firstname on actor(first_name);
create index idx_lastname on actor(last_name);
```

## 38、针对actor表创建视图actor_name_view

只包含first_name以及last_name两列，并对这两列重新命名，first_name为first_name_v，last_name修改为last_name_v：

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')))
```

### 答案

```sql
create view actor_name_view as
select first_name as first_name_v, last_name as last_name_v
from actor;
```

```sql
create view actor_name_view (first_name_v, last_name_v) as
select first_name, last_name
from actor;
```

create view ... as ...创建视图。

## 39、针对上面的salaries表emp_no字段创建索引idx_emp_no

针对salaries表emp_no字段创建索引idx_emp_no，查询emp_no为10005, 使用强制索引。

```sql
CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
select * from salaries indexed by idx_emp_no where emp_no=10005
```

参考：[索引](https://blog.csdn.net/qq_36071795/article/details/83956068)
数据库的索引问题就是查找问题。数据库索引，是数据库管理系统中一个排序的数据结构，以协助快速查询、更新数据库中表的数据。索引的实现通常使用B树和变种的B+树(mysql常用的索引就是B+树)。
除了数据之外,数据库系统还维护为满足特定查找算法的数据结构,这些数据结构以某种方式引用数据.这种数据结构就是索引。
在MySQL中为force index：

```sql
select * from salaries force index (idx_emp_no) where emp_no = 10005
```

## 40、在last_update后面新增加一列名字为create_date

存在actor表，包含如下列信息：

```sql
CREATE TABLE IF NOT EXISTS actor (
actor_id smallint(5) NOT NULL PRIMARY KEY,
first_name varchar(45) NOT NULL,
last_name varchar(45) NOT NULL,
last_update timestamp NOT NULL DEFAULT (datetime('now','localtime')));
```

现在在last_update后面新增加一列名字为create_date, 类型为datetime, NOT NULL，默认值为'0000 00:00:00'。(这里的默认值写的有问题，默认值为'0000-00-00 00:
00;00'才能通过)

### 答案

```sql
alter table actor 
add column create_date datetime not null default '0000-00-00 00:00:00'
```

其中的column可以省略，not null 和default可以交换位置，default值可以加括号。

# 牛客数据库SQL实战题(41-50题)

## 41、构造一个触发器audit_log

构造一个触发器audit_log，在向employees_test表中插入一条数据的时候，触发插入相关的数据到audit中。

```sql
CREATE TABLE employees_test(
ID INT PRIMARY KEY NOT NULL,
NAME TEXT NOT NULL,
AGE INT NOT NULL,
ADDRESS CHAR(50),
SALARY REAL
);

CREATE TABLE audit(
EMP_no INT NOT NULL,
NAME TEXT NOT NULL
);
```

### 答案

```sql
create trigger audit_log after insert on employees_test
begin
insert into audit values(new.id, new.name);
end;
```

参考：[牛客讨论区](https://www.nowcoder.com/questionTerminal/7e920bb2e1e74c4e83750f5c16033e2e)
构造触发器时注意以下几点：
1）用 CREATE TRIGGER 语句构造触发器，用 BEFORE或AFTER 来指定在执行后面的SQL语句之前或之后来触发TRIGGER。
2）触发器执行的内容写出 BEGIN与END 之间。
3）可以使用 NEW与OLD 关键字访问触发后或触发前的employees_test表单记录。

## 42、删除emp_no重复的记录，只保留最小的id对应的记录。

```
CREATE TABLE IF NOT EXISTS titles_test (
id int(11) not null primary key,
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```sql
delete from titles_test 
where emp_no not in (select min(id) from titles_test group by emp_no)
```

## 43、将所有to_date为9999-01-01的全部更新为NULL

将所有to_date为9999-01-01的全部更新为NULL,且 from_date更新为2001-01-01。

```sql
CREATE TABLE IF NOT EXISTS titles_test (
id int(11) not null primary key,
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```
update titles_test 
set to_date=null, from_date='2001-01-01'
where to_date='9999-01-01'
```

update后面不用加table关键字，修改的两列用逗号链接，而不是and。

## 44、将id=5以及emp_no=10001的行数据替换成id=5以及emp_no=10005

其他数据保持不变，使用replace实现。

```sql
CREATE TABLE IF NOT EXISTS titles_test (
id int(11) not null primary key,
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```sql
update titles_test
set emp_no=replace(emp_no, 10001, 10005)
where id=5
```

运用REPLACE(X,Y,Z)函数。其中X是要处理的字符串，Y是X中将要被替换的字符串，Z是用来替换Y的字符串，最终返回替换后的字符串。

## 45、将titles_test表名修改为titles_2017

```sql
CREATE TABLE IF NOT EXISTS titles_test (
id int(11) not null primary key,
emp_no int(11) NOT NULL,
title varchar(50) NOT NULL,
from_date date NOT NULL,
to_date date DEFAULT NULL);
```

### 答案

```sql
alter table titles_test rename to titles_2017
```

mysql中不用写to

## 46、在audit表上创建外键约束，其emp_no对应employees_test表的主键id

```sql
CREATE TABLE employees_test(
ID INT PRIMARY KEY NOT NULL,
NAME TEXT NOT NULL,
AGE INT NOT NULL,
ADDRESS CHAR(50),
SALARY REAL
);

CREATE TABLE audit(
EMP_no INT NOT NULL,
create_date datetime NOT NULL
);
```

### 答案

```sql
drop table audit;
create table audit(
    emp_no int not null,
    create_date datetime not null,
    foreign key(emp_no) references employees_test(id));
```

这一题判断代码的程序有问题，中间的字段前面必须4个空格，并且最后的);提头写到新行会判错。
再就是SQLite中不能通过 ALTER TABLE ... ADD FOREIGN KEY ... REFERENCES ... 语句来对已创建好的字段创建外键，因此只能先删除表，再重新建表的过程中创建外键。
mysql使用ALTER添加外键的语句表达式为：ALTER TABLE tablename ADD FOREIGN
KEY...REFERENCES...。而在这里不能使用alter来添加外键，因此就只能先删除表，然后再建立该表，在表中直接进行外键约束。
alter table my_tab1 add [constraint 外键名] foreign key(外键字段名) references mytab2(主键字段名);

## 47、如何获取emp_v和employees有相同的数据

存在如下的视图：

```sql
create view emp_v as select * from employees where emp_no >10005;
```

如何获取emp_v和employees有相同的数据？

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

其实这题，emp_v本来就是从employees导出的视图，要获取他们相同的数据，直接select * from emp_v就好。
用where：

```sql
select v.*
from emp_v as v, employees as em
where v.emp_no=em.emp_no
```

select v.\*或者select em.\*都可以，但是不能直接select \*，否则会得到两张表中符合条件的重复记录。
用intersect求交集的方法：

```sql
select *
from emp_v
intersect
select *
from employees
```

两表位置可以调换。

## 48、将所有获取奖金的员工当前的薪水增加10%

```sql
create table emp_bonus(
emp_no int not null,
recevied datetime not null,
btype smallint not null);

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL, 
PRIMARY KEY (emp_no,from_date));
```

### 答案

```sql
update salaries
set salary=salary*1.1
where emp_no in (select sa.emp_no
                 from salaries as sa 
                 inner join emp_bonus as bo
                 on sa.emp_no=bo.emp_no 
                 where sa.to_date='9999-01-01')
```

## 49、针对库中的所有表生成select count(*)对应的SQL语句

输出格式为：


| cnts                                                                                                                                      |
| ----------------------------------------------------------------------------------------------------------------------------------------- |
| select count(*) from employees;                                                                                                           |
| select count(*) from departmens;                                                                                                          |
| select count(*) from dept_emp;                                                                                                            |
| select count(*) from dept_manager;                                                                                                        |
| select count(*) from salaries;                                                                                                            |
| select count(*) from titles;                                                                                                              |
| 这题的意思是要获取所有表的名字，然后输出上面的表格，一张表一行。                                                                          |
| 参考：[牛客讨论区](https://www.nowcoder.com/questionTerminal/355036f7f0c8429a85281f7ac05b457a)                                            |
| 在 SQLite 系统表 sqlite_master 中可以获得所有表的索引，其中字段 name 是所有表的名字，而且对于自己创建的表而言，字段 type 永远是 'table'。 |
| 然后在 SQLite 中用 “                                                                                                                     |

### 答案

```sql
select "select count(*) from "|| name ||";" as cnts
from sqlite_master
where type='table'
```

在mysql中获取所有表名的语句为：

```sql
select table_name from information_schema.tables where table_schema='shop';
```

其中shop为数据库名字。
mysql中合并字段用concat()
所以mysql版本答案为：

```sql
create table hi as 
select table_name from information_schema.tables where table_schema='shop';

select * from hi;

select concat('select count(*) from', ' ', TABLE_NAME, ';') as cnts from hi;
```

或将上述两个步骤合并为一条语句为：

```sql
select concat('select count(*) from', ' ', TABLE_NAME, ';') as cnts
 from (select table_name from information_schema.tables where table_schema='shop') as hi;
```

## 50、将employees表中的所有员工的last_name和first_name通过(')连接起来

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

```sql
select last_name||"'"||first_name
from employees;
```

# 牛客数据库SQL实战题(51-61题)

## 51、查找字符串'10,A,B' 中逗号','出现的次数cnt

参考：[牛客讨论区](https://www.nowcoder.com/questionTerminal/e3870bd5d6744109a902db43c105bd50)
由于 SQLite 中没有直接统计字符串中子串出现次数的函数，因此本题用length()函数与replace()
函数的结合灵活地解决了统计子串出现次数的问题，属于技巧题，即先用replace函数将原串中出现的子串用空串替换，再用原串长度减去替换后字符串的长度，最后除以子串的长度（本题中此步可省略，若子串长度大于1则不可省）。

```sql
select (length("10,A,B")-length(replace("10,A,B",",","")))/length(",") AS cnt;
```

## 52、获取Employees中的first_name

查询按照first_name最后两个字母，按照升序进行排列。

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

输出格式：


| first_name |
| ---------- |
| Chirstaion |
| Tzvetan    |
| Bezalel    |

### 答案

参考：[牛客讨论区](https://www.nowcoder.com/questionTerminal/74d90728827e44e2864cce8b26882105)
本题考查 substr(X,Y,Z) 或 substr(X,Y) 函数的使用。其中X是要截取的字符串。Y是字符串的起始位置（注意第一个字符的位置为1，而不为0），取值范围是±(1\~length(X))。
当Y等于length(X)时，则截取最后一个字符；
当Y等于负整数-n时，则从倒数第n个字符处截取。
Z是要截取字符串的长度，取值范围是正整数，若Z省略，则从Y处一直截取到字符串末尾；
若Z大于剩下的字符串长度，也是截取到字符串末尾为止。

```sql
select first_name from employees 
order by substr(first_name, length(first_name)-1)
```

或

```sql
select first_name from employees 
order by substr(first_name, -2)
```

## 53、按照dept_no进行汇总

属于同一个部门的emp_no按照逗号进行连接，结果给出dept_no以及连接出的结果employees。

```sql
CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));
```

输出格式：


| dept_no | employees   |
| ------- | ----------- |
| d001    | 10001,10002 |
| d002    | 10006       |

### 答案

此题要用SQLite的聚合函数group_concat(X,Y)，其中X是要连接的字段，Y是连接时用的符号，可省略，默认为逗号。此函数必须与 GROUP BY 配合使用。

```sql
select dept_no, group_concat(emp_no) as employees
from dept_emp
group by dept_no;
```

## 54、查找排除当前最大、最小salary之后的员工的平均工资avg_salary

```sql
CREATE TABLE salaries ( 
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

输出格式：


| avg_salary       |
| ---------------- |
| 69462.5555555556 |

### 答案

本题有点问题，本题能通过的答案在挑选当前最大、最小salary时没加to_date = '9999-01-01'作条件限制，所以挑选出来的是全表最大、最小salary，然后对除去这两个salary再作条件限制to_date='
9999-01-01'，求平均薪水。

```sql
select avg(salary) as avg_salary
from salaries
where to_date='9999-01-01'
and salary not in (select min(salary) from salaries)
and salary not in (select max(salary) from salaries)
```

## 55、分页查询employees表，每5行一页，返回第2页的数据

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

### 答案

用limit：

```sql
select *
from employees
limit 5,5
```

用limit和offset（offset表示跳过多少条记录）：

```sql
select *
from employees
limit 5
offset 5
```

## 56、获取所有员工的emp_no、部门编号dept_no以及对应的bonus类型btype和recevied，没有分配具体的员工不显示

此题的原题给的表多了两个不相关的表，少了一个emp_bonus表。下面是更换过的表：

```sql
CREATE TABLE dept_emp ( 
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));

CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));

create table emp_bonus(
emp_no int not null,
recevied datetime not null,
btype smallint not null);
```

输出格式：


| e.emp_no | dept_no | btype | received   |
| -------- | ------- | ----- | ---------- |
| 10001    | d001    | 1     | 2010-01-01 |
| 10002    | d001    | 2     | 2010-10-01 |
| 10003    | d004    | 3     | 2011-12-03 |
| 10004    | d004    | 1     | 2010-01-01 |
| 10005    | d003    |       |            |
| 10006    | d002    |       |            |
| 10007    | d005    |       |            |
| 10008    | d005    |       |            |
| 10009    | d006    |       |            |
| 10010    | d005    |       |            |

### 答案

```sql
select em.emp_no, de.dept_no, eb.btype, eb.recevied
from employees as em
join dept_emp as de
on em.emp_no=de.emp_no
left join emp_bonus as eb
on de.emp_no=eb.emp_no
```

## 57、使用含有关键字exists查找未分配具体部门的员工的所有信息

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));

CREATE TABLE dept_emp (
emp_no int(11) NOT NULL,
dept_no char(4) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,dept_no));
```

### 答案

```sql
select *
from employees
where not exists (select emp_no 
                  from dept_emp 
                  where emp_no = employees.emp_no)
```

## 58、获取employees中的行数据，且这些行也存在于emp_v中

存在如下的视图：

```sql
create view emp_v as select * from employees where emp_no >10005;

CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));
```

获取employees中的行数据，且这些行也存在于emp_v中。注意不能使用intersect关键字。

```sql
select em.* 
from employees as em, emp_v as ev 
where em.emp_no = ev.emp_no
```

## 59、获取有奖金的员工相关信息

给出emp_no、first_name、last_name、奖金类型btype、对应的当前薪水情况salary以及奖金金额bonus。
bonus类型btype为1其奖金为薪水salary的10%，btype为2其奖金为薪水的20%，其他类型均为薪水的30%。 当前薪水表示to_date='9999-01-01'

```sql
CREATE TABLE employees (
emp_no int(11) NOT NULL,
birth_date date NOT NULL,
first_name varchar(14) NOT NULL,
last_name varchar(16) NOT NULL,
gender char(1) NOT NULL,
hire_date date NOT NULL,
PRIMARY KEY (emp_no));

create table emp_bonus(
emp_no int not null,
recevied datetime not null,
btype smallint not null);

CREATE TABLE salaries (
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL, 
PRIMARY KEY (emp_no,from_date));
```

输出格式：


| emp_no | first_name | last_name | btype   | salary  | bonus   |
| ------ | ---------- | --------- | ------- | ------- | ------- |
| 10001  | Georgi     | Facello   | 1       | 88958   | 8895.8  |
| 10002  | Bezalel    | Simmel    | 2       | 72527   | 14505.4 |
| 10003  | Parto      | Bamford   | 3 43311 | 12993.3 |         |

### 答案

使用case语句：

```sql
select em.emp_no, em.first_name, em.last_name, eb.btype, sa.salary,
(case eb.btype 
 when 1 then sa.salary*0.1
 when 2 then sa.salary*0.2
 else sa.salary*0.3
 end) as bonus
from employees as em
join salaries as sa
on em.emp_no=sa.emp_no and sa.to_date='9999-01-01'
join emp_bonus as eb
on em.emp_no=eb.emp_no
```

## 60、统计salary的累计和running_total

按照salary的累计和running_total，其中running_total为前两个员工的salary累计和，其他以此类推。 具体结果如下Demo展示。

```sql
CREATE TABLE salaries ( 
emp_no int(11) NOT NULL,
salary int(11) NOT NULL,
from_date date NOT NULL,
to_date date NOT NULL,
PRIMARY KEY (emp_no,from_date));
```

输出格式：


| emp_no | salary | running_total |
| ------ | ------ | ------------- |
| 10001  | 88958  | 88958         |
| 10002  | 72527  | 161485        |
| 10003  | 43311  | 204796        |

### 答案

复用salaries表

```sql
select sa1.emp_no, sa1.salary, sum(sa2.salary)
from salaries as sa1 
join salaries as sa2 
on sa2.emp_no<=sa1.emp_no
and sa1.to_date='9999-01-01' 
and sa2.to_date='9999-01-01' 
group by sa1.emp_no
```

## 61、对于employees表中，给出奇数行的first_name

本题表述有问题，本题的意思是将firstname进行排序，选择排序后行号为奇数的行，输出的时候是原表的相对顺序。

```sql
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
```

输出格式：


| first_name |
| ---------- |
| Georgi     |
| Chirstian  |
| Anneke     |

### 答案

```sql
select e1.first_name 
from employees e1
where (select count(*) 
       from employees e2
       where e1.first_name <=e2.first_name)%2=1;
```

或

```sql
select e1.first_name
from (select e2.first_name, 
             (select count(e3.first_name)
              from employees as e3
              where e2.first_name>=e3.first_name) as rowid
      from employees as e2) as e1
where rowid%2=1
```
