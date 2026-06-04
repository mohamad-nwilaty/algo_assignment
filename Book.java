public class Book {
    long ISBN;
   
   String titel;
   String author;
   int copies;
   boolean isAvailable;

        public Book(long ISBN, String titel, String author, boolean isAvailable,int copies) {
            this.ISBN = ISBN;
            this.titel = titel;
            this.author = author;
            this.copies= copies;
            this.isAvailable = true;
        }

        public Book(long ISBN, String titel, String author, boolean isAvailable) {
            this.ISBN = ISBN;
            this.titel = titel;
            this.author = author;
            this.isAvailable = isAvailable;
        }

        public Book(long ISBN, String titel, String author) {
            this.ISBN = ISBN;
            this.titel = titel;
            this.author = author;
        }

        @Override
        public String toString() {
            return "ISBN=" + ISBN + ", titel=" + titel + ", author=" + author + ", Available=" + (isAvailable ? "Yes" : "No");
            
            
            }
}
