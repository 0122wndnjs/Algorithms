// README for Bucket.java

// Classes:
  // Bucket.java
    // This class implements the functioning of the buckets and contains the main function. Need to run this class.
    // run.sh compiles and runs this class with the input file as specified on the terminal

// For the Bucket Sort I used the data structure ArrayList of ArrayList of String implemented in Java.
// This helps me have dynamic size for the arrays while having O(1) functioning for functions like
// .get(), .size(), .set().
// For the whole implementation I first insert the elements into one of 26 buckets depending on their characters
// at index that I pass. I had to do this so that when using recursion for sorting the sub buckets I
// can insert depending on characters present at indices {1.....n}.
// After inserting the elements I sort using insertion sort if the no. of elements in the buckets < 10.
// Otherwise I create another temp object of type Buckets and insert all the elements in that bucket in
// the temp object. Then I sort it recursively and go through the whole temp object's buckets and add it
// in order(already ordered) to the existing bucket.

// For printing the list of names, I go through all the buckets and make a string containing all the names.
// As i traverse in order through the ArrayList my string is build up in a lexicographic order.
// At last I just print the returned string into the file "output.txt"
