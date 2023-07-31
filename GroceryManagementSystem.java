import java.util.Scanner;

class Item {
    String name;
    int price;
    int stock;

    Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

public class GroceryManagementSystem {
    private static Scanner in = new Scanner(System.in);
    private static Item[] inventory = new Item[100];
    private static int itemCount = 5;
    static {
        inventory[0] = new Item("rice", 100, 10);
        inventory[1] = new Item("atta", 70, 8);
        inventory[2] = new Item("oil", 80, 9);
        inventory[3] = new Item("dal", 60, 15);
        inventory[4] = new Item("sugar", 50, 20);
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("----- Grocery Management System -----");
            System.out.println("1. Admin");
            System.out.println("2. Shopkeeper");
            System.out.println("3. Client");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    shopkeeperMenu();
                    break;
                case 3:
                    clientMenu();
                    break;
                case 4:
                    System.out.println("Thank you for choosing our services!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private static void displayItemList() {
        System.out.println("----- Item List -----");
        System.out.println("Name\tPrice\tStock");
        for (int i = 0; i < itemCount; i++) {
            System.out.println(inventory[i].name + "\t" + inventory[i].price + "\t" + inventory[i].stock);
        }
    }

    private static void adminMenu() {
        displayItemList();
    }

    private static void shopkeeperMenu() {
        System.out.println("Enter the operation type");
        System.out.println("1. Display item list");
        System.out.println("2. Add new item");
        System.out.println("3. Update price of item");
        System.out.println("4. Update item stock");
        int operation = in.nextInt();
        in.nextLine();

        switch (operation) {
            case 1:
                displayItemList();
                break;
            case 2:
                addNewItem();
                break;
            case 3:
                updateItemPrice();
                break;
            case 4:
                updateItemStock();
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
        }
    }

    private static void addNewItem() {
        System.out.print("Enter the number of items to add: ");
        int numItemsToAdd = in.nextInt();
        in.nextLine();

        for (int i = 0; i < numItemsToAdd; i++) {
            System.out.print("Enter item name: ");
            String name = in.nextLine();

            boolean itemExists = false;
            for (int j = 0; j < itemCount; j++) {
                if (name.equalsIgnoreCase(inventory[j].name)) {
                    itemExists = true;
                    break;
                }
            }

            if (itemExists) {
                System.out.println("Item \"" + name + "\" already exists.");
            } else {
                System.out.print("Enter item price: ");
                int price = in.nextInt();
                in.nextLine();
                System.out.print("Enter item stock: ");
                int stock = in.nextInt();
                in.nextLine();

                inventory[itemCount] = new Item(name, price, stock);
                itemCount++;

                System.out.println("Item added successfully!");
            }
        }
    }

    private static void updateItemPrice() {
        System.out.print("Enter the number of items whose price is to be updated: ");
        int numItemsToUpdate = in.nextInt();
        in.nextLine();

        for (int i = 0; i < numItemsToUpdate; i++) {
            System.out.print("Enter the name of the item whose price is to be updated: ");
            String itemName = in.nextLine();
            boolean itemFound = false;

            for (int j = 0; j < itemCount; j++) {
                if (itemName.equalsIgnoreCase(inventory[j].name)) {
                    System.out.print("Enter the new price: ");
                    int newPrice = in.nextInt();
                    in.nextLine();
                    inventory[j].price = newPrice;
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                System.out.println("Item \"" + itemName + "\" not found in the inventory.");
            }
        }
    }

    private static void updateItemStock() {
        System.out.print("Enter the number of items whose stock is to be updated: ");
        int numItemsToUpdate = in.nextInt();
        in.nextLine();

        for (int i = 0; i < numItemsToUpdate; i++) {
            System.out.print("Enter the name of the item whose stock is to be updated: ");
            String itemName = in.nextLine();
            boolean itemFound = false;

            for (int j = 0; j < itemCount; j++) {
                if (itemName.equalsIgnoreCase(inventory[j].name)) {
                    System.out.print("Enter the new stock: ");
                    int newStock = in.nextInt();
                    in.nextLine();
                    inventory[j].stock = newStock;
                    itemFound = true;
                    break;
                }
            }

            if (!itemFound) {
                System.out.println("Item \"" + itemName + "\" not found in the inventory.");
            }
        }
    }

    private static void clientMenu() {
        System.out.println("Enter the operation type");
        System.out.println("1. Display item list");
        System.out.println("2. Item to buy");
        int operation = in.nextInt();
        in.nextLine();

        switch (operation) {
            case 1:
                displayItemList();
                break;
            case 2:
                buyItems();
                break;
            default:
                System.out.println("Invalid operation. Please try again.");
        }
    }

    private static void buyItems() {
        System.out.println("----- Item List -----");
        System.out.println("Name\tPrice\tStock");
        for (int i = 0; i < itemCount; i++) {
            System.out.println(inventory[i].name + "\t" + inventory[i].price + "\t" + inventory[i].stock);
        }
        System.out.println("---------------------");

        int totalAmount = 0;

        while (true) {
            System.out.print("Enter the name of the item you want to buy (or type 'exit' to stop): ");
            String itemName = in.nextLine();
            if (itemName.equalsIgnoreCase("exit")) {
                break;
            }

            boolean itemFound = false;
            int itemIndex = -1;

            for (int i = 0; i < itemCount; i++) {
                if (itemName.equalsIgnoreCase(inventory[i].name)) {
                    itemFound = true;
                    itemIndex = i;
                    break;
                }
            }

            if (itemFound) {
                System.out.print("Enter the quantity you want to buy: ");
                int quantityToBuy = in.nextInt();
                in.nextLine(); // Consume the newline character left by nextInt()

                if (inventory[itemIndex].stock < quantityToBuy) {
                    System.out.println("This item is not available for the quantity you asked.");
                } else {
                    int totalPrice = inventory[itemIndex].price * quantityToBuy;
                    inventory[itemIndex].stock -= quantityToBuy;
                    totalAmount += totalPrice;

                    System.out.println("----- Receipt -----");
                    System.out.println("Item Name: " + itemName);
                    System.out.println("Unit Price: " + inventory[itemIndex].price);
                    System.out.println("Quantity: " + quantityToBuy);
                    System.out.println("Total Price: " + totalPrice);
                    System.out.println("-------------------");
                }
            } else {
                System.out.println("Item \"" + itemName + "\" not found in the inventory.");
            }
        }

        System.out.println("Total Amount to be Paid: " + totalAmount);
    }

}
