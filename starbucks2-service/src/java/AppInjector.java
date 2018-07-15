
import com.google.inject.AbstractModule;
import dao.CardDao;
import dao.CardImpl;
import dao.PurchaseDao;
import dao.PurchaseImpl;
import dao.UserProfileDao;
import dao.UserProfileImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author syle
 */
public class AppInjector extends AbstractModule {
    @Override
    protected void configure() {
        // bind Dao to the right Impl
        bind(CardDao.class).to(CardImpl.class);
        bind(UserProfileDao.class).to(UserProfileImpl.class);
        bind(PurchaseDao.class).to(PurchaseImpl.class);
    }
}
