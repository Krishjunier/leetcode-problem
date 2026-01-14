import java.util.*;

class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        // Precompute palindrome table
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    pal[i][j] = (len <= 2) || pal[i + 1][j - 1];
                }
            }
        }

        List<List<String>> res = new ArrayList<>();
        dfs(0, s, pal, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int start, String s, boolean[][] pal,
                     List<String> path, List<List<String>> res) {

        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (pal[start][end]) {
                path.add(s.substring(start, end + 1));
                dfs(end + 1, s, pal, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
}
