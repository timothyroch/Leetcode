# O(n) time, O(n) space using a set to track seen numbers
class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        seen = set()
        for n in nums:
            if n in seen:
                return True
            seen.add(n)

        return False
