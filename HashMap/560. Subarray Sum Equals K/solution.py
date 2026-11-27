class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        res = 0
        prefix = [0]*(len(nums)+1)
        prefixMap = {}
        prefixMap[0] = 1
        for i in range(1, len(nums)+1):
            prefix[i] = prefix[i-1]+nums[i-1]
            currval = prefix[i]

            if currval-k in prefixMap:
                res += prefixMap[currval-k]

            currFreq = prefixMap.get(prefix[i], 0)
            prefixMap[prefix[i]] = currFreq + 1
        
        return res
