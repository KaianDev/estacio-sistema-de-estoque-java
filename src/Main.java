import domain.Client;
import domain.IceCream;
import domain.IceCreamShop;
import domain.Supplier;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IceCreamShop iceCreamShop = new IceCreamShop("GELATOS SORVETERIA");

        System.out.println(
                MessageFormat.format(
                        "BEM-VINDO AO SISTEMA DE ESTOQUE DÁ {0} 😁",
                        iceCreamShop.getName())
        );
        System.out.println("---------------------------------------\n");
        while (true){
            System.out.println("1 - REGISTRAR SORVETE");
            System.out.println("2 - REGISTRAR FORNECEDOR");
            System.out.println("3 - REGISTRAR CLIENTE");
            System.out.println("4 - VER ESTOQUE");
            System.out.println("5 - LISTAR CLIENTES & COMPRAS");
            System.out.println("6 - REGISTRAR VENDA");
            System.out.println("7 - REGISTRAR COMPRA");
            System.out.println("8 - SAIR");
            System.out.println("-----------------------------------------\n");


            System.out.print("O QUE DESEJA FAZER? ");
            int choice = scanner.nextInt();

            if(choice == 1){
                registerIceCream(scanner, iceCreamShop);
            }

            if(choice == 2){
                registerSupplier(scanner, iceCreamShop);
            }

            if(choice == 3){
                registerClient(scanner, iceCreamShop);
            }

            if(choice == 4){
                seeStock(iceCreamShop);
            }

            if(choice == 5){
                listClientAndShopping(iceCreamShop);
            }

            if(choice == 6){
                registerSales(scanner, iceCreamShop);
            }

            if(choice == 7) {
                registerShopping(scanner, iceCreamShop);
            }

            if(choice == 8){
                System.out.println("Até logo! 👋🏻");
                break;

            }

        }

        scanner.close();
    }
    public static void registerIceCream(Scanner scanner, IceCreamShop iceCreamShop){
        scanner.nextLine();
        System.out.println("👉 REGISTRAR SORVETE");
        System.out.print("Informe o sabor: ");
        String flavor = scanner.nextLine();
        System.out.print("Informe a marca: ");
        String brand = scanner.nextLine();
        System.out.print("Informe o preço: (Ex: 10,0) ");
        double price = scanner.nextDouble();
        System.out.print("Informe a quantidade: ");
        int quantity = scanner.nextInt();

        IceCream iceCream = new IceCream(flavor, brand, price, quantity);
        iceCreamShop.registerIceCream(iceCream);
    }

    public static void registerSupplier(Scanner scanner, IceCreamShop iceCreamShop){
        scanner.nextLine();
        System.out.println("👉 REGISTRAR FORNECEDOR");
        System.out.print("Informe o nome do Fornecedor: ");
        String supplierName = scanner.nextLine();

        Supplier supplier = new Supplier(supplierName);
        iceCreamShop.registerSupplier(supplier);
    }

    public static void registerClient(Scanner scanner, IceCreamShop iceCreamShop){
        scanner.nextLine();
        System.out.println("👉 REGISTRAR CLIENTE");
        System.out.print("Informe o nome do cliente: ");
        String clientName = scanner.nextLine();

        Client client = new Client(clientName);
        iceCreamShop.registerClient(client);
    }

    public static void seeStock(IceCreamShop iceCreamShop){
        System.out.println("👉 VER ESTOQUE");
        iceCreamShop.listIceCream();
    }

    public static void listClientAndShopping(IceCreamShop iceCreamShop){
        System.out.println("👉 LISTAR CLIENTES & COMPRAS");
        iceCreamShop.listClients();
    }

    public static void registerSales(Scanner scanner, IceCreamShop iceCreamShop){
        scanner.nextLine();
        System.out.println("👉 REGISTRAR VENDAS");
        System.out.print("Informe o nome do cliente: ");
        String clientName = scanner.nextLine();
        Optional<Client> client = iceCreamShop.getClientByName(clientName);
        if(client.isPresent()){
            while (true){
                iceCreamShop.listIceCream();
                System.out.print("Informe o id: ");
                String iceCreamId = scanner.next();
                Optional<IceCream> iceCream = iceCreamShop.getIceCreamById(iceCreamId);

                if(iceCream.isPresent()){
                    var iceCreamItemFromStock = iceCream.get();
                    System.out.print("Informe a quantidade: ");
                    int quantity = scanner.nextInt();

                    if(iceCreamItemFromStock.getQuantity() >= quantity){
                        IceCream newIceCreamClientItem = new IceCream(
                                iceCreamItemFromStock.getFlavor(),
                                iceCreamItemFromStock.getBrand(),
                                iceCreamItemFromStock.getPrice(),
                                quantity
                        );
                        iceCreamItemFromStock.decreaseQuantity(quantity);
                        client.get().addIceCreamToList(newIceCreamClientItem);
                    } else {
                        System.out.println("❌ Quantidade insuficiente no estoque.");
                    }
                }
                System.out.print("Deseja registrar mais vendar para esse cliente? [S/N] ");
                String option = scanner.next();

                if(option.equalsIgnoreCase("n")){
                    break;
                }

            }
        }
    }

    public static void registerShopping(Scanner scanner, IceCreamShop iceCreamShop){
        scanner.nextLine();
        System.out.println("👉 REGISTRAR COMPRAS");
        iceCreamShop.listSuppliers();
        var supplierList = iceCreamShop.getSupplierList();
        if(!supplierList.isEmpty()){
            System.out.print("Informe o nome do fornecedor: ");
            String supplierName = scanner.nextLine();
            Optional<Supplier> supplier = iceCreamShop.getSupplierByName(supplierName);
            if(supplier.isPresent()){
                while (true){
                    System.out.print("O que deseja abastecer: ");
                    iceCreamShop.listIceCream();

                    System.out.print("Informe o ID do sorvete: ");
                    String iceCreamId = scanner.next();

                    Optional<IceCream> iceCream = iceCreamShop.getIceCreamById(iceCreamId);

                    if(iceCream.isPresent()){
                        System.out.print("Digite a quantidade que deseja abastecer: ");
                        int quantity = scanner.nextInt();

                        iceCream.get().increaseQuantity(quantity);
                    }

                    System.out.print("Deseja abastecer mais? [S/N] ");
                    String option = scanner.next();

                    if(option.equalsIgnoreCase("n")){
                        break;
                    }
                }
            }
        }
    }
}