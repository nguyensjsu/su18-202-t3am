package processor;

import dao.BaseDao;
import dao.CardImpl;
import helper.JSONHelper;
import model.Card;

import java.util.Map;

public class BizPostProcessor extends HttpProcessor {
    @Override
    String handle(final Map<String, Object> map) throws Exception {

        BaseDao dao;

        switch (path){

            case "reload":
                Card c = JSONHelper.fromJson2(body, Card.class);
                dao = new CardImpl();
                dao.create(c);
                return JSONHelper.toJson(c);
                
            case "purchase":
                // add code here to do purchase
                return "purchase";
                
            case "signin":
                // add code here
                return "signin";
                
            case "signout":
                // add code here
                return "signout";

            default:
                throw new Exception("Not supported.");
        }
    }
}
