package sassySearcher;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Entry {
    @RequestMapping("/")
    public String entry(@RequestParam(value = "query", defaultValue = "") String param) throws IOException {
        String lowParam = param.toLowerCase();

        FileHandler handler = new FileHandler();
        List<Path> whiskeyPaths = handler.readAll("Whiskey");
        List<Path> woodworkingPaths = handler.readAll("Woodworking");
        List<Path> paths = new ArrayList<>();
        paths.addAll(whiskeyPaths);
        paths.addAll(woodworkingPaths);

        PageDB db = new PageDB();

        Page page = new Page(db);
        page.create(paths);

        Query query = new Query(db);
        ArrayList<SearchResult> result = query.query(lowParam);

        Presenter presenter = new Presenter(result, lowParam);
        return presenter.getResult();
    }
}
