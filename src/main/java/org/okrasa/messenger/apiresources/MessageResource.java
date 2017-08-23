package org.okrasa.messenger.apiresources;

import org.okrasa.messenger.apiresources.beans.MessageFilterBean;
import org.okrasa.messenger.model.Message;
import org.okrasa.messenger.service.MessageService;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.*;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
//    public List<Message> getMessages(@QueryParam("year") int year,
//                                     @QueryParam("start") int start,
//                                     @QueryParam("size") int size) {

        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        } else if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
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
//    public Message getOneMessage(@PathParam("messageId") Long messageId) {
    public Message getOneMessage(@Context UriInfo uriInfo,
                                 @PathParam("messageId") Long messageId) {

//        return messageService.getMessage(messageId);
        Message message = messageService.getMessage(messageId);
        message.addLink(getUliForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
//        message.addLink(uriInfo.getAbsolutePathBuilder().path(), "comments");

    }

    private String getUliForSelf(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                      .path(MessageResource.class)
                      .path(Long.toString(message.getId()))
                      .build().toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {

        return uriInfo.getAbsolutePathBuilder()
                      .path(ProfileResource.class)
                      .path(message.getAuthor())
                      .build().toString();

    }

    private String getUriForComments(UriInfo uriInfo, Message message) {

        return uriInfo.getBaseUriBuilder()
                      .path(MessageResource.class)
                      .path(MessageResource.class, "getCommentResource")
                      .path(CommentResource.class)
                      .resolveTemplate("messageId", message.getId())
                      .build().toString();

    }

    @POST
//    public Message postMessage(Message message) {
    public Response postMessage(@Context UriInfo uriInfo, Message message) {

//        return messageService.addMessage(message);
        Message newMessage = messageService.addMessage(message);
//        return Response.status(Status.CREATED)
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri)
                       .entity(newMessage)
                       .build();

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

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {

        return new CommentResource();

    }

}
