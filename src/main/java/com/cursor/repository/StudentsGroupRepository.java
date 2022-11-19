package com.cursor.repository;

import com.cursor.entity.StudentsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentsGroupRepository extends JpaRepository<StudentsGroup, Long> {
}
