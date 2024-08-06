package domain;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                "Cliente cadastrado com sucesso! \nNome: {0}", client.getName())
        );
    }

    public void registerSupplier(Supplier supplier){
        supplierList.add(supplier);
    }

    public void registerIceCream(IceCream iceCream){
        if (iceCreamList.isEmpty()){
            iceCreamList.add(iceCream);
            System.out.println(MessageFormat.format(
                    "🎉 {0} - O sorvete de {1} da marca {2} foi adicionado ao estoque! \n",
                    iceCream.getId(),
                    iceCream.getFlavor(),
                    iceCream.getBrand()
            ));
        } else {
            for (IceCream iceCream1: iceCreamList){
                if(Objects.equals(iceCream1.getId(), iceCream.getId())){
                    System.out.println("❌ O sorvete já foi está cadastrado!");
                    return;
                }
            }
            iceCreamList.add(iceCream);
            System.out.println(MessageFormat.format(
                    "🎉 {0} - O sorvete de {1} da marca {2} foi adicionado ao estoque!\n",
                    iceCream.getId(),
                    iceCream.getFlavor(),
                    iceCream.getBrand()
            ));
        }

    }

    public void listIceCream(){
        System.out.println("--- Lista de Sorvetes ---");
        if(iceCreamList.isEmpty()){
            System.out.println("Lista Vazia");
        } else {
            for(IceCream iceCream: iceCreamList){
                System.out.println(iceCream.toString());
                System.out.println("----------");
            }
        }
        this.totalStock();

    }

    public Optional<IceCream> getIceCreamById (String id) {
        if(iceCreamList.isEmpty()){
            System.out.println("O estoque está vazio");
            return Optional.empty();
        }
        for (IceCream iceCream: iceCreamList){
            if(iceCream.getId().equalsIgnoreCase(id)){
                return Optional.of(iceCream);
            }
        }
        return Optional.empty();
    }

    public void totalStock(){
        var tot = iceCreamList.stream().mapToInt(IceCream::getQuantity).reduce(Integer::sum);
        if(tot.isPresent()) {
            System.out.println("Quantidade no estoque: " +tot.getAsInt());
            System.out.println("-----------");
        }
    }

    public void listClients(){
        if(clientList.isEmpty()){
            System.out.println("❌ Não há clientes cadastrados");
        } else {
            for (Client client: clientList){
                System.out.println(MessageFormat.format("Nome: {0}", client.getName()));
                if(client.getIceCreamList().isEmpty()){
                    System.out.println("O cliente não comprou sorvetes");
                } else {
                    System.out.println("Pedidos: ");
                    client.getIceCreamList().forEach(iceCream -> System.out.println(iceCream.toString() +"\n"));
                    client.totalIceCreamValue();
                    System.out.println("\n--------");
                }
            }

        }
    }

    public Optional<Client> getClientByName(String clientName){
        if(clientList.isEmpty()){
            System.out.println("❌ Não clientes cadastrados");
            return Optional.empty();
        }
        for (Client client : clientList){
            if(client.getName().equalsIgnoreCase(clientName)){
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

    public void listSuppliers(){
        System.out.println("--- Lista de fornecedores ---");
        if(supplierList.isEmpty()){
            System.out.println("❌ Nenhum fornecedor cadastrado!");
        } else {
            supplierList.forEach(s -> System.out.println("Nome do fornecedor: "+s.getName()));
        }
        System.out.println("-----------");
    }

    public Optional<Supplier> getSupplierByName(String supplierName){
        if(supplierList.isEmpty()){
            System.out.println("❌ Não clientes cadastrados");
            return Optional.empty();
        }
        for (Supplier supplier : supplierList){
            if(supplier.getName().equalsIgnoreCase(supplierName)){
                return Optional.of(supplier);
            }
        }
        return Optional.empty();
    }
}
