package Scrumbags.logic;

import java.util.Objects;

public class Podcast {
    String name;
    String publisher;
    String url;
    String rss;
    
    public Podcast(String name, String publisher, String url, String rss){
        this.name = name;
        this.publisher = publisher;
        this.url = url;
        this.rss = rss;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getrss() {
        return rss;
    }

    public void setrss(String rss) {
        this.rss = rss;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Podcast other = (Podcast) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.rss, other.rss)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "\nNimi: " + this.name 
                + "\nJulkaisija: " + this.publisher
                + "\n url: " + this.url
                + "\n rss: " + this.rss;
    }
    
    
}
