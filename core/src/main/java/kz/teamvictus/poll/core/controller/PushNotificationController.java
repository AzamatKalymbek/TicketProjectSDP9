package kz.teamvictus.poll.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.teamvictus.poll.core.service.IOfferService;
import kz.teamvictus.poll.core.service.IPushNotificationService;
import kz.teamvictus.poll.core.service.ITicketMessageService;
import kz.teamvictus.poll.core.service.ITicketService;
import kz.teamvictus.utils.CommonService;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static kz.teamvictus.utils.Constants.TOKEN_PREFIX;

@RestController
@RequestMapping("/push")
@Api(value = "Push Notification REST API controller", description = "[add and delete players]")
public class PushNotificationController extends CommonService {
   private static final Logger LOGGER = LoggerFactory.getLogger(PushNotificationController.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   IPushNotificationService pushNotificationService;

   @ApiOperation(value = " - добавляет player")
   @PostMapping("/{playerId}")
   public ResponseEntity<?> addPlayer(HttpServletRequest req, @PathVariable(value = "playerId") String playerId){
      try {
         String userToken = req.getHeader("Authorization").replace(TOKEN_PREFIX,"");
         return builder(success(pushNotificationService.addPlayer(userToken, playerId)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @ApiOperation(value = " - удаляет player")
   @DeleteMapping("/{playerId}")
   public ResponseEntity<?> deletePlayer(HttpServletRequest req, @PathVariable(value = "playerId") String playerId){
      try {
         String userToken = req.getHeader("Authorization").replace(TOKEN_PREFIX,"");
         return builder(success(pushNotificationService.deletePlayer(userToken, playerId)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }
}
