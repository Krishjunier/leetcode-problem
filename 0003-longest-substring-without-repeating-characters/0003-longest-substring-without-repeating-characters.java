class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len=s.length();
        int [] freq=new int[128];
        int right=0, left=0,max_len=0;
        while(right<len){
            char ch=s.charAt(right);
            freq[ch]++;
            while(freq[ch]>1){
                freq[s.charAt(left)]--;
                left++;
            }
            max_len=Math.max(max_len,right-left+1);
            right++;
        }
        return max_len;
    }
}