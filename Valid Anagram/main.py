# LeetCode 242 - Valid Anagram | Hash map freq count | O(n) time, O(k) space
class Solution(object):
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        if len(s) != len(t):
            return False
        ht, hs = {}, {}
        for i in range(len(s)):
           ht[t[i]] = 1 + ht.get(t[i], 0)
           hs[s[i]] = 1 + hs.get(s[i], 0) 
        return ht == hs
