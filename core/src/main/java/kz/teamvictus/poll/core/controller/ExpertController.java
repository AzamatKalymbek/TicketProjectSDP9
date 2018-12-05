package kz.teamvictus.poll.core.controller;


import kz.teamvictus.utils.CommonService;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expert")
public class ExpertController extends CommonService {
   private static final Logger LOGGER = LoggerFactory.getLogger(ExpertController.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());




}