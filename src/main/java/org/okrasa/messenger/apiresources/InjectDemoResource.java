package org.okrasa.messenger.apiresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

//    PathParam
//    QuerryParam
//    MatrixParam
//    HeaderParam
//    CookieParam
//    FormParam

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("customHeaderValue") String header,
                                            @CookieParam("name") String cookie) {

        return "Your matrix parameter was: " + matrixParam +
                " and your header parameter was: " + header +
                " and your cookie parameter was: " + cookie;

    }

}
