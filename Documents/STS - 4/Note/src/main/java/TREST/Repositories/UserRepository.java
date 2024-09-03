package TREST.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import TREST.Models.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	@Query("SELECT u FROM User u WHERE u.user_name = :user_name")
	User findByApelido(@Param("user_name") String userName);
}