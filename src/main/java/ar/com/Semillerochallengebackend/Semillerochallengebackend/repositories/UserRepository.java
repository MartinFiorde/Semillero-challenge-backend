package ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM user "
            + "WHERE user.active = 1", nativeQuery = true)
    public List<User> getAllActive();

    public List<User> findByFirstName(String search);

    public List<User> findByLastName(String search);
    
    public List<User> findByEmail(String search);

    @Query(value = "SELECT * FROM user "
            + "WHERE user.first_name LIKE %:search% "
            + "OR user.last_name LIKE %:search%", nativeQuery = true)
    public List<User> findLikeName(@Param("search") String search);

    @Query(value = "SELECT * FROM user "
            + "INNER JOIN user_courses ON user.id = user_courses.user_id "
            + "WHERE user_courses.courses_id = :courseId", nativeQuery = true)
    public List<User> findUsersByCourseId(@Param("courseId") String courseId);
}
