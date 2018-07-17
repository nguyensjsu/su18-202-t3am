package dao;

import helper.ServerConfigHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by Lin Cheng
 */
public class BasePOJO {

    static protected SqlSessionFactory client;

    static {
        try (Reader reader = Resources.getResourceAsReader("sql-maps-config.xml")) {
            Properties databaseProperties = ServerConfigHelper.getDatabaseConnectionProperties();
            client = new SqlSessionFactoryBuilder().build(reader, databaseProperties);
            PropertyConfigurator.configureAndWatch("/opt/t3am/log4j.properties");
        } catch (IOException e) {
            System.out.println("Database Connection String is not valid...");
            e.printStackTrace();
        }
    }
}
