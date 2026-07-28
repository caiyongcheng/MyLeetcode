# MyLeetcode

LeetCode 个人题解合集，使用 Java + Maven 构建，覆盖 Easy / Medium / Hard 全难度。

## 许可证

本项目采用**反996许可证 版本1.0**：

> 版权所有（c）2021 蔡永程
>
> 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以下统称为"授权作品"）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
>
> 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修改。
> 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际劳工标准的核心公约。
> 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证的行为的权利。
>
> 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。

## 题量统计

| 分类 | 简单 | 中等 | 困难 | 合计 |
|------|:----:|:----:|:----:|:----:|
| LeetCode 正式题 | 245 | 476 | 84 | **805** |
| 未完成题目 | 2 | 35 | 27 | **64** |
| 剑指 Offer | 5 | 5 | 2 | **12** |
| 面试题精选 | 2 | 9 | - | **11** |
| LCP | - | 5 | - | **5** |
| LRC | 1 | 1 | - | **2** |
| 周赛 | - | 5 | - | **5** |

> 总计约 **920+** 个题解，配套 **668** 个测试用例资源文件。

## 项目结构

```
src/main/java/
├── letcode/
│   ├── normal/
│   │   ├── easy/                  # LeetCode 简单题
│   │   ├── medium/                # LeetCode 中等题
│   │   ├── difficult/             # LeetCode 困难题
│   │   └── unansweredquestions/   # 未完成题目（统一管理）
│   ├── offer/                     # 剑指 Offer
│   ├── interview/                 # 面试题精选
│   ├── lcp/ / lcr/ / lrc/         # LCP/LCR/LRC 系列
│   ├── competition/               # 周赛解答
│   ├── arithmetic/                # 算法模板（Dijkstra、Floyd 等）
│   └── utils/                     # 测试工具集
├── datastructure/                  # 手写数据结构库
│   ├── heap/    (堆)
│   ├── queue/   (队列)
│   ├── stack/   (栈)
│   └── tree/    (AVL 树、红黑树)
src/main/resources/                # 测试用例文本资源
src/test/java/                     # JUnit 5 单元测试
idea-testutil-runner-plugin/       # IDEA 插件子项目（Gradle/Kotlin）
scripts/                           # 维护脚本
```

### 文件命名约定

- `_123.java` — 已完成的 LeetCode 题解
- `N_123.java` — 待完成的题目
- `_123_New.java` — 已题解的改进版本

## 构建与运行

- **JDK**: 25+
- **构建工具**: Maven 3.6+

```bash
# 运行所有测试
mvn test

# 跳过测试打包
mvn -DskipTests package

# 运行单个测试类
mvn test -Dtest=TestCaseInputUtilsTest

# 迁移 main 方法的测试用例到资源文件
powershell -File scripts/migrate-main-testcases.ps1
```

## 自研工具

### TestUtil 测试框架

核心测试引擎，支持从 LeetCode 示例字符串自动解析输入、反射调用解题方法、彩色输出结果和执行时间。

```java
@SolutionTestMethod
public int[] twoSum(int[] nums, int target) { ... }
```

测试用例存放在 `src/main/resources/TestCase_{题号}.txt`，自动被框架加载执行。

### IDEA 插件

位于 `idea-testutil-runner-plugin/` 目录，支持：
- 右键运行解题类
- 提交解法到 LeetCode
- 生成每日一题模板

构建插件：
```bash
cd idea-testutil-runner-plugin
.\build.ps1
```

## 测试用例迁移脚本

将 `main` 前的注释测试用例迁移到 `src/main/resources/TestCase{类名}.txt` 的 PowerShell 脚本详见 [scripts/README.md](scripts/README.md)。

---

持续更新中 🚀
