readme.txt for JohnsonsAlgorithm.java

JohnsonsAlgorithm.java is the file for finding all pairs shortest path. To run this code,
first on the command line, type, javac JohnsonsAlgorithm.java to compile the code. If
compiled successfully, type java JohnsonsAlgorithm to run the program. If the program is
running, it will show the message to enter the number of vertexes. I tested with 3 vertexes
as given as instruction and typed the sample adjacency matrix. And it will print out the
output for it. And than, I tested with the case that contains negative number. Than, it will
print out the message, which is "Negative cycle".

There were some flaws using writing the output.txt file when the program reads the input file.
I couldn't fix it and instead, I made it manually type the number of vertexes and than
adjacency matrix, which is exactly same as the sample. Also, to make it clear, I tried with
random 4 vertexes adjacency matrix and it worked as well. So I can say that there are no
errors on algorithm itself, but just simply creating the output.txt file had some problem and
I couldn't finish that part. I commented out the Printing out the output.txt file which I failed,
so it could show my effort on that. Also, since I was not reading an input.txt file and just
manually typing, I chanced FileNotFoundException to InputMismatchException so it can catch the
wrong format of the input vertexes or matrix.  
