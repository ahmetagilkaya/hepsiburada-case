package tr.com.hepsiburada.rest_api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tr.com.hepsiburada.model.dto.rest.history.ProductViewDTO;
import tr.com.hepsiburada.rest_api.core.annotation.SwaggerDefaultApiResponses;
import tr.com.hepsiburada.rest_api.service.HistoryService;

@RestController
@RequestMapping("/api/history")
@SwaggerDefaultApiResponses
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @ApiOperation("Fetch Ten Last Viewed Products In User History")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request to fetch ten last viewed products in user history was successfully responded.")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}/last-ten-products-viewed", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductViewDTO getLastTenProductsViewedByUser(@PathVariable("userId") String userId) {
        return historyService.getLastTenProductsViewedByUser(userId);
    }

    @ApiOperation("Delete Product From User History")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product has deleted successfully from user history")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProductFromUserHistory(@PathVariable("userId") String userId, @PathVariable("productId") String productId) {
        historyService.deleteProductFromUserHistory(userId, productId);
    }

}
