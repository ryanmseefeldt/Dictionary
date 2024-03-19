Employee/Customer/Member Lookup Dictionary using Binary Search Tree

Description: This program implements a dictionary using a binary search tree (BST) to perform employee,
customer, or member lookup operations. It provides functionalities for adding, deleting, 
modifying, and looking up records. The database starts empty and each node in the BST contains 
the following fields: first name, last name, street address, city, state, zip code, email, phone 
number, and a primary key for identification.

Features: Upon execution, the program displays a menu with options for add, delete, 
modify, lookup, and list number of records. For adding records, the user is prompted 
for the appropriate primary key along with the user data, and a confirmation is 
provided upon successful addition. Deleting records prompts the user for the field 
to be deleted and confirms the deletion operation. Lookup operations allow the user 
to choose between pre-order, in-order, or post-order traversal, and the program 
prints out the corresponding values. Modification prompts the user for the field(s) 
to be modified, along with confirmation before proceeding.

Implementation Details: The program is implemented in Java. It utilizes a binary 
search tree data structure to efficiently store and manage records. Users have 
the option to use either recursion or iteration to implement the BST. Sorting 
or balancing of the tree is not implemented in this version.

Usage: Compile the Dictionary.java file. Execute the compiled program. Follow the 
on-screen menu prompts to perform desired operations.

Notes: This program is intended to be part of a GitHub portfolio for showcasing to potential employers. 
Feel free to extend or customize the program as needed for your specific requirements or use cases.
