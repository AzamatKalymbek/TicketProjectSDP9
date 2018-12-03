package kz.teamvictus.poll.security.controller;

import kz.teamvictus.utils.CommonService;
import kz.teamvictus.utils.error.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController extends CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping
    public String test() throws InternalException {
        return "Hello world!";
    }

}
