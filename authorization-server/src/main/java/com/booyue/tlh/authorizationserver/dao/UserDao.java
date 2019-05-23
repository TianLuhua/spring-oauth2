package com.booyue.tlh.authorizationserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    /**
     * 改方法jpa默认已经实现
     *
     * @param userName
     * @return
     */
    User findByuserName(String userName);
}
