// README for Selection Class

// Classes:
  // Selection.java
    // This class implements the functioning of the selection algorithm and contains the main function. Need to run this class.
    // run.sh compiles and runs this class with the input file as specified on the terminal

// I used the nums array from the given code snippet to create a fresh copy of the array containing
// only the numbers and no null values. Then I use my partition function(using logic given in the slides)
// again and again to do a search.

// The search works as follows.
// I run my partition function and save the value that is returned by it (the index at which the pivot lands)
// I check whether it is equal to the given index, if it is just return the element at that index,
// if not then loop such that I check if the index that I need to find is less then the returned index
// if it is then I decrease the length on which partition function runs by changing the endindex = returned index
// else I decrease the length by changing the start index = returned index.
// This algorithm helps me keep on reducing the effective size of array on which I run my partition algorithm
// while checking whether I have successfully placed an element at the index I want.
