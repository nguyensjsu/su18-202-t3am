package processor;

import com.google.inject.Inject;
import dao.CardDao;
import dao.PurchaseDao;
import dao.UserProfileDao;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

abstract class HttpProcessor implements Processor {
    @Inject
    CardDao cardDao;
    
    @Inject
    UserProfileDao userProfileDao;
    
    @Inject
    PurchaseDao purchaseDao;

    protected String body = "";
    protected String path = null;
    protected Map<String, String> paramMap = new HashMap<>();

    @Override
    public void process(Exchange exchange) throws Exception {
        InputStream inputStream = exchange.getIn().getBody(InputStream.class);
        try {
            body = "";
            paramMap = new HashMap<>();

            if(inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    body += line;
                }
                body = body.trim();
            }

            if(exchange.getIn().getHeader("path") != null) {
                path = exchange.getIn().getHeader("path") + "";
            }

            if(exchange.getIn().getHeader("CamelHttpQuery") != null) {
                String params = exchange.getIn().getHeader("CamelHttpQuery") + "";
                if (StringUtils.isNotBlank(params) && !"null".equalsIgnoreCase(params)) {
                    paramMap = Arrays.stream(params.split("&")).map(s -> s.split("=")).collect(Collectors.toMap(a -> a[0], a -> a[1]));
                }
            }

            Map<String, Object> map = new HashMap<>();
            map.put( "Access-Control-Allow-Origin","*" );
            map.put( "Access-Control-Allow-Methods","*" );
            map.put( "Content-Type", "application/json; charset=UTF-8" );

            String resp = handle(map);

            exchange.getOut().setBody(resp);
            exchange.getOut().setHeaders(map);

        } catch (Throwable e) {
            e.printStackTrace();
            exchange.getOut().setBody(e.getMessage());
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
        }
    }

    abstract String handle(final Map<String, Object> map) throws Exception;
}

