class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        prefix = [0]*(len(nums)+1)
        prefixMap = {}
        prefixMap[0] = 0
        res = 0
        maxLen = 0
        for i in range(1, len(nums)+1):
            x = 1 if nums[i-1] == 1 else -1
            curr = prefix[i-1] + x
            prefix[i] = curr
            if curr in prefixMap:
                lenInt = i-prefixMap[curr]
                maxLen = lenInt if lenInt > maxLen else maxLen
            else:
                prefixMap[curr] = i

        return maxLen
