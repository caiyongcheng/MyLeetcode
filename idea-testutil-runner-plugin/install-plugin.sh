#!/usr/bin/env bash
set -euo pipefail

PLUGIN_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DIST_DIR="$PLUGIN_DIR/build/distributions"
TARGET_FOLDER="idea-testutil-runner-plugin"
IDEA_HOME="${IDEA_HOME:-}"
REBUILD=0
USE_USER_CONFIG=0
PLUGIN_ZIP=""

usage() {
    cat <<'EOF'
用法: ./install-plugin.sh [--rebuild] [--user-config] [--idea-home PATH] [--zip PATH]

默认优先安装到:
  ~/.local/share/JetBrains/IntelliJIdea*/plugins
  ~/.config/JetBrains/IntelliJIdea*/plugins
  $IDEA_HOME/plugins
EOF
}

while [[ $# -gt 0 ]]; do
    case "$1" in
        --rebuild) REBUILD=1 ;;
        --user-config) USE_USER_CONFIG=1 ;;
        --idea-home) IDEA_HOME="${2:-}"; shift ;;
        --zip) PLUGIN_ZIP="${2:-}"; shift ;;
        -h|--help) usage; exit 0 ;;
        *) echo "未知参数: $1" >&2; usage; exit 1 ;;
    esac
    shift
done

resolve_plugin_zip() {
    if [[ -n "$PLUGIN_ZIP" && -f "$PLUGIN_ZIP" ]]; then
        printf '%s\n' "$PLUGIN_ZIP"
        return
    fi
    if [[ -d "$DIST_DIR" ]]; then
        local latest
        latest="$(find "$DIST_DIR" -maxdepth 1 -name '*.zip' -type f -printf '%T@ %p\n' 2>/dev/null | sort -nr | head -1 | cut -d' ' -f2-)"
        if [[ -n "$latest" ]]; then
            printf '%s\n' "$latest"
            return
        fi
    fi
    return 1
}

resolve_plugins_dir() {
    local dir
    if [[ "$USE_USER_CONFIG" -eq 1 || -z "$IDEA_HOME" ]]; then
        for base in "$HOME/.local/share/JetBrains" "$HOME/.config/JetBrains"; do
            if [[ -d "$base" ]]; then
                dir="$(find "$base" -maxdepth 1 -type d -name 'IntelliJIdea*' | sort -r | head -1)"
                if [[ -n "$dir" ]]; then
                    mkdir -p "$dir/plugins"
                    printf '%s\n' "$dir/plugins"
                    return
                fi
            fi
        done
        for base in "$HOME/.local/share/JetBrains" "$HOME/.config/JetBrains"; do
            if [[ -d "$base" ]]; then
                dir="$(find "$base" -maxdepth 1 -type d -name 'IdeaIC*' | sort -r | head -1)"
                if [[ -n "$dir" ]]; then
                    mkdir -p "$dir/plugins"
                    printf '%s\n' "$dir/plugins"
                    return
                fi
            fi
        done
    fi
    if [[ -n "$IDEA_HOME" && -d "$IDEA_HOME" ]]; then
        mkdir -p "$IDEA_HOME/plugins"
        printf '%s\n' "$IDEA_HOME/plugins"
        return
    fi
    return 1
}

if [[ "$REBUILD" -eq 1 ]] || ! resolve_plugin_zip >/dev/null 2>&1; then
    if command -v gradle >/dev/null 2>&1; then
        (cd "$PLUGIN_DIR" && gradle buildPlugin --no-daemon -q)
    else
        echo "未找到 gradle，请先构建插件 zip。" >&2
        exit 1
    fi
fi

ZIP_PATH="$(resolve_plugin_zip)"
PLUGINS_DIR="$(resolve_plugins_dir)" || {
    echo "未找到 IDEA plugins 目录。请设置 IDEA_HOME 或使用 --user-config。" >&2
    exit 1
}

TARGET_DIR="$PLUGINS_DIR/$TARGET_FOLDER"
rm -rf "$TARGET_DIR"
mkdir -p "$PLUGINS_DIR"
unzip -q "$ZIP_PATH" -d "$PLUGINS_DIR"

echo ""
echo "已安装插件到: $TARGET_DIR"
echo "来源 zip: $ZIP_PATH"
echo "请完全退出并重新启动 IntelliJ IDEA 后生效。"
