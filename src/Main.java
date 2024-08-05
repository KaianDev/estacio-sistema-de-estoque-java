import domain.Client;
import domain.IceCream;
import domain.IceCreamShop;
import domain.Supplier;

import java.text.MessageFormat;
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
            System.out.println("5 - SAIR");
            System.out.println("-----------------------------------------\n");


            System.out.print("O QUE DESEJA FAZER? ");
            int choice = scanner.nextInt();

            if(choice == 1){
                System.out.println("👉 REGISTRAR SORVETE");
                System.out.print("Informe o sabor: ");
                String flavor = scanner.next();
                System.out.print("Informe a marca: ");
                String brand = scanner.next();
                System.out.print("Informe o preço: (Ex: 10,0) ");
                double price = scanner.nextDouble();
                System.out.print("Informe a quantidade: ");
                int quantity = scanner.nextInt();

                IceCream iceCream = new IceCream(flavor, brand, price, quantity);
                iceCreamShop.registerIceCream(iceCream);
            }

            if(choice == 2){
                System.out.println("👉 REGISTRAR FORNECEDOR");
                System.out.print("Informe o nome do Fornecedor: ");
                String supplierName = scanner.next();

                Supplier supplier = new Supplier(supplierName);
                iceCreamShop.registerSupplier(supplier);
            }

            if(choice == 3){
                System.out.println("👉 REGISTRAR CLIENTE");
                System.out.print("Informe o nome do cliente: ");
                String clientName = scanner.next();

                System.out.println("O que o cliente comprou?");
                iceCreamShop.listIceCream();

                System.out.print("Informe o identificador: (Ex: #Sabor@Marca) ");
                String iceCreamId = scanner.next();
                IceCream iceCream = iceCreamShop.getIceCreamById(iceCreamId);

                Client client = new Client(clientName);
                client.addIceCreamToList(iceCream);


                iceCreamShop.registerClient(client);
            }

            if(choice == 4){
                iceCreamShop.listIceCream();
            }

            if(choice == 5){
                System.out.println("Até logo 👋🏻");
                break;
            }
        }


        scanner.close();
    }
}