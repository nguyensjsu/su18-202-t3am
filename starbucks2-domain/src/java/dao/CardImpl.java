package dao;

import model.Card;
import org.apache.ibatis.session.SqlSession;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class CardImpl extends BasePOJO implements CardDao {
    @Override
    public Card create(Card o) throws Exception {
        SqlSession s = client.openSession(true);
        s.insert("ns.card.create", o);
        s.close();
        return o;
    }

    @Override
    public List<Card> list(String kw) throws Exception {
        SqlSession s = client.openSession(true);
        List<Card> ret = s.selectList("ns.card.list", Long.valueOf(kw));
        s.close();
        return ret;
    }

    @Override
    public Card update(Card o) throws Exception {
        throw new NotImplementedException();
    }

    @Override
    public Card find(String key) throws Exception {
        throw new NotImplementedException();
    }


}
