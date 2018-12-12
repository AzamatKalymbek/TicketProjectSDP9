package kz.teamvictus.poll.core.controller;


import kz.teamvictus.poll.core.model.Offer;
import kz.teamvictus.poll.core.model.Ticket;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import static kz.teamvictus.utils.Constants.TOKEN_PREFIX;

// ExpertController: /expert - основной маршрут
@RestController
@RequestMapping("/expert")
public class ExpertController extends CommonService {
   private static final Logger LOGGER = LoggerFactory.getLogger(ExpertController.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   ITicketService iTicketService;

   @Autowired
   IOfferService iOfferService;

   @Autowired
   ITicketMessageService iTicketMessageService;

   // - эксперт берёт список тикетов по ticket_status_id (GET /expert/ticket?status=)
   @GetMapping("/ticket")
   public ResponseEntity<?> getTicketListByStatusId(@RequestParam(value = "status") Long status){
      try {
         return builder(success(iTicketService.getAllTicketByTicketStatusId(status)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @PostMapping("/offer")
   public ResponseEntity<?> offerTicket(@Valid @RequestBody Offer offer){
      try {
         return builder(success(iOfferService.addOffer(offer)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   // - эксперт берет тикет (GET /expert/ticket/{ticketId})
   @GetMapping("/ticket/{ticketId}")
   public ResponseEntity<?> getTicket( @PathVariable(value = "ticketId") Long ticketId){
      try {
         return builder(success(iTicketService.getTicketById(ticketId)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   // - эксперт обновляет тикет (PATCH /expert/ticket/)
   @PatchMapping("/ticket")
   public ResponseEntity<?> updateTicket(@Valid @RequestBody Ticket ticket){
      try {
         return builder(success(iTicketService.updateTicket(ticket.getId(), ticket)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   // - эксперт берет список СВОИХ офферов по offer_status_id (GET /expert/offer?status=)
   @GetMapping("/offer")
   public ResponseEntity<?> getExpertOfferList(HttpServletRequest req, @RequestParam(value = "status") Long status){
      try {
         String userToken = req.getHeader("Authorization").replace(TOKEN_PREFIX,"");
         return builder(success(iOfferService.getAllByUserIdAndStatusId(userToken, status)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @GetMapping("/ticket/{ticketId}/offer")
   public ResponseEntity<?> getExpertOffer(@PathVariable(value = "ticketId") Long ticketId,
                                           @RequestParam(value = "status") Long status){
      try {
         return builder(success(iOfferService.getOfferByTicketIdAndStatusId(ticketId, status)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }
}
