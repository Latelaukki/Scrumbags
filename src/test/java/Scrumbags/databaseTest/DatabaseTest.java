/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scrumbags.databaseTest;

import Scrumbags.database.*;
import Scrumbags.logic.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toniramo
 */
public class DatabaseTest {

    private String testDbName = "databaseTest.db";
    private Dao db;

    private Book joulupukinLomakirja = new Book("Joulupukin lomakirja", "Mauri Kunnas", "978-951-1-35993-7", 30, 2020);
    private Book kuinkaPuutKasvavat = new Book("Kuinka puut kasvavat", "Saku Tuominen", "978-951-1-36427-6", 123, 2021);
    private Book pizze = new Book("Pizze", "Saku Tuominen", "978-951-1-29097-1", 62, 2015);
    private Book tirakirja = new Book("Tirakirja", "Antti Laaksonen", "---", -1, -1);
    private Book scrumbook = new Book("Scrumbook", "---", "---", -1, -1);

    private Link tira = new Link("Tietorakenteet ja algoritmit", "https://tira-s19.mooc.fi/");
    private Link ohtu = new Link("Ohjelmistotuotanto", "https://ohjelmistotuotanto-hy.github.io/");

    private Podcast sodoma = new Podcast("Radio Sodoma", "YLE Podcast", "---", "---");
    private Podcast bbc = new Podcast("BBC Newscast", "BBC", "https://www.bbc.co.uk/programmes/p05299nl/episodes/downloads", "https://podcasts.files.bbci.co.uk/p05299nl.rss");
    private Podcast pmh = new Podcast("Pieleen mennyt historia", "Yle", "https://areena.yle.fi/audio/1-50677839", "https://feeds.yle.fi/areena/v1/series/1-50677839.rss?lang=fi&downloadable=true");

    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    private final PrintStream sysOut = System.out;
    
    ArrayList<String> errMsgs;
    
    @Before
    public void setUp() throws ClassNotFoundException {
        System.setOut(new PrintStream(testOut));

        db = new Database("jdbc:sqlite:" + testDbName);
        db.addBook(joulupukinLomakirja);
        db.addLink(tira);
        db.addPodcast(bbc);
    }

    @After
    public void tearDown() {
        deleteDatabase();
        System.setOut(sysOut);
    }

    public DatabaseTest() {
        this.errMsgs = new ArrayList<>();
    }
    
    @Test
    public void addingNewValidBookReturnsTrue() {
        assertTrue(db.addBook(kuinkaPuutKasvavat));
    }

    @Test
    public void existingBookCanBeFoundByName() {
        assertTrue(db.getBooksByName("Joulupukin lomakirja").contains(joulupukinLomakirja));
    }
    
    @Test
    public void getBooksByNameReturnsNullUponDatabaseError() {
        deleteDatabase();
        assertNull(db.getBooksByName("Joulupukin lomakirja"));
    }

    @Test
    public void existingBookCanBeFoundByYear() {
        assertTrue(db.getBooksByYear(2020).contains(joulupukinLomakirja));
    }
    
    @Test
    public void getBooksByYearReturnsNullUponDatabaseError() {
        deleteDatabase();
        assertNull(db.getBooksByYear(2020));
    }

    @Test
    public void existingLinkCanBeFoundByName() {
        assertTrue(db.getLinksByName("Tietorakenteet ja algoritmit").contains(tira));
    }
    
    @Test
    public void getLinksByNameReturnsNullUponDatabaseError() {
        deleteDatabase();
        assertNull(db.getLinksByName("Tietorakenteet ja algoritmit"));
    }
    
    @Test
    public void afterAddingNewValidBookItCanBeFoundByAuthor() {
        db.addBook(kuinkaPuutKasvavat);
        assertTrue(db.getBooksByAuthor("Saku Tuominen").contains(kuinkaPuutKasvavat));
    }
    
    @Test
    public void getBooksByAuthorReturnsNullUponDatabaseError() {
        db.addBook(kuinkaPuutKasvavat);
        deleteDatabase();
        assertNull(db.getBooksByAuthor("Saku Tuominen"));
    }

    @Test
    public void afterAddingNewValidBookItCanBeFoundByISBN() {
        db.addBook(kuinkaPuutKasvavat);
        assertEquals(kuinkaPuutKasvavat, (db.getBookByIsbn("978-951-1-36427-6")));
    }
    
    @Test
    public void getBookByISBNReturnsNullUponDatabaseError() {
        db.addBook(kuinkaPuutKasvavat);
        deleteDatabase();
        assertNull(db.getBookByIsbn("978-951-1-36427-6"));
    }

