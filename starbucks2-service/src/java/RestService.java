import com.google.inject.Guice;
import com.google.inject.Injector;
import helper.ServerConfigHelper;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import processor.BizGetProcessor;
import processor.BizOptionProcessor;
import processor.BizPostProcessor;

public class RestService extends CamelService {

    static RestService instance = new RestService();

    private RestService() {
    }

    static public RestService instance(){
        return instance;
    }

    @Override
    public void addRoutes() throws Exception {
        //add REST endpoints
        addRoute(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                restConfiguration().component("restlet")
                    .host(ServerConfigHelper.getHost())
                    .port(ServerConfigHelper.getPort())
                    .bindingMode(RestBindingMode.auto);

                rest("/api/v1").enableCORS(true)
                        .options("/{path}").to("direct:bizoption")
                        .get("/{path}").to("direct:bizget")
                        .post("/{path}").to("direct:bizpost");

                from("direct:bizoption")
                    .process(optionProcessor);
                from("direct:bizget")
                        .process(getProcessor);
                from("direct:bizpost")
                        .process(postProcessor);
            }
        });
    }
    
    static BizGetProcessor getProcessor;
    static BizPostProcessor postProcessor;
    static BizOptionProcessor optionProcessor;

    public static void main(String[] args){
        Injector injector = Guice.createInjector(new AppInjector());
        getProcessor = injector.getInstance(BizGetProcessor.class);
        postProcessor = injector.getInstance(BizPostProcessor.class);
        optionProcessor = injector.getInstance(BizOptionProcessor.class);
        
        try {        
            instance.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}