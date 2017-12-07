package sassySearcher.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PageDB implements Serializable {
    HashMap<String, Integer> wordToId;
    ArrayList<Page> pages;

    public PageDB() {
        wordToId = new HashMap<>();
        pages = new ArrayList<>();
    }

    void addPage(Page page) {
       pages.add(page);
    }

    public int getWordId(String word) {
       if (wordToId.containsKey(word)) {
           return wordToId.get(word);
       } else {
           int id = wordToId.size();
           wordToId.put(word, id);
           return id;
       }
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public Page getPage(int i) {
        return pages.get(i);
    }
}
