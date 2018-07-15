package processor;

import dao.BaseDao;
import dao.CardImpl;
import dao.PurchaseImpl;
import helper.DateHelper;
import helper.JSONHelper;
import helper.UUIDHelper;
import model.Card;

import java.util.List;
import java.util.Map;

import model.Purchase;
import model.UserProfile;

public class BizGetProcessor extends HttpProcessor {

    @Override
    String handle(final Map<String, Object> map) throws Exception {

        BaseDao dao;
        String uid;

        switch (path){

            case "cards":
                dao = new CardImpl();
                uid = paramMap.get("uid");
                List<Card> is = dao.list(uid);
                return JSONHelper.toJson(is);

            case "purchases":
                // This is to get the purchase history.
                // For the overall transaction history, both the card list and purchase history can be used
                // to calculate the remaining balance for the user. Card (+bal) and Purchase (-bal).
                dao = new PurchaseImpl();
                uid = paramMap.get("uid");
                List<Purchase> pList = dao.list(uid);
                return JSONHelper.toJson(pList);   
                
            case "user_profile":
                // add code to response with user_profile
                uid = paramMap.get("uid");
                UserProfile user = new UserProfile();
                user.setFull_name("Sy Le");
                user.setBalance(20.0);
                user.setUser_id(UUIDHelper.getRandomUUID());
                user.setDate_added(DateHelper.getCurrentEpochTimestamp());
                return JSONHelper.toJson(user);

            default:
                return "Not supported.";
        }

    }

}
