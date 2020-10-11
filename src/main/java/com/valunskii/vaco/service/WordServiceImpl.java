package com.valunskii.vaco.service;

import com.valunskii.vaco.domain.Word;
import com.valunskii.vaco.repo.WordRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    private WordRepo wordRepo;

    @Autowired
    public WordServiceImpl(WordRepo wordRepo) {
        this.wordRepo = wordRepo;
    }


    @Override
    public Word create(Word word) {
        return wordRepo.save(word);
    }

    @Override
    public Long count() {
        return wordRepo.count();
    }

    @Override
    public List<Word> getAll() {
        return wordRepo.findAll();
    }

    @Override
    public Word getById(Long id) {
        return wordRepo.findById(id).orElseThrow(() -> new RuntimeException("This word does not exist in database"));
    }

    @Override
    public Word update(Word word, Long id) {
        Word wordFromDb = getById(id);
        BeanUtils.copyProperties(word, wordFromDb, "id");
        return wordRepo.save(wordFromDb);
    }

    @Override
    public void delete(Word word) {
        wordRepo.delete(word);
    }
}
