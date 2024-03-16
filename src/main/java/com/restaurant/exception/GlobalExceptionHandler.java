package com.restaurant.exception;

import com.restaurant.exception.addressnotfoundexception.AddressNotFoundExceptin;
import com.restaurant.exception.cartitemexception.CartItemNotFoundException;
import com.restaurant.exception.cartnotfoundexception.CartNotFoundException;
import com.restaurant.exception.categoryexception.CategoryNotFoundException;
import com.restaurant.exception.deliverypersonexception.DeliveryPersonNotFoundException;
import com.restaurant.exception.itemexception.ItemNotFoundException;
import com.restaurant.exception.menuexception.MenuNotFoundException;
import com.restaurant.exception.ordernotfoundexception.OrderNotFoundExceptionException;
import com.restaurant.exception.restaurantexception.RestaurantNotFoundException;
import com.restaurant.exception.userexception.UserAlreadyExist;
import com.restaurant.exception.userexception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<String> userAlreadyExistExceptionHandler(UserAlreadyExist ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String > handleUsrNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<String> restaurantNotFoundException(RestaurantNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<String> menuNotFoundException(MenuNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> categoryNotFoundException(CategoryNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> itemNotFoundException(ItemNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DeliveryPersonNotFoundException.class)
    public ResponseEntity<String> deliveryPersonNotFoundException(DeliveryPersonNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AddressNotFoundExceptin.class)
    public ResponseEntity<String> addressNotFoundException(AddressNotFoundExceptin ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundExceptionException.class)
    public ResponseEntity<String> orderNotFoundException(OrderNotFoundExceptionException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> cartNotFoundException(CartNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<String> cartItemNotFoundException(CartItemNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(BadCredentialsExceptions.class)
    public ResponseEntity<String> badCredentialsExceptionHandler(BadCredentialsExceptions ex) {
        System.out.println("Working");
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex){
        System.out.println("Woringin jsdf kajdf ");
        ProblemDetail errorDetail = null;


            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("Access denied reason", "Not authorized");

//        System.out.println(errorDetail.getDetail());
        return errorDetail;
    }
}
