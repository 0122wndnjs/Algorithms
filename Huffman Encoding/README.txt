// README for Huffman Encoding Class

// Classes:
  // Node.java
    // Node type array is used in Huffman encoding class extensively. It can support tree structure as well because of the members - Node left and Node right.

  //Huffman.java
    // This class implements the functioning of the Huffman Encoding and contains the main function. Need to run this class.
    // run.sh compiles and runs this class with the input file as specified on the terminal

// For Huffman Encoding I have first taken an input string from the input.txt function.
// Then I convert it into a char[] and sort it using the JAVA built in sort
// Sorting helps me find the uniquecharacters and their frequency in O(n) by iterating through the loop
// After finding the uniquecharacters and their frequency I make a Node[] array and insert the nodes
// such that they contain the character, frequency, and Encoding. The Nodes also have member left and right
// of type Node that are used after wards to create the tree structure.

// After I have the Node[] array names "q" completed I sort it using insertion sort (used from lecture slides)
// on basis of the frequency of the characters in a Descending order. Then I get the last two nodes and make
// a tree out of it by making them left and right child of new nodes. Then I make this node the last element
// in the array and sort the array and repeat. I do this until I have only one Node in my array as it
// signifies that I have made a tree containing all the nodes of unique characters.

// After this I use a helper function to actually create Encoding for each of the Nodes using the rule
// if left node add 0 to the parent node's Encoding and set it to be the Encoding of left node.
// else right node add 1 to the parent node's Encoding and set it to be the Encoding of right node.
// After this I use a helper function to go through the whole tree and edit an array that will just
// contain these modified Nodes individually so that I can write it in a file. I had to do this because
// I was unable to find a better way to traverse through the tree using recursion and same time actually
// writing to an output.txt file.
// After this final array is constructed I just iterate through it and write to a output.txt file

// I was confused regarding one thing and that was what index should I put the Nodes containing uniquecharacters
// on, I came up with the soln of using 2*prev_index+1 for left node and 2*prev_index+2 for right node,
// however as this makes the length of the array big so while declaring the array I have it to be
// length = 20*no. of uniquecharacters
