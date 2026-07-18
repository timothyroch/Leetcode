import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indices =
            new HashMap<>((int) (nums.length / 0.75f) + 1);

        for (int i = 0; i < nums.length; i++) {
            Integer matchingIndex = indices.get(target - nums[i]);

            if (matchingIndex != null) {
                return new int[]{matchingIndex, i};
            }

            indices.put(nums[i], i);
        }

        return new int[0];
    }
}
