# Solve Two Sum using hash map in O(n) time
class Solution(object):
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        hm = {}
        for i, n in enumerate(nums):
            if (target - n) in hm:
                return [hm[target - n], i]
            hm[n] = i
