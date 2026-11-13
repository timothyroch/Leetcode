class Allocator:

    def __init__(self, n: int):
        self.allocator = [None]*n
        self.space = n
        

    def allocate(self, size: int, mID: int) -> int:
        # i, j = 0, 0
        # found = True
        # while True:
        #     if i >= self.space:
        #         return -1
        #     if self.allocator[i]:
        #         i+=1
        #         continue
        #     j = i

        #     found = True
        #     while j-i+1 < size:
        #         if j+1 >= self.space:
        #             return -1
        #         if self.allocator[j+1]:
        #             i = j+1
        #             found = False
        #             break
        #         j+=1
            
        #     if not found:
        #         continue

        #     for k in range(i, j+1):
        #         self.allocator[k] = mID
        #     return i
        # return -1

        # faster solution

        free = 0
        found = False
        for i in range(self.space):
            if not self.allocator[i]:
                free+=1
                if free == size:
                    found = True
                    break
            else:
                free = 0
        if found:
            self.allocator[i-size+1 : i+1] = [mID]*size
            return i-size+1
        return -1
        

    def freeMemory(self, mID: int) -> int:
        count = 0
        for i in range(self.space):
            if self.allocator[i] == mID:
                count+=1
                self.allocator[i] = None
        return count
        


# Your Allocator object will be instantiated and called as such:
# obj = Allocator(n)
# param_1 = obj.allocate(size,mID)
# param_2 = obj.freeMemory(mID)
