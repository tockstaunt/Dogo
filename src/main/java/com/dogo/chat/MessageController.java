package com.dogo.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Autowired
	MessageRepository dao;

	@GetMapping("/chat")
	public List<Message> getMessages() {
		List<Message> foundMessages = dao.findAll();

		return foundMessages;
	}

	@PostMapping("/chat")
	public ResponseEntity<Message> postMessage(@RequestBody Message message) {

		while (dao.exists(message.getId())) {
			System.out.println("Space already used try to PUT or use a differnt id");
			return ResponseEntity.badRequest().build();

		}
			
			Message createdMessage = dao.save(message);
			System.out.println("========Message Created========");
			System.out.println(" ID:" + createdMessage.getId());
			System.out.println(" Name:"+ createdMessage.getName());
			System.out.println(" Content:"+ createdMessage.getContent());
			return ResponseEntity.ok(createdMessage);
		
		}
	

	@PutMapping("/chat")
	public ResponseEntity<Message> putMessage(@RequestBody Message message) {
		Message updateMessage = dao.save(message);
		System.out.println("========Message Updated========");
		System.out.println(" ID:" + updateMessage.getId());
		System.out.println(" Name:"+ updateMessage.getName());
		System.out.println(" Content:"+ updateMessage.getContent());
		
		return ResponseEntity.ok(updateMessage);
	}

}