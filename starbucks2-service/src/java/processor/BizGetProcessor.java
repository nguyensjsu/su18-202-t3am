package processor;

import helper.DateHelper;
import helper.JSONHelper;
import helper.UUIDHelper;
import model.Card;
import model.AuthRequest;
import java.util.List;
import java.util.Map;

import model.Purchase;
import model.UserProfile;

public class BizGetProcessor extends HttpProcessor {

    @Override
    String handle(final Map<String, Object> map) throws Exception {
        String uid;

        switch (path){

            case "cards":
                uid = paramMap.get("uid");
                List<Card> is = cardDao.list(uid);
                return JSONHelper.toJson(is);

            case "purchases":
                // This is to get the purchase history.
                // For the overall transaction history, both the card list and purchase history can be used
                // to calculate the remaining balance for the user. Card (+bal) and Purchase (-bal).
                uid = paramMap.get("uid");
                List<Purchase> pList = purchaseDao.list(uid);
                return JSONHelper.toJson(pList);

            case "user_profile":
                // add code to response with user_profile
                final String email = paramMap.get("email");
                final UserProfile up = userProfileDao.find(email);
                if (up != null)
                   return JSONHelper.toJson(up);
                return "{\"error\" : true}";

            default:
                return "Not supported.";
        }
    }
}
