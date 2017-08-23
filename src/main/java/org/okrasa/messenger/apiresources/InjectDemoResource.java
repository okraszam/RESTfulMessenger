package org.okrasa.messenger.apiresources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

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
//    Context
//    BeanParam

    @GET
    @Path("annotations")
    public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
                                            @HeaderParam("customHeaderValue") String header,
                                            @CookieParam("name") String cookie) {

        return "Your matrix parameter was: " + matrixParam +
                " and your header parameter was: " + header +
                " and your cookie parameter was: " + cookie;

    }

    @GET
    @Path("context")
    public String getParamsUsingContext(@Context UriInfo uriInfo,
                                        @Context HttpHeaders httpHeaders) {

        String path = uriInfo.getAbsolutePath().toString();
        String header = httpHeaders.getCookies().toString();
        return "Path is: " + path +
                " cookies are: " + header;

    }

}
