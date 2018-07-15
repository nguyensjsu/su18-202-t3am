package processor;

import dao.BaseDao;
import dao.CardImpl;
import helper.JSONHelper;
import model.Card;

import java.util.List;
import java.util.Map;
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
                
            case "user_profile":
                // add code to response with user_profile
                uid = paramMap.get("uid");
                UserProfile user = new UserProfile();
                user.setUid(Long.valueOf(uid));
                user.setFull_name("Sy Le");
                user.setBalance(20.0);
                user.setDate_added(1524957777777l);
                return JSONHelper.toJson(user);

            default:
                return "Not supported.";
        }

    }

}
