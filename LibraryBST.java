class LibraryBST{
    private BNode root;

    public LibraryBST() {
        root= null;
    }
    //                 الاضافة 
    private  BNode addRecursive(BNode current, Book book){
        if (current ==null){
            return new BNode(book);
        }
        if(book.ISBN < current.book.ISBN){
            current.left = addRecursive(current.left,book);
        }
        if(book.ISBN > current.book.ISBN){
            current.right = addRecursive(current.right,book);
        }
        else{
                System.out.println("Book with this ISBN already exists");
            }
        return current;
   }

   public void insertBook(Book book){
       root = addRecursive (root, book);
       System.out.println("Add Book:"+ book.titel);
   }
   
   //       البحث                  
   public Book searchBook(long ISBN) {
        BNode result = searchRecursive(root, ISBN);
        if (result != null) {
            return result.book;
        }
        return null;
    }

    private BNode searchRecursive(BNode current, long ISBN) {
        if (current == null || current.book.ISBN == ISBN) {
            return current;
        }
        if (ISBN < current.book.ISBN) {
            return searchRecursive(current.left, ISBN);
        }
        return searchRecursive(current.right, ISBN);
    }
    // بيفيد بالواجهات            
    public void displayAllBooks() {
        System.out.println("    All Books (Sorted by ISBN)    ");
        inorderTraversal(root);
        System.out.println("--------------------------------------");
    }

    private void inorderTraversal(BNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.println(node.book);
            inorderTraversal(node.right);
        }
    }
    // الحذف                 
    public void deleteBook(long ISBN){
    root = deleteRecursive(root, ISBN);
        System.out.println("Book with ISBN " + ISBN + " deleted if existed");
    }

    private BNode deleteRecursive(BNode current, long ISBN) {
        if (current == null) {
            return null;
        }
        if (ISBN < current.book.ISBN) {
            current.left = deleteRecursive(current.left, ISBN);
        } else if (ISBN > current.book.ISBN) {
            current.right = deleteRecursive(current.right, ISBN);
        } else if (ISBN == current.book.ISBN) {
            
            if (current.left == null && current.right == null) {
                return null;
            }
            
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            
            long smallestIsbn = findSmallest(current.right);
            current.book = findBookByIsbn(current.right, smallestIsbn).book;
            current.right = deleteRecursive(current.right, smallestIsbn);
            return current;
            }
        return current;
        }

    
    private long findSmallest(BNode root) {
        return root.left == null ? root.book.ISBN : findSmallest(root.left);
    }
    
    private BNode findBookByIsbn(BNode node, long ISBN) {
        if (node == null || node.book.ISBN == ISBN) return node;
        return ISBN < node.book.ISBN ? findBookByIsbn(node.left, ISBN) : findBookByIsbn(node.right, ISBN);
    }
    // تعديل عدد النسخ                   
   public boolean updateCopies(long ISBN, int newCopies) {
        Book book = searchBook(ISBN);
        if (book != null) {
            if (newCopies >= 0) {
                book.copies = newCopies;
                System.out.println("Copies updated for ISBN " + ISBN +"  :  "+ newCopies);
                return true;
            } else {
                System.out.println("Invalid number of copies");
                return false;
            }
        }
        System.out.println("Book with ISBN " + ISBN + "not found");
        return false;
    }


}