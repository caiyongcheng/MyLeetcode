package letcode.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * GraphQL 请求体构建（供 HttpURLConnection 与 JCEF fetch 共用）。
 */
final class LeetCodeGraphqlBodies {

    private LeetCodeGraphqlBodies() {
    }

    @NotNull
    static String buildGraphqlBody(@NotNull String query, @Nullable Map<String, String> variables) {
        return buildGraphqlBody(query, variables, null);
    }

    @NotNull
    static String buildGraphqlBody(@NotNull String query,
                                   @Nullable Map<String, String> variables,
                                   @Nullable Map<String, String> rawJsonVariables) {
        StringBuilder json = new StringBuilder(128);
        json.append("{\"query\":").append(LeetCodeHttpHeaders.escapeJsonString(query));
        if ((variables != null && !variables.isEmpty()) || (rawJsonVariables != null && !rawJsonVariables.isEmpty())) {
            json.append(",\"variables\":{");
            boolean first = true;
            if (variables != null) {
                for (Map.Entry<String, String> entry : variables.entrySet()) {
                    if (!first) {
                        json.append(',');
                    }
                    first = false;
                    json.append(LeetCodeHttpHeaders.escapeJsonString(entry.getKey())).append(':')
                            .append(LeetCodeHttpHeaders.escapeJsonString(entry.getValue()));
                }
            }
            if (rawJsonVariables != null) {
                for (Map.Entry<String, String> entry : rawJsonVariables.entrySet()) {
                    if (!first) {
                        json.append(',');
                    }
                    first = false;
                    json.append(LeetCodeHttpHeaders.escapeJsonString(entry.getKey())).append(':')
                            .append(entry.getValue());
                }
            }
            json.append('}');
        }
        json.append('}');
        return json.toString();
    }
}
