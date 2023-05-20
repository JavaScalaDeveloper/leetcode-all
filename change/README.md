# 替换所有的除Java以外的代码
- 正则匹配```python[\s\S]*?```

- 正则匹配\[English Version]([\s\S]*?)\)

- 清除空行 ^\s*$
- 给所有class添加package：AddPackagePrefix
- 合并所有md：MergeMdFiles
- 替换文件路径
```text
fastly.jsdelivr.net
替换成：
gcore.jsdelivr.net
```
