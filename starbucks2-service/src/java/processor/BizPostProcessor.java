package processor;

import dao.BaseDao;
import dao.CardImpl;
import helper.JSONHelper;
import model.Card;

public class BizPostProcessor extends PostProcessor {
    @Override
    String handle() throws Exception {

        BaseDao dao;

        switch (path){

            case "reload":
                Card c = JSONHelper.fromJson2(body, Card.class);
                dao = new CardImpl();
                dao.create(c);
                return JSONHelper.toJson(c);

            default:
                throw new Exception("Not supported.");
        }
    }
}
