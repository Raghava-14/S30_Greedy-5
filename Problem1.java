//Time = O(m*n), where m is the length of the input strings
//Space = O(m*n)

class Solution {
    public boolean isMatch(String s, String p) {
        // Get the lengths of the input strings
        int m = s.length(), n = p.length();
        
        // Initialize the DP array to store intermediate results
        boolean[][] dp = new boolean[m+1][n+1];
        
        // Base case: empty strings match
        dp[0][0] = true;
        
        // Fill the first row of the DP array
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j-1) == '*') {
                // If the current pattern character is '*', copy the value from the previous column
                dp[0][j] = dp[0][j-1];
            }
        }
        
        // Fill the rest of the DP array using a nested for loop
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '?' || s.charAt(i-1) == p.charAt(j-1)) {
                    // If the current pattern character is '?' or matches the current string character, 
                    // copy the value from the previous diagonal cell
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    // If the current pattern character is '*', either copy the value from the previous 
                    // column or from the previous row (depending on whether the '*' matches the current
                    // string character)
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }
        
        // Return the final result stored in the bottom-right corner of the DP array
        return dp[m][n];
    }
}
