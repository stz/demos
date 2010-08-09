package org.klab.demo.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Application {
    
    private static Logger LOG = LoggerFactory.getLogger(Application.class);
    
    @RequestMapping("/home")
    public @ResponseBody String home() {
        LOG.info("Hello world!");
        return "Hello world!";
    }
}
