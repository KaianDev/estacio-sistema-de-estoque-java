package domain;

public class Supplier {
    private Integer id;
    private String name;

    public Supplier(){}

    public Supplier(String name){
        this.id = generateId();
        this.name = name;
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
}
