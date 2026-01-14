class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1]; 
        int top = -1;
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];

            while (top != -1 && heights[stack[top]] > currHeight) {
                int height = heights[stack[top--]];
                int left = (top == -1) ? -1 : stack[top];
                int width = i - left - 1;
                int area = height * width;
                if (area > maxArea) maxArea = area;
            }
            stack[++top] = i;
        }
        return maxArea;
    }
}
