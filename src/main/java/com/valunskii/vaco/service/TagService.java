package com.valunskii.vaco.service;

import com.valunskii.vaco.domain.Tag;

import java.util.List;

public interface TagService {
    Long count();

    Tag create(Tag tag);

    List<Tag> getAll();

    Tag getById(Long id);

    Tag update(Tag tag, Long id);

    void delete(Tag tag);

    Tag getByName(String key);
}
