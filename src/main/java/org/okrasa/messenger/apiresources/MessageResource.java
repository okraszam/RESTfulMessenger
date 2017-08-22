package org.okrasa.messenger.apiresources;

import org.okrasa.messenger.model.Message;
import org.okrasa.messenger.service.MessageService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("start") int start,
                                     @QueryParam("size") int size) {

        if (year > 0) {
            return messageService.getAllMessagesForYear(year);
        } else if (start >= 0 && size > 0) {
            return messageService.getAllMessagesPaginated(start, size);
        } else {
            return messageService.getAllMessages();
        }

    }

//    @GET
//    @Path("?year={year}")
//    public List<Message> getMessagesForYear(@QueryParam("year") int year) {
//
//        return messageService.getAllMessagesForYear(year);
//
//    }
//
//    @GET
//    @Path("?start={start}&size={size}")
//    public List<Message> getMessagesPaginated(@QueryParam("start") int start, @QueryParam("size") int size) {
//
//        return messageService.getAllMessagesPaginated(start, size);
//
//    }

    @GET
    @Path("/{messageId}")
    public Message getOneMessage(@PathParam("messageId") Long messageId) {

        return messageService.getMessage(messageId);

    }

    @POST
    public Message postMessage(Message message) {

        return messageService.addMessage(message);

    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") Long messageId, Message message){

        message.setId(messageId);
        return messageService.updateMessage(message);

    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") Long messageId) {

        messageService.deleteMessage(messageId);

    }

}
