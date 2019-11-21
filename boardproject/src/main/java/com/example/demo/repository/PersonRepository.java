package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

//@Repository 원래는 적어줘야하는데 안적는 이유는 DataAccessConfig에 base package에서 스캔할때 메모리에 뜸
public interface PersonRepository {
	Person findById(int id);
	
	//파라미터는 무조건 하나여야함. 안그러면 mapper랑 연결 못 함.
	void save(Person person);
	
	void delete(int id);
	void update(Person person);
}
