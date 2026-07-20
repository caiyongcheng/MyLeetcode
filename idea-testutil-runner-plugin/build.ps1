param(
    [string]$IdeaHome = "D:\JetBrains\IntelliJ IDEA 2023.3.1"
)

$ErrorActionPreference = "Stop"

$PluginDir = $PSScriptRoot
$BuildDir = Join-Path $PluginDir "build"
$ClassesDir = Join-Path $BuildDir "classes"
$PackageRoot = Join-Path $BuildDir "package\LeetCode-TestUtil-Runner"
$DistDir = Join-Path $BuildDir "distributions"
$PluginJar = Join-Path $PackageRoot "lib\leetcode-testutil-runner.jar"
$PluginJarZip = Join-Path $PackageRoot "lib\leetcode-testutil-runner.jar.zip"
$PluginZip = Join-Path $DistDir "leetcode-testutil-runner.zip"
$JavacArgs = Join-Path $BuildDir "javac.args"

function New-ZipFromDirectory {
    param(
        [string]$SourceDir,
        [string]$Destination
    )

    if (Test-Path $Destination) {
        Remove-Item -LiteralPath $Destination -Force
    }
    Add-Type -AssemblyName System.IO.Compression
    Add-Type -AssemblyName System.IO.Compression.FileSystem

    $zip = [System.IO.Compression.ZipFile]::Open($Destination, [System.IO.Compression.ZipArchiveMode]::Create)
    try {
        Get-ChildItem -LiteralPath $SourceDir -File -Recurse | ForEach-Object {
            $relative = $_.FullName.Substring($SourceDir.Length).TrimStart('\', '/').Replace('\', '/')
            [System.IO.Compression.ZipFileExtensions]::CreateEntryFromFile($zip, $_.FullName, $relative) | Out-Null
        }
    } finally {
        $zip.Dispose()
    }
}

if (-not (Test-Path $IdeaHome)) {
    throw "IDEA home not found: $IdeaHome"
}

function Resolve-Javac {
    $candidates = @()
    if ($env:JAVA_HOME) {
        $candidates += Join-Path $env:JAVA_HOME "bin/javac"
        if ($IsLinux -or $IsMacOS) {
            $candidates += Join-Path $env:JAVA_HOME "bin/javac.exe"
        }
    }
    $cmd = Get-Command javac -ErrorAction SilentlyContinue
    if ($cmd) {
        $candidates += $cmd.Source
    }
    if ($IsLinux) {
        $candidates += "/usr/lib/jvm/java-21-openjdk-amd64/bin/javac"
    }
    foreach ($path in $candidates | Select-Object -Unique) {
        if (-not $path -or -not (Test-Path $path)) {
            continue
        }
        $version = & $path -version 2>&1 | Out-String
        if ($version -match 'version "21[.\d]*"') {
            return $path
        }
    }
    if ($cmd) {
        return $cmd.Source
    }
    throw "javac not found (Java 21 recommended for IntelliJ 2023.3+)"
}

$javac = Resolve-Javac
$libJars = Get-ChildItem -Path (Join-Path $IdeaHome "lib") -Filter "*.jar" -File
$javaJars = Get-ChildItem -Path (Join-Path $IdeaHome "plugins\java\lib") -Filter "*.jar" -File -Recurse
$pathSeparator = if ($IsLinux -or $IsMacOS) { ':' } else { ';' }
$classpath = (($libJars + $javaJars) | ForEach-Object { $_.FullName.Replace('\', '/') }) -join $pathSeparator

if (Test-Path $BuildDir) {
    Remove-Item -LiteralPath $BuildDir -Recurse -Force
}
New-Item -ItemType Directory -Force -Path $ClassesDir | Out-Null
New-Item -ItemType Directory -Force -Path (Split-Path $PluginJar -Parent) | Out-Null
New-Item -ItemType Directory -Force -Path $DistDir | Out-Null

$sources = Get-ChildItem -Path (Join-Path $PluginDir "src\main\java") -Filter "*.java" -Recurse |
        ForEach-Object { $_.FullName }
if (-not $sources) {
    throw "No Java source found"
}

$argLines = @(
    "-encoding"
    "UTF-8"
    "-cp"
    "`"$classpath`""
    "-d"
    "`"$($ClassesDir.Replace('\', '/'))`""
) + ($sources | ForEach-Object { "`"$($_.Replace('\', '/'))`"" })
Set-Content -LiteralPath $JavacArgs -Value $argLines -Encoding ASCII

& $javac "@$JavacArgs"
if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}

Get-ChildItem -LiteralPath (Join-Path $PluginDir "src\main\resources") |
    Copy-Item -Destination $ClassesDir -Recurse -Force

New-ZipFromDirectory -SourceDir $ClassesDir -Destination $PluginJarZip
Move-Item -LiteralPath $PluginJarZip -Destination $PluginJar -Force
New-ZipFromDirectory -SourceDir (Split-Path $PackageRoot -Parent) -Destination $PluginZip

Write-Host "Plugin zip: $PluginZip"
