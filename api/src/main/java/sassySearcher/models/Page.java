package sassySearcher.models;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {
    public String url;
    public ArrayList<Integer> words;
    public PageDB db;
    public double pageRank = 1.0;
    public ArrayList<String> links;
    private int ITERATIONS = 20;

    public Page(PageDB db) {
        this.db = db;
        this.links = new ArrayList<>();
    }

    public Page(String url, ArrayList<Integer> words, PageDB db) {
        this.url = url;
        this.words = words;
        this.db = db;
        this.links = new ArrayList<>();
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
                for (String word : words) {
                    int id = db.getWordId(word);
                    wordCollection.add(id);
                }
            });

            Page page = new Page(url, wordCollection, db);
            db.addPage(page);
            calculatePageRank();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void calculatePageRank() {
        for (int i = 0; i < ITERATIONS; i++) {
            for (Page page : db.getPages()) {
                iteratePageBank(page);
            }
        }
    }

    public boolean hasLinkTo(String pageUrl) {
        return links.contains(pageUrl);
    }

    public String getUrl() {
        return url;
    }

    public int getNoLinks() {
        return links.size();
    }

    private void iteratePageBank(Page page) {
        double pr = 0;
        for (Page pa : db.getPages()) {
            if (pa.hasLinkTo(page.getUrl())) {
                pr += pa.pageRank / (double) pa.getNoLinks();
            }
        }

        pr = 0.85 * pr + 0.15;
        page.pageRank = pr;
    }
}
