package com.syd.repository;

import com.syd.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ykue on 2017/6/29.
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Modifying
    @Transactional
    @Query("update UserEntity us set us.nickname=:qNickname, us.username=:qUsername,  us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qNickname") String nickname, @Param("qUsername") String username, @Param("qPassword") String password, @Param("qId") Integer id);


}
