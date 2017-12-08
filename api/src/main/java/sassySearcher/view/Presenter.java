package sassySearcher.view;

import sassySearcher.models.SearchResult;

import java.util.ArrayList;

public class Presenter {
    String query;
    ArrayList<SearchResult> result;
    int amount = 10;

    public Presenter(ArrayList<SearchResult> result, String query) {
        this.query = query;
        this.result = result;
    }

    public String getResult() {
        StringBuilder builder = new StringBuilder();

        builder.append("[");
        for (int i = 0; i < amount; i++) {
            builder.append(getResult(i)).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");

        return builder.toString();
    }

    private String getResult(int i) {
        return result.get(i).toString();
    }

}
