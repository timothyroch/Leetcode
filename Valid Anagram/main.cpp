// # LeetCode 242 - Valid Anagram | Hash map freq count | O(n) time, O(k) space
class Solution {
public:
    bool isAnagram(string s, string t) {
        if (s.size() != t.size()) return false;
        unordered_map<char, int> hm;
        for (int i = 0; i < s.size(); i++) {
            hm[t[i]]++;
            hm[s[i]]--;
        }
        for (auto& [ch, val] : hm) {
            if (val != 0) return false;
        }
        return true;
    }
};
