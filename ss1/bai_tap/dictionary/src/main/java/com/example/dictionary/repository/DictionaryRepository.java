package com.example.dictionary.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DictionaryRepository {
    private static Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("apple", "táo");
        dictionary.put("banana", "chuối");
        dictionary.put("orange", "cam");
        dictionary.put("dog", "chó");
    }
    public String findMeaning(String word) {
        return dictionary.get(word.toLowerCase());
    }
}
