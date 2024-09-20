# Bogus-Sort
Bogus sort is a sorting algorithm that takes inspiration from Bogo Sort where it is relying on random number generator to sort the array. It is meant to disregard efficiency and be more of a funny concept. Here are the general steps:

1) It creates a new array the same size as the unsorted array

2) Generate a new random index from 0 to n -1, where n is the length of the array

3) Store the index to not repeat already stored ones

4) Take the element at that index in the unsorted array and store it in the new array

5) If the array is sorted so far, move on to generate the next index. Otherwise reset and all indices are up for grabs again.

6) Repeat from step 2 until we reach the end of the array

# Implementation
The original implementation of the code was in Java and can be found in the Bogus_Sort.Java file. There were some good design choices and benefits in Java such as a one line isSorted method. This removes the need to iterate through the whole list and checks adjacent elements if the array is past the first index. To handle out of bounds errors, the logic has to be a little backwards to terminate the line early if the current index is at zero.

The genRandIndex method checks if the current index was reset, and if so, it clears all used indices. Then is makes use of a do while to keep generating random indices until a new one is found. Once it is found, it stores it and returns it.

contOrReset calls on isSorted. If it is, continue by incrementing the current index, else restart at the first index.

The Sort itself will terminate once the current index is at the end. We know the array is sorted at this point because we check if it is sorted after each number generated. We store a random element from the original in the new array, and then we call contOrReset. Once the while loop terminates, it returns the sorted array.

# Testing and Performance

Some things I decided to test for in the Bogus_Sort.java file was if it worked on empty lists, if it worked on lists that were already sorted, if the sort worked at all, if it worked on negative numbers, and if it worked on a larger list.
The sort has no problem with any of these test cases. However, the last test does take longer to produce an output. Due to the nature of the sort, every new element adds exponentially more permutations. In this example, you have a 1/13 chance to pick the correct element times a 1/12*1/11*1/10*…*1/1 chance to get the correct sequence. That means that there are over 6 billion possible combinations, or 13! (size of the list factorial). For reference, a list with 6 values has 720 and 5 values has 120.

There is a chance that the list never gets sorted on any size array (O(n*n!) would be average and worst case but theoretically it could be O(infinity) since any sequence can repeat at any time) as only the exact sequence results in a sorted array. There is also a chance that it sorts it in O(n) runtime complexity if it gets it correct on the first attempt. As the size of the list increases, it become less likely that the sort will complete as quickly. Space complexity is also O(n) since we need to generate another array and list the size of the original.

Everytime you reset, everything up until that point has no contribution to the end result as opposed to other sorts that make progress towards the sorted array on every pass through.

# Vizualizer
To make a visualizer, I implemented the sort in Python instead of writing a script for my Java code. I just found it easier to do it that way and Python has great tools to do so. The visual can be found in the Sorting_Visual file.

The visualizer is animated and actually updates as the sort operates. In order for humans to interpret it, it updates significantly slower than the computer would actually sort. This makes this implementation of the visual impractical for large lists, but great to see how the sort is operating.

In the visual, we can see that the first two shift the most often. That is because they have to be sorted until we can move on. Also, everytime we reset, those are the elements that are being adjusted. We always assume element 0 is sorted, which is why we see element 1 update even if we see that element 0 is not going to be the smallest value. As we approach the end, we are more likely to generate the correct sequence.

The visual may also show two elements that are identical, even if the original did not have any repeating values. That just means that they have not been overridden, but by the end, there will be no repeated values unless the original array has some.


