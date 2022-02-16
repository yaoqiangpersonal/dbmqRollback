package com.weiliang.dbmqRollback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weiliang.dbmqRollback.pojo.Demo;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

}
