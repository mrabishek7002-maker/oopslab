
import java.util.Scanner;

class Product {
    int id;
    String name;
    String category;
    double price;
    String seller;
    boolean sold;

    Product(int id, String name, String category,
            double price, String seller) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.seller = seller;
        this.sold = false;
    }

    void display() {
        System.out.println("-----------------------------");
        System.out.println("Product ID   : " + id);
        System.out.println("Product Name : " + name);
        System.out.println("Category     : " + category);
        System.out.println("Price        : Rs." + price);
        System.out.println("Seller       : " + seller);
        System.out.println("Status       : " +
                (sold ? "Sold" : "Available"));
    }
}

public class SecondHandExchange {

    static Scanner sc = new Scanner(System.in);
    static Product[] products = new Product[100];
    static int count = 0;

    static void addProduct() {
        if (count == products.length) {
            System.out.println("Product storage is full!");
            return;
        }

        System.out.println("\n--- Add Product ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].id == id) {
                System.out.println("Product ID already exists!");
                return;
            }
        }

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        if (price < 0) {
            System.out.println("Invalid price!");
            return;
        }

        System.out.print("Enter Seller Name: ");
        String seller = sc.nextLine();

        products[count] = new Product(
                id, name, category, price, seller);
        count++;

        System.out.println("Product Added Successfully!");
    }

    static void viewProducts() {
        System.out.println("\n--- Available Products ---");

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (!products[i].sold) {
                products[i].display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Products Available!");
        }
    }

    static void searchProduct() {
        System.out.println("\n--- Search Product ---");

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (products[i].name.equalsIgnoreCase(name)) {
                products[i].display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Product Not Found!");
        }
    }

    static void buyProduct() {
        System.out.println("\n--- Buy Product ---");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].id == id) {

                if (products[i].sold) {
                    System.out.println(
                            "Product Already Sold!");
                } else {
                    System.out.println(
                            "Product: " + products[i].name);
                    System.out.println(
                            "Price: Rs." + products[i].price);

                    System.out.print(
                            "Confirm Purchase (Y/N): ");
                    char choice = sc.next().charAt(0);
                    sc.nextLine();

                    if (choice == 'Y' || choice == 'y') {
                        products[i].sold = true;
                        System.out.println(
                                "Purchase Successful!");
                    } else {
                        System.out.println(
                                "Purchase Cancelled!");
                    }
                }
                return;
            }
        }

        System.out.println("Product Not Found!");
    }

    static void soldProducts() {
        System.out.println("\n--- Sold Products ---");

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (products[i].sold) {
                products[i].display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Products Sold Yet!");
        }
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println(
                    "\n===== SECOND HAND PRODUCT EXCHANGE =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Available Products");
            System.out.println("3. Search Product");
            System.out.println("4. Buy Product");
            System.out.println("5. View Sold Products");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    viewProducts();
                    break;

                case 3:
                    searchProduct();
                    break;

                case 4:
                    buyProduct();
                    break;

                case 5:
                    soldProducts();
                    break;

                case 6:
                    System.out.println(
                            "Thank You for Using Our System!");
                    break;

                default:
                    System.out.println(
                            "Invalid Choice! Try Again.");
            }

        } while (choice != 6);

        sc.close();
    }
}