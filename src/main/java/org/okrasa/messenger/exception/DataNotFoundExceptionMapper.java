package org.okrasa.messenger.exception;

import org.okrasa.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.*;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "https://google.com");
        return Response.status(Status.NOT_FOUND)
                       .entity(errorMessage)
                       .build();
    }

}
