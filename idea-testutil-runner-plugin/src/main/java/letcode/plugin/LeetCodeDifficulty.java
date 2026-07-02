package letcode.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

enum LeetCodeDifficulty {
    ANY("不限", null, null),
    EASY("简单", "EASY", "Easy"),
    MEDIUM("中等", "MEDIUM", "Medium"),
    HARD("困难", "HARD", "Hard");

    final String label;
  @Nullable
    private final String cnFilter;
  @Nullable
    private final String globalFilter;

    LeetCodeDifficulty(String label, @Nullable String cnFilter, @Nullable String globalFilter) {
        this.label = label;
        this.cnFilter = cnFilter;
        this.globalFilter = globalFilter;
    }

    @Override
    public String toString() {
        return label;
    }

    @Nullable
    String filterValue(boolean cnEndpoint) {
        return cnEndpoint ? cnFilter : globalFilter;
    }

    @NotNull
    static LeetCodeDifficulty fromLabel(@Nullable String label) {
        if (label == null) {
            return ANY;
        }
        for (LeetCodeDifficulty value : values()) {
            if (value.label.equals(label) || value.name().equalsIgnoreCase(label)) {
                return value;
            }
        }
        return ANY;
    }

    @NotNull
    static String normalizeFromApi(@Nullable String difficulty) {
        if (difficulty == null || difficulty.isEmpty()) {
            return MEDIUM.globalFilter == null ? "Medium" : MEDIUM.globalFilter;
        }
        String upper = difficulty.trim().toUpperCase(Locale.ROOT);
        if (upper.contains("EASY")) {
            return "Easy";
        }
        if (upper.contains("HARD")) {
            return "Hard";
        }
        return "Medium";
    }
}
