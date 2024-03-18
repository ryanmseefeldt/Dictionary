//Programmer: Ryan Seefeldt
//Class: CS& 145 Lab 6
//Date:3/8/2024
// Purpose: The Employee class represents an employee with various attributes 
//   such as ID, first name, last name, address, email, and phone number. 
//   It also includes an inner class IntTree that represents a binary search 
// tree (BST) of Employee objects.

// Here's a breakdown of the class and its components:
// Attributes:

//  id: An integer representing the unique identifier for the employee.
//  firstName: A string representing the first name of the employee.
//  lastName: A string representing the last name of the employee.
//  streetAddress: A string representing the street address of the employee.
//  city: A string representing the city of the employee.
//  state: A string representing the state of the employee.
//  zipCode: A string representing the ZIP code of the employee.
//  email: A string representing the email address of the employee.
//  phoneNumber: A string representing the phone number of the employee.


// Inner Class IntTreeNode:
//  Represents a node in the binary search tree (IntTree) of Employee objects.
//  Contains references to the Employee object and its left and right children.

// Inner Class IntTree:
// Includes methods for adding, removing, and searching for employees in the tree.
// Provides methods for printing the tree in pre-order, post-order, and in-order traversal.
// Allows modifying employee data in the tree.




import java.util.*;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String email;
    private String phoneNumber;

    // Constructor
    public Employee(int id, String firstName, String lastName, String streetAddress,
                    String city, String state, String zipCode, String email, 
                    String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public class IntTreeNode {
        public Employee employee;
        public IntTreeNode left;
        public IntTreeNode right;

        public IntTreeNode(Employee employee) {
            this(employee, null,null);
        }

        public IntTreeNode(Employee employee, IntTreeNode left,
                                     IntTreeNode right) {
            this.employee = employee;
            this.left = left;
            this.right = right;
                            
        }
    }
    

    // an IntTree object that represents an entire binary tree of strings.
    public class IntTree {
        private IntTreeNode overallRoot; //null for empty tree
    
        public IntTree() {
            overallRoot = null;
        }    
        
    // This is the public method that is called to add a new employee to 
    // the BST. It assigns the root of the tree to the result of the private 
    // add method.
    public void add(Employee employee) {
        overallRoot = add(overallRoot, employee);
    } // end of add public
    
    
    // Overall, this add method ensures that the new employee is inserted 
    // into the BST in the correct position according to their ID, maintaining 
    // the binary search tree property. If a duplicate primary key is encountered, 
    // it notifies the user to re-enter a unique ID.
    // If the root is not null, it compares the ID of the employee with the ID of t
    // he root.employee to determine whether to insert the employee into the left 
    // or right subtree.
    private IntTreeNode add(IntTreeNode root, Employee employee) {
        if (root == null) {
            return new IntTreeNode(employee);
        } else {
            // If the employee ID is less than the root.employee ID, it recursively 
            // calls add on the left subtree (root.left).
            if (employee.id < root.employee.id) {
                root.left = add(root.left, employee);
//                System.out.println("added to the left");
            // If the employee ID is greater than the root.employee ID, it 
            // recursively calls add on the right subtree (root.right).
            } else if (employee.id > root.employee.id) {
                root.right = add(root.right, employee);
//                System.out.println("added to the right");
            // If the employee ID is equal to the root.employee ID, it prints a 
            // message indicating that it's a duplicate primary key and must be re-entered.
            } else {
                System.out.println("Duplicate Primary Key. Must Re-enter.");
            }    
            return root;
        }
    }  // end of add private                 

    // Overall, the remove method is responsible for removing an employee from 
    // the binary search tree while maintaining the binary search tree properties.
    // It recursively traverses the tree to find the node with the matching 
    // employee ID and then handles three cases: no child, one child, and two 
    // children. It uses the concept of the inorder successor to handle the 
    // case where the node to be removed has two children. Finally, 
    // it returns the modified root node of the subtree.
    public void remove(int employeeId) {
            
        // Call the private remove method starting from the overall root
        overallRoot = remove(overallRoot, employeeId);
    } // end of remove public

    private IntTreeNode remove(IntTreeNode root, int employeeId) {
        if (root == null) {
            return null;
        }

        // If the employee ID to be removed is less than the current 
        // node's employee ID
        if (employeeId < root.employee.id) {
            root.left = remove(root.left, employeeId);

        // If the employee ID to be removed is greater than the current node's 
        // employee ID   
        } else if (employeeId > root.employee.id) {

            //// Recursively search and remove in the right subtree
            root.right = remove(root.right, employeeId);
        //// Node to be deleted found    
        } else {

            // If the current node has no left child
            if (root.left == null) {
                // Replace the current node with its right child 
                // (or null if no right child exists)
                return root.right;
            // If the current node has no right child    
            } else if (root.right == null) {
                // Replace the current node with its left child
                return root.left;
            } else {
                // Node has two children
                // Find the inorder successor (smallest node in the right subtree)
                IntTreeNode successor = findSuccessor(root.right);
                // Replace the current node's employee data with the successor's employee 
                // data
                root.employee = successor.employee;
                // Remove the successor node from the right subtree
                root.right = remove(root.right, successor.employee.id);
            }
        }
        return root;
    }  // end of remove private

    // This method is to find the inorder successor in the BST
    private IntTreeNode findSuccessor(IntTreeNode node) {
        // Start from the given node and traverse left until the leftmost node is reached
        IntTreeNode successor = node;
        while (successor.left != null) {
            successor = successor.left;
        }
        return successor;
    } // end of findSuccessor

    // This method performs a preorder traversal of the binary search tree (BST). 
    //   Preorder traversal visits the root node first, then recursively visits 
    //   the left subtree, and finally recursively visits the right subtree. 
    //   Within each recursive call, the employee data of the current node 
    //   is printed using the printEmployee method. The traversal continues 
    //   until all nodes in the BST have been visited.  
    public void printPreOrder() {
       printPreOrder(overallRoot);
    }  // end of printPreOrder public

    private void printPreOrder(IntTreeNode root) {
        if (root != null) {

            printEmployee(root.employee);            
            printPreOrder(root.left); // Recursively print the left subtree
            printPreOrder(root.right); // Recursively print the right subtree
        }
    }  // end of printPreOrder private


    // This method performs a postorder traversal of the binary search tree (BST). 
    // Postorder traversal recursively visits the left subtree, then the right 
    // subtree, and finally the root node. Within each recursive call, the 
    // employee data of the current node is printed using the printEmployee method.
    // The traversal continues until all nodes in the BST have been visited.
    public void printPostOrder() {
        printPostOrder(overallRoot);
    }  // end of printPostOrder public

    private void printPostOrder(IntTreeNode root) {
        if (root != null) {
            printPostOrder(root.left); // Recursively print the left subtree
            printPostOrder(root.right); // Recursively print the right subtree
            printEmployee(root.employee);   
        }
    } // end of printPostOrder private
    
    
    // This method performs an inorder traversal of the binary search tree (BST).
    // Inorder traversal recursively visits the left subtree, then the current 
    // node, and finally the right subtree. Within each recursive call, the 
    // employee data of the current node is printed using the printEmployee method.
    // The traversal continues until all nodes in the BST have been visited.
    public void printInOrder() {
        printInOrder(overallRoot);
    } // end of printInOrder public

    private void printInOrder(IntTreeNode root) {
        if (root != null) {
            printInOrder(root.left); // Recursively print the left subtree

            printEmployee(root.employee);   

            printInOrder(root.right); // Recursively print the right subtree
        }
    } // end of printInOrder private
    
    // This method allows for comparison of different fields of the Employee object.
    // It handles case-insensitive comparisons for string fields and direct 
    // comparisons for the ID field. If the provided field name is not recognized, 
    // it returns false to indicate an invalid field name.
    private boolean compareField(String fieldName, String targetValue, Employee employee) {
        switch (fieldName.toLowerCase()) {
            case "id":
                return String.valueOf(employee.id).equals(targetValue);
            case "firstname":
                return employee.firstName.equalsIgnoreCase(targetValue);
            case "lastname":
                return employee.lastName.equalsIgnoreCase(targetValue);
            case "streetaddress":
                return employee.streetAddress.equalsIgnoreCase(targetValue);
            case "city":
                return employee.city.equalsIgnoreCase(targetValue);
            case "state":
                return employee.state.equalsIgnoreCase(targetValue);
            case "zipcode":
                return employee.zipCode.equalsIgnoreCase(targetValue);
            case "email":
                return employee.email.equalsIgnoreCase(targetValue);
            case "phonenumber":
                return employee.phoneNumber.equalsIgnoreCase(targetValue);
            default:
                // Handle invalid field name
                return false;
        }
    } // end of compareField

    // This method searches for an employee in a binary search tree 
    // (BST) based on a specified field name and target value, 
    // using a pre-order traversal approach. 
    public Employee searchPreOrderByField(String fieldName, String targetValue) {
        return searchPreOrderByField(overallRoot, fieldName, targetValue);
    } // end of searchPreOrderByField public

    private Employee searchPreOrderByField(IntTreeNode root, String fieldName, 
                                                     String targetValue) {
        if (root == null) {
            return null; // Employee not found
        }
        
        // Compare the field value of the current node with the target value 
        if (compareField(fieldName, targetValue, root.employee)) {
            return root.employee;
        }
        
        // Recursively search in the left subtree
        Employee leftResult = searchPreOrderByField(root.left, fieldName, targetValue);
        // If employee found in the left subtree, return it
        if (leftResult != null) {
            return leftResult; // Employee found in the left subtree
        }
        // Recursively search in the right subtree
        Employee rightResult = searchPreOrderByField(root.right, fieldName, targetValue);
        // If employee found in the right subtree, return it
        if (rightResult != null) {
            return rightResult; // Employee found in the right subtree
        }

        return null; // Employee not found in this subtree
    }  // end of searchPreOrderByField private  



    // The purpose of this method is to perform a post-order traversal of the binary
    // search tree, searching for an employee based on a specified field name and 
    // target value. It returns the first occurrence of the employee with the 
    // matching field value, if found, or null if the employee is not found.
    public Employee searchPostOrderByField(String fieldName, String targetValue) {
        // Start the search from the overall root of the binary search tree
        return searchPostOrderByField(overallRoot, fieldName, targetValue);
    } // end of searchPostOrderByField public

    private Employee searchPostOrderByField(IntTreeNode root, String fieldName, 
                                                             String targetValue) {
        // // Check if the current node is null (base case for recursion)                                                     
        if (root == null) {
            return null; // Employee not found
        }
        
        // Recursively search in the left subtree
        Employee leftResult = searchPostOrderByField(root.left, fieldName, targetValue);
        // If employee found in the left subtree, return it
        if (leftResult != null) {
            return leftResult; // Employee found in the left subtree
        }
        
        // Recursively search in the right subtree   
        Employee rightResult = searchPostOrderByField(root.right, fieldName, targetValue);
        
        if (rightResult != null) {
            return rightResult; // Employee found in the right subtree
        }

        // Compare the field value of the current node with the target value
        if (compareField(fieldName, targetValue, root.employee)) {
            return root.employee; // Employee found at the current node
        }

        return null; // Employee not found in this subtree
    } //end of searchPostOrderByField private


    // The purpose of this method is to perform an in-order traversal of the binary 
    // search tree, searching for an employee based on a specified field name and 
    // target value. It returns the first occurrence of the employee with the 
    // matching field value, if found, or null if the employee is not found.
    public Employee searchInOrderByField(String fieldName, String targetValue) {
        return searchInOrderByField(overallRoot, fieldName, targetValue);
    } // end of searchInOrderByField public

    private Employee searchInOrderByField(IntTreeNode root, String fieldName, 
                                                                   String targetValue) {
        if (root == null) {
            return null; // Employee not found
        }
        
        // Recursively search in the left subtree
        Employee leftResult = searchInOrderByField(root.left, fieldName, targetValue);
        if (leftResult != null) {
            return leftResult; // Employee found in the left subtree
        }

        // Compare the field value of the current node with the target value
        if (compareField(fieldName, targetValue, root.employee)) {
            return root.employee; // Employee found at the current node
        }

        // Recursively search in the right subtree  
        Employee rightResult = searchInOrderByField(root.right, fieldName, targetValue);
        if (rightResult != null) {
            return rightResult; // Employee found in the right subtree
        }

        return null; // Employee not found in this subtree
    } // end of searchInOrderByField private
    
    // The purpose of this method is to modify the specified field of an Employee object
    // with the provided new value. It handles different field names by switching on the 
    // fieldName parameter and updating the corresponding field of the Employee object 
    // accordingly. If the fieldName is not recognized, it returns null to indicate 
    // that the modification was unsuccessful.
    private Employee modifyField(String fieldName, String newValue, Employee employee) {
        switch (fieldName.toLowerCase()) {
            case "id":
                employee.id = Integer.parseInt(newValue);
                return employee;
            case "firstname":
                employee.firstName = newValue;
                return employee;
            case "lastname":
                employee.lastName = newValue;
                return employee;
            case "streetaddress":
                employee.streetAddress = newValue;
                return employee;
            case "city":
                employee.city = newValue;
                return employee;
            case "state":
                employee.state = newValue;
                return employee;
            case "zipcode":
                employee.zipCode = newValue;
                return employee;
            case "email":
                employee.email = newValue;
                return employee;
            case "phonenumber":
                employee.phoneNumber = newValue;
                return employee;
            default:
                // Handle invalid field name
                return null;
        }
    } // end of modifyField
    
    // The purpose of this method is to modify an Employee object's field based 
    // on the specified field to change, the current value of that field, and 
    // the new data to replace it with. It first searches for the employee with 
    // the specified field and current value. If the field to change is the ID, 
    // it removes the employee with the current ID, updates the ID with the new 
    // data, and then adds the modified employee back into the tree. If the field 
    // to change is not the ID, it modifies the specified field with the new data, 
    // removes the employee with the current ID, and then adds the modified 
    // employee back into the tree. Finally, it returns the modified employee.
    public Employee modify(String fieldToChange, String changeValue, String newData) {
        Employee employee = modify(overallRoot, fieldToChange, changeValue, newData);
        return employee;
    }  // end of modify public
    
    private Employee modify(IntTreeNode root, String fieldToChange, String changeValue, 
                                                                      String newData) {
        Employee employee = searchInOrderByField(root, fieldToChange, changeValue);
        if (employee == null) {
            return null;
        }    
        if (fieldToChange.equals("id")) {
             remove(employee.id);
             employee.id = Integer.parseInt(newData);
        } else {     
            employee = modifyField(fieldToChange, newData, employee);
            remove(employee.id);
        }
        add(employee);           
        return employee;
    } // end of modify private        

    } // end of intTree

    // Getter method for the id field
    public int getId() {
        return id;
    } // end of getId

    
    // This version of the method takes an Employee object as a parameter
    // and prints its details to the console. It formats the output to 
    // display the employee's ID, first name, last name, street address, 
    // city, state, zip code, email, and phone number.
    private void printEmployee(Employee employee) {
    
        System.out.println(employee.id + ":"); 
        System.out.println("   " + employee.firstName); 
        System.out.println("   " + employee.lastName); 
        System.out.println("   " + employee.streetAddress); 
        System.out.println("   " + employee.city); 
        System.out.println("   " + employee.state); 
        System.out.println("   " + employee.zipCode); 
        System.out.println("   " + employee.email); 
        System.out.println("   " + employee.phoneNumber); 
    }  // end of printEmployee private
    
    // This overloaded version of the method does not take any parameters. 
    // Instead, it prints the details of the current Employee object 
    // (using its instance variables) to the console. It provides a 
    // convenient way to print the details of an employee without 
    // needing to pass an Employee object explicitly.            
    public void printEmployee() {
    
        System.out.println(id + ":"); 
        System.out.println("   " + firstName); 
        System.out.println("   " + lastName); 
        System.out.println("   " + streetAddress); 
        System.out.println("   " + city); 
        System.out.println("   " + state); 
        System.out.println("   " + zipCode); 
        System.out.println("   " + email); 
        System.out.println("   " + phoneNumber); 
    } // end of printEmployee public           
            
}  // end of employee