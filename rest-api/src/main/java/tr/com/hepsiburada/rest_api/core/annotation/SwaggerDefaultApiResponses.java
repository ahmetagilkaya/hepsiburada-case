package tr.com.hepsiburada.rest_api.core.annotation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import tr.com.hepsiburada.rest_api.core.exception.ExceptionMessage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad request, the request parameters or body information are incorrect", response = ExceptionMessage.class),
        @ApiResponse(code = 401, message = "Unauthorized, user is not authorized to send requests to this endpoint", response = ExceptionMessage.class),
        @ApiResponse(code = 403, message = "Forbidden, user is forbidden from making requests to this endpoint", response = ExceptionMessage.class),
        @ApiResponse(code = 408, message = "Request Timeout, the request has timed out", response = ExceptionMessage.class),
        @ApiResponse(code = 500, message = "Internal Server Error, there is an error occurring on the server", response = ExceptionMessage.class)
})
public @interface SwaggerDefaultApiResponses {
}
