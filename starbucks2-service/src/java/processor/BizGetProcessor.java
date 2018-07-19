package processor;

import helper.JSONHelper;
import model.Card;
import java.util.List;
import java.util.Map;

import model.Purchase;
import model.ServerResponse;
import model.UserProfile;

public class BizGetProcessor extends HttpProcessor {

    @Override
    String handle(final Map<String, Object> map) throws Exception {
        final ServerResponse resp = new ServerResponse();
        
        String uid;

        try{
            switch (path){
                case "cards":
                    uid = paramMap.get("uid");

                    if(uid != null) {
                        List<Card> is = cardDao.list(uid);
                        resp.setResponse(is);
                    } else {
                        resp.setError(true);
                        resp.setMsg("No card found for this user_id...");
                    }
                    break;

                case "purchases":
                    // This is to get the purchase history.
                    // For the overall transaction history, both the card list and purchase history can be used
                    // to calculate the remaining balance for the user. Card (+bal) and Purchase (-bal).
                    uid = paramMap.get("uid");

                    if(uid != null) {
                        List<Purchase> pList = purchaseDao.list(uid);
                        resp.setResponse(pList);
                    } else {
                        resp.setError(true);
                        resp.setMsg("No purchase found for this user_id...");
                    }
                    break;

                case "user_profile":
                    // add code to response with user_profile
                    final String email = paramMap.get("email");
                    final UserProfile up = userProfileDao.find(email);
                    if (up != null){
                        resp.setResponse(up);
                    } else {
                        resp.setError(true);
                        resp.setMsg("No user_profile found for this user_id...");
                    }
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
