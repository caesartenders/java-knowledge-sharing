package nl.code4all.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        String message = "Testing cucumber and selenium.";

        return new ModelAndView("welcome", "message", message);
    }

    @RequestMapping("/about")
    public ModelAndView about() {

        return new ModelAndView("about");
    }
}
