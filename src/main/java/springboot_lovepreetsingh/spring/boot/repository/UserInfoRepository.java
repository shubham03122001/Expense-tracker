package springboot_lovepreetsingh.spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot_lovepreetsingh.spring.boot.entities.UserInfo;

import java.util.Optional;


@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo , Long> {

    UserInfo findByUsername(String username);
}
