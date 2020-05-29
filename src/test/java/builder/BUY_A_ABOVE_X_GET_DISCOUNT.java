package builder;

import com.creditdatamw.zerocell.annotation.Column;

public class BUY_A_ABOVE_X_GET_DISCOUNT {
    
    @Column(index=0, name="PromoID")
    private String PromoID;
    
    @Column(index=1, name="Percentage Discount on Product")
    private String percentageDiscountOnProduct;
    
    @Column(index=2, name="Fixed Discount on Product")
    private String fixedDiscountOnProduct;
    
    @Column(index=3, name="Max Discount")
    private String maxDiscount;
    
    @Column(index=4, name="Threshold")
    private String threshold;
    
    public void setPromoID(String promoID) {
        this.PromoID = promoID;
    }
    
    public String getPromoID(){
        return PromoID;
    }
    
    public void setPercentageDiscountOnProduct(String percentageDiscountOnProduct) {
        this.percentageDiscountOnProduct = percentageDiscountOnProduct;
    }
    
    public String getPercentageDiscountOnProduct(){
        return percentageDiscountOnProduct;
    }
    
    public void setFixedDiscountOnProduct(String fixedDiscountOnProduct){
        this.fixedDiscountOnProduct=fixedDiscountOnProduct;
    }
    
    public String getFixedDiscountOnProduct(){
        return fixedDiscountOnProduct;
    }
    
    public void setMaxDiscount(String maxDiscount){
        this.maxDiscount=maxDiscount;
    }
    
    public String getMaxDiscount(){
        return maxDiscount;
    }
    
    public void setThreshold(String threshold){
        this.threshold=threshold;
    }
    
    public String getThreshold(){
        return threshold;
    }
    
    
}
