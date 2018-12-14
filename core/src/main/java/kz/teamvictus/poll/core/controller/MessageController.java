package kz.teamvictus.poll.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.teamvictus.poll.core.model.TicketMessage;
import kz.teamvictus.poll.core.service.IOfferService;
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

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
@Api(value = "Message REST API controller", description = "[get and save operations]")
public class MessageController extends CommonService {
   private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   ITicketService iTicketService;

   @Autowired
   IOfferService iOfferService;

   @Autowired
   ITicketMessageService iTicketMessageService;

   @ApiOperation(value = " - эксперт берет список сообщении по ticket_id (GET /expert/message?ticket=)")
   @GetMapping()
   public ResponseEntity<?> getTicketMessageList(@RequestParam(value = "ticket") Long ticket){
      try {
         return builder(success(iTicketMessageService.getAllTicketMessageByTicketId(ticket)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @ApiOperation(value = " - эксперт создает сообщение (POST /expert/message)")
   @PostMapping()
   public ResponseEntity<?> saveTicketMessage(@Valid @RequestBody TicketMessage ticketMessage){
      try {
         return builder(success(iTicketMessageService.addTicketMessage(ticketMessage)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }
}
