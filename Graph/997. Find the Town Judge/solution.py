class Solution:
    def findJudge(self, n: int, trust: list[list[int]]) -> int:
        truster = [0]*n
        trusted = [0]*n
        for a, b in trust:
            truster[a-1]+=1
            trusted[b-1] += 1

        for i in range(n):
            if truster[i] == 0 and trusted[i]==n-1:
                return i+1
        return -1
        
