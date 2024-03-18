//Programmer: Ryan Seefeldt
//Class: CS& 145 Lab 6
//Date:3/8/2024
//Purpose: This is implementing a dictionary application using a Binary Search 
//  Tree (BST) data structure. The dictionary is designed to store and manage 
//  entries for employees, where each entry contains various pieces of 
// information such as primary key, first name, last name, address, email, 
// and phone number.

// Employee Class: This class represents an individual employee and 
//  contains attributes such as userId, firstName, lastName, address, 
//  city, state, zipCode, email, and phone.

// BST Implementation: The Employee.IntTree class represents the 
//  BST implementation to store Employee objects. The BST operations 
//  include adding an employee, removing an employee by field, printing, 
//  searching employees by different criteria of pre-order, post-order, 
//  and in- order and modifying employee data.

// Main Method: The main method is the entry point of the program. 
//   It presents a menu to the user with options to add, remove, print, 
//  lookup, modify, or quit.

// Utility Methods: The code includes several utility methods such 
// as getString, getInt, printChoices, orderChoices, printFieldsList, 
// confirmAction, getFieldData, and returnType to facilitate user input, 
// menu display, and data manipulation.

// Test Command: There's a hidden test command fillbook in the menu that 
//  fills the BST with predefined employee data for testing purposes.

