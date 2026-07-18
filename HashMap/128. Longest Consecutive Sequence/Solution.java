import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> values = new HashSet<>();

        for (int num : nums) {
            values.add(num);
        }

        int longest = 0;

        for (int num : values) {
            if (values.contains(num - 1)) {
                continue;
            }

            int length = 1;
            int next = num + 1;

            while (values.contains(next)) {
                length++;
                next++;
            }

            if (length > longest) {
                longest = length;
            }
        }

        return longest;
    }
}
