package domain;

import java.text.MessageFormat;

public class IceCream {
    private String id;
    private String flavor;
    private String brand;
    private Double price;
    private Integer quantity;

    public IceCream(){}

    public IceCream(String flavor, String brand, Double price, Integer quantity) {
        this.id = MessageFormat.format("#{0}-{1}", flavor.toLowerCase(), brand.toLowerCase()).replaceAll(" ","");
        this.flavor = flavor;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuantity(Integer quantity){
        this.quantity -= quantity;
    }

    public void increaseQuantity(Integer quantity){
        this.quantity += quantity;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return MessageFormat.format("ID: {0} \nSabor: {1} \nPreço: {2} \nMarca: {3} \nQuantidade: {4}",
                getId(), getFlavor(), getPrice(), getBrand(), getQuantity()
        );
    }
}
