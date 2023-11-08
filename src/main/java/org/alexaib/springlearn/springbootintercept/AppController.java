package org.alexaib.springlearn.springbootintercept;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping({"/", ""})
    public String index() {
        return "index";
    }

    @GetMapping("/closed")
    public String closed(Model model) {
        return "closed";
    }

}
