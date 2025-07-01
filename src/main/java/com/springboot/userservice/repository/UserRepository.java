package com.springboot.userservice.repository;

import com.springboot.userservice.entity.UserInfo;
import com.springboot.userservice.entity.UserInfoDTO;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface UserRepository extends CrudRepository<UserInfo,String> {

    Optional<UserInfo> findByUserId(String id);

}
