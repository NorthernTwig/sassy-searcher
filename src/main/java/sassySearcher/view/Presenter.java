package sassySearcher.view;

import sassySearcher.models.SearchResult;

import java.util.ArrayList;

public class Presenter {
    String query;
    ArrayList<SearchResult> result;
    int amount = 5;

    public Presenter(ArrayList<SearchResult> result, String query) {
        this.query = query;
        this.result = result;
    }

    public String getResult() {
        StringBuilder builder = new StringBuilder();

        builder.append("<h3>Result for search query: ").append(query).append("</h3>");
        builder.append("<ul>");
        for (int i = 0; i < amount; i++) {
            builder.append("<li>").append(getResult(i)).append("</li>");
        }
        builder.append("</ul>");

        return builder.toString();
    }

    private String getResult(int i) {
        return result.get(i).toString();
    }

}
