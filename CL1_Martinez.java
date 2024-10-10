/* Vianey Martinez
[CS1101] Comprehensive Lab 1
This work is to be done individually. It is not permitted to.
share, reproduce, or alter any part of this assignment for any
purpose. Students are not permitted to share code, upload
this assignment online in any form, or view/receive/
modifying code written by anyone else. This assignment is part.
of an academic course at The University of Texas at El Paso and
a grade will be assigned for the work produced individually by
the student.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CL1_Martinez{

    public static void main(String[] args){
        Scanner userIn = new Scanner(System.in);

        boolean isMinerEats = true;

        int quantity = 0;

        double subtotal = 0;

        System.out.println("Welcome to Miner Eats!");

        while (isMinerEats){//Start Loop
            //Menu
            System.out.println("----------------Menu----------------");
            System.out.println("Choose an option (1-5): ");
            System.out.println("1. Add a food");
            System.out.println("2. View cart");
            System.out.println("3. Clear cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.println("------------------------------------");

            int option = userIn.nextInt();

            switch (option) {
                case 1://Order
                    Scanner userInput = new Scanner(System.in);
                    try {
                        File file = new File("food.txt");
                        Scanner scanner = new Scanner(file);

                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            System.out.println(line);
                        }
                        scanner.close();
                    } catch (FileNotFoundException e) {

                        System.out.println("File not found: " + e.getMessage());

                    }

                    double foodPrice = 0;
                    System.out.println("Please type the food you want to add: ");
                    String foodChoice = userInput.nextLine();
                    
                    try {
                        File myFile = new File("food.txt");
                        Scanner scanner = new Scanner(myFile);

                        while (scanner.hasNextLine()){//Start Loop
                            String food = scanner.next();
                            double price = scanner.nextDouble();

                                if(foodChoice.equalsIgnoreCase(food)){
                                    foodPrice = price;
                                }
                            }//End Loop
                            scanner.close();

                    } catch (FileNotFoundException e){
                        System.out.println("File not found: " + e.getMessage());
                    }

                        System.out.println("How much of that item do you want?");
                            int firstQuantity = userInput.nextInt();

                            subtotal += foodPrice * firstQuantity;
                            quantity += firstQuantity;

                        System.out.println("----------------Cart----------------");
                        System.out.println("Number of items: "+ quantity);
                        System.out.println("Total: $"+ subtotal);
                        System.out.println("------------------------------------\n");
                        break;

                case 2://View Cart
                    System.out.println("----------------Cart----------------");
                    System.out.println("Number of items: "+ quantity);
                    System.out.println("Total: $"+ subtotal);
                    System.out.println("------------------------------------\n");
                    break;
                    
                case 3://Clear Cart
                    System.out.println("Are you sure you would like to clear the cart? \nYes or No");
                    Scanner clearCart = new Scanner(System.in);
                    String clearCartResponse = clearCart.nextLine();

                    if (clearCartResponse.equalsIgnoreCase("Yes")) {
                        quantity = 0;
                        subtotal = 0.0;
                        System.out.println("Your cart is now empty.");
                        System.out.println("Number of items: " + quantity);
                        System.out.println("Total: $" + subtotal);
                    } else if (clearCartResponse.equalsIgnoreCase("No")) {
                        System.out.println("Cart not changed. Going back to menu.");
                    } else {
                        System.out.println("Invalid response, please enter Yes or No. Try again.");
                        clearCartResponse = clearCart.nextLine();
                        if (clearCartResponse.equalsIgnoreCase("Yes")) {
                            quantity = 0;
                            subtotal = 0.0;
                            System.out.println("Your cart is now empty.");
                            System.out.println("Number of items: " + quantity);
                            System.out.println("Total: $" + subtotal);
                        } else if (clearCartResponse.equalsIgnoreCase("No")) {
                            System.out.println("Cart not changed. Going back to menu.\n");
                        }
                    }
                    break;

                case 4:// Checkout
                    System.out.println("How do you want your item? \n1. Pickup   2. Delivery");
                    Scanner checkOut = new Scanner(System.in);
                    int checkOutResponse = checkOut.nextInt();
                    String cardNumber = "";
                    double total = 0.0;

                    switch (checkOutResponse) {
                        case 1:// Pickup
                            total = (subtotal * 0.0725) + subtotal;

                            System.out.println("----------Order Information---------");
                            System.out.println("Number of items: " + quantity);
                            System.out.println("Total: $" + total);
                            System.out.println("------------------------------------\n");

                            // Card Info
                            System.out.println("Please enter a 16-digit card number for payment: ");
                            cardNumber = checkOut.next();

                            while (cardNumber.length() != 16) {
                                System.out.println("Error: Please be sure you've entered 16 digits. Try again.");
                                cardNumber = checkOut.next();
                            }

                            // Print Receipt
                            System.out.println("Success! Thank you for shopping with Miner Eats!\n");

                            System.out.println("----------------Receipt----------------");
                            System.out.println("Number of items purchased: " + quantity);
                            System.out.println("Total: $" + total);
                            System.out.println("----------------Receipt----------------\n");
                            System.out.println("Ending program.");

                            isMinerEats = false;
                            break;

                        case 2:// Delivery
                            System.out.println("There will be a $5.00 delivery fee.\n");

                            double delivery = 5.00;
                            subtotal += delivery;
                            total = (subtotal * 0.0725) + subtotal;

                            System.out.println("Please enter your address: ");
                            checkOut.nextLine();
                            String address = checkOut.nextLine();
                            System.out.println("Delivery address is: "+ address);

                            System.out.println("Would you like to add a tip? \nEnter the number: 1. Yes  2. No");
                            String tipChoice = checkOut.nextLine();

                            if (tipChoice.equals("1")) { // Yes to Tip
                                System.out.println("What percent would you like to tip?");
                                double tipPercent = checkOut.nextDouble();
                                System.out.println("You tipped %" + tipPercent);

                                total = total + (total * (tipPercent / 100));

                                System.out.println("----------Order Information---------");
                                System.out.println("Number of items: " + quantity);
                                System.out.println("Total: $" + total);
                                System.out.println("------------------------------------\n");

                                // Card Info
                                System.out.println("Please enter a 16-digit card number for payment: ");
                                cardNumber = checkOut.next();

                                while (cardNumber.length() != 16) {//Loop Starts
                                    System.out.println("Error: Please be sure you've entered 16 digits. Try again.");
                                    cardNumber = checkOut.next();
                                }//Loop Ends

                                // Print Receipt
                                System.out.println("Success! Thank you for shopping with Miner Eats!\n");
                                System.out.println("----------------Receipt----------------");
                                System.out.println("Number of items purchased: " + quantity);
                                System.out.println("Total: $" + total);
                                System.out.println("----------------Receipt----------------\n");
                                System.out.println("Ending program.");

                                isMinerEats = false;
                            } else if (tipChoice.equals("2")) { // No to Tip
                                System.out.println("----------Order Information---------");
                                System.out.println("Number of items: " + quantity);
                                System.out.println("Total: $" + total);
                                System.out.println("------------------------------------\n");

                                // Card Info
                                System.out.println("Please enter a 16-digit card number for payment: ");
                                cardNumber = checkOut.next();

                                while (cardNumber.length() != 16) {//Start Loop
                                    System.out.println("Error: Please be sure you've entered 16 digits. Try again.");
                                    cardNumber = checkOut.next();
                                }//End Loop

                                // Print Receipt
                                System.out.println("Success! Thank you for shopping with Miner Eats!");
                                System.out.println("----------------Receipt----------------");
                                System.out.println("Number of items purchased: " + quantity);
                                System.out.println("Total: $" + total);
                                System.out.println("----------------Receipt----------------\n");
                                System.out.println("Ending program.");

                                isMinerEats = false;
                            } else {
                                System.out.println("Invalid option for tip.");
                            }
                            break;

                        default:
                            System.out.println("Invalid input. Please try again and enter either 1 or 2 only!");
                        }
                        break;

                case 5://Exit
                    System.out.println("Thank you for using Miner Eats. Goodbye!");
                    isMinerEats = false;
                    break;

                default://Invalid Input
                    System.out.println("The option entered is not apart of our menu. Please try again.");
                    option = userIn.nextInt();
                    break;

            }//End of Switch
        }//End Loop
    }//Public Static
}//Public Class