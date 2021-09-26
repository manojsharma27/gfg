import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Problem : https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1#
 */
public class CountOccurencesOfAnagrams {

    int search(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        if(n < m) {
            return 0;
        }

        Map<Character, Integer> pmap = new HashMap<>();
        for(int i=0; i<m; i++) {
            char ch = pat.charAt(i);
            pmap.put(ch, pmap.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> tmap = new HashMap<>();

        int i=0;
        while(i<m) {
            char ch = txt.charAt(i);
            tmap.put(ch, tmap.getOrDefault(ch, 0) + 1);
            i++;
        }


        int count = matches(pmap, tmap) ? 1 : 0;

        while(i<n) {
            char ch = txt.charAt(i);
            tmap.put(ch, tmap.getOrDefault(ch, 0) + 1);

            char pch = txt.charAt(i-m);
            int pval = tmap.getOrDefault(pch, 0);
            if(pval > 1) {
                tmap.put(pch, pval - 1);
            } else {
                tmap.remove(pch);
            }

            if(matches(pmap, tmap)) {
                count++;
            }

            i++;
        }
        return count;
    }

    private boolean matches(Map<Character, Integer> pmap, Map<Character, Integer> tmap) {
        if(pmap.size() != tmap.size()) {
            return false;
        }

        for(char key : pmap.keySet()) {
            if(!tmap.containsKey(key)) {
                return false;
            }

            if(!Objects.equals(pmap.get(key), tmap.get(key))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String txt = "";

        String word = "";
        CountOccurencesOfAnagrams obj = new CountOccurencesOfAnagrams();
        int count = obj.search(word, txt);
        System.out.println(count);
    }
}
