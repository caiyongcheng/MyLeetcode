package letcode.plugin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeetCodeGraphqlClientTest {

    @Test
    void fetchRandomTitleSlugByDifficulty() throws Exception {
        LeetCodeSettings settings = new LeetCodeSettings();
        settings.endpoint = "https://leetcode.cn/graphql/";
        LeetCodeGraphqlClient client = new LeetCodeGraphqlClient(settings);

        String slug = client.fetchRandomTitleSlug(LeetCodeDifficulty.EASY);
        assertNotNull(slug);
        assertFalse(slug.isEmpty());
    }
}
