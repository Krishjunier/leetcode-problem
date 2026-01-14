class Solution {
    Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(0, 0, s, p);
    }

    private boolean dfs(int i, int j, String s, String p) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean ans;

        // If pattern is exhausted
        if (j == p.length()) {
            ans = (i == s.length());
        } else {
            boolean firstMatch =
                    i < s.length() &&
                    (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');

            // If next char is '*'
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                ans = dfs(i, j + 2, s, p) ||
                      (firstMatch && dfs(i + 1, j, s, p));
            } else {
                ans = firstMatch && dfs(i + 1, j + 1, s, p);
            }
        }

        memo[i][j] = ans;
        return ans;
    }
}
