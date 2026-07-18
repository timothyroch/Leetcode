import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups =
            new HashMap<>((int) (strs.length / 0.75f) + 1);

        for (String str : strs) {
            char[] characters = str.toCharArray();
            Arrays.sort(characters);
            String key = new String(characters);

            List<String> group = groups.get(key);

            if (group == null) {
                group = new ArrayList<>();
                groups.put(key, group);
            }

            group.add(str);
        }

        return new ArrayList<>(groups.values());
    }
}
