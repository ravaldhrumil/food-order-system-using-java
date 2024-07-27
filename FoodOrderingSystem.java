import java.util.*;
import java.io.*;

public class FoodOrderingSystem {
    static Scanner scanner = new Scanner(System.in);

    static void CheckInput() {
        clearScreen();

        System.out.print("\n\t\tPress 1 to Pay " + calculateSum(TotalBill) + ".\n\t\tPress 2 to Cancel Payment.\n");
        System.out.print("\n\t\tPlease enter choice: ");
        try {
            int cpay = scanner.nextInt();

            switch (cpay) {
                case 1:
                    System.out.print("\n\t\t-) Your payment of " + calculateSum(TotalBill)
                            + " rupees is received successfully.\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    System.out.println("\n\t\t   Thank You For Visiting INDIAN FOOD TRUCK.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    clearScreen();
                    System.exit(0);
                    break;
                case 2:
                    System.out.print(
                            "\n\n\t\tPayment Cancellation of " + calculateSum(TotalBill) + " rupees is successful.\n");
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    DeleteOrderItems();
                    DeleteItemsCart();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\tEnter Valid input.");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    CheckInput();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n\tInvalid input!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ee) {

                ee.printStackTrace();
            }
            scanner.nextLine();
            CheckInput();
        }

    }

    static void CheckCVVNo() {
        try {
            System.out.print("\n\t\tEnter CVV number (in 3 digits): ");

            try {
                int cvv = scanner.nextInt();

                String checkCvv = Integer.toString(cvv);
                if (checkCvv.length() == 3) {
                    CheckInput();
                } else {
                    System.out.println("\t\t\tInvalid CVV number.");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    deleteLastThreeLines();
                    CheckCVVNo();
                }
            } catch (NumberFormatException e) {
                System.out.println("\n\tPlease enter integer only!");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ee) {
                    ee.printStackTrace();
                }
                deleteLastThreeLines();
                CheckCVVNo();
            }
        } catch (Exception ee) {
            System.out.println("Invalid input!!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scanner.nextLine();
            deleteLastThreeLines();
            CheckCVVNo();
        }

    }

    static void CheckCardNo() {
        clearScreen();
        System.out.println("\n\n\t\t---Enter Card Details---");

        System.out.print("\t\tEnter your Card number (in 12 digits): ");
        try {
            long cno = scanner.nextLong();

            String checkCno = Long.toString(cno);
            if (checkCno.length() == 12) {
                CheckCVVNo();
            } else {
                System.out.println("\t\t\tInvalid Card number.");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                deleteLastFourLines();
                CheckCardNo();
            }
        } catch (Exception ee) {
            System.out.println("\n\tInvalid input!");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            scanner.nextLine();
            CheckCardNo();
        }

    }

    static void PayBill() {
        clearScreen();

        System.out.print("\n\t\t >>> Bill of your Order is " + calculateSum(TotalBill) + ".\n");

        System.out.println("\n\t\t>> Press 1 to pay via card.");
        System.out.println("\t\t>> Press 2 to pay via UPI.");
        System.out.println("\t\t>> Press 3 for Cancel Payment and Exit.");
        System.out.print("\n\t\tPlease enter choice: ");

        try {
            int payment_option = scanner.nextInt();

            switch (payment_option) {
                case 1:
                    CheckCardNo();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("\n\t\tThe Payment ID is 1234567897.");
                    System.out.print("\n\t\t-) Payment of " + calculateSum(TotalBill) + " is received.\n");
                    System.out.println("\n\t\t   Thank You For Visiting INDIAN FOOD TRUCK.");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    clearScreen();
                    System.exit(0);
                    break;
                case 3:
                    System.out.printf("Payment Cancellation of %d rupees is successful.\n", calculateSum(TotalBill));
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!!");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    PayBill();
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n\tInvalid input!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            scanner.nextLine();
            PayBill();
        }

    }

    static int calculateSum(ArrayList<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }

    static void DisplayCart() {
        clearScreen();

        try (BufferedReader br = new BufferedReader(new FileReader("Display Cart.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("\n\t\t  Bill of your Order is " + calculateSum(TotalBill) + ".\n");

        System.out.println("\n\n\tPress 1 to Pay Bill.");
        System.out.println("\tPress 2 to Order More Items.");
        System.out.println("\tPress 3 to Cancel Order and order again.");
        System.out.println("\tPress 4 to exit.");
        System.out.print("\n\tPlease enter choice: ");

        try {
            int display_bill = scanner.nextInt();

            switch (display_bill) {
                case 1:
                    DeleteOrderItems();
                    DeleteItemsCart();
                    PayBill();
                    break;
                case 2:
                    MenuPage();
                    break;
                case 3:
                    TotalBill.clear();
                    DeleteOrderItems();
                    DeleteItemsCart();
                    MenuPage();
                    break;
                case 4:
                    DeleteOrderItems();
                    DeleteItemsCart();
                    clearScreen();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter Valid input!!");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DisplayCart();
                    break;
            }
        } catch (Exception ee) {
            System.out.println("\n\tInvalid input!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scanner.nextLine();
            DisplayCart();
        }
    }

    static void ReOrderLogic() {

        System.out.println("\t\t\t");
        System.out.print("\t\tPlease enter choice: ");
        try {
            int re_order = scanner.nextInt();

            try {
                if (re_order == 1) {
                    DisplayCart();
                } else if (re_order == 2) {
                    MenuPage();
                } else if (re_order == 3) {
                    TotalBill.clear();
                    DeleteOrderItems();
                    DeleteItemsCart();
                    MenuPage();
                } else if (re_order == 4) {
                    DeleteOrderItems();
                    DeleteItemsCart();
                    clearScreen();
                    System.exit(0);
                } else {
                    System.out
                            .print("\t\t\t- " + re_order + " is an incorrect option.\n\t\t\t- ReEnter Your Choice.\n");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    deleteLastFourLines();
                    ReOrderLogic();
                }
            } catch (Exception e) {
                System.out.printf("\t\t\t- %d is an invalid input.\n\t\t\t- ReEnter Your Choice.\n", re_order);
                deleteLastThreeLines();
                ReOrderLogic();
            }
        } catch (Exception ee) {
            System.out.println("\n\t\tInvalid input!!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            scanner.nextLine();
            deleteLastFourLines();
            ReOrderLogic();
        }

    }

    static void displayOrderedItems() {
        clearScreen();

        try (BufferedReader br = new BufferedReader(new FileReader("Display Ordered Items.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final ArrayList<Integer> TotalBill = new ArrayList<>();

    static void addToCart() {

        try (FileWriter writer = new FileWriter("Display Cart.txt", true)) {
            writer.write(String.format("\n\t\t  - %s %s of Price :- %d.", Quantity, FoodItem, FinalPrice));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void storeOrderedData() {

        try (FileWriter writer = new FileWriter("Display Ordered Items.txt", true)) {
            writer.write(
                    String.format("\n\t- You have selected %s %s of price :- %d", Quantity, Name_Of_Food,
                            FinalPrice));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void DeleteItemsCart() {
        clearScreen();

        try (BufferedReader br = new BufferedReader(new FileReader("Display Cart.txt"))) {
            String firstLine = br.readLine();

            try (FileWriter writer = new FileWriter("Display Cart.txt")) {
                if (firstLine != null) {
                    writer.write(firstLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DeleteOrderItems() {
        try (FileWriter writer = new FileWriter("Display Ordered Items.txt")) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int Quantity;
    static int FinalPrice;

    public static void quantity() {

        System.out.print("\t\t  || Enter the Quantity of " + Name_Of_Food + " you would like to have : ");
        try {
            Quantity = scanner.nextInt();

            try {
                if (Quantity <= 0) {
                    System.out.println("\t\t\t  - Enter Quantity Greater than ZERO.");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    deleteLastTwoLines();
                    quantity();
                } else {
                    deleteLastTwoLines();
                    FinalPrice = Price * Quantity;
                    storeOrderedData();
                    addToCart();

                    TotalBill.add(FinalPrice);

                }
            } catch (NumberFormatException e) {
                System.out.print("\t\t\t- " + Quantity + " is an invalid input, ReEnter Your Choice.\n");
                deleteLastTwoLines();
                quantity();
            }
        } catch (Exception ee) {
            System.out.println("\t\t\t  - Enter Quantity is invalid!!!.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            scanner.nextLine();
            deleteLastTwoLines();
            quantity();
        }

    }

    static String Name_Of_Food;
    static int Price;
    static String FoodItem;

    public static void StoreNameOrderedItem() {
        try (BufferedReader br = new BufferedReader(new FileReader("Menu_for_order_logic.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(String.valueOf(foodId))) {
                    String[] parts = line.split(":-");
                    String with3 = parts[0].trim();
                    Name_Of_Food = with3.substring(3);
                    Price = Integer.parseInt(parts[1].trim());
                    FoodItem = Name_Of_Food;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean foodIdCheck(int foodId) {
        try (BufferedReader br = new BufferedReader(new FileReader("Id_Check.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(String.valueOf(foodId))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void ReOrder() {
        System.out.println("\n\t\t>> Press 1 to Display Cart.(Pay bill available here)");
        System.out.println("\t\t>> Press 2 to Order More Items.");
        System.out.println("\t\t>> Press 3 to Cancel Order and Order Again.");
        System.out.println("\t\t>> Press 4 to Exit.");
        ReOrderLogic();
    }

    public static void DisplayOrderedItems() {
        try (BufferedReader br = new BufferedReader(new FileReader("Display Ordered Items.txt"))) {
            clearScreen();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int foodId;

    public static void itemOrder(int i) {
        System.out.print("\t\t  || Enter the ID of " + i + " item that you would like to Order : ");
        try {
            foodId = scanner.nextInt();

            if (foodId == '\n') {
                System.out.printf("\t\t\t   - %d is an Invalid Choice.\n\t\t\t   - ReEnter Your Choice.\n", foodId);
                scanner.nextLine();
                deleteLastThreeLines();
                itemOrder(i);
            } else if (foodIdCheck(foodId)) {
                StoreNameOrderedItem();
                quantity();
            } else {
                System.out
                        .print("\t\t\t   - Food ID " + foodId
                                + " is not available.\n\t\t\t   - ReEnter the ID of Food.\n");
                try {
                    Thread.sleep(1500);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                scanner.nextLine();
                deleteLastThreeLines();
                itemOrder(i);
            }
        } catch (Exception ee) {
            System.out
                    .print("\t\t\t   - Entered food ID is not available.\n\t\t\t   - ReEnter the ID of Food.\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            scanner.nextLine();
            deleteLastThreeLines();
            itemOrder(i);
        }

    }

    public static void deleteLastLines() {
        for (int i = 0; i < 1; i++) {
            System.out.print("\033[1A");
            System.out.print("\033[2K");
        }
    }

    public static void deleteLastTwoLines() {
        for (int i = 0; i < 2; i++) {
            System.out.print("\033[1A");
            System.out.print("\033[2K");
        }
    }

    public static void deleteLastThreeLines() {
        for (int i = 0; i < 3; i++) {
            System.out.print("\033[1A");
            System.out.print("\033[2K");
        }
    }

    public static void deleteLastFourLines() {
        for (int i = 0; i < 4; i++) {
            System.out.print("\033[1A");
            System.out.print("\033[2K");
        }
    }

    static int ItemOrder;

    public static void Order() {

        System.out.print("\t  >> How many items would you like to order in total : ");
        try {
            ItemOrder = scanner.nextInt();

            if (ItemOrder > 0) {
                for (int i = 1; i <= ItemOrder; i++) {
                    itemOrder(i);
                }
                DisplayOrderedItems();
                ReOrder();
            } else {
                System.out.println("\t\t\t- Enter Items Greater than ZERO.");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                scanner.nextLine();
                deleteLastTwoLines();
                Order();
            }
        } catch (Exception ee) {
            System.out.println("\t\t\t- Invalid input!!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scanner.nextLine();
            deleteLastTwoLines();
            Order();
        }

    }

    public static void Menu() {
        try (BufferedReader br = new BufferedReader(new FileReader("Menu.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MenuPage() {
        clearScreen();
        Menu();
        Order();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Custinput() {
        try {
            Scanner sc = new Scanner(System.in);
            try (BufferedReader br = new BufferedReader(new FileReader("Welcome Page.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            int CustomerInput = sc.nextInt();
            if (CustomerInput == 1) {
                MenuPage();
            } else {
                System.out
                        .println("\n\t\t- " + CustomerInput + " is Not a Valid Input.\n\t\t- Enter Your Choice Again.");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                sc.nextLine();
                clearScreen();
                Custinput();
            }
            sc.close();
        } catch (Exception ee) {
            System.out.println("\nInvalid input!!");
            try {
                Thread.sleep(3000);
                clearScreen();
                Custinput();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        DeleteOrderItems();
        DeleteItemsCart();
        Custinput();
    }
}