package kz.teamvictus.poll.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kz.teamvictus.poll.core.service.ICategoryService;
import kz.teamvictus.poll.core.service.ITicketStatusService;
import kz.teamvictus.utils.CommonService;
import kz.teamvictus.utils.error.InternalException;
import kz.teamvictus.utils.error.InternalExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@RequestMapping("/choices")
@Api(value = "Choices REST API controller", description = "shows choices")
public class ChoicesController extends CommonService {
   private static final Logger LOGGER = LoggerFactory.getLogger(ChoicesController.class);
   private final InternalExceptionHelper IE_HELPER = new InternalExceptionHelper(this.toString());

   @Autowired
   ICategoryService categoryService;
   @Autowired
   ITicketStatusService ticketStatusService;

   @ApiOperation(value = "Get list of all choices [category, ticket status]")
   @ApiResponses(
           value = {
                   @ApiResponse(code= 100, message = "100 is the message"),
                   @ApiResponse(code= 200, message = "Successful return list of choices")
           }
   )
   @GetMapping()
   public ResponseEntity<?> getChoicesList(){
      try {
         HashMap map = new HashMap();
         map.put("category", categoryService.getAllCategory());
         map.put("ticketStatus", ticketStatusService.getAllTicketStatus());
         return builder(success(map));
      } catch (InternalException e) {
         LOGGER.error(e.getMessage(), e);
         return builder(errorWithDescription(e.getErrorRef(), e.getMessage()));
      }
   }
}
