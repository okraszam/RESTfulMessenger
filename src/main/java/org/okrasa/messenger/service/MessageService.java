package org.okrasa.messenger.service;

import org.okrasa.messenger.mockdao.DatabaseClass;
import org.okrasa.messenger.model.Message;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
    }

    public List<Message> getAllMessages () {

        return new ArrayList<>(messages.values());

    }

    public Message getMessage (Long id) {

        return messages.get(id);

    }

    public List<Message> getAllMessagesForYear(int year) {

        List<Message> messagesForYear = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for(Message message : messages.values()) {
            calendar.setTime(message.getCreated());
            if(calendar.get(Calendar.YEAR) == year) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;

    }

    public List<Message> getAllMessagesPaginated(int start, int size) {

        ArrayList<Message> list = new ArrayList<>(messages.values());
        return list.subList(start, start + size);

    }

    public Message addMessage (Message message) {

        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;

    }

    public Message updateMessage (Message message) {

        if(message.getId() <= 0) {
            return null;
        } else {
            messages.put(message.getId(), message);
            return message;
        }

    }

    public Message deleteMessage (Long id) {

        return messages.remove(id);

    }

}
