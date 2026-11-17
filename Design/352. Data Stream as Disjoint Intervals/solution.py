class SummaryRanges:

    def __init__(self):
        self.vals = set()
        self.disjointsets = []
        

    def addNum(self, value: int) -> None:

        before = value-1
        after = value+1
        
        vals = self.vals
        if value in vals:
            return
        
        elif before in vals and after in vals:
            # unite sets 
            ind1 = 0
            ind2 = 0
            st = 0
            end = 0
            for i in range(len(self.disjointsets)):
                it = self.disjointsets[i]
                f, l = it[0], it[1]
                if l == before:
                    ind1 = i
                    st = f
                if f == after:
                    ind2 = i
                    end = l
            if ind1 < ind2:
                self.disjointsets.pop(ind1)
                self.disjointsets.pop(ind2-1)
            else:
                self.disjointsets.pop(ind1)
                self.disjointsets.pop(ind2)
            self.disjointsets.append([st, end])

        elif before in vals:
            for i in range(len(self.disjointsets)):
                it = self.disjointsets[i]
                lst = it[1]
                if lst == before:
                    self.disjointsets[i][1] = value
        elif after in vals:
            for i in range(len(self.disjointsets)):
                it = self.disjointsets[i]
                fst = it[0]
                if fst == after:
                    self.disjointsets[i][0] = value
            
        else:
            self.disjointsets.append([value, value])

        self.vals.add(value)
            
        

    def getIntervals(self) -> list[list[int]]:
        heap = []
        for st in self.disjointsets:
            heapq.heappush(heap, (st[0], st[1]))
        res = []
        while heap:
            it = heapq.heappop(heap)
            res.append([it[0], it[1]])

        return res
        


# Your SummaryRanges object will be instantiated and called as such:
# obj = SummaryRanges()
# obj.addNum(value)
# param_2 = obj.getIntervals()
