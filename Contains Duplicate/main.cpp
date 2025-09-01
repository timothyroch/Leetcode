// O(n) time, O(n) space using a set to track seen number
class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        std::unordered_set<int> seen;
        for (int n : nums) {
            if (seen.contains(n)) {
                return true;
            }
            seen.insert(n);
        }
        return false;
    }
};
