package de.tekup.gca.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.gca.entities.Message;
import de.tekup.gca.services.MessageService;

@RestController
@RequestMapping (value = "/api/message")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@GetMapping(value = "/list")
	public List<Message> messagelist() { //  @RequestBody Absence absence : eli jeni mel front
		
		return messageService.allMessages();
	}
	
	@GetMapping (value = "/findMessage/{message_id}")
	public ResponseEntity<Message> findMessage (@PathVariable Long message_id) {
		try {
			   Message m =  messageService.findMessage(message_id);	
			
			return new ResponseEntity<Message>(m, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping (value = "/deleteMessage/{message_id}")
	public ResponseEntity<Void> deleteMessage (@PathVariable Long message_id) {
		try {
			messageService.findMessage(message_id);
			messageService.deleteMessage(message_id);		
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/addMessage")
	public ResponseEntity<Message> addMessage(@RequestBody Message message) { //  @RequestBody Absence absence : eli jeni mel front
		
		try {
			Message m = messageService.addMessage(message);
			return new ResponseEntity<Message>(m, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	}
	
	@PutMapping(value = "/updateMessage")
	public ResponseEntity<Message> updateMessage(@RequestBody Message message) { //  @RequestBody Absence absence : eli jeni mel front
		
		try {
			Message m = messageService.addMessage(message);
			return new ResponseEntity<Message>(m, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}	
	} 
	
	@GetMapping (value = "/findMessagesById/{message_id}")
	public ResponseEntity<List<Message>> findMessagesById (@PathVariable Long message_id) {
		try {
			   List<Message> m =  messageService.allMessageById(message_id)	;
			
			return new ResponseEntity<List<Message>>(m, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
