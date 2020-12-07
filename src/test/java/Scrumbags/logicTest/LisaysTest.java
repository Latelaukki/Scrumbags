package Scrumbags.logicTest;

import java.util.ArrayList;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import Scrumbags.logic.*;
import Scrumbags.database.*;

/**
* Perustestit tietokantaan lisäämiselle
*/
public class LisaysTest {
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    
    Service service;
    ArrayList<String> errMsgs;
    
    public LisaysTest() {
        this.service = new Service(new DatabaseMock());
        this.errMsgs = new ArrayList<>();
    }
    
    @Before
    public void setup() {
        System.setOut(new PrintStream(testOut));
    }
    
    @After
    public void tearDown() {
        System.setOut(sysOut);
    }
    
    @Test
    public void addingBookReturnsTrue() {
        assertTrue(this.service.addBook("Aapinen", "Tuntematon", "---", -1, -1));
    }
    
    @Test
    public void addingLinkReturnsTrue() {
        assertTrue(this.service.addLink("Google", "http://www.google.com"));
    }
    
    @Test
    public void addedBookExistsWithRightName() {
        this.service.addBook("Aapinen2", "Tuntematon2", "---", -1, -1);
        ArrayList<Book> booklist = this.service.getBooksByAuthor("Tuntematon2");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("Aapinen2")) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void noDuplicateBooksAdded() {
        this.service.addBook("nimi", "kirjailija", "isbn1234", 123, 123);
        assertFalse(this.service.addBook("1", "2", "isbn1234", 3, 4));
    }

    @Test
    public void errorMessagePrintedUponAttemptToAddDuplicateBook() {
        this.service.addBook("nimi", "kirjailija", "isbn1234", 123, 123);
        this.service.addBook("1", "2", "isbn1234", 3, 4);
        
        this.errMsgs.add(testOut.toString());
                
        assertTrue(errMsgs.contains("Virhe: ISBN-koodi on jo käytössä.\n"));
    }
    
    @Test
    public void errorMessagePrintedUponAttemptToAddDuplicateLink() {
        this.service.addLink("Google", "http://www.google.com");
        this.service.addLink("Kuukkeli", "http://www.google.com");
        
        this.errMsgs.add(testOut.toString());
                
        assertTrue(errMsgs.contains("Virhe: URL-osoite on jo käytössä.\n"));
    }
    
    @Test
    public void errorMessagePrintedUponAttemptToAddDuplicatePodcast() {
        this.service.addPodcast("Urheilucast", "Esko Seppänen", "https://soundcloud.com/urheilucast", "---");
        this.service.addPodcast("Urheilucast", "---", "https://soundcloud.com/kokkicast", "rss.rss.rss");
        
        this.errMsgs.add(testOut.toString());
                
        assertTrue(errMsgs.contains("Virhe: Podcastin nimi on jo käytössä.\n"));
    }

    @Test
    public void nameSearchFailsIfBookNotAddedYet() {
        assertNull(this.service.getBooksByName("XYZ"));
    }
    
    @Test
    public void nameSearchFailsIfNoBookWithNameGivenExists() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        this.service.addBook("nimi", "kirjailija", "isbn1234", 123, 123);
        assertNull(this.service.getBooksByName("XYZ"));
    } 
   
    
}