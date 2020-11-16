package de.tekup.gca.services;

import java.util.List;

import de.tekup.gca.entities.Message;

public interface MessageService {
	
	public Message addMessage (Message message);
	public void deleteMessage (Long absence_id);
	public Message updateMessage (Message message);
	public List<Message> allMessages ();
	public Message findMessage (Long message_id);
	public List<Message> allMessageById(Long user_id);

}
