package com.valunskii.vaco.service;

import com.valunskii.vaco.domain.Word;

import java.util.List;

public interface WordService {

    Word create(Word word) ;

    Long count();

    List<Word> getAll();

    Word getById(Long id);

    Word update(Word word, Long id);

    void delete(Word word);
}
