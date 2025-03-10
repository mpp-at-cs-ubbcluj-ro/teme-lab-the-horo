package org.example;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Main {
    public static void main(String[] args) {
        Multiset<String> words = HashMultiset.create();
        words.add("Hello", 2);
        System.out.println(words);
    }
}