class Solution(object):
    def maxArea(self, height):
        left = 0
        right = len(height) - 1
        maxwater = 0

        while left < right:
            hl = height[left]
            hr = height[right]
            area = (right - left) * (hl if hl < hr else hr)
            if area > maxwater:
                maxwater = area

            if hl < hr:
                left += 1
            else:
                right -= 1

        return maxwater
