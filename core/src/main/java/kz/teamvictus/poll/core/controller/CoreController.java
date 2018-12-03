package kz.teamvictus.poll.core.controller;

import kz.teamvictus.poll.core.service.IUserService;
import kz.teamvictus.utils.CommonService;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core")
public class CoreController extends CommonService {

   private static final Logger LOGGER = LoggerFactory.getLogger(CoreController.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   private IUserService iUserService;

   @GetMapping("/user")
   public ResponseEntity<?> getUserList() {
      try {
         return builder(success(iUserService.getAllUser()));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }
}
