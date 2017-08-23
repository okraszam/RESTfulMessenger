package org.okrasa.messenger.service;

import org.okrasa.messenger.mockdao.DatabaseClass;
import org.okrasa.messenger.model.Comment;
import org.okrasa.messenger.model.ErrorMessage;
import org.okrasa.messenger.model.Message;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments (long messageId) {

        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());

    }

    public Comment getComment (long messageId, long commentId) {

//        Map<Long, Comment> comments = messages.get(messageId).getComments();
//        return comments.get(commentId);
        ErrorMessage errorMessage = new ErrorMessage("Not Found", 404, "https://google.com");
        Response response = Response.status(Status.NOT_FOUND)
                                    .entity(errorMessage)
                                    .build();
        Message message = messages.get(messageId);
        if(message == null) {
            throw new WebApplicationException(response);
        }
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        if(comment == null) {
//            throw new WebApplicationException(response);
            throw new NotFoundException(response);
        }
        return comment;

    }

    public Comment addComment (long messageId, Comment comment) {

        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;

    }

    public Comment updateComment (long messageId, Comment comment) {

        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if(comment.getId() <= 0) {
            return null;
        } else {
            comments.put(comment.getId(), comment);
            return comment;
        }

    }

    public Comment removeComment (long messageId, long commentId) {

        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);

    }



}
