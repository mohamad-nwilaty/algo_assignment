public class Main {
    public static void main(String[] args) {
        Book b1 = new Book(200 , "harry potter" , "jk rowling");
        Book b2 = new Book(110 , "GOT" , "someone");
        Book b3 = new Book(2 , "lord of weebs" , "someone else");
        LibraryBST btree = new LibraryBST();
        btree.insertBook(b1);
        btree.insertBook(b2);
        btree.insertBook(b3);
        btree.displayAllBooks();
        btree.deleteBook(110);
        btree.displayAllBooks();
        // test
        
    }
}
