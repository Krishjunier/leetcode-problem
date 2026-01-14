class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        // Ensure word2 is the shorter one (slight cache win)
        if (n > m) {
            return minDistance(word2, word1);
        }

        int[] dp = new int[n + 1];

        // Base case: converting "" -> word2
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int prevDiag = dp[0];   // dp[i-1][j-1]
            dp[0] = i;              // dp[i][0]

            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prevDiag;
                } else {
                    dp[j] = 1 + Math.min(
                            prevDiag,
                            Math.min(dp[j], dp[j - 1])
                    );
                }
                prevDiag = temp;
            }
        }

        return dp[n];
    }
}

