package sassySearcher;

import java.util.ArrayList;
import java.util.HashMap;

public class PageDB {
    HashMap<String, Integer> wordToId;
    ArrayList<Page> pages;

    PageDB() {
        wordToId = new HashMap<>();
        pages = new ArrayList<>();
    }

    void addPage(Page page) {
       pages.add(page);
    }

    int getWordId(String word) {
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

    Page getPage(int i) {
        return pages.get(i);
    }
}
