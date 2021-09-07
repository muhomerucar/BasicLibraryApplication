import java.util.Objects;

public class Book {
    public String name;
    public String author;
    public String language;
    public String publisher;
    public int printLength;
    public int publicationYear;

    public Book(String name, String author, String language, String publisher, int printLength, int publicationYear) {
        this.name = name;
        this.author = author;
        this.language = language;
        this.publisher = publisher;
        this.printLength = printLength;
        this.publicationYear = publicationYear;
    }


    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPrintLength() {
        return printLength;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "Book: " + this.name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.name.toLowerCase());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.name == obj){
            return true;
        }
        if((obj == null) || (getClass() != obj.getClass())){
            return false;
        }
        Book book = (Book) obj;
        return Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }
}
