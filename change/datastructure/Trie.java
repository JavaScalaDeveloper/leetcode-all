package change.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {
    public Trie[] children = new Trie[26];
    public List<Integer> v = new ArrayList<>();

    public void insert(String word, int i) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            node.v.add(i);
        }
    }

    public List<Integer> search(String pref) {
        Trie node = this;
        for (char c : pref.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return Collections.emptyList();
            }
            node = node.children[c];
        }
        return node.v;
    }
}