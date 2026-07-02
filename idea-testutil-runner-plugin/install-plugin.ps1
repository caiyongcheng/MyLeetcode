param(
    [string]$IdeaHome = "D:\JetBrains\IntelliJ IDEA 2023.3.1",
    [string]$PluginZip = "",
    [switch]$Rebuild,
    [switch]$UseUserConfig
)

$ErrorActionPreference = "Stop"

$PluginDir = $PSScriptRoot
$DistDir = Join-Path $PluginDir "build\distributions"
$PluginId = "idea-testutil-runner-plugin"
$TargetFolderName = "idea-testutil-runner-plugin"

function Resolve-PluginZip {
    param([string]$ExplicitZip)

    if ($ExplicitZip -and (Test-Path $ExplicitZip)) {
        return (Resolve-Path $ExplicitZip).Path
    }

    if (-not (Test-Path $DistDir)) {
        return $null
    }

    $candidate = Get-ChildItem -Path $DistDir -Filter "*.zip" -File |
            Sort-Object LastWriteTime -Descending |
            Select-Object -First 1
    if ($candidate) {
        return $candidate.FullName
    }
    return $null
}

function Resolve-PluginsDirectory {
    param(
        [string]$Home,
        [bool]$PreferUserConfig
    )

    if ($PreferUserConfig) {
        $patterns = @(
            "$env:APPDATA\JetBrains\IntelliJIdea*\plugins",
            "$env:LOCALAPPDATA\JetBrains\IntelliJIdea*\plugins"
        )
        foreach ($pattern in $patterns) {
            $dirs = Get-ChildItem -Path $pattern -Directory -ErrorAction SilentlyContinue |
                    Sort-Object Name -Descending
            if ($dirs) {
                return $dirs[0].FullName
            }
        }
    }

    if ($Home -and (Test-Path $Home)) {
        $installPlugins = Join-Path $Home "plugins"
        if (-not (Test-Path $installPlugins)) {
            New-Item -ItemType Directory -Force -Path $installPlugins | Out-Null
        }
        return $installPlugins
    }

    return $null
}

function Invoke-GradleBuild {
    $gradle = Get-Command gradle -ErrorAction SilentlyContinue
    if ($gradle) {
        Push-Location $PluginDir
        try {
            & gradle buildPlugin --no-daemon -q
            if ($LASTEXITCODE -ne 0) {
                throw "gradle buildPlugin failed with exit code $LASTEXITCODE"
            }
            return
        } finally {
            Pop-Location
        }
    }

    Write-Host "未找到 gradle，改用 build.ps1 构建..."
    & (Join-Path $PluginDir "build.ps1") -IdeaHome $IdeaHome
}

if ($Rebuild -or -not (Resolve-PluginZip -ExplicitZip $PluginZip)) {
    Invoke-GradleBuild
}

$zipPath = Resolve-PluginZip -ExplicitZip $PluginZip
if (-not $zipPath) {
    throw "未找到插件 zip，请先执行 gradle buildPlugin 或 .\build.ps1"
}

$pluginsDir = Resolve-PluginsDirectory -Home $IdeaHome -PreferUserConfig:$UseUserConfig
if (-not $pluginsDir) {
    throw @"
未找到 IDEA plugins 目录。
请指定 -IdeaHome "D:\JetBrains\IntelliJ IDEA 2023.3.1"
或使用 -UseUserConfig 安装到 %APPDATA%\JetBrains\IntelliJIdea*\plugins
"@
}

$targetDir = Join-Path $pluginsDir $TargetFolderName
if (Test-Path $targetDir) {
    Remove-Item -LiteralPath $targetDir -Recurse -Force
}

Add-Type -AssemblyName System.IO.Compression.FileSystem
[System.IO.Compression.ZipFile]::ExtractToDirectory($zipPath, $pluginsDir)

Write-Host ""
Write-Host "已安装插件到: $targetDir"
Write-Host "来源 zip: $zipPath"
Write-Host "请完全退出并重新启动 IntelliJ IDEA 后生效。"
