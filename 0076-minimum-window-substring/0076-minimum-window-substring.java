class Solution {
    public String minWindow(String s, String t) {
        if (s.length()<t.length()) return "";
        int [] freq=new int  [128];
        for(char ch:t.toCharArray()){
            freq[ch]++;
        }
        int left = 0, right = 0;
        int req_len = t.length();
        int min_len = Integer.MAX_VALUE;
        int start = 0;
        while(right<s.length()){
            char c=s.charAt(right);
            if(freq[c]>0){
                req_len--;
            }
            freq[c]--;
            right++;
            while(req_len==0){
                if(right-left < min_len){
                    min_len = right-left;
                    start=left;
                }
                char leftchar=s.charAt(left);
                freq[leftchar]++;
                if (freq[leftchar]>0){
                    req_len++;
                }
                left++;
            }
        }
        return min_len==Integer.MAX_VALUE?"":s.substring(start,start+min_len);
        
    }
}