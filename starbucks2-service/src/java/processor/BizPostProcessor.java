package processor;

import helper.DateHelper;
import helper.JSONHelper;
import helper.UUIDHelper;
import model.Card;
import model.Purchase;
import model.UserProfile;
import model.AuthRequest;

import java.util.Map;
import model.ServerResponse;

public class BizPostProcessor extends HttpProcessor {
    @Override
    String handle(final Map<String, Object> map) throws Exception {
        final ServerResponse resp = new ServerResponse();
        
        try{
            switch (path) {
                case "reload":
                    final Card c = JSONHelper.fromJson2(body, Card.class);
                    c.setDate_added(DateHelper.getCurrentEpochTimestamp());
                    cardDao.create(c);
                    resp.setResponse(c);
                    break;

                case "purchase":
                    // User ID, balance(or the cost of the order), and purchase location will be from the body
                    final Purchase p = JSONHelper.fromJson2(body, Purchase.class);
                    p.setDate_added(DateHelper.getCurrentEpochTimestamp());
                    purchaseDao.create(p);
                    resp.setResponse(p);
                    break;

                case "signup":
                    UserProfile u = JSONHelper.fromJson2(body, UserProfile.class);

                    // generate the uid and current timestamp
                    u.setBalance(0.0);
                    u.setUser_id(UUIDHelper.getRandomUUID());
                    u.setDate_added(DateHelper.getCurrentEpochTimestamp());

                    userProfileDao.create(u);
                    resp.setResponse(u);
                    break;

                case "signin":
                    AuthRequest a = JSONHelper.fromJson2(body, AuthRequest.class);
                    UserProfile up = userProfileDao.find(a.getEmail());
                    if ((up != null) && (a.authenticate(up))){
                        resp.setResponse(up);
                    } else {
                        resp.setError(true);
                        resp.setMsg("Invalid email/password...");
                    }
                    break;

                case "signout":
                    resp.setMsg("Signed Out");
                    break;

                default:
                    resp.setError(true);
                    resp.setResponse("API is not supported");
                    break;
            }
        } catch(Exception e){
            resp.setError(true);
            resp.setMsg(e.toString());
        }
        
        return JSONHelper.toJson(resp);
    }
}
