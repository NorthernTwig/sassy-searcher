package sassySearcher.utils;

import sassySearcher.models.PageDB;

import java.io.*;
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

    public void writeIndex(PageDB db, String name) throws IOException {
        FileOutputStream fout = new FileOutputStream("src/main/resources/Index/" + name + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(db);
        fout.close();
        oos.close();
    }

    public boolean indexExists(String name) {
        File f = new File("src/main/resources/Index/" + name + ".ser");
        return f.exists() && !f.isDirectory();
    }

    public PageDB getDB(String name) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("src/main/resources/Index/" + name + ".ser"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        return (PageDB) oi.readObject();
    }
}
