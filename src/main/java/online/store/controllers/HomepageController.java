package online.store.controllers;

import online.store.services.ProductsService;
import online.store.model.wrappers.ProductsWrapper;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomepageController {
    private final ProductsService productService;
 
    public HomepageController(ProductsService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/categories")
    public String getProductCategories() {
    return productService.getAllSupportedCategories()
            .stream()
            .collect(Collectors.joining(","));
    }
    
    @GetMapping("/deals_of_the_day/{number_of_products}")
    public ProductsWrapper getDealsOfTheDay(
            @PathVariable(name = "number_of_products") int numberOfProducts) {
        return new ProductsWrapper(productService.getDealsOfTheDay(numberOfProducts));
    }   
    
    @GetMapping("/products")
    public ProductsWrapper getProductsForCategory(
            @RequestParam(name = "category", required = false) String category) {
    if (category != null && !category.isEmpty()) {
    return new ProductsWrapper(productService.getProductsByCategory(category));
    }
    return new ProductsWrapper(productService.getAllProducts());
    }
}