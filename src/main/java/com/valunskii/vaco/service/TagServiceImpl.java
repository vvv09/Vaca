package com.valunskii.vaco.service;

import com.valunskii.vaco.domain.Tag;
import com.valunskii.vaco.repo.TagRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagRepo tagRepo;

    @Autowired
    public TagServiceImpl(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }


    @Override
    public Long count() {
        return null;
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepo.save(tag);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepo.findAll();
    }

    @Override
    public Tag getById(Long id) {
        return tagRepo.findById(id).orElseThrow(() -> new RuntimeException("This tag does not exist in database"));
    }

    @Override
    public Tag update(Tag tag, Long id) {
        Tag tagFromDb = getById(id);
        BeanUtils.copyProperties(tag, tagFromDb, "id");
        return tagRepo.save(tagFromDb);
    }

    @Override
    public void delete(Tag tag) {
        tagRepo.delete(tag);
    }

    @Override
    public Tag getByName(String name) {
        return tagRepo.findByName(name).orElseThrow(() -> new RuntimeException("This tag does not exist in database"));
    }
}
