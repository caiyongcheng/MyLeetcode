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

$javac = (Get-Command javac -ErrorAction Stop).Source
$libJars = Get-ChildItem -Path (Join-Path $IdeaHome "lib") -Filter "*.jar" -File
$javaJars = Get-ChildItem -Path (Join-Path $IdeaHome "plugins\java\lib") -Filter "*.jar" -File -Recurse
$classpath = (($libJars + $javaJars) | ForEach-Object { $_.FullName.Replace('\', '/') }) -join ";"

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

Copy-Item -LiteralPath (Join-Path $PluginDir "src\main\resources\META-INF") `
    -Destination (Join-Path $ClassesDir "META-INF") -Recurse -Force

New-ZipFromDirectory -SourceDir $ClassesDir -Destination $PluginJarZip
Move-Item -LiteralPath $PluginJarZip -Destination $PluginJar -Force
New-ZipFromDirectory -SourceDir (Split-Path $PackageRoot -Parent) -Destination $PluginZip

Write-Host "Plugin zip: $PluginZip"
