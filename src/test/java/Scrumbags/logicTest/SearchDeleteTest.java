package Scrumbags.logicTest;

import Scrumbags.logic.*;
import Scrumbags.database.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchDeleteTest {
    Service service;
    
    public SearchDeleteTest() {
        this.service = new Service(new DatabaseMock());
    }
    
    @Test
    public void searchReturnsRightBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getBooks("Taavi", "1");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ABC") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 2005) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void bookAmountIsRight() {
        this.service.addBook("Aapinen", "Nobody", "---", -1, -1);
        this.service.addBook("Bk", "Nobody", "---", -1, -1);
        this.service.addBook("MAGA", "Nobody", "---", -1, -1);
        assertEquals(3, this.service.getBooksByAuthor("Nobody").size());
    }
    
    
    @Test
    public void isbnSearchReturnsRightBook() {
        this.service.addBook("ASD", "WASD", "123-14", 50, 13);
        ArrayList<Book> booklist = this.service.getBookByIsbn("123-14");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ASD") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 13) {
                found = true;
            }
        }
        assertEquals(true, found);
    }
    
    @Test
    public void isbnSearchReturnsNullIfIsbnIsEmpty() {
        this.service.addBook("Marmeladit", "A. Happonen", "---", 50, 2005);
        ArrayList<Book> booklist = this.service.getBookByIsbn("---");
        assertNull(booklist);
    }

    @Test
    public void isbnSearchFailsIfBookNotAddedYet() {
        assertNull(this.service.getBookByIsbn("123-14"));
    }
    
    @Test
    public void yearSearchReturnsRightBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getBooks("2005", "3");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ABC") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 2005) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void yearSearchFailsIfBookNotAddedYet() {
        assertNull(this.service.getBooksByYear(1044));
    }
    
    @Test
    public void yearSearchFailsIfYearDoesNotExistsInAnyBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        this.service.addBook("nimi", "kirjailija", "isbn1234", 123, 123);
        assertNull(this.service.getBooksByYear(2000));
    }
    
    @Test
    public void nameSearchReturnsRightBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getBooks("ABC", "2");
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ABC") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 2005) {
                found = true;
            }
        }
        assertEquals(true, found);
    }
    
    @Test
    public void nameSearchReturnsRightLink() {
        this.service.addLink("Kotikokki", "http://www.kotikokki.fi");
        ArrayList<Link> linklist = this.service.getLinksByName("Kotikokki");
        boolean found = false;
        for (Link l: linklist) {
            if (l.getName().equals("Kotikokki") && l.getAddress().equals("http://www.kotikokki.fi")) {
                found = true;
            }
        }
        assertEquals(true, found);
    }
    
    @Test
    public void bookmarkAmountIsRight() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        this.service.addLink("Google", "http://www.google.com");
        this.service.addLink("Omasivu", "http://www.urpo.fi");
        int linksCount = this.service.getAllLinks().size();
        int booksCount = this.service.getAllBooks().size();
        int podcastCount = this.service.getAllPodcasts().size();
        assertTrue(linksCount+booksCount+podcastCount == 3);
    }
    
    @Test
    public void listAllBooksReturnsAddedBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getAllBooks();
        boolean found = false;
        for (Book b: booklist) {
            if (b.getName().equals("ABC") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 2005) {
                found = true;
            }
        }
        assertEquals(true, found);
    }
    
    @Test
    public void listAllLinksReturnsAddedLink() {
        this.service.addLink("Google", "http://www.google.com");
        ArrayList<Link> linklist = this.service.getAllLinks();
        boolean found = false;
        for (Link l: linklist) {
            if (l.getName().equals("Google") && l.getAddress().equals("http://www.google.com")) {
                found = true;
            }
        }
        assertEquals(true, found);
    }
    
    @Test
    public void listAllPodcastsReturnsAddedPodcast() {
        this.service.addPodcast("Historiavartti", "YLE", "http://yle.fi/podit/historiavartti", "---");
        ArrayList<Podcast> castlist = this.service.getAllPodcasts();
        boolean found = false;
        for (Podcast c: castlist) {
            if (c.getName().equals("Historiavartti") && c.getPublisher().equals("YLE") && c.getUrl().equals("http://yle.fi/podit/historiavartti")) {
                found = true;
            }
        }
        assertEquals(true, found);
    }

    @Test
    public void bookSearchIsNotNull() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getBooks("Taavi", "1");
        assertNotNull(booklist);
    }

    @Test
    public void bookSearchIsNull() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        boolean found = this.service.bookNameExists("ABCD");
        assertEquals(false, found);
    }    
    
    @Test
    public void deleteBookRemovesBook() {
        this.service.addBook("ABC", "Taavi", "123-14", 50, 2005);
        ArrayList<Book> booklist = this.service.getAllBooks();
        this.service.removeBook(booklist, "1");
        ArrayList<Book> booklist2 = this.service.getAllBooks();
        boolean found = false;
        for (Book b: booklist2) {
            if (b.getName().equals("ABC") && b.getIsbn().equals("123-14") && b.getPages() == 50 && b.getYear() == 2005) {
                found = true;
            }
        }
        assertFalse(found);
    }
    
    @Test
    public void deleteLinkRemovesLink() {
        this.service.addLink("Kotikokki", "http://www.kotikokki.fi");
        ArrayList<Link> linklist = this.service.getAllLinks();
        this.service.removeLink(linklist, "1");
        ArrayList<Link> linklist2 = this.service.getAllLinks();
        boolean found = false;
        for (Link l : linklist2) {
            if (l.getName().equals("Kotikokki") && l.getAddress().equals("http://www.kotikokki.fi")) {
                found =true;
            }
        }
        assertFalse(found);
    }
    
    @Test
    public void deletePodcastRemovesPodcast() {
        this.service.addPodcast("Historiavartti", "YLE", "http://yle.fi/podit/historiavartti", "---");
        ArrayList<Podcast> castlist = this.service.getAllPodcasts();
        this.service.removePodcast(castlist,"1");
        ArrayList<Podcast> castlist2 = this.service.getAllPodcasts();
        boolean found = false;
        for (Podcast c: castlist2) {
            if (c.getName().equals("Historiavartti") && c.getPublisher().equals("YLE") && c.getUrl().equals("http://yle.fi/podit/historiavartti")) {
                found = true;
            }
        }
        assertFalse(found);
    }

}
