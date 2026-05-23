# LeetCode TestUtil Runner

IntelliJ IDEA 本地插件，用于在题解类上右键运行 `letcode.utils.TestUtilRunner`，也支持生成每日一题并将当前题解提交到 LeetCode。

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

构建产物：

```text
idea-testutil-runner-plugin\build\distributions\leetcode-testutil-runner.zip
```

## 安装

在 IDEA 中打开：

```text
Settings -> Plugins -> 齿轮 -> Install Plugin from Disk
```

选择上面的 zip 后重启 IDEA。也可以直接复制：

```text
idea-testutil-runner-plugin\build\package\LeetCode-TestUtil-Runner
```

到 IDEA 配置目录的 `plugins` 下。

## 配置 LeetCode 登录信息

先执行一次：

```text
Tools -> Generate LeetCode Daily Question
```

在弹窗中配置：

- `GraphQL Endpoint`：默认 `https://leetcode.cn/graphql/`
- `Cookie`：从浏览器登录 LeetCode 后复制请求 Cookie
- `CSRF Token`：通常取 Cookie 中的 `csrftoken`
- `Extra Headers`：可选，一行一个 `Name: value`

配置保存在 IDEA 项目属性中，不写入 git。

## 生成每日一题

菜单入口：

```text
Tools -> Generate LeetCode Daily Question
```

插件会拉取当天每日一题，生成 Java 题解骨架和可解析出的示例用例。生成文件的 Javadoc 中会包含：

```java
/**
 * Link: https://leetcode.cn/problems/two-sum/
 */
```

后续提交到 LeetCode 会依赖这个 `Link` 解析 `titleSlug`。

## 本地运行题解

在题解 `.java` 文件编辑器或项目视图中右键：

```text
Run via TestUtilRunner
```

运行前会弹出测试用例确认框，可按 `TestUtil.test()` 的输入格式编辑用例。

默认用例来源优先级：

1. 当前题解类 `main` 方法前的注释用例
2. `src/main/resources/TestCase<类简单名>.txt`
3. 空输入框

确认后，插件会把用例保存到：

```text
src/main/resources/TestCase<类简单名>.txt
```

例如类 `_632` 对应：

```text
src/main/resources/TestCase_632.txt
```

## 提交到 LeetCode

完成题解代码后，在当前题解 `.java` 文件编辑器或项目视图中右键：

```text
Submit LeetCode Solution
```

插件会执行以下流程：

1. 从当前文件 Javadoc 的 `Link: https://leetcode.cn/problems/<titleSlug>/` 解析题目 slug
2. 通过 GraphQL 获取提交所需的内部 `questionId`
3. 读取当前 Java 文件，移除 `package` 声明，并把当前顶层类名改为 `Solution`
4. POST 到 `/problems/<titleSlug>/submit/`
5. 轮询 `/submissions/detail/<submission_id>/check/`
6. 弹窗展示判题结果

通过时会显示 Accepted 信息；未通过时会尽量展示 LeetCode 返回的：

- 失败状态，例如 `Wrong Answer`、`Time Limit Exceeded`
- `failed_testcase` 或 `last_testcase`
- `code_output` / `std_output`
- `expected_output`
- 编译错误或运行错误信息

如果提示缺少 `Link`，请使用每日一题生成功能创建题解，或手动在文件注释中补充：

```java
/**
 * Link: https://leetcode.cn/problems/two-sum/
 */
```

如果提示未登录或缺少 CSRF，请重新复制浏览器中的 LeetCode Cookie 和 `csrftoken` 到插件配置。
