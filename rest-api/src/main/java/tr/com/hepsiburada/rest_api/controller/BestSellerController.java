package tr.com.hepsiburada.rest_api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tr.com.hepsiburada.model.dto.rest.history.ProductViewDTO;
import tr.com.hepsiburada.rest_api.core.annotation.SwaggerDefaultApiResponses;
import tr.com.hepsiburada.rest_api.service.BestSellerService;

@RestController
@RequestMapping("/api/best-seller")
@SwaggerDefaultApiResponses
@RequiredArgsConstructor
public class BestSellerController {

    private final BestSellerService bestSellerService;

    @ApiOperation("Fetch Best Seller Products For User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request to fetch the best seller products for the user was successfully responded.")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductViewDTO getBestSellerProductsByUser(@PathVariable("userId") String userId) {
        return bestSellerService.getBestSellerProductsByUser(userId);
    }

}
