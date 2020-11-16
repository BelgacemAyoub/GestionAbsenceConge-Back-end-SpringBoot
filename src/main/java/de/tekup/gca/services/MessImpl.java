package de.tekup.gca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.gca.entities.Message;
import de.tekup.gca.entities.User;
import de.tekup.gca.repository.MessageRepo;
import de.tekup.gca.repository.UserRepo;

@Service
public class MessImpl implements MessageService {
	
	@Autowired
	MessageRepo messageRepo;
	@Autowired
	UserRepo userRepo;

	@Override
	public Message addMessage(Message message) {
		return messageRepo.save(message);
		
	}

	@Override
	public void deleteMessage(Long message_id) {
		messageRepo.deleteById(message_id);
		
	}

	@Override
	public Message updateMessage(Message message) {
		return messageRepo.save(message);
		
	}

	@Override
	public List<Message> allMessages() {
		return messageRepo.findAll();
	}

	@Override
	public Message findMessage(Long message_id) {
		  return messageRepo.findById(message_id).orElseThrow(() -> new RuntimeException("reclamation not exist"));

	}

	@Override
	public List<Message> allMessageById(Long user_id) {
		  User u = userRepo.findById(user_id).orElseThrow(() -> new RuntimeException("User not exist"));
		  return u.getMessages();
	}

}
