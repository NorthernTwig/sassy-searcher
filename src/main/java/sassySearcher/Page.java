package sassySearcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Page {
    String url;
    ArrayList<Integer> words;
    PageDB db;

    Page(PageDB db) {
        this.db = db;
    }
    Page(String url, ArrayList<Integer> words, PageDB db) {
        this.url = url;
        this.words = words;
        this.db = db;
    }

    public void create(List<Path> paths) throws IOException {
        for (Path file : paths) {
            generatePage(file.toString(), file);
        }
    }

    private void generatePage(String url, Path wordsFile) {
        try {
            ArrayList<Integer> wordCollection = new ArrayList<>();

            Files.lines(wordsFile).map(line -> line.split(" ")).forEach(words -> {
                for (String word: words) {
                    int id = db.getWordId(word);
                    wordCollection.add(id);
                }
            });

            Page page = new Page(url, wordCollection, db);
            db.addPage(page);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