import java.util.*;
public class Dictionary {

/**
 * The main method of the Dictionary program.
 * It initializes an empty Employee object and creates a Binary Search Tree 
 * (BST) using it. It presents a menu to the user with options to add, 
 * remove, print, lookup, modify, or quit. It interacts with the user 
 * via input and performs corresponding actions based on user input.
 */
    public static void main(String[] args){
        Employee employeeNull = new Employee(0, "", "", "", "", "", "", "", "");
        Employee.IntTree tree = employeeNull.new IntTree();
        boolean contEntry = true;
          do {
             // print the number of choices for main menu.
             printChoices();
             // Get the user's command.
             String command = getString(" Choice: ");
             // convert the command to lower case for easier compare
             command = command.toLowerCase();
             int index=0;
             switch (command) {
                 // Add data to the BST
                 case "1":
                 case "add":
                 case "a": {
                     int userId = getInt("Enter Primary Key: ");
                     String firstName = getString("Enter First Name: ");
                     String lastName = getString("Enter Last Name: ");
                     String address = getString("Enter Street Address: ");
                     String city = getString("Enter City: ");
                     String state = getString("Enter State: ");
                     String zipCode = getString("Enter Zip Code: ");
                     String email = getString("Enter email address: ");
                     String phone = getString("Enter Phone Number: ");
                     Employee employee =  new Employee(userId, firstName, lastName, 
                                           address, city, state, 
                                           zipCode, email, phone);
                     tree.add(employee);
                     System.out.println("Element added to the BST");                      
                     break;
                 }
                 // Remove data from the BST by searching for the element and 
                 // removing the found element by eployee id 
                 case "2":
                 case "remove":
                 case "r": {
                     // get the field that you want data removed from
                     String fieldToSearch = printFieldsList("Search for Data to " + 
                                              "remove by Field", "remove by");
                     // convert the field number entered to the actual type                         
                     String typeOfData = returnType(fieldToSearch);
                     // get the data at that field that you would like to be removed                          
                     String fieldDataToSearch = getFieldData(fieldToSearch);                         
                     // find an element containing that data in the type sent
                     Employee foundEmployee = tree.searchInOrderByField(typeOfData, 
                                                                 fieldDataToSearch);
                     // if data found process else print error                                            
                     if (foundEmployee != null) {
                         System.out.println("Employee found:");
                         foundEmployee.printEmployee();
                         // check to make sure that you want to remove data.
                         boolean really = confirmAction();
                         if (really) {
                             tree.remove(foundEmployee.getId());
                             System.out.println("Element Removed");
                         } else {
                             System.out.println("Did not remove Element");
                         }        
                     } else {
                         System.out.println("Element Not Found");
                     }

                     break;
                 }
                 // Print the data in pre/post/in-order format based on user entry.
                 case "3": // print complete contact list
                 case "print":
                 case "p": {
                     if (tree !=null) {
                         command = orderChoices("Enter how you would like to Print",
                                                                       "Print");
                         switch(command.toLowerCase()) {
                             case "1":
                             case "pre":
                             case "pre-order": {
                                 tree.printPreOrder();
                                 break;
                             }    
                             case "2":
                             case "post":
                             case "post-order": {
                                 tree.printPostOrder();
                                 break;
                             }    
                             case "3":
                             case "in":
                             case "in-order": {
                                 tree.printInOrder();
                                 break;
                             }
                             default: {
                                 System.out.println("Invalid Command");    
                                 break;
                             } 
                         }       
                     } else {
                         System.out.println("Empty List");
                     }    
                     break;
                        
                     
                 }    
                 // Look up data in pre/post/in-order based on user input
                 // If data is found print element                 
                 case "4":
                 case "lookup":
                 case "l": {
                     Employee foundEmployee = null;
                     // get user input to determine how to search the BST
                     String lookupType = orderChoices("Lookup data by pre/post/in-order",
                                                "Lookup");
                     // Get the field that you would like to base your lookup on.                           
                     String lookupField = printFieldsList("Enter Lookup Data", 
                                                "Lookup by");
                     // convert to correct field type                           
                     String typeOfData = returnType(lookupField);
                                              
                     String fieldDataToSearch = getFieldData(lookupField);
                     switch (lookupType) {
                         case "1": {  // pre-order
                             foundEmployee = tree.searchPreOrderByField(typeOfData, 
                                            fieldDataToSearch);
                             break;
                         }                     
                         case "2": {  // post-order
                             foundEmployee = tree.searchPostOrderByField(typeOfData, 
                                            fieldDataToSearch);
                             break;
                         }                     
                         case "3": {  //in-order
                             foundEmployee = tree.searchInOrderByField(typeOfData, 
                                            fieldDataToSearch);
                             break;
                         }                     
                         default: {
                             break;
                         }
                     }
                     // if employee found then confirm and print employee else return 
                     // error        
                     if (foundEmployee != null) {
                         System.out.println("Employee found:");
                         foundEmployee.printEmployee();
                     } else {
                         System.out.println("Element Not Found");
                     }
                     break;
                 }
                 // Modify data by entering field to be modified
                 //  data to searched for in that field
                 //  and new data to replace the existing data.
                 // If data is found then remove current element with old
                 // data and add new element. This will allow for correct 
                 // interaction with BST when id is changed.                  
                 case "5":
                 case "modify":
                 case "m": {
                 
                     Employee foundEmployee = null;
                     String option = "Get field that you would like to modify: ";
                     String modifyField = printFieldsList(option, "Modify");
                     modifyField = returnType(modifyField);
                     String modifyData = getString("Enter Data to be modified: ");

                     foundEmployee = tree.searchInOrderByField(modifyField, 
                                            modifyData);
                     if (foundEmployee != null) {
                         String modifyNewData = getString("Enter New Data: ");
                         System.out.println("Element before Modify");
                         foundEmployee.printEmployee();
                         foundEmployee = tree.modify(modifyField, modifyData, 
                                                        modifyNewData);
                         System.out.println("Element has been Modified");
                         foundEmployee.printEmployee();                            
                     } else {
                         System.out.println("Element Not Found");
                     }                            
                     
                     break;
                 }
                  // Allows user to exit the program.
                  case "6": // quit program
                  case "quit":
                  case "q": {
                     System.out.println("\nThank you for visiting BST Dictionary");
                     contEntry = false; // set to false to exit while loop
                     break;
                  }
                  // This is a hidden test command to fill the elements in the BST with
                  // data if desired for easier testing.
                  case "fillbook": {
                      Employee employee1 =  new Employee(5, "Bill", "Seefeldt", 
                                           "1013 Village Dr.", "Oceanside", "CA", 
                                           "92057", "beseefeldt@gmail.com",
                                           "561-543-2229");
                      Employee employee2 =  new Employee(4, "john", "williams", 
                                           "1010 johnson st", "San Diego", "CA", 
                                           "92057", "jw@gmail.com", "760-777-0011");
                      Employee employee3 =  new Employee(6, "Ryan", "Seefeldt", 
                                           "1013 Village Dr.", "Oceanside", "CA", 
                                           "92057", "ryanmseefeldt@gmail.com",
                                           "561-777-0012");
                      Employee employee4 =  new Employee(8, "Sandy", "gonzalez", 
                                           "6441 Marbletree Lane", "LakeWorth", "FL", 
                                           "33467", "sseefeldt@aol", "561-441-1061");
                      Employee employee5 =  new Employee(1, "jason", "Seefeldt", 
                                           "34 monroe st.", "Lynn", "ma", 
                                           "01090", "jasonseefeldt@aol.com", 
                                           "561-777-6390");
                      Employee employee6 =  new Employee(7, "clarissa", "cano", 
                                           "12023 Holystone st.", "Winter Park", "FL", 
                                           "34787", "ccano2014@gmail.com", 
                                           "561-568-6208");
                      Employee employee7 =  new Employee(3, "Fred", "Flinstone", 
                                           "1122 boogie ave.", "prehistoric", "CC", 
                                           "55443", "fflinstone@gmail.com", "1");
                      Employee employee8 = new Employee(10, "John", "Doe", 
                                           "123 Main St", "Anytown", "CA", 
                                           "12345", "john@example.com", 
                                           "123-456-7890");
                      Employee employee9 = new Employee(2, "Jane", "Smith", 
                                           "456 Elm St", "Othertown", "NY", 
                                           "54321", "jane@example.com", 
                                           "987-654-3210");
                      Employee employee10 = new Employee(9, "Bob", "Johnson", 
                                           "789 Oak St", "Somewhere", "TX", "67890", 
                                           "bob@example.com", "456-789-0123");
                      tree.add(employee1);
                      tree.add(employee2);
                      tree.add(employee3);
                      tree.add(employee4);
                      tree.add(employee5);
                      tree.add(employee6);
                      tree.add(employee7);
                      tree.add(employee8);
                      tree.add(employee9);
                      tree.add(employee10);
                     
                      break;
                  }
                  // Will alert user that it is not a valid command
                  default: {
                     System.out.println("Invalid Entry\n");
                     break;
                  }
               }
            } while (contEntry);


   } // end of main
   
   
   // This method will allow the user to get a string of data and print the prompt
   // for that string
   public static String getString(String printText) {
      Scanner reader = new Scanner(System.in);
      String command;
      System.out.print(printText);
      command= reader.nextLine();
      return command;
   } // end of getcommand


