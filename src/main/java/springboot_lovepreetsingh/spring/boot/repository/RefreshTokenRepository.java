package springboot_lovepreetsingh.spring.boot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot_lovepreetsingh.spring.boot.entities.RefreshToken;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken , Integer> {
    Optional<RefreshToken> findByToken(String token);
}
