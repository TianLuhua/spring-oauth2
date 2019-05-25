package com.booyue.tlh.authorizationserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    /**
     * @param userName
     * @return
     */
    User findByuserName(String userName);
}
