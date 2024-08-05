package domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class IceCreamShop {

    private List<IceCream> iceCreamList;
    private List<Supplier> supplierList;
    private List<Client> clientList;
    private String name;

    public IceCreamShop(){}

    public IceCreamShop(String name){
        this.name = name;
        iceCreamList = new ArrayList<>();
        supplierList = new ArrayList<>();
        clientList = new ArrayList<>();
    }

    public List<IceCream> getIceCreamList() {
        return iceCreamList;
    }

    public void setIceCreamList(List<IceCream> iceCreamList) {
        this.iceCreamList = iceCreamList;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void registerClient(Client client){
        clientList.add(client);
        System.out.println(MessageFormat.format(
                "Cliente cadastrado com sucesso! \nNome: {0} \nSorvetes: {1}",
                client.getName(), client.getIceCreamList())
        );
    }

    public void registerSupplier(Supplier supplier){
        supplierList.add(supplier);
    }

    public void registerIceCream(IceCream iceCream){
        iceCreamList.add(iceCream);
        System.out.println(MessageFormat.format(
                "{0} - O sorvete de {1} da marca {2} foi adicionado ao estoque!\n",
                iceCream.getId(),
                iceCream.getFlavor(),
                iceCream.getBrand()
        ));
    }

    public void listIceCream(){
        System.out.println("-------------- Lista de Sorvetes -----------");
        if(iceCreamList.isEmpty()){
           System.out.println("Lista Vazia");
       } else {
            for(IceCream iceCream: iceCreamList){
                System.out.println(iceCream.toString());
                System.out.println("----------");
            }
        }

    }

    public IceCream getIceCreamById (String id) {
        if(iceCreamList.isEmpty()){
            System.out.println("O estoque está vazio");
            return null;
        }
        for (IceCream iceCream: iceCreamList){
            if(iceCream.getId().equals(id)){
                return iceCream;
            } else {
                return null;
            }
        }
        return null;
    }
}
