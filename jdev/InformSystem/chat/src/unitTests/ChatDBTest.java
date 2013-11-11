package unitTests;

import java.io.*;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import server.ChatDB;

public class ChatDBTest {
    File f;
    ChatDB cdb;
    public ChatDBTest() {
    }

    @Before
    public void setUp() throws Exception {
        cdb = new ChatDB();
        cdb.setDBPath("c:/myDB.mydb");
        f = cdb.creatDB();
    }

    @After
    public void tearDown() throws Exception {
        f.delete();
    }

    /**
     * @see server.ChatDB#searchClient(String,String)
     */
    @Test
    public void testSearchClient() {
        cdb.addClient("Talosar", "12345");
        cdb.addClient("Talosarich", "12345");
        assertTrue(cdb.searchClient("Talosar", "12345"));
    }

    /**
     * @see server.ChatDB#addClient(String,String)
     */
    @Test
    public void testAddClient() {
        assertTrue(cdb.addClient("Talosar", "12345"));
        assertTrue(cdb.addClient("Talosarich", "12345"));
    }
}
