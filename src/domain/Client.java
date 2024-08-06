package domain;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private Integer id;
    private String name;
    private List<IceCream> iceCreamList;

    public Client(){}

    public Client(String name){
        this.id = generateId();
        this.name = name;
        iceCreamList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer generateId(){
        return  getId() == null ? 1 : getId() +1;
    }

    public void addIceCreamToList(IceCream iceCream){
        this.iceCreamList.add(iceCream);
    }

    public List<IceCream> getIceCreamList() {
        return iceCreamList;
    }

    public void setIceCreamList(List<IceCream> iceCreamList) {
        this.iceCreamList = iceCreamList;
    }

    public void totalIceCreamValue(){
        var totalIceCreamValue = iceCreamList.stream().mapToDouble(x ->  x.getQuantity() * x.getPrice()).reduce(Double::sum);
        if(totalIceCreamValue.isPresent()){
            System.out.println("Valor total do cliente "+totalIceCreamValue.getAsDouble());
        }
    }
}
