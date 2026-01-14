class Solution(object):
    def maxSubArray(self, nums):
        curr = max_sum = nums[0]
        for num in nums[1:]:
            curr = max(num, curr + num)
            if curr > max_sum:
                max_sum = curr
        return max_sum
