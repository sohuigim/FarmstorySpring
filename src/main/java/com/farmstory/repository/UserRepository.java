package com.farmstory.repository;

import com.farmstory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public int countByUserUid(String userUid);
    public int countByUserNick(String userNick);
    public int countByUserEmail(String userEmail);
    public int countByUserHp(String userHp);
}
