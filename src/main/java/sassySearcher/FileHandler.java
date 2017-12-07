package sassySearcher;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
    public List<String> read(String dataPath) throws IOException, URISyntaxException {
        URI uri = this.getClass().getResource("/" + dataPath).toURI();
        return Files.readAllLines(Paths.get(uri), Charset.defaultCharset());
    }

    public List<Path> readAll(String name) throws IOException {
        return Files.walk(Paths.get("src/main/resources/" + name))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }
}
