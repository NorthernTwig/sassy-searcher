package sassySearcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Entry {
    @RequestMapping("/")
    public String entry() {
        return "hello!";
    }
}