    @Test
    public void bookCannotBeFoundByAuthorBeforeItIsAdded() {
        db.addBook(pizze);
        assertFalse(db.getBooksByAuthor("Saku Tuominen").contains(kuinkaPuutKasvavat));
    }

    @Test
    public void bookCannotBeFoundByISBNBeforeItIsAdded() {
        assertEquals(null, db.getBookByIsbn("978-951-1-36427-6"));
    }

    @Test
    public void addingExistingBookWithoutISBNReturnsTrue() {
        pizze.setIsbn(null);
        assertTrue(db.addBook(pizze));
        assertTrue(db.addBook(pizze));
    }

    @Test
    public void nonExistingBookCannotBeFoundByName() {
        ArrayList<Book> results = db.getBooksByYear(pizze.getYear());
        if (results != null) {
            assertFalse(results.contains(pizze));
        }
    }

    @Test
    public void nonExistingBookCannotBeFoundByYear() {
        ArrayList<Book> results = db.getBooksByYear(pizze.getYear());
        if (results != null) {
            assertFalse(results.contains(pizze));
        }
    }

    @Test
    public void nonExistingLinkCannotBeFoundByName() {
        assertEquals(null, db.getLinksByName(ohtu.getName()));
    }

    @Test
    public void removingExistingBookReturnsTrue() {
        assertTrue(db.removeBook(joulupukinLomakirja.getIsbn(), joulupukinLomakirja.getName()));
    }

    @Test
    public void removingExistingLinkReturnsTrue() {
        assertTrue(db.removeLink(tira.getAddress()));
    }

    @Test
    public void afterRemovingExistingBookItCannotBeFoundByName() {
        db.removeBook(joulupukinLomakirja.getIsbn(), joulupukinLomakirja.getName());
        assertNull(db.getBooksByName(joulupukinLomakirja.getName()));
    }

    @Test
    public void afterRemovingExistingBookItCannotBeFoundByAuthor() {
        db.removeBook(joulupukinLomakirja.getIsbn(), joulupukinLomakirja.getName());
        assertNull(db.getBooksByAuthor(joulupukinLomakirja.getAuthor()));
    }

    @Test
    public void afterRemovingExistingBookItCannotBeFoundByISBN() {
        db.removeBook(joulupukinLomakirja.getIsbn(), joulupukinLomakirja.getName());
        assertNull(db.getBookByIsbn(joulupukinLomakirja.getIsbn()));
    }

    @Test
    public void afterRemovingExistingLinkItCannotBeFoundByName() {
        db.removeLink(tira.getAddress());
        assertNull(db.getLinksByName(tira.getName()));
    }

    @Test
    public void afterAddingTwoBooksWithoutISBNThoseCanBeAddedAndFoundByName() {
        db.addBook(tirakirja);
        db.addBook(scrumbook);

        assertTrue(db.getBooksByName(tirakirja.getName()).contains(tirakirja));
        assertTrue(db.getBooksByName(scrumbook.getName()).contains(scrumbook));
    }

    @Test
    public void afterAddingTwoBooksWithoutISBNAndRemovingOtherItCannotBeFoundByName() {
        db.addBook(tirakirja);
        db.addBook(scrumbook);

        db.removeBook(tirakirja.getIsbn(), tirakirja.getName());

        assertNull(db.getBooksByName(tirakirja.getName()));
        assertTrue(db.getBooksByName(scrumbook.getName()).contains(scrumbook));
    }

    @Test
    public void addingValidPodcastReturnsTrue() {
        assertTrue(db.addPodcast(sodoma));
    }

    @Test
    public void existingPodcastCanBeFoundByName() {
        db.addPodcast(sodoma);
        assertTrue(db.getPodcastsByName("Radio Sodoma").contains(sodoma));
    }
    
    @Test
    public void getPodcastByNameReturnsNullUponDatabaseError() {
        db.addPodcast(sodoma);
        deleteDatabase();
        assertNull(db.getPodcastsByName("Radio Sodoma"));
    }

    @Test
    public void removingExistingPodcastReturnsTrue() {
        db.addPodcast(sodoma);
        assertTrue(db.removePodcast("Radio Sodoma"));
    }

    @Test
    public void afterRemovingExistingPodcastItCannotBeFoundByName() {
        db.addPodcast(sodoma);
        db.removePodcast("Radio Sodoma");

        ArrayList<Podcast> results = db.getPodcastsByName("Radio Sodoma");
        if (results != null) {
            assertFalse(results.contains(sodoma));
        }
    }

    public void getAllBooksReturnsCorrectAmountOfBooks() {
        assertTrue(db.getAllBooks().size() == 1);
        db.addBook(pizze);
        assertTrue(db.getAllBooks().size() == 2);
        db.addBook(kuinkaPuutKasvavat);
        assertTrue(db.getAllBooks().size() == 3);
    }

