class Solution {
    public int characterReplacement(String s, int k) {
        int left=0,right=0,maxfreq=0,len=0;
        int[]  freq = new int [26];
        while(right<s.length()){
            char ch=s.charAt(right);
            freq[ch-'A']++;
            maxfreq=Math.max(maxfreq,freq[ch-'A']);
            while((right-left+1)-maxfreq>k){
                freq[s.charAt(left)-'A']--;
                left++;
            }
            len=Math.max(len,right-left+1);
            right++;
        }
        return len;
    }
}