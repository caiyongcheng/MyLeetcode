# 将 main 方法前的测试用例注释迁移到 TestCase*.txt，并移除 main 及对应注释块。
# 默认 dry-run；加 -Apply 才写入；-Force 覆盖已存在的 TestCase 文件。
param(
    [string]$Root = (Get-Location).Path,
    [string]$SourceDir = "src/main/java/letcode",
    [switch]$Apply,
    [switch]$Force
)

$ErrorActionPreference = "Stop"
$Utf8NoBom = New-Object System.Text.UTF8Encoding $false
$TestCaseTemplate = "src/main/resources/TestCase{0}.txt"

# 不参与迁移的工具类（保留 main）
$SkipClassNames = @("TestUtilRunner", "CastUtils")

function Test-IsCommentOrBlankLine {
    param([string]$Trimmed)
    if ([string]::IsNullOrWhiteSpace($Trimmed)) { return $true }
    if ($Trimmed.StartsWith("//")) { return $true }
    if ($Trimmed.StartsWith("/*")) { return $true }
    if ($Trimmed.StartsWith("*")) { return $true }
    if ($Trimmed.EndsWith("*/")) { return $true }
    return $false
}

# 与 TestCaseInputUtils.getTestInputFromClassFileMainMethod 一致的解析，并记录供迁移的注释行范围
function Get-MainTestCaseInfo {
    param([string[]]$Lines)

    $sb = New-Object System.Text.StringBuilder
    $inComment = 0
    $mainLineIdx = -1
    $blockStart = -1
    $blockEnd = -1
    $lastBlockStart = -1
    $lastBlockEnd = -1

    for ($i = 0; $i -lt $Lines.Count; $i++) {
        $lineStr = $Lines[$i]
        if ([string]::IsNullOrEmpty($lineStr)) { continue }

        $trimLineStr = $lineStr.Trim()

        if ($trimLineStr.StartsWith("/*")) {
            $inComment = 1
            if ($blockStart -lt 0) { $blockStart = $i }
            $blockEnd = $i
        }
        elseif ($trimLineStr.EndsWith("*/")) {
            $blockEnd = $i
            $inComment = 0
        }
        elseif ($inComment -eq 0 -and $trimLineStr.StartsWith("//")) {
            if ($blockStart -lt 0) { $blockStart = $i }
            $blockEnd = $i
            [void]$sb.Append($trimLineStr.Substring(2))
        }
        elseif ($inComment -ne 0) {
            if ($trimLineStr.StartsWith("*")) {
                $trimLineStr = $trimLineStr.Substring(1)
            }
            if ($trimLineStr -match '^\s*@param\s*args') { continue }
            if ($trimLineStr -eq "<p>") { continue }
            [void]$sb.Append($trimLineStr)
        }
        elseif ($trimLineStr.StartsWith("public static void main")) {
            $mainLineIdx = $i
            break
        }
        else {
            [void]$sb.Clear()
            $blockStart = -1
            $blockEnd = -1
        }
    }

    if ($mainLineIdx -ge 0 -and $blockStart -ge 0) {
        $lastBlockStart = $blockStart
        $lastBlockEnd = $blockEnd
    }

    return @{
        TestInput      = $sb.ToString()
        MainLineIndex  = $mainLineIdx
        CommentStart   = $lastBlockStart
        CommentEnd     = $lastBlockEnd
    }
}

