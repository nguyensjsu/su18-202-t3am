/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.inject.Singleton;
import model.Purchase;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

/**
 *
 * @author syle
 */
@Singleton
public class PurchaseImpl extends BasePOJO implements PurchaseDao{

    @Override
    public Purchase create(Purchase o) throws Exception {
        // Create a brand new purchase entry
        SqlSession s = client.openSession(true);
        s.insert("ns.purchase.create", o);
        s.close();
        return o;
    }

    @Override
    public List<Purchase> list(String kw) throws Exception {
        // Similar to the list of cards, there will also be a list of purchases.
        // Keeping a list of the purchases
        SqlSession s = client.openSession(true);
        List<Purchase> ret = s.selectList("ns.purchase.list", kw);
        s.close();
        return ret;
    }

    @Override
    public Purchase update(Purchase o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Purchase find(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
