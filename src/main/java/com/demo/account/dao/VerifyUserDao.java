package com.demo.account.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.account.model.VerifyUser;

@Repository
public interface VerifyUserDao extends CrudRepository<VerifyUser, Long> {

	VerifyUser findVerifyUserByCode(String code);

}
