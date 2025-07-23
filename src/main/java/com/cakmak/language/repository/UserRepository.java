package com.cakmak.language.repository;

import com.cakmak.language.model.entity.Users;
import com.cakmak.language.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Users> {
    Optional<Users> findByEmail(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    @Query("select u from Users u left join fetch u.roles where u.id =:id")
    Optional<Users> findByUser_Id(Long id);
    @Query("select u from Users u where u.id <>:id and u.email=:email")
    Optional<Users> findByEmailAndId(String email, Long id);
}
