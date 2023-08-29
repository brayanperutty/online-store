package online.store.model;

import java.util.*;

public class CheckoutRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private List<ProductInfo> products;
 
    private String creditCard;
 
    public static class ProductInfo {
        private long productId;
        private long quantity;
        
        public long getProductId(){
            return productId;
        }
        
        public void setProductId(long productId){
            this.productId = productId;
        }
        
    public long getQuantity(){
            return quantity;
        }
        
        public void setQuantity(long quantity){
            this.quantity = quantity;
        }
    }
    
    public CheckoutRequest(){};
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getShippingAddress(){
        return this.shippingAddress;
    }
    
    public void setShippingAddress(String shippingAddress){
        this.shippingAddress = shippingAddress;
    }
    
    public List<ProductInfo> getProducts(){
        return this.products;
    }
    
    public void setProducts(List<ProductInfo> products){
        this.products = products;
    }
    
    public String getCreditCard(){
        return this.creditCard;
    }
    
    public void setCreditCard(String creditCard){
        this.creditCard = creditCard;
    }
    
}