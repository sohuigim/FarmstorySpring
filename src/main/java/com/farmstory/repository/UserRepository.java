package com.farmstory.repository;

import com.farmstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public int countByUserUid(String userUid);
    public int countByUserNick(String userNick);
    public int countByUserEmail(String userEmail);
    public int countByUserHp(String userHp);
    public int countByUserPass(String userPass);

    User findByUserEmail(String userEmail);
    User findByUserUid(String userUid);

    @Modifying
    @Query("UPDATE User u SET u.userLeaveDate = :leaveDate WHERE u.userUid = :userUid")
    int updateByUserLeaveDate(@Param("userUid") String userUid, @Param("leaveDate") LocalDateTime leaveDate);

}
