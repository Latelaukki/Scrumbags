package Scrumbags.database;

import Scrumbags.logic.Book;
import Scrumbags.logic.Link;
import Scrumbags.logic.Podcast;
import java.util.ArrayList;

public class DatabaseMock implements Dao {

    private ArrayList<Book> books;
    private ArrayList<Link> links;
    private ArrayList<Podcast> podcasts;

    public DatabaseMock() {
        this.books = new ArrayList<>();
        this.links = new ArrayList<>();
        this.podcasts = new ArrayList<>();
    }
   
    @Override
    public boolean addBook(Book book) {
        this.books.add(book);
        return true;
    }

    @Override
    public boolean addLink(Link link) {
        this.links.add(link);
        return true;
    }
    
    @Override
    public boolean removeBook(String isbn, String name) {
        Book removable = null;
        for (Book b: this.books) {
            if (b.getIsbn().equals(isbn) && b.getName().equals(name)) {
                removable = b;
            }
        }
        if (removable != null){
            books.remove(removable);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeLink(String url) {
        Link removable = null;
        for (Link l: this.links) {
            if (l.getAddress().equals(url)) {
                removable = l;
            }
        }
        if (removable != null){
            links.remove(removable);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePodcast(String name) {
        Podcast removable = null;
        for (Podcast p: this.podcasts) {
            if (p.getName().equals(name)) {
                removable = p;
            }
        }
        if (removable != null){
            podcasts.remove(removable);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> booklist = new ArrayList<>();
        for (Book b: this.books) {
            if (b.getAuthor().equals(author)) {
                booklist.add(b);
            }
        }
        if (booklist.isEmpty()){
            return null;
        }
        return booklist;
    }

    @Override
    public ArrayList<Link> getLinksByName(String name) {
        ArrayList<Link>  linklist = new ArrayList<>();
        for (Link l: this.links) {
            if (l.getName().equals(name)) {
                linklist.add(l);
            }
        }
        return linklist;
    }

    @Override
    public ArrayList<Book> getBooksByName(String name) {
        ArrayList<Book> booklist = new ArrayList<>();
        for (Book b: this.books) {
            if (b.getName().equals(name)) {
                booklist.add(b);
            }
        }
        if (booklist.isEmpty()){
            return null;
        }
        return booklist;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        for (Book b: this.books) {
            if(b.getIsbn().equals("---") || b.getIsbn().equals("")) {
                continue;
            }
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Book> getBooksByYear(int year) {
        ArrayList<Book> booklist = new ArrayList<>();
        for (Book b: this.books) {
            if (b.getYear() == year) {
                booklist.add(b);
            }
        }
        if (booklist.isEmpty()) {
            return null;
        }
        return booklist;
    }

    @Override
    public boolean addPodcast(Podcast podcast) {
        podcasts.add(podcast);
        return true;
    }

    @Override
    public ArrayList<Podcast> getPodcastsByName(String name) {
        ArrayList<Podcast> podcastlist = new ArrayList<>();
        for (Podcast p: this.podcasts) {
            if (p.getName().equals(name)) {
                podcastlist.add(p);
            }
        }
        if (podcastlist.isEmpty()) {
            return null;
        }
        return podcastlist;
    }

    @Override
    public ArrayList<Link> getAllLinks() {
        return this.links;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return this.books;
    }

    @Override
    public ArrayList<Podcast> getAllPodcasts() {
        return this.podcasts;
    }
}