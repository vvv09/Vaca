package com.valunskii.vaco.repo;

import com.valunskii.vaco.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepo extends JpaRepository<Word, Long> {
}
