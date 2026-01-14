class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];

        // Worst-case cuts
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }

        for (int center = 0; center < n; center++) {

            // Odd-length palindromes
            for (int l = center, r = center;
                 l >= 0 && r < n && s.charAt(l) == s.charAt(r);
                 l--, r++) {

                dp[r] = Math.min(dp[r], l == 0 ? 0 : dp[l - 1] + 1);
            }

            // Even-length palindromes
            for (int l = center - 1, r = center;
                 l >= 0 && r < n && s.charAt(l) == s.charAt(r);
                 l--, r++) {

                dp[r] = Math.min(dp[r], l == 0 ? 0 : dp[l - 1] + 1);
            }
        }

        return dp[n - 1];
    }
}
