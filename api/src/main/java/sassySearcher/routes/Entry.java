package sassySearcher.routes;

import org.springframework.web.bind.annotation.*;
import sassySearcher.models.PageDB;
import sassySearcher.models.SearchResult;
import sassySearcher.utils.FileHandler;
import sassySearcher.models.Page;
import sassySearcher.utils.Query;
import sassySearcher.view.Presenter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Entry {

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/")
    public String entry(@RequestParam(value = "query", defaultValue = "") String param) throws IOException, ClassNotFoundException {
        String name = "index";
        String lowParam = param.toLowerCase();

        FileHandler handler = new FileHandler();
        PageDB db = new PageDB();
        if (!handler.indexExists(name)) {
            List<Path> whiskeyPaths = handler.readAll("Whiskey");
            List<Path> woodworkingPaths = handler.readAll("Woodworking");
            List<Path> paths = new ArrayList<>();
            paths.addAll(whiskeyPaths);
            paths.addAll(woodworkingPaths);


            Page page = new Page(db);
            page.create(paths);

            handler.writeIndex(db, name);
        } else {
            db = handler.getDB(name);
        }

        Query query = new Query(db);
        ArrayList<SearchResult> result = query.query(lowParam);

        Presenter presenter = new Presenter(result, lowParam);
        return presenter.getResult();
    }
}
