package com.bridgelabz.register.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.register.model.User;
@Repository
public interface UserDAO extends CrudRepository<User, Long>
{

}
