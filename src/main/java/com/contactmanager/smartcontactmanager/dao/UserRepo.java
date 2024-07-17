package com.contactmanager.smartcontactmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactmanager.smartcontactmanager.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
