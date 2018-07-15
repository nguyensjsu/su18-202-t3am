package processor;

import dao.BaseDao;
import dao.CardImpl;
import helper.JSONHelper;
import model.Card;

import java.util.List;
import java.util.Map;

public class BizGetProcessor extends GetProcessor {

    @Override
    Object handle(final Map<String, Object> map) throws Exception {

        BaseDao dao;

        switch (path){

            case "cards":
                dao = new CardImpl();
                String uid = paramMap.get("uid");
                List<Card> is = dao.list(uid);
                return JSONHelper.toJson(is);

            default:
                return "Not supported.";
        }

    }

}
