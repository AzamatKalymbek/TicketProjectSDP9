package kz.teamvictus.poll.core.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.teamvictus.poll.core.model.Offer;
import kz.teamvictus.poll.core.model.Ticket;
import kz.teamvictus.poll.core.service.IOfferService;
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

import static kz.teamvictus.utils.Constants.TOKEN_PREFIX;

// ClientController: /client - основной маршрут
@RestController
@RequestMapping("/client")
@Api(value = "Client REST API controller", description = "shows client operations [read, create, update]")
public class ClientController extends CommonService {

   private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   ITicketService iTicketService;
   @Autowired
   IOfferService offerService;

   @ApiOperation(value = " - список тикетов клиента (GET /client/ticket/)")
   @GetMapping("/ticket")
   public ResponseEntity<?> getTicketList(HttpServletRequest req){
      try {
         String userToken = req.getHeader("Authorization").replace(TOKEN_PREFIX,"");
         return builder(success(iTicketService.getAllTicketByUserId(userToken)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @ApiOperation(value = " - клиент берёт тикет (GET /client/ticket/{ticketId})")
   @GetMapping("/ticket/{ticketId}")
   public ResponseEntity<?> getTicket(HttpServletRequest req, @PathVariable(value = "ticketId") Long ticketId){
      try {
         String userToken = req.getHeader("Authorization").replace(TOKEN_PREFIX,"");
         return builder(success(iTicketService.getTicketByIdAndUserId(userToken, ticketId)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @ApiOperation(value = " - клиент создает тикет (POST /client/ticket/)")
   @PostMapping("/ticket")
   public ResponseEntity<?> saveTicket(HttpServletRequest req, @Valid @RequestBody Ticket ticket){
      try {
         String userToken = req.getHeader("Authorization").replace(TOKEN_PREFIX,"");
         return builder(success(iTicketService.addTicket(userToken, ticket)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @ApiOperation(value = " - клиент обновляет тикет (PATCH /client/ticket/)")
   @PatchMapping("/ticket")
   public ResponseEntity<?> updateTicket(@Valid @RequestBody Ticket ticket){
      try {
         return builder(success(iTicketService.updateTicket(ticket.getId(), ticket)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @ApiOperation(value = " - клиент берёт список предложения для тикета (GET /client/ticket/{ticketId}/offer)")
   @GetMapping("/ticket/{ticketId}/offer")
   public ResponseEntity<?> getClientOffer(@PathVariable(value = "ticketId") Long ticketId, Long status){
      try {
         if (status != null) {
            return builder(success(offerService.getOfferByTicketIdAndStatusId(ticketId, status)));
         }
         return builder(success(offerService.getByTicketId(ticketId)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }

   @ApiOperation(value = " - клиент обновляет предложение (PATCH /client/ticket/{ticketId}/offer)")
   @PatchMapping("/ticket/offer")
   public ResponseEntity<?> updateClientOffer(@Valid @RequestBody Offer offer){
      try {
         return builder(success(offerService.updateOffer(offer.getId(), offer)));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }
}
