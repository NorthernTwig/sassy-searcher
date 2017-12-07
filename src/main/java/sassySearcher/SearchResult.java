package sassySearcher;

public class SearchResult implements Comparable<SearchResult> {
    public Page page;
    public double score;

    public SearchResult(Page page, double score) {
        this.page = page;
        this.score = score;
    }

    public Page getPage() {
        return page;
    }

    public double getScore() {
        return score;
    }

    public String toString() {
        return  page.url + "  -  " + score + "\n";
    }

    @Override
    public int compareTo(SearchResult searchResult) {
        return Double.compare(searchResult.getScore(), score);
    }
}