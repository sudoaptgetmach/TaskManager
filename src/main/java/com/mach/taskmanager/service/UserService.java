package com.mach.taskmanager.service;

import com.mach.taskmanager.domain.user.UserListData;
import com.mach.taskmanager.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Page<UserListData> listUsers(Pageable pageable) {
        return repository.findAll(pageable).map(UserListData::new);
    }
}
