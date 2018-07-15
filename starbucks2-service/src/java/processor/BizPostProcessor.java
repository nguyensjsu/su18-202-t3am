package processor;

import dao.BaseDao;
import dao.CardImpl;
import dao.UserProfileImpl;
import helper.DateHelper;
import helper.JSONHelper;
import helper.UUIDHelper;
import model.Card;
import model.Purchase;
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
                // User ID and balance (or the cost of the order) will be from the body
                Purchase p = JSONHelper.fromJson2(body, Purchase.class);
                p.setDate_added(DateHelper.getCurrentEpochTimestamp());
                dao = new CardImpl();
                dao.create(p);
                return JSONHelper.toJson(p);
                
            case "signup":
                UserProfile u = JSONHelper.fromJson2(body, UserProfile.class);
                
                // generate the uid and current timestamp
                u.setBalance(0.0);
                u.setUser_id(UUIDHelper.getRandomUUID());
                u.setDate_added(DateHelper.getCurrentEpochTimestamp());
                
                
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
