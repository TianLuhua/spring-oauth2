package com.booyue.tlh.servicehi.service;

import com.booyue.tlh.servicehi.dao.User;

public interface UserService {
    User create(String userName, String password);
}
