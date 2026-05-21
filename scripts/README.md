# Scripts

## migrate-main-testcases.ps1

将题解类 `main` 方法前的测试用例注释（与 `TestCaseInputUtils.getTestInputFromClassFileMainMethod` 相同规则）迁移到 `src/main/resources/TestCase{类名}.txt`，并删除对应注释块与 `main` 方法。

```powershell
# 预览（默认，不写文件）
pwsh ./scripts/migrate-main-testcases.ps1

# 指定仓库根目录
pwsh ./scripts/migrate-main-testcases.ps1 -Root "E:\code\java\leetcode"

# 实际写入
pwsh ./scripts/migrate-main-testcases.ps1 -Apply

# 覆盖已存在的 TestCase 文件
pwsh ./scripts/migrate-main-testcases.ps1 -Apply -Force
```

参数：

| 参数 | 说明 |
|------|------|
| `-Root` | 项目根目录，默认当前目录 |
| `-SourceDir` | 扫描的 Java 源码目录，默认 `src/main/java/letcode` |
| `-Apply` | 执行写入；省略则为 dry-run |
| `-Force` | 覆盖已存在的 `TestCase*.txt`；否则跳过并报告 |
