class Solution(object):
    def minTimeToVisitAllPoints(self, points):
        total = 0
        x1 = points[0][0]
        y1 = points[0][1]

        for i in range(1, len(points)):
            p = points[i]
            dx = p[0] - x1
            dy = p[1] - y1

            if dx < 0:
                dx = -dx
            if dy < 0:
                dy = -dy

            total += dx if dx > dy else dy
            x1 = p[0]
            y1 = p[1]

        return total