package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

//새로운 데이터 조작 함수 만들때
/*
 * Interface 부터
 * mapper
 * 함수 만들기
 * */

@RestController
public class PersonController {

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/person/{id}")
	public Person getPerson(@PathVariable int id) {
		Person person = personRepository.findById(1);
		return person;
	}

	// 어노테이션을 다르게 맵핑하면 주소를 같게 해도 된다. 요청할때 방식만 다르게 해주면 됨. 위에는 GET방식 여기는 DELETE방식
	
	 @DeleteMapping("/person/{id}") 
	 	public int deletePerson(@PathVariable int id) { 
		 try {
			 personRepository.delete(id);
			 return 1;
		} catch (Exception e) {
			return -1;
		} 
		  
	}
	 

	// Person person
	// 주의점 :그냥 Person person으로 파라미터 주면  1) param, x-www-form-urlencoded 등 form data 만 받아준다.
	// 2) 데이터가 json 즉, raw data로 들어오면 @RequestBody Person person 하면 된다.
	// public @ResponseBody Person ... 안하는 이유는 @RestController라서
	@PostMapping("/person")
	public int setPerson(Person person) {
		try {
			personRepository.save(person);
			return 1; // 정상 : 1
		} catch (Exception e) {
			return -1;
		}
	}
	
	@PutMapping("/person")
	public int updatePerson(Person person) {
		try {
			personRepository.update(person);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
}
