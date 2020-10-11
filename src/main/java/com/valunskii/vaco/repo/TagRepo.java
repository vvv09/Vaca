package com.valunskii.vaco.repo;

import com.valunskii.vaco.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepo extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

}
