import java.util.ArrayList;

public class BookList {
    public String name;
    public ArrayList<Book> listOfBooks;

    public BookList(String name) {
        this.name = name;
        this.listOfBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getListOfBooks() {
        return listOfBooks;
    }

    @Override
    public String toString() {
        return "BookList {" +
                "Name='" + name + '\'' +
                '}';
    }

    public void addBook(Book book){
        if(this.listOfBooks.contains(book)){
            System.out.println("The book you have enter is already in the '" +this.name + "' list.");
        }else if(!listOfBooks.contains(book) && (book != null)){
            this.listOfBooks.add(book);
            System.out.println(book + " has been added to the '" +this.name + "' list.");
        }
    }

    public void listBooks(){
        for(Book book : this.listOfBooks){
            System.out.println(book.name);
        }
    }

    public Book getBook(int number){
        Book book = this.listOfBooks.get(number);
        return book;
    }

    public void deleteBook(Book book){
        this.listOfBooks.remove(book);
        System.out.println(book + " has been deleted from the '" +this.name + "' list.");
    }

    public String getBookNames(){
        String listName = "Name of the list: '" +this.name + "'";
        String logFile = " ";
        for(Book book : this.listOfBooks){
            logFile += "Book name: '" +book.name + "' ";
        }
        return listName + logFile;
    }

    public int getSizeofTheList(){
        return this.listOfBooks.size();
    }

}
