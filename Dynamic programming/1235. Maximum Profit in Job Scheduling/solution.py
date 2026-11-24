class Solution:
    def jobScheduling(self, startTime: list[int], endTime: list[int], profit: list[int]) -> int:

        best_profit = 0

        sortedTup = []
        for i in range(len(startTime)):
            tup = (startTime[i], endTime[i], profit[i])
            sortedTup.append(tup)

        sortedTup.sort()
        newSt, newEnd, newProf = [], [], []
        for i in range(len(sortedTup)):
            s, e, p  = sortedTup[i]
            newSt.append(s)
            newEnd.append(e)
            newProf.append(p)

        lenST = len(newSt)



        cache = [None]*lenST

        for i in range(lenST-1, -1, -1):
            if cache[i] is not None:
                continue
            end = newEnd[i]

            g, d = i, lenST
            while g<d:
                middle = (g+d)//2
                if newSt[middle]>=end:
                    d = middle
                elif newSt[middle]<end:
                    g=middle+1

            cache[i] = newProf[i]
            if i+1<lenST and g<lenST:
                cache[i]= max(cache[g]+cache[i], cache[i+1])

            elif i+1<lenST:
                cache[i] = max(newProf[i], cache[i+1])
        
        best_profit = max(cache)

        return best_profit
