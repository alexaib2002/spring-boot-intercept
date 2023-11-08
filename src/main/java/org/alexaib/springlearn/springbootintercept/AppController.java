package org.alexaib.springlearn.springbootintercept;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Value("${config.schedule.start}")
    private int scheduleStart;
    @Value("${config.schedule.end}")
    private int scheduleEnd;

    @GetMapping({"/", ""})
    public String index() {
        return "index";
    }

    @GetMapping("/closed")
    public String closed(Model model) {
        model.addAttribute("message",
                new StringBuilder("Sorry, we're currently offline...\n")
                        .append("Our opening hours are from ")
                        .append(scheduleStart)
                        .append(" to ")
                        .append(scheduleEnd)
                        .toString());
        return "closed";
    }

}
