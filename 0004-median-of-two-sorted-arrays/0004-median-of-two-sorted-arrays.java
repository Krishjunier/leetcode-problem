class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n=nums1.length,m=nums2.length;
        int [] nums=new int[n+m];
        int i=0,j=0,k=0;
        while(i<n && j<m){
            if(nums1[i]<=nums2[j]){
                nums[k++]=nums1[i++];
            }
            else {
                nums[k++]=nums2[j++];
            }
        }
        while(i<n) nums[k++]=nums1[i++];
        while(j<m) nums[k++]=nums2[j++];
        int len=nums.length;
        if (nums.length%2==1){
            return (double)(nums[len/2]);
        }
        else{
            return (double)(nums[len/2]+nums[(len/2)-1])/2;
        }
        
    }
}