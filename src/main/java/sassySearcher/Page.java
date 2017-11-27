package sassySearcher;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

class Page {
    String url;
    ArrayList<String> words;
    PageDB db;

    private Page(String url, ArrayList<String> words) {
        this.url = url;
        this.words = words;
        this.db = new PageDB();
    }

    private void generatePage(String url, File wordsFile) {
        try {
            ArrayList<Integer> wordCollection= new ArrayList<>();

            Files.lines(Paths.get(String.valueOf(wordsFile))).map(line -> line.split(" ")).forEach(words -> {
                for (String word: words) {
                    int id = db.getWordId(word);
                    wordCollection.add(id);
                }
            });

            Page page = new Page(url, words);
            db.addPage(page);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
