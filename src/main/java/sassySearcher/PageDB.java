package sassySearcher;

import java.util.ArrayList;
import java.util.HashMap;

public class PageDB {
    HashMap<String, Integer> wordToId;
    ArrayList<Page> pages;

    public PageDB() {
        wordToId = new HashMap<>();
        pages = new ArrayList<>();
    }

    public void addPage(Page page) {
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


}
