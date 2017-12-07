package sassySearcher.utils;

import sassySearcher.models.SearchResult;
import sassySearcher.models.PageDB;
import sassySearcher.models.Page;

import java.util.ArrayList;
import java.util.Collections;

public class Query {
    ArrayList<SearchResult> result;
    PageDB db;

    public Query(PageDB db) {
        this.db = db;
    }

    public ArrayList<SearchResult> query(String query) {
        result = new ArrayList<>();

        double[] content = new double[db.getPages().size()];
        double[] location = new double[db.getPages().size()];
        for (int i = 0; i < db.getPages().size(); i++) {
            Page page = db.getPage(i);
            content[i] = getCountFrequencyScore(page, query);
            location[i] = getCountLocationScore(page, query);
        }

        normalizeScores(content, false);
        normalizeScores(location, true);

        for (int i = 0; i < db.getPages().size(); i++) {
            Page p = db.getPage(i);
            double score = 1.0 * content[i] + 0.5 * location[i];
            result.add(new SearchResult(p, score));
        }

        Collections.sort(result);
        return result;

    }

    private void normalizeScores(double[] scores, boolean smallIsBetter) {
        if (smallIsBetter) {
            double min = Double.MAX_VALUE;
            for (double score : scores) {
                if (score < min) min = score;
            }
            for (int i = 0; i < scores.length; i++) {
                scores[i] = min / Math.max(scores[i], 0.00001);
            }
        } else {
            double max = Double.MIN_VALUE;
            for (double score : scores) {
                if (score > max) max = score;
            }

            if (max == 0.0) max = 0.00001;
            for (int i = 0; i < scores.length; i++) {
                scores[i] = scores[i] / max;
            }

        }
    }

    private double getCountLocationScore(Page page, String query) {
        double score = 0;
        String[] words = query.split(" ");
        for (int i = 0; i < words.length; i++) {
            boolean found = false;
            String word = words[i];
            int id = db.getWordId(word);
            for (Integer wordInt: page.words) {
                if (wordInt == id) {
                    score += i;
                    found = true;
                }
            }

            if (!found) {
                score += 100000;
            }
        }
        return score;
    }

    private double getCountFrequencyScore(Page page, String query) {
        double score = 0;
        String[] words = query.split(" ");
        for (String word1 : words) {
            int id = db.getWordId(word1);
            for (Integer word : page.words) {
                if (id == word) {
                    score += 1;
                }
            }
        }
        return score;
    }


}
