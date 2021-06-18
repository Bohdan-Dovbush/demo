package com.example.demo.repository.user;

import com.example.demo.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(final String email);
    @Query(value = "SELECT COUNT(*) FROM user",nativeQuery = true)
    Integer findCountUser();
    @Query(value = "SELECT * FROM user as u left join details d on u.id = d.details_id WHERE u.id = ?1", nativeQuery = true)
    Optional<UserEntity> findDetailsAndContactById(Long id);
}
