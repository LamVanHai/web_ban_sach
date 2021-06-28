package com.bookshop.repository;

import com.bookshop.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT e from User e where e.email=:email and e.enable=:enable")
    User findOneByEmailAndEnable(@Param("email") String email, @Param("enable") Integer enable);

    User findByEmail(String email);

    @Query("SELECT e from User e where e.phone_number=:phone")
    User findOneByPhone_number(@Param("phone") String phone);

    @Query("SELECT e from User e where e.verification_Code=:code")
    User findOneByVerification_Code(@Param("code") String code);

    @Query("select e from User e where concat(e.name,e.phone_number,e.address,e.enable,e.email,e.id) like %?1%")
    List<User> findByKeyWord(String keyWord, Pageable pageable);

    @Query("select e from User e where concat(e.name,e.phone_number,e.address,e.enable,e.email,e.id) like %?1%")
    List<User> countByKeyWord(String keyWord);

}
