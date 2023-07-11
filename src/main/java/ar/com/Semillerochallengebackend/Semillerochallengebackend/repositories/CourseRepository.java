package ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(value = "SELECT * FROM course WHERE course.active = 1", nativeQuery = true)
    public List<Course> getAllActive();

    public List<Course> findByTitle(String search);
    
    public List<Course> findByTeacherId(String search);
    
    @Query(value = "SELECT * FROM course WHERE course.title LIKE %:search%", nativeQuery = true)
    public List<Course> findLikeTitle(@Param("search") String search);

}
