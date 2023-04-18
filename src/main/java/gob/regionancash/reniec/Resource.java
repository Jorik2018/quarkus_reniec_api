package gob.regionancash.reniec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.isobit.SOAPClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Resource  extends SOAPClient{

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private Client client;

    private String password="40931308";
    private String nuRucUsuario="20530689019";
    private String nuDniUsuario="40931308";
    
	//@Inject
   // public PostResourceClient(/*PostServiceProperties properties*/) {
        //baseUrl = properties.getBaseUrl();
    public Resource() {
        client = ClientBuilder.newBuilder()
                .executorService(executorService)
                .build();
    }
    
    
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("Consultar")
	public String Consultar(
			@QueryParam("nuDniConsulta") String nuDniConsulta,
			@QueryParam("nuDniUsuario") String nuDniUsuario_,
			@QueryParam("nuRucUsuario") String nuRucUsuario_,
			@QueryParam("password") String password_,
			@QueryParam("out") String out
	) throws Exception {
		return ClientBuilder.newBuilder().build()
				.target("https://ws5.pide.gob.pe/Rest/Reniec/Consultar?nuDniUsuario="+nuDniUsuario+"&password="+password+"&nuRucUsuario="+nuRucUsuario+"&out="+out+"&nuDniConsulta="+nuDniConsulta).request().get(String.class);
	}
    
    private String target="https://ws5.pide.gob.pe/services/ReniecConsultaDni.ReniecConsultaDniHttpsSoap11Endpoint";
    
    @POST
    public Object hello(Map params) throws Exception {
		String out="json";
		return client
		.target("https://ws5.pide.gob.pe/Rest/Reniec/Consultar?nuDniUsuario="+nuDniUsuario+"&password="+password+"&nuRucUsuario="+nuRucUsuario+"&out="+out+"&nuDniConsulta="+params.get("dni")).request().get(String.class);
        	/*Document doc = getDocument(
        			target,
        			"consultar",
        			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.reniec.gob.pe/\">\r\n"
            				+ "   <soapenv:Header/>\r\n"
            				+ "   <soapenv:Body>\r\n"
            				+ "      <ws:consultar>\r\n"
            				+ "         <arg0>\r\n"
            				+ "            <!--Optional:-->\r\n"
            				+ "            <nuDniConsulta>"+params.get("dni")+"</nuDniConsulta>\r\n"
    						+ "            <!--Optional:-->\r\n"
    						+ "            <nuDniUsuario>"+nuDniUsuario+"</nuDniUsuario>\r\n"
            				+ "            <!--Optional:-->\r\n"
            				+ "            <nuRucUsuario>"+nuRucUsuario+"</nuRucUsuario>\r\n"
            				+ "            <!--Optional:-->\r\n"
            				+ "            <password>"+password+"</password>\r\n"
            				+ "         </arg0>\r\n"
            				+ "      </ws:consultar>\r\n"
            				+ "   </soapenv:Body>\r\n"
            				+ "</soapenv:Envelope>");
        	Map result=(Map) transform(doc.getChildNodes().item(0).getChildNodes().item(0).getChildNodes().item(0).getChildNodes().item(0).getChildNodes());
        	HashMap m=new HashMap();
        	m.put("return",result);
        	m.putAll(result);
        	return m;*/
    }
    
}