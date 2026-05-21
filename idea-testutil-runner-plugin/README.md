# LeetCode TestUtil Runner

IntelliJ IDEA 本地插件：在 Java 题解类上右键，通过 `letcode.utils.TestUtilRunner` 调用 `TestUtil`，不用再手写 `main` 方法。

## 构建

```powershell
cd idea-testutil-runner-plugin
.\build.ps1
```

默认使用本机 IDEA：

```text
D:\JetBrains\IntelliJ IDEA 2023.3.1
```

如需指定 IDEA 安装目录：

```powershell
.\build.ps1 -IdeaHome "D:\JetBrains\IntelliJ IDEA 2023.3.1"
```

产物：

```text
idea-testutil-runner-plugin\build\distributions\leetcode-testutil-runner.zip
```

## 安装

IDEA 中打开：

```text
Settings -> Plugins -> 齿轮 -> Install Plugin from Disk
```

选择上面的 zip 后重启 IDEA。

也可以直接复制 `build\package\LeetCode-TestUtil-Runner` 到 IDEA 配置目录的 `plugins` 下。

## 使用

在题解 `.java` 文件编辑器或项目视图中右键，选择：

```text
Run via TestUtilRunner
```

每次运行前都会弹出测试用例确认框，可以按原来的 `TestUtil.test()` 输入格式编辑用例。

默认用例来源优先级：

1. 当前题解类 `main` 方法前的注释用例。
2. 项目内 `src/main/resources/TestCase<类简单名>.txt`（与 `TestCaseInputUtils` 文本用例路径一致）。
3. 空输入框。

点击 OK 后，插件会把确认后的用例保存到：

```text
src/main/resources/TestCase<类简单名>.txt
```

例如类 `_632` 对应 `src/main/resources/TestCase_632.txt`。

插件会创建临时 Application 运行配置：

- Main class: `letcode.utils.TestUtilRunner`
- Program arguments: 当前题解类全限定名和 Base64 编码后的测试用例
- Working directory: 当前项目根目录
- Module: 当前题解类所在模块
