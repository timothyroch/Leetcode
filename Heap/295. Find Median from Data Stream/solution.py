class MedianFinder:

    def __init__(self):
        self.rh = []
        self.lh = []
        self.meds = []
        

    def addNum(self, num: int) -> None:
        if len(self.meds) == 0:
            self.meds.append(num)
        elif len(self.meds) == 1:
            med = self.meds[0]
            if med==num:
                self.meds.append(num)
                return
            if med <num:
                if len(self.rh) == 0:
                    self.meds.append(num)
                    return
                min_left = self.rh[0]
                if num <= min_left:
                    self.meds.append(num)
                    return
                else:
                    min_left = heapq.heappop(self.rh)
                    self.meds.append(min_left)
                    heapq.heappush(self.rh, num)
            else:
                if len(self.lh) == 0:
                    self.meds = [num, self.meds[0]]
                    return
                max_left = -self.lh[0]
                if max_left<=num:
                    self.meds = [num, self.meds[0]]
                else:
                    max_left = -heapq.heappop(self.lh)
                    self.meds = [max_left, self.meds[0]]
                    heapq.heappush(self.lh, -num)
        else:
            m1, m2 = self.meds[0], self.meds[1]
            if num <= m1:
                heapq.heappush(self.lh, -num)
                heapq.heappush(self.rh, m2)
                self.meds = [m1]
            elif num >= m2:
                heapq.heappush(self.rh, num)
                heapq.heappush(self.lh, -m1)
                self.meds = [m2]
            else:
                self.meds = [num]
                heapq.heappush(self.rh, m2)
                heapq.heappush(self.lh, -m1)

    def findMedian(self) -> float:
        if len(self.meds) == 1:
            return self.meds[0]
        elif len(self.meds) == 2:
            return (self.meds[0]+self.meds[1])/2
