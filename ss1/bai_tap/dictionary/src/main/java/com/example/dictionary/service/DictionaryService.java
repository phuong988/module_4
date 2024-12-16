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
        if(word == null || word.isEmpty()){
            return "You have to enter a word";
        }
        String meaning = dictionaryRepository.findMeaning(word);
        if(meaning != null){
            return meaning;
        } else {
            return "Not found with this word";
        }
    }
}
