package cradle.rancune.algo.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rancune@126.com on 2020/7/17.
 */
public class Strings {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        Map<Character, Integer> map = new HashMap<>();
        int result = 0;
        int i = -1;
        int j = 0;
        for (j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(i, map.get(c));
            }
            result = Math.max(result, j - i);
            map.put(c, j);
        }
        return result;
    }
}
