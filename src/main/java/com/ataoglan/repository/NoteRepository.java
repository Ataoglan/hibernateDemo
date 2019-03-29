package com.ataoglan.repository;

import com.ataoglan.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /*That is all you have to do in the repository layer. You will now be able to use JpaRepository’s methods like save(), findOne(), findAll(), count(), delete() etc.

You don’t need to implement these methods. They are already implemented by Spring Data JPA’s SimpleJpaRepository. This implementation is plugged in by Spring automatically at runtime.*/

}
