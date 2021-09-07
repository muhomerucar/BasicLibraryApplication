import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<BookList> lists = new ArrayList<>();
    public static List<Book> stock = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Welcome to the Library Application!");


        boolean flag = true;

        while (flag){
            System.out.println("Instructions...\n" +
                    "1 - To create new book\n" +
                    "2 - To create new book list\n" +
                    "3 - To list all stock books in the books list\n" +
                    "4 - To list all available book list in the lists\n" +
                    "5 - To delete a book from stock list\n" +
                    "6 - To move a book from stock list to another list...\n" +
                    "7 - To move a book from list to another list...\n" +
                    "8 - To create file to note all books in a list...\n" +
                    "9 - To select list and list all books in it...\n" +
                    "0 - To exit from the app...");

            Scanner scanner = new Scanner(System.in);
            try {
                int instruction = scanner.nextInt();
                switch (instruction){
                    case 1:
                        createNewBook();
                        break;
                    case 2:
                        createNewBookList();
                        break;
                    case 3:
                        listStockBooks();
                        break;
                    case 4:
                        listAllBookLists();
                        break;
                    case 5:
                        try {
                            deleteBookFromStockList();
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("You have entered invalid number!");
                        }
                        break;
                    case 6:
                        try {
                            moveBookFromStockListToOtherList();
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("You have entered invalid number!");
                        }
                        break;
                    case 7:
                        try {
                            moveBookFromListToAnotherList();
                        } catch (IndexOutOfBoundsException e){
                            System.out.println("You have entered invalid number!");
                        }
                        break;
                    case 8:
                        saveList(select_A_BookList());
                        break;
                    case 9:
                        listAllBooksInSelectedList();
                        break;
                    case 0:
                        flag = false;
                        System.out.println("Exiting from the Library App...");
                        break;
                    default:
                        System.out.println("Been waiting for user enter a valid instruction...");
                        break;
                }

            }catch (InputMismatchException e){
                System.out.println("You have entered invalid instruction!");
            }


        }
    }


    public static Book createNewBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter name of the book...");
        String name = scanner.nextLine();
        System.out.println("Please enter author of the book...");
        String author = scanner.nextLine();
        System.out.println("Please enter language of the book...");
        String language = scanner.nextLine();
        System.out.println("Please enter publisher of the book...");
        String publisher = scanner.nextLine();
        System.out.println("Please enter print length of the book...");
        int printLength = scanner.nextInt();
        System.out.println("Please enter publication year of the book...");
        int publicationYear = scanner.nextInt();


        Book book = new Book(name,author,language,publisher,printLength,publicationYear);
        stock.add(book);
        System.out.println(book + " has been created and it has been added to stock list");
        return book;
    }

    public static BookList createNewBookList(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a name for new book list...");
        String name = scanner.nextLine();
        BookList bookList = new BookList(name);
        System.out.println("You have created new book list '" +name + "' and it has been added to lists.");
        lists.add(bookList);
        return bookList;
    }

    public static void listStockBooks(){
        if(stock.size() == 0){
            System.out.println("There is no book in stock\n");
        }
        else if(stock.size() > 0){
            int i = 1;
            for(Book book : stock){
                System.out.println(i + ". " +book);
                i++;
            }
        }

    }

    public static void listAllBookLists(){
        int i = 1;
        for(BookList bookList : lists){
            System.out.println(i + ". " +bookList);
            i++;
        }
    }

    public static void deleteBookFromStockList(){
        listStockBooks();
        System.out.println("Please enter the book number to delete book from stock list...");
        Scanner scanner = new Scanner(System.in);
        int bookNumber = scanner.nextInt() - 1;
        System.out.println(stock.get(bookNumber) + " has been deleted.");
        stock.remove(bookNumber);
    }

    public static void moveBookFromStockListToOtherList(){
        listStockBooks();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a book number to select...");
        int bookNumber = scanner.nextInt() - 1;
        Book chosenBook = stock.get(bookNumber);
        System.out.println(chosenBook.name + " has been selected...");
        listAllBookLists();
        System.out.println("Please enter number of the list to select list for adding the book...");
        int chosenListNumber = scanner.nextInt() - 1;
        BookList chosenList = lists.get(chosenListNumber);
        System.out.println(chosenList.name + " list is selected...");
        chosenList.addBook(chosenBook);
        stock.remove(chosenBook);
        System.out.println(chosenBook.name + " is in the '" +chosenList.name + "' list.");
    }

    public static void moveBookFromListToAnotherList(){
        listAllBookLists();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of the list to select...");
        int chosenListNumber = scanner.nextInt() - 1;
        BookList chosenList = lists.get(chosenListNumber);
        System.out.println(chosenList.name + " has been selected.");
        chosenList.listBooks();
        System.out.println("Please enter number of the book to select...");
        int chosenBookNumber = scanner.nextInt() - 1;
        Book chosenBook = chosenList.getBook(chosenBookNumber);
        System.out.println(chosenBook.name + " has been selected...");
        listAllBookLists();
        System.out.println("Please enter number of the list to move selected book to...");
        int anotherChosenListNumber = scanner.nextInt() - 1;
        BookList anotherChosenList = lists.get(anotherChosenListNumber);
        System.out.println(anotherChosenList + " has selected.");
        anotherChosenList.addBook(chosenBook);
        chosenList.deleteBook(chosenBook);
        System.out.println(chosenBook + " has moved to '" +anotherChosenList.name + "' and deleted from '" +chosenList.name + "'");

    }

    public static BookList select_A_BookList(){
        Scanner scanner = new Scanner(System.in);
        listAllBookLists();
        System.out.println("Please enter a number of list to select the list");
        int numberOfList = scanner.nextInt();
        BookList selectedBookList = lists.get(numberOfList-1);
        return selectedBookList;
    }

    public static void saveList(BookList bookList){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("SaveFile.txt"));
            writer.write(bookList.getBookNames());
            System.out.println("File is created successfully with the content ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listAllBooksInSelectedList(){
        BookList selectedBookList = select_A_BookList();
        for(int i = 0; i < selectedBookList.getSizeofTheList(); i++){
            System.out.println(selectedBookList.getBook(i));
        }
    }
}
