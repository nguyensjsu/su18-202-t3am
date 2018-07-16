
package helper ;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import helper.JSONHelper;

/**
 * Testing the JSONHelper Classes
 * Hyunwook Shin
 */

public class JSONHelperTest {

    public JSONHelperTest() {
    }

    @Before
    public void setUp() {
       // todo expand
    }

    @Test
    public void FromJson2Test() throws Exception {
       // todo expand
       String json = "{ \"a\": 321, \"b\" : \"BB\"}";
       DummyClass d = JSONHelper.fromJson2( json, DummyClass.class );
       
       assertEquals(321, d.getA());
       assertEquals("BB", d.getB());
    }

    @Test
    public void FromJsonForObjList() throws Exception {
       // todo expand
       String json = "[{ \"a\": 321, \"b\" : \"BB\"}]";
       List<DummyClass> lst = JSONHelper.fromJsonForObjList( json, DummyClass.class );
       
      for (DummyClass d : lst) {
          assertEquals(321, d.getA());
          assertEquals("BB", d.getB());
       }
    }

    @After
    public void tearDown() {
       // todo expand
    }
}
