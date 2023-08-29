package online.store.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import online.store.exceptions.CreditCardValidationException;
import online.store.services.OrdersService;
import online.store.services.ProductsService;
import online.store.services.CreditCardValidationService;
import online.store.model.CheckoutRequest;
import online.store.model.Order;
import java.util.*;

@RestController
public class CheckoutController {
    
    private final OrdersService ordersService;
    private final ProductsService productsService;
    private final CreditCardValidationService creditCardValidationService;
     
    public CheckoutController(OrdersService ordersService, ProductsService productsService, CreditCardValidationService creditCardValidationService) {
        this.ordersService = ordersService;
        this.productsService = productsService;
        this.creditCardValidationService = creditCardValidationService;
    }
    
    private static boolean isNullOrBlank(String input) {
    return input == null || input.isEmpty() || input.trim().length() == 0;
    }
    
    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody CheckoutRequest checkoutRequest) {
    Set<Order> orders = new HashSet<>(checkoutRequest.getProducts().size());
    if(isNullOrBlank(checkoutRequest.getCreditCard())) {
        return new ResponseEntity<>("Credit card information is missing",
                                     HttpStatus.PAYMENT_REQUIRED);
    }
    if (isNullOrBlank(checkoutRequest.getFirstName())) {
        return new ResponseEntity<>("First name is missing", HttpStatus.BAD_REQUEST);
    }
    if (isNullOrBlank(checkoutRequest.getLastName())) {
        return new ResponseEntity<>("Last name is missing", HttpStatus.BAD_REQUEST);
    }
    creditCardValidationService.validate(checkoutRequest.getCreditCard());
    for (CheckoutRequest.ProductInfo productInfo : checkoutRequest.getProducts()) {
 
        Order order = new Order(checkoutRequest.getFirstName(),
                checkoutRequest.getLastName(),
                checkoutRequest.getEmail(),
                checkoutRequest.getShippingAddress(),
                productInfo.getQuantity(),
                productsService.getProductById(productInfo.getProductId()),
                checkoutRequest.getCreditCard());
        orders.add(order);
    }
    ordersService.placeOrders(orders);
    return new ResponseEntity<>("success", HttpStatus.OK);
    }

   @ExceptionHandler({CreditCardValidationException.class})
   public ResponseEntity<String> handleCreditCardError(Exception ex) {
    System.out.println(String.format("Request to /checkout path threw an exception %s", ex.getMessage()));
    return new ResponseEntity<>("Credit card is invalid, please use another form of payment",
            HttpStatus.BAD_REQUEST);
    }
}