package com.example.dictionary.service;

import com.example.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Dictionary;

@Service
public class DictionaryService implements IDictionaryService  {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    public String findMeaning(String word) {
        if (word == null || word.trim().isEmpty()) {
            return "You must enter at least 1 word";
        }
        String meaning = dictionaryRepository.findMeaning(word.trim());

        if (meaning != null && !meaning.isEmpty()) {
            return meaning;
        } else {
            return "No meaning found for the word: " + word;
        }
    }
}
