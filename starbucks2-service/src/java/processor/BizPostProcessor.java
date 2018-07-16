package processor;

import helper.DateHelper;
import helper.JSONHelper;
import helper.UUIDHelper;
import model.Card;
import model.Purchase;
import model.UserProfile;
import model.AuthRequest;

import java.util.Map;

public class BizPostProcessor extends HttpProcessor {
    @Override
    String handle(final Map<String, Object> map) throws Exception {
        switch (path) {
            case "reload":
                Card c = JSONHelper.fromJson2(body, Card.class);
                cardDao.create(c);
                return JSONHelper.toJson(c);
                
            case "purchase":
                // User ID, balance(or the cost of the order), and purchase location will be from the body
                Purchase p = JSONHelper.fromJson2(body, Purchase.class);
                p.setDate_added(DateHelper.getCurrentEpochTimestamp());
                purchaseDao.create(p);
                return JSONHelper.toJson(p);
                
            case "signup":
                UserProfile u = JSONHelper.fromJson2(body, UserProfile.class);
                
                // generate the uid and current timestamp
                u.setBalance(0.0);
                u.setUser_id(UUIDHelper.getRandomUUID());
                u.setDate_added(DateHelper.getCurrentEpochTimestamp());
                
                userProfileDao.create(u);
                return JSONHelper.toJson(u);
                
            case "signin":
                AuthRequest a = JSONHelper.fromJson2(body, AuthRequest.class);
                UserProfile up = userProfileDao.find(a.getUserId());
                if (a.authenticate(up))
                   return "{\"status\" : \"authenticated\"}";
                return "{\"status\" : \"failed\"}";

            case "signout":
                return "{\"status\" : \"signed out\"}";

            default:
                throw new Exception("Not supported.");
        }
    }
}
