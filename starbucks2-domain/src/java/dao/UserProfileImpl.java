/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.UserProfile;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author syle
 */
public class UserProfileImpl  extends BasePOJO implements UserProfileDao {
    @Override
    public UserProfile create(UserProfile o) throws Exception {
        SqlSession s = client.openSession(true);
        s.insert("ns.user_profile.create", o);
        s.close();
        return o;
    }

    @Override
    public UserProfile update(UserProfile o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserProfile find(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserProfile> list(String kw) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
