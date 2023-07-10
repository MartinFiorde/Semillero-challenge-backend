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

    public List<Course> findByTitle(String title);
    
    @Query(value = "SELECT * FROM course WHERE course.title LIKE %:title%", nativeQuery = true)
    public List<Course> findLikeTitle(@Param("title") String title);
    
}
