import java.util.*;

class Solution {
    private Map<Integer, List<String>> memo = new HashMap<>();
    private Set<String> dict;
    private int maxLen;

    public List<String> wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        maxLen = 0;
        for (String w : wordDict) {
            maxLen = Math.max(maxLen, w.length());
        }

        // 🔥 Pruning step: Word Break I check
        if (!canBreak(s)) return new ArrayList<>();

        return dfs(s, 0);
    }

    private List<String> dfs(String s, int start) {
        if (memo.containsKey(start)) return memo.get(start);

        List<String> res = new ArrayList<>();

        if (start == s.length()) {
            res.add(""); // base case
            return res;
        }

        for (int end = start + 1; end <= s.length() && end - start <= maxLen; end++) {
            String word = s.substring(start, end);
            if (dict.contains(word)) {
                List<String> subs = dfs(s, end);
                for (String sub : subs) {
                    res.add(sub.isEmpty() ? word : word + " " + sub);
                }
            }
        }

        memo.put(start, res);
        return res;
    }

    // Word Break I DP for early pruning
    private boolean canBreak(String s) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = Math.max(0, i - maxLen); j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
