
package propertyrentalsystem;
import java.util.Scanner;

class Property {
    String type;
    double monthlyRent;
    boolean isRented;
    String tenantName;
    String contactNumber;
    int leaseDuration;
    

    public Property(String type, double monthlyRent) {
        this.type = type;
        this.monthlyRent = monthlyRent;
        this.isRented = false;
        this.tenantName = "";
        this.contactNumber = "";
        this.leaseDuration = 0;
        
        
  
    }
}


public class PropertyRentalSystem {
    static Property[] properties = new Property[4]; 
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeProperties();

        while (true) {
            System.out.println("\nWelcome to the Property Rental System!\n");
            System.out.println("1. List available properties");
            System.out.println("2. Rent a property");
            System.out.println("3. View rented properties");
            System.out.println("4. Add a new property");
            System.out.println("5. Remove Tenant");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAvailableProperties();
                    break;
                case 2:
                    rentProperty();
                    break;
                case 3:
                    viewRentedProperties();
                    break;
                case 4:
                    addNewProperty();
                    break;
                    case 5:
                    removeTenant();
                    break;
                case 6:
                    System.out.println("Exiting the Property Rental System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void initializeProperties() {
        properties[0] = new Property("Apartment", 1000.0);
        properties[1] = new Property("House", 1500.0);
        properties[2] = new Property("Condo", 1200.0);
    }

    private static void listAvailableProperties() {
        System.out.println("\nAvailable Properties:");
        System.out.println("----------------------------------------------");
        System.out.printf("| %-6s | %-20s | %-12s |\n", "Number", "Property Type", "Monthly Rent");
        System.out.println("----------------------------------------------");

        boolean hasAvailableProperties = false;

        for (int i = 0; i < properties.length; i++) {
            Property property = properties[i];
            if (property != null && !property.isRented) {
                System.out.printf("| %-6d | %-20s | $%-12.2f |\n", i + 1, property.type, property.monthlyRent);
                hasAvailableProperties = true;
            }
        }

        if (!hasAvailableProperties) {
            System.out.println("| No available properties.                       |");
        }

        System.out.println("----------------------------------------------");
    }


    private static void rentProperty() {
        listAvailableProperties();

        System.out.print("\nEnter property number to rent: ");
        int propertyNumber = scanner.nextInt();

        if (propertyNumber >= 1 && propertyNumber <= properties.length) {
            Property property = properties[propertyNumber - 1];

            if (!property.isRented) {
                System.out.println("\nEnter tenant details:");
                System.out.print("Tenant Name: ");
                scanner.nextLine(); 
                String tenantName = scanner.nextLine();
                System.out.print("Contact Number: ");
                String contactNumber = scanner.nextLine();
                System.out.print("Lease Duration (in months): ");
                int leaseDuration = scanner.nextInt();

                property.isRented = true;
                property.tenantName = tenantName;
                property.contactNumber = contactNumber;
                property.leaseDuration = leaseDuration;

                System.out.println("\nProperty rented successfully!");
                listAvailableProperties(); 
            } else {
                System.out.println("\nSorry, the selected property is already rented.");
            }
        } else {
            System.out.println("\nInvalid property number. Please enter a valid number.");
        }
    }

    private static void viewRentedProperties() {
        System.out.println("\nRented Properties:");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("| %-6s | %-20s | %-15s | %-21s |\n", "Number", "Property Type", "Tenant Name", "Lease Duration (mons)");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 0; i < properties.length; i++) {
            Property property = properties[i];
            if (property.isRented) {
                System.out.printf("| %-6d | %-20s | %-15s | %-21d |\n", i + 1, property.type, property.tenantName, property.leaseDuration);
            }
        }

        System.out.println("----------------------------------------------------------------------");
    }

    private static void addNewProperty() {
        System.out.println("\n- Adding Property");
        System.out.println("Enter new property details:");

        System.out.print("Property Type: ");
        scanner.nextLine(); // Consume the newline character
        String propertyType = scanner.nextLine();

        System.out.print("Monthly Rent: $");
        double monthlyRent = scanner.nextDouble();

        for (int i = 0; i < properties.length; i++) {
            if (properties[i] == null) {
                properties[i] = new Property(propertyType, monthlyRent);
                break;
            }
        }
        System.out.println("\nNew property added successfully!");
        listAvailableProperties(); 
    }

    private static void removeTenant() {
        System.out.println("\n-Remove a Tenant");
        System.out.println("Enter property number to remove tenant: ");
        
       int propertyNumber = scanner.nextInt();
       if (propertyNumber  >= 1 && propertyNumber <= properties.length){
           Property property = properties[propertyNumber -1];
           
           
           if (property.isRented){
               System.out.println("\nRemoving tenant from the selected property...");
               property.isRented = false;
               property.tenantName = "";
               property.contactNumber  ="";
               property.leaseDuration = 0;
                
               System.out.println("Tenant Remove Successfully!");
               listAvailableProperties();
           }else{
               System.out.println("\nThe selected property is not currently rented.");
                }
       }else{
             System.out.println("\nInvalid property number..please enter a valid number");
                       
               
           }
    }  
}


       