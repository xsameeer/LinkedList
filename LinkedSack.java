package cmsc256;
/*************
 *Class name: LinkedSack
 *Class Description: This class implements a generic linked sack data structure.
 *                   It implements the SackInterface and the Iterable interface to allow
 *                   iteration over the elements in the sack.
 --------------------------------------------------------------------------------------------
 *Name: Sameer Ali
 *Version date: 02/25/2024
 *CMSC 256 901
 ***************/

import java.util.Arrays;
import java.util.Iterator;

public class LinkedSack<T> implements SackInterface<T>, Iterable<T> {
    private Node start;
    private int size;

    private class Node {
        private T data;     // Entry in sack
        private Node next;  // link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

    // Method to get the current size of the sack
    @Override
    public int getCurrentSize() {
        return size;
    }

    // Method to check if the sack is empty
    @Override
    public boolean isEmpty() {
        return start == null && size == 0; // Sack is empty if start is null and size is 0
    }

    // Method to add a new entry to the sack
    @Override
    public boolean add(T newEntry) {
        if (newEntry == null) return false; // If newEntry is null, return false

        Node cNode = new Node(newEntry, start); // Create a new node with newEntry
        start = cNode;
        size++;
        return true;
    }

    // Method to remove an entry from the sack
    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T placeHolder = start.data;
        start = start.next; // Move start to the next node
        size--;
        return placeHolder; // Return the removed value
    }

    // Method to remove a specific entry from the sack
    @Override
    public boolean remove(T anEntry) {
        Node nodeBefore = null;
        for (Node cNode = start; cNode != null; cNode = cNode.next) {
            if (cNode.data.equals(anEntry)) { // If the data of the current node matches anEntry
                if (nodeBefore == null) { // If current node is the start node
                    start = cNode.next; // Update start to skip the current node
                } else {
                    nodeBefore.next = cNode.next; // Update the next reference of the previous node
                }
                size--;
                return true;
            }
            nodeBefore = cNode; // Update nodeBefore for the next iteration
        }
        return false;
    }

    // Method to clear the sack (remove all entries)
    @Override
    public void clear() {
        size = 0;
        start = null;
    }

    // Method to get the frequency of a specific entry in the sack
    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        for (Node cNode = start; cNode != null; cNode = cNode.next) {
            if (cNode.data.equals(anEntry)) { // If the data of the current node matches anEntry
                frequency++;
            }
        }
        return frequency;
    }

    // Method to check if the sack contains a specific entry
    @Override
    public boolean contains(T anEntry) {
        Node cNode = start;
        if (cNode == null) return false; // If sack is empty, return false
        do {
            if (cNode.data.equals(anEntry)) { // If the data of the current node matches anEntry
                return true;
            }
            cNode = cNode.next; // Move to the next node
        } while (cNode != null); // Repeat until end of sack
        return false;
    }

    // Method to convert the sack to an array
    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size]; // Create an array to store the elements
        Node cNode = start;
        int index = 0;
        for (; cNode != null; cNode = cNode.next) {
            arr[index++] = cNode.data; // Store the data of each node in the array
        }
        return arr; // Return the array containing sack elements
    }

    // Method to check if this sack is equal to another sack
    public boolean equals(LinkedSack<T> otherSack) {
        if (size != otherSack.size) { // If sizes are different, sacks are not equal
            return false;
        }
        T[] thisArray = toArray(); // Convert this sack to an array
        T[] otherArray = otherSack.toArray(); // Convert other sack to an array
        Arrays.sort(thisArray); // Sort elements of this sack array
        Arrays.sort(otherArray); // Sort elements of other sack array
        return Arrays.equals(thisArray, otherArray); // Compare sorted arrays for equality
    }

    // Method to duplicate all elements in the sack
    public boolean duplicateAll() {
        if (isEmpty()) return false; // If sack is empty, return false

        // a copy of the original linked list
        LinkedSack<T> copySack = new LinkedSack<>();
        for (Node cNode = start; cNode != null; cNode = cNode.next) {
            copySack.add(cNode.data); // Add each element of the original sack to the copy
        }

        // Add all elements from the copy back to the original sack
        for (Node cNode = copySack.start; cNode != null; cNode = cNode.next) {
            add(cNode.data); // Add each element from the copy to the original sack
        }

        return true;
    }

    // Method to remove duplicates from the sack
    public void removeDuplicates() {
        Node cNode = start;
        while (cNode != null) {
            Node runner = cNode; // Create a runner node starting from cNode
            while (runner.next != null) { // Loop until the end of the sack
                if (cNode.data.equals(runner.next.data)) { // If duplicate is found
                    runner.next = runner.next.next; // Remove the duplicate node
                    size--;
                     // Exit inner loop
                } else {
                    runner = runner.next; // Move runner to the next node
                }
            }
            cNode = cNode.next; // Move cNode to the next node
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node cNode = start; // Reference to the current node

            @Override
            public boolean hasNext() {return cNode != null;} // Returns true if current node is not null

            @Override
            public T next() {
                if (!hasNext()) return null; // If no more elements, return null

                T data = cNode.data; // Get the data of the current node
                cNode = cNode.next; // Move to the next node
                return data; // Return the value of the current node
            }
        };
    }
}
