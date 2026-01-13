class Solution(object):
    def longestPalindrome(self, s):
        if len(s)<=1: 
            return s
        start=end=0
        def palin(l,r):
            while l>=0 and r<len(s) and s[l]==s[r]:
                l-=1
                r+=1
            return l+1,r-1
        for i in range(len(s)):
            left1,right1=palin(i,i)
            left2,right2=palin(i,i+1)
            if right1-left1 >end-start:
                start,end=left1,right1
            if right2-left2 >end-start:
                start,end=left2,right2
        return s[start:end+1]

        