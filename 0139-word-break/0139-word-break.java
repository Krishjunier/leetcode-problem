import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        int n = s.length();
        int maxLen = 0;
        for (String w : wordDict) {
            maxLen = Math.max(maxLen, w.length());
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(0, i - maxLen); j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 🔥 early exit
                }
            }
        }
        return dp[n];
    }
}
