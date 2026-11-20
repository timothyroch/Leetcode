class Solution:
    def sampleStats(self, count: list[int]) -> list[float]:
        minimum = float("inf")
        maximum = float("-inf")
        lenLst = len(count)

        mode, freq = 0, 0
        foundMin = False
        for i in range(lenLst):
            if count[i] > 0 and not foundMin:
                minimum = i
                foundMin=True
            if count[i]>freq:
                mode = i
                freq = count[i]

        for i in range(lenLst-1, -1, -1):
            if count[i]>0:
                maximum = i
                break

        total = 0
        n = 0

        for i in range(lenLst):
            total+=(i*count[i])
            n+=count[i]

        mean = 0
        if n>0:
            mean = total/n

        median = 0
        seensf = 0
        for i in range(lenLst):
            seensf+= count[i]
            if seensf >= n/2:

                if n%2==1:
                    median = i
                    
                else:
                    if seensf > n/2:
                        median = i
                    else:
                        for j in range(i+1, lenLst):
                            if count[j]>0:
                                median = (i+j)/2
                                break
                break


        
        return [minimum, maximum, mean, median, mode]
