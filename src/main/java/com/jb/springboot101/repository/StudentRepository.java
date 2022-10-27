package com.jb.springboot101.repository;

import com.jb.springboot101.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // @Query("SELECT s FROM Studen s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);
    Optional<Student> findStudentBySid(Long sid);
    Boolean existsBySid(Long sid);
    Optional<Student> deleteBySid(Long sid);
}
