package pl.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

//    List<String> findRoleByUserName(String username);

}