    @Test
    public void getAllBooksReturnsNullIfListIsEmpty() {
        db.removeBook(joulupukinLomakirja.getIsbn(), joulupukinLomakirja.getName());
        assertNull(db.getAllBooks());
    }
    
    @Test
    public void getAllBooksReturnsNullUponDatabaseError() {
        db.addBook(pizze);
        db.addBook(kuinkaPuutKasvavat);
        deleteDatabase();
        
        assertNull(db.getAllBooks());
    }

    @Test
    public void getAllBooksReturnsOnlyExistingBooks() {
        assertTrue(db.getAllBooks().contains(joulupukinLomakirja));
        assertFalse(db.getAllBooks().contains(pizze));
    }

    @Test
    public void getAllLinksReturnsCorrectAmountOfLinks() {
        assertTrue(db.getAllLinks().size() == 1);
        db.addLink(ohtu);
        assertTrue(db.getAllLinks().size() == 2);
    }

    @Test
    public void getAllLinksReturnsNullIfListIsEmpty() {
        db.removeLink(tira.getAddress());
        assertNull(db.getAllLinks());
    }
    
    @Test
    public void getAllLinksReturnsNullUponDatabaseError() {
        db.addLink(ohtu);
        deleteDatabase();
        
        assertNull(db.getAllLinks());
    }

    @Test
    public void getAllLinksReturnsOnlyExistingLinks() {
        assertTrue(db.getAllLinks().contains(tira));
        assertFalse(db.getAllLinks().contains(ohtu));
    }

    @Test
    public void getAllPodcastsReturnsOnlyExistingPodcasts() {
        assertTrue(db.getAllPodcasts().contains(bbc));
        assertFalse(db.getAllPodcasts().contains(pmh));
    }

    @Test
    public void getAllPodcastsReturnsCorrectAmountOfPodcasts() {
        assertTrue(db.getAllPodcasts().size() == 1);
        db.addPodcast(pmh);
        assertTrue(db.getAllPodcasts().size() == 2);
    }

    @Test
    public void getAllPodcastsReturnsNullIfListIsEmpty() {
        db.removePodcast(bbc.getName());
        assertNull(db.getAllPodcasts());
    }
    
    @Test
    public void getAllPodcastsReturnsNullUponDatabaseError() {
        db.addPodcast(pmh);
        deleteDatabase();
        
        assertNull(db.getAllPodcasts());
    }
    
    @Test
    public void attemptToAddBookYieldsErrorMessageUponDatabaseError() {
        deleteDatabase();
        
        db.addBook(tirakirja);
        
        this.errMsgs.add(testOut.toString());
                
        assertTrue(errMsgs.contains("Virhe tietokannan käsittelyssä: org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: Books)\n"));
    }
    
    @Test
    public void attemptToRemoveBookYieldsErrorMessageUponDatabaseError() {
        deleteDatabase();
        
        db.removeBook(joulupukinLomakirja.getIsbn(), joulupukinLomakirja.getName());
        
        this.errMsgs.add(testOut.toString());
        
        assertTrue(errMsgs.contains("Virhe tietokannan käsittelyssä: org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: Books)\n"));
    }
    
    @Test
    public void attemptToAddLinkYieldsErrorMessageUponDatabaseError() {
        deleteDatabase();
        
        db.addLink(tira);
        
        this.errMsgs.add(testOut.toString());
                
        assertTrue(errMsgs.contains("Virhe tietokannan käsittelyssä: 1\n"));
    }
    
    @Test
    public void attemptToRemoveLinkYieldsErrorMessageUponDatabaseError() {
        deleteDatabase();
        
        db.removeLink(tira.getAddress());
        
        this.errMsgs.add(testOut.toString());
        
        assertTrue(errMsgs.contains("Virhe tietokannan käsittelyssä: org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: Links)\n"));
    }
    
    @Test
    public void attemptToAddPodcastYieldsErrorMessageUponDatabaseError() {
        deleteDatabase();
        
        db.addPodcast(sodoma);
        
        this.errMsgs.add(testOut.toString());
                
        assertTrue(errMsgs.contains("Virhe tietokannan käsittelyssä: org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: Podcasts)\n"));
    }
    
    @Test
    public void attemptToRemovePodcastYieldsErrorMessageUponDatabaseError() {
        deleteDatabase();
        
        db.removePodcast(bbc.getName());
        
        this.errMsgs.add(testOut.toString());
                
        assertTrue(errMsgs.contains("Virhe tietokannan käsittelyssä: org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such table: Podcasts)\n"));
    }

    private void deleteDatabase() {
        try {
            File db = new File(testDbName);
            db.delete();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
