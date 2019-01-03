// README for Priority Queue Class

// Classes:
  // Node.java
    // Node type arrays are used for the heaps to contain data for all the customers in the PQueue class.

  // PQueue.java
    // This class implements the functioning of the Priority Queue using Heaps and contains the main function. Need to run this class.
    // run.sh compiles and runs this class with the input file as specified on the terminal

// My Code consist of two .java files - Node.java and PQueue.java
// Node.java contains the Node class which is the structure that saves name and Priority of the customer
// PQueue.java contains the PQueue class that actually implements the Priority Queue using the Heap method
// Most of the functioning/logic of my implementation comes from what was taught in the class and slides.
// I implemented the PQueue using Heap which uses array.
// For the functions the details are as follows :-
// Maximum - just return the 0th index element because it would be the maximum in a max Heap
// ExtractMax - Overwrite the 0th element in array with the last element then make the last element null
//              and run the max_heapify function.
// insert - insert the new node at the last part of the array and then run the build max heapify function
//          on the whole heap to make it a heap.

// The functions that are required take a PQueue type object and the Node type object accordingly to insert,
// extract_max, and find maximum.
