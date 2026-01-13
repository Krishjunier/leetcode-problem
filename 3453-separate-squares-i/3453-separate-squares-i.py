class Solution(object):
    def separateSquares(self, squares):
        """
        :type squares: List[List[int]]
        :rtype: float
        """
        total_area=0
        low,high=sys.float_info.max,0
        for x,y,l in squares:
            total_area+=l*l
            low=min(low,y)
            high=max(high,y+l)
        target=total_area/2.0
        for _ in range(100):
            mid=(high+low)/2.0
            area_below=0.0
            for x,y,l in squares:
                if mid<=y:
                    continue 
                elif mid>=y+l: 
                    area_below+=l*l
                else: 
                    area_below+=(mid-y)*l
            if area_below < target:
                low = mid
            else:
                high = mid
        return low
        