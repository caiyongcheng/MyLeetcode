import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/7 8:58
 * description 本地模拟环境
 */
public class Main {

    static int[] arr;

    static Map<String, Integer> cache = new HashMap<>();

    public static int dp(int idx) {
        if (idx >= arr.length) {
            return 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            stringBuilder.append("a");
        }
        for (int i = idx; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
        }
        if (cache.get(stringBuilder.toString()) != null) {
            return cache.get(stringBuilder.toString());
        }
        if (arr[idx] == 0) {
            return dp(idx + 1);
        }
        int max = 0;
        boolean hass = idx + 4 < arr.length;
        for (int i = 0; hass && i < 5; i++) {
            if (arr[idx + i] == 0) {
                hass = false;
                break;
            }
        }
        int dp0 = 0;
        int dp1 = 0;
        int dp2 = 0;
        int dp3 = 0;
        int dp4 = 0;
        if (hass) {
            for (int i = 0; i < 5; i++) {
                arr[idx + i]--;
            }
            dp0 = (idx + 2) * 10 + dp(idx);
            for (int i = 0; i < 5; i++) {
                arr[idx + i]++;
            }
        }
        if (arr[idx] >= 1) {
            arr[idx] -= 1;
            dp1 = idx + dp(idx);
            arr[idx] += 1;
        }
        if (arr[idx] >= 2) {
            arr[idx] -= 2;
            dp2 = idx * 4 + dp(idx);
            arr[idx] += 2;
        }
        if (arr[idx] >= 3) {
            arr[idx] -= 3;
            dp3 = idx * 6 + dp(idx);
            arr[idx] += 3;
        }
        if (arr[idx] >= 4) {
            arr[idx] -= 4;
            dp4 = idx * 12 + dp(idx);
            arr[idx] += 4;
        }
        max = Integer.max(dp0, Integer.max(dp1, Integer.max(dp2, Integer.max(dp3, dp4))));
        cache.put(stringBuilder.toString(), max);
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arr = new int[14];
        String s = scanner.nextLine();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch >= '1' && ch <= '9') {
                arr[ch - '0']++;
            } else if (ch == '0') {
                arr[10]++;
            } else if (ch == 'J') {
                arr[11]++;
            } else if (ch == 'Q') {
                arr[12]++;
            } else if (ch == 'K') {
                arr[13]++;
            }
        }
        System.out.println(dp(1));
    }

}
