//package cn.com.bsoft.junit.test;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import junit.framework.TestCase;
//import org.junit.Test;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//
//public class Testd extends TestCase
//{
//    private Collection<String> collection;
//
//
//    @BeforeClass
//    public static void oneTimeSetUp()
//    {
//        // one-time initialization code
//    }
//
//
//    @AfterClass
//    public static void oneTimeTearDown()
//    {
//        // one-time cleanup code
//    }
//
//
//    @Before
//    public void setUp()
//    {
//        collection = new ArrayList<String>();
//    }
//
//
//    @After
//    public void tearDown()
//    {
//        collection.clear();
//    }
//
//
//    @Test
//    public void testEmptyCollection()
//    {
//        assertTrue(collection.isEmpty());
//    }
//
//
//    @Test
//    public void testOneItemCollection()
//    {
//        collection.add("itemA");
//        collection.add("itemB");
//        assertEquals(2, collection.size());
//    }
//}