   // This method will print the main menu choices for the dictionary menu
   public static void printChoices() {
      System.out.println("\nPlease select an option:");
      System.out.println("1 - (Add) to tree");
      System.out.println("2 - (Remove) from tree");
      System.out.println("3 - (Print) tree");
      System.out.println("4 - (Lookup) tree");
      System.out.println("5 - (Modify) element in tree");
      System.out.println("6 - (Quit)");
   } // end of print choices
    

   // This method will print the choices if the user would like to 
   //  choose the type of search or print they would like to do.
   public static String orderChoices(String option, String type) {
      System.out.println(option);
      System.out.println("1 - Pre-order" + type);
      System.out.println("2 - Post-order " + type);
      System.out.println("3 - In-order " + type);
      String command = getString("Enter Choice: ");
      return command;
      
   } // end of print search choices


   // This method will allow the user to choose which field they would like
   //   to manipulate or search or lookup.
   public static String printFieldsList(String option, String type) {
   
      System.out.println(option);
      System.out.println("1 - " + type + " Primary Key");
      System.out.println("2 - " + type + " First Name");
      System.out.println("3 - " + type + " Last Name");
      System.out.println("4 - " + type + " Street Address");
      System.out.println("5 - " + type + " City");
      System.out.println("6 - " + type + " State");
      System.out.println("7 - " + type + " Zip Code");
      System.out.println("8 - " + type + " email");
      System.out.println("9 - " + type + " Phone Number");
      String command = getString("Enter Choice: ");
      return command;
   } //end of print fields list

   // This method allows for confirmation before making changes
   public static boolean confirmAction() {
       String command = getString("Are you sure y/n: ");
       boolean confirmation = false;
       switch(command.toLowerCase()) {
           case "y":
           case "yes": {
               confirmation = true;
               break;
           }
           case "n":
           case "no": {
               confirmation = false;
               break;
           }    
           default: {
               confirmation = false;
               break;
           }
       }                    
   
       return confirmation;
   }


   // This method allows the user to get an integer from the user
   public static int getInt(String printText) {
      Scanner reader = new Scanner(System.in);
      int command;
      System.out.print(printText);
      command = reader.nextInt();
      reader.nextLine(); // Employ the scanner consuming the nextLine character
      return command;
   } // end of getInt


   // This method is by used to prompt the user correctly based on the
   // field the user would like to edit and it returns the correct data.
   public static String getFieldData(String field) {
      String commandPrompt = "";
      String commandFieldData;
      switch (field.toLowerCase()) {
         case "1":
         case "primary":
         case "id": {
             commandPrompt = "Enter Primary Key: ";
             break;
         }    
         case "2":
         case "first name": {
            commandPrompt = " Enter First Name: ";
            break;
         }   
         case "3":
         case "last name": {
            commandPrompt = " Enter Last Name: ";
            break;
         }
         case "4":
         case "address": {
            commandPrompt = " Enter Address: ";
            break;
         }
         case "5":
         case "c":
         case "city": {
            commandPrompt = " Enter City: ";
            break;
         }   
         case "6":
         case "s":
         case "state": {
            commandPrompt = " Enter State: ";
            break;
         }
         case "7":
         case "z":
         case "zip code": {
            commandPrompt = " Enter Zip Code: ";
            break;
         }
         case "8":
         case "e":
         case "email": {
            commandPrompt = " email: ";
            break;
         }
         case "9":
         case "p":
         case "phone number": {
            commandPrompt = " Enter Phone Number: ";
            break;
         }
         default: {
            break;
         }
      }
      commandFieldData = getString(commandPrompt);
      return commandFieldData;
   } // end of get field data
   
   
   // This method converts the number entry to the proper field 
   public static String returnType(String fieldName) {
       String type;
       switch (fieldName.toLowerCase()) {
           case "1":
               return "id";
           case "2":
               return "firstname";
           case "3":
               return "lastname";
           case "4":
               return "streetaddress";
           case "5":
               return "city";
           case "6":
               return "state";
           case "7":
               return "zipcode";
           case "8":
               return "email";
           case "9":
               return "phonenumber";
           default:
               // Handle invalid field name
               return "";
        }
    } // end of returnType


} //end dictionary