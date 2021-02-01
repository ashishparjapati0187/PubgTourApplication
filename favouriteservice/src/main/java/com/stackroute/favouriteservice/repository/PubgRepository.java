package com.stackroute.favouriteservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.domain.UserMatches;



@Repository            //Integer is wrapper class used
public interface PubgRepository extends JpaRepository<UserMatches, String>{


	List<UserMatches> findByUserId(String userId);
}