# 从 main 签名行起匹配花括号，返回 main 方法结束行索引（含）
function Get-MainMethodEndLineIndex {
    param(
        [string[]]$Lines,
        [int]$MainStartIdx
    )

    $openIdx = -1
    for ($i = $MainStartIdx; $i -lt $Lines.Count; $i++) {
        if ($Lines[$i] -match '\{') {
            $openIdx = $i
            break
        }
    }
    if ($openIdx -lt 0) {
        throw "未找到 main 方法起始花括号: line $($MainStartIdx + 1)"
    }

    $depth = 0
    $inLineComment = $false
    $inBlockComment = $false
    $inString = $false
    $stringQuote = [char]0
    $inChar = $false

    for ($i = $openIdx; $i -lt $Lines.Count; $i++) {
        $line = $Lines[$i]
        $len = $line.Length
        for ($j = 0; $j -lt $len; $j++) {
            $ch = $line[$j]
            $next = if ($j + 1 -lt $len) { $line[$j + 1] } else { [char]0 }

            if ($inLineComment) {
                continue
            }
            if ($inBlockComment) {
                if ($ch -eq '*' -and $next -eq '/') {
                    $inBlockComment = $false
                    $j++
                }
                continue
            }
            if ($inString) {
                if ($ch -eq '\' -and $j + 1 -lt $len) { $j++; continue }
                if ($ch -eq $stringQuote) { $inString = $false }
                continue
            }
            if ($inChar) {
                if ($ch -eq '\' -and $j + 1 -lt $len) { $j++; continue }
                if ($ch -eq "'") { $inChar = $false }
                continue
            }

            if ($ch -eq '/' -and $next -eq '/') {
                $inLineComment = $true
                $j++
                continue
            }
            if ($ch -eq '/' -and $next -eq '*') {
                $inBlockComment = $true
                $j++
                continue
            }
            if ($ch -eq '"') {
                $inString = $true
                $stringQuote = '"'
                continue
            }
            if ($ch -eq "'") {
                $inChar = $true
                continue
            }
            if ($ch -eq '{') {
                $depth++
            }
            elseif ($ch -eq '}') {
                $depth--
                if ($depth -eq 0) {
                    return $i
                }
            }
        }
        $inLineComment = $false
    }

    throw "main 方法花括号未闭合: line $($MainStartIdx + 1)"
}

function Remove-MainAndCommentBlock {
    param(
        [string[]]$Lines,
        [int]$CommentStart,
        [int]$CommentEnd,
        [int]$MainStart,
        [int]$MainEnd
    )

    $removeStart = if ($CommentStart -ge 0) { $CommentStart } else { $MainStart }
    # 去掉 main 后紧邻的空行，避免留下多余空行
    $removeEnd = $MainEnd
    while ($removeEnd + 1 -lt $Lines.Count -and [string]::IsNullOrWhiteSpace($Lines[$removeEnd + 1])) {
        $removeEnd++
    }

    $before = if ($removeStart -gt 0) { $Lines[0..($removeStart - 1)] } else { @() }
    $after = if ($removeEnd + 1 -lt $Lines.Count) { $Lines[($removeEnd + 1)..($Lines.Count - 1)] } else { @() }

    $result = New-Object System.Collections.Generic.List[string]
    foreach ($l in $before) { [void]$result.Add($l) }
    foreach ($l in $after) { [void]$result.Add($l) }
    return ,$result.ToArray()
}

$rootPath = (Resolve-Path -LiteralPath $Root).Path
$scanPath = Join-Path $rootPath $SourceDir
if (-not (Test-Path -LiteralPath $scanPath)) {
    throw "源码目录不存在: $scanPath"
}

$mode = if ($Apply) { "APPLY" } else { "DRY-RUN" }
Write-Host "[$mode] Root=$rootPath SourceDir=$SourceDir Force=$Force"

$javaFiles = Get-ChildItem -LiteralPath $scanPath -Filter "*.java" -Recurse -File
$planned = 0
$skippedExists = 0
$skippedEmpty = 0
$skippedNoMain = 0
$skippedUtil = 0

foreach ($file in $javaFiles) {
    $className = [System.IO.Path]::GetFileNameWithoutExtension($file.Name)
    if ($SkipClassNames -contains $className) {
        $skippedUtil++
        continue
    }

    $lines = [System.IO.File]::ReadAllLines($file.FullName, $Utf8NoBom)
    $info = Get-MainTestCaseInfo -Lines $lines
    if ($info.MainLineIndex -lt 0) {
        $skippedNoMain++
        continue
    }

    $testInput = $info.TestInput
    if ([string]::IsNullOrEmpty($testInput)) {
        $skippedEmpty++
        continue
    }

    $testCasePath = Join-Path $rootPath ($TestCaseTemplate -f $className)
    $testCaseExists = Test-Path -LiteralPath $testCasePath

    if ($testCaseExists -and -not $Force) {
        Write-Host "[SKIP exists] $className -> $testCasePath"
        $skippedExists++
        continue
    }

    $mainEnd = Get-MainMethodEndLineIndex -Lines $lines -MainStartIdx $info.MainLineIndex
    $preview = if ($testInput.Length -gt 80) { $testInput.Substring(0, 80) + "..." } else { $testInput }

    $action = if ($testCaseExists) { "OVERWRITE" } else { "CREATE" }
    Write-Host "[$action] $($file.FullName)"
    Write-Host "  -> $testCasePath ($($testInput.Length) chars) preview: $preview"
    Write-Host "  -> remove lines $($info.CommentStart + 1)-$($mainEnd + 1) (comment+main)"

    if ($Apply) {
        $parent = Split-Path -Parent $testCasePath
        if (-not (Test-Path -LiteralPath $parent)) {
            New-Item -ItemType Directory -Path $parent -Force | Out-Null
        }
        [System.IO.File]::WriteAllText($testCasePath, $testInput, $Utf8NoBom)

        $newLines = Remove-MainAndCommentBlock `
            -Lines $lines `
            -CommentStart $info.CommentStart `
            -CommentEnd $info.CommentEnd `
            -MainStart $info.MainLineIndex `
            -MainEnd $mainEnd
        [System.IO.File]::WriteAllLines($file.FullName, $newLines, $Utf8NoBom)
    }

    $planned++
}

Write-Host "---"
Write-Host "Summary: planned=$planned skip_no_main=$skippedNoMain skip_empty=$skippedEmpty skip_exists=$skippedExists skip_util=$skippedUtil"
if (-not $Apply) {
    Write-Host "Dry-run only. Re-run with -Apply to write changes."
}
