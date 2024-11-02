/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 9/20/2024

Author: Dylan Theis
Date: Fall 2024
Class: CSC420
Project: Radix Sorting
Description: Sorting characters at indexes into buckets to have alphabetical order at the index
Ex. Abc Bca Cab
Index 2
Bca Cab Abc
Index 1
Cab Abc Bca
Index 0
Abc Bca Cab

*/


import java.io.*;
import java.util.*;

public class theisRadix {
    public static void main(String[] args) {
        // Display name, email, and certification
        System.out.println("Submitted by: Dylan Theis - theisd@csp.edu");
        System.out.println("I certify that this is my own work.");

        // Load words from file
        List<String> words = loadFile("words.txt");

        // Creating 27 buckets for a-z and a space
        // I chose linked list because it keeps the order of insertion, and as efficient insertions and removals
        List<LinkedList<String>> buckets = new ArrayList<>(27);
        // For each bucket add a new linked list
        for (int i = 0; i < 27; i++) {
            buckets.add(new LinkedList<>());
        }

        // Once buckets are created process the words sorting them into buckets
        radixSorting(words, buckets);

        // Display complete
        System.out.println("\nDone...");
    }

    // Loading words from file
    private static List<String> loadFile(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        // Load each string into a list of words
        //List<String> words = new ArrayList<>();
            String line;
            // While the line contains words
            while ((line = reader.readLine()) != null) {
                // Convert line to lowercase
                words.add(line.toLowerCase());
            }
        } catch(IOException e){
            System.out.println("File Error: " + e.getMessage());
        }
        return words;

    }

    // Radix sort method taking words and buckets
    private static void radixSorting(List<String> words, List<LinkedList<String>> buckets) {
        // For character going backwards (right to left)
        for (int index = 19; index >= 0; index--) {
            // For each character index, place words into the appropriate bucket
            for (String word : words) {
                // Get the character at listed index, if none, make it space char
                char ch = (index < word.length()) ? word.charAt(index) : ' ';
                // Space goes to bucket 0, a = 1, b = 2, etc.
                int bucketIndex = (ch == ' ') ? 0 : (ch - 'a' + 1);
                // Add the word to each bucket based on the character
                buckets.get(bucketIndex).add(word);
            }

            // Clear the words list
            words.clear();
            // For each bucket in buckets
            for (LinkedList<String> bucket : buckets) {
                // Add words from bucket
                words.addAll(bucket);
                // Clear bucket for the next iteration
                bucket.clear();
            }

            // Display the result of iterating each index
            System.out.println("\nIterating on index: " + index);
            // Print each word
            for (String word : words) {
                System.out.println("\t" + word);
            }
        }
    }
}

