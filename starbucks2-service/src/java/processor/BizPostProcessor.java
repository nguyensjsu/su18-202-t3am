package processor;

import dao.BaseDao;
import dao.CardImpl;
import dao.UserProfileImpl;
import helper.JSONHelper;
import model.Card;
import model.UserProfile;

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
                
            case "signup":
                UserProfile u = JSONHelper.fromJson2(body, UserProfile.class);
                
                // generate the uid
                u.setUid(2);
                
                
                dao = new UserProfileImpl();
                dao.create(u);
                return JSONHelper.toJson(u);
                
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
