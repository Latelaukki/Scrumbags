package Scrumbags.logic;

import Scrumbags.database.Dao;
import java.util.ArrayList;

public class Service {

    private Dao database;

    // Create database:
    public Service(Dao database) {
        this.database = database;
    }

    public boolean addBook(String name, String author, String isbn, int pages, int year) {
        Book book = new Book(name, author, isbn, pages, year);
        if (bookIsbnExists(isbn)) {
            System.out.println("Virhe: ISBN-koodi on jo käytössä.");
            return false;
        }
        return this.database.addBook(book);
    }

    public boolean addLink(String name, String address) {
        ArrayList<Link> checkLinks = database.getAllLinks();

        if (checkLinks != null) {
            if (checkLinks.stream().anyMatch(l -> l.getAddress().equals(address))) {
                System.out.println("Virhe: URL-osoite on jo käytössä.");
                return false;
            }
        }

        Link link = new Link(name, address);
        return this.database.addLink(link);
    }

    public boolean addPodcast(String name, String publisher, String url, String rss) {
        if (getPodcastsByName(name) != null) {
            System.out.println("Virhe: Podcastin nimi on jo käytössä.");
            return false;
        }

        Podcast podcast = new Podcast(name, publisher, url, rss);
        return this.database.addPodcast(podcast);
    }

    public ArrayList<Book> getBooks(String search, String by) {
        if (by.equals("1")) {
            return getBooksByAuthor(search);
        } else if (by.equals("2")) {
            return getBooksByName(search);
        } else if (by.equals("3")) {
            return getBooksByYear(Integer.parseInt(search));
        } else if (by.equals("4")) {
            return getBookByIsbn(search);
        }
        return null;
    }

    public boolean removeBook(ArrayList<Book> booklist, String id) {
        String isbn = "";
        String name = "";
        int i = 1;
        for (Book b : booklist) {
            if (i == Integer.parseInt(id)) {
                isbn = b.getIsbn();
                name = b.getName();
            }
            i++;
        }
        return this.database.removeBook(isbn, name);
    }

    public boolean removeLink(ArrayList<Link> linklist, String id) {
        String url = "";
        int i = 1;
        for (Link l : linklist) {
            if (i == Integer.parseInt(id)) {
                url = l.address;
                i++;
            }
        }
        System.out.println("url: " + url);
        return this.database.removeLink(url);
    }

    public boolean removePodcast(ArrayList<Podcast> podlist, String id) {
        String name = "";
        int i = 1;
        for (Podcast p: podlist) {
            if (i == Integer.parseInt(id)) {
                name = p.getName();
            }
            i++;
        }
        System.out.println("name: " + name);
        return this.database.removePodcast(name);
    }
    
    public ArrayList<Book> getBooksByAuthor(String author) {
        return this.database.getBooksByAuthor(author);
    }

    public ArrayList<Link> getLinksByName(String name) {
        return this.database.getLinksByName(name);
    }

    public ArrayList<Book> getBooksByName(String name) {
        return database.getBooksByName(name);
    }

    public ArrayList<Book> getBooksByYear(int year) {
        return database.getBooksByYear(year);
    }

    public ArrayList<Book> getBookByIsbn(String isbn) {
        if (bookIsbnExists(isbn)) {
            ArrayList<Book> result = new ArrayList<>();

            Book book = database.getBookByIsbn(isbn);// ISBN-haku palauttaa yhden tuloksen.
            result.add(book);

            return result;
        }
        return null;
    }

    public ArrayList<Podcast> getPodcastsByName(String name) {
        return database.getPodcastsByName(name);
    }

    public boolean bookIsbnExists(String isbn) {
        return database.getBookByIsbn(isbn) != null;
    }

    public boolean bookNameExists(String name) {
        return database.getBooksByName(name) != null;
    }

    public ArrayList<Book> getAllBooks() {
        return database.getAllBooks();
    }

    public ArrayList<Link> getAllLinks() {
        return database.getAllLinks();
    }

    public ArrayList<Podcast> getAllPodcasts() {
        return database.getAllPodcasts();
    }
}
