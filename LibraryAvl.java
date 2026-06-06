class LibraryAvl{
    private Node root;

    public LibraryAvl() {
        root= null;
    }

    private int getNodeHeight(Node node){
        return node == null ? 0 : node.height ;
    }

    private void updateNodeHeight(Node node){
        if(node != null){
            node.height = 1 + Math.max(getNodeHeight(node.left), getNodeHeight(node.right));
        }  
    }

    private int getBalance(Node node){
        return node == null ? 0 : getNodeHeight(node.left) - getNodeHeight(node.right) ;
    }

    private Node rightRotate(Node x){
        Node y = x.left ;
        Node branch = y.right ;

        y.right = x ; // rotate
        x.left = branch ;

        updateNodeHeight(x);
        updateNodeHeight(y);
        return y ; // return the new root

    }

    private Node leftRotate(Node x){
        Node y = x.right;
        Node branch = y.left ;

        y.left = x ;
        x.right = branch;

        updateNodeHeight(x);
        updateNodeHeight(y);
        return y ;
    }
    
    //                 الاضافة 
    private  Node addRecursive(Node current, Book book){
        if (current ==null){
            return new Node(book);
        }
        if(book.ISBN < current.book.ISBN){
            current.left = addRecursive(current.left,book);
        }
        else if(book.ISBN > current.book.ISBN){
            current.right = addRecursive(current.right,book);
        }
        else{
                System.out.println("Book with this ISBN already exists");
                return current;
            }
        
            updateNodeHeight(current);
            int balance = getBalance(current);

            if(balance > 1 && book.ISBN < current.left.book.ISBN){ // left - left
                return rightRotate(current);
            }
            if(balance > 1 && book.ISBN > current.left.book.ISBN){ //left - right
                current.left = leftRotate(current.left);
                return rightRotate(current);
            }
            if(balance < -1 && book.ISBN > current.right.book.ISBN ){ // right - right
                return leftRotate(current);
            }
            if(balance < -1 && book.ISBN < current.right.book.ISBN ){ // right - left
                current.right = rightRotate(current.right);
                return leftRotate(current);
            }
            return current; // if node is balanced return it check the rest in the call chain
   }

   public void insertBook(Book book){
       root = addRecursive (root, book);
       System.out.println("Add Book:"+ book.titel);
   }
   
   //       البحث                  
   public Book searchBook(long ISBN) {
        Node result = searchRecursive(root, ISBN);
        if (result != null) {
            return result.book;
        }
        return null;
    }

    private Node searchRecursive(Node current, long ISBN) {
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

    private void inorderTraversal(Node node) {
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

    private Node deleteRecursive(Node current, long ISBN) {
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
            
            Node successor   = findMin(current.right);
            current.book = successor.book;
            current.right = deleteRecursive(current.right, successor.book.ISBN);

            }
        updateNodeHeight(current);
        int balance = getBalance(current);

        if(balance > 1 && getBalance(current.left) >= 0){ // left - left
            return rightRotate(current);
        }
        if(balance > 1 && getBalance(current.left) < 0){ //left - right
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }
        if(balance < -1 && getBalance(current.right) <= 0 ){ // right - right
            return leftRotate(current);
        }
        if(balance < -1 && getBalance(current.right) > 0){ // right - left
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }
        return current; 
        }

    
        private Node findMin(Node node){
            while(node.left != null){
                node = node.left;
            }
            return node;
        }
    
    private Node findBookByIsbn(Node node, long ISBN) {
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

    public int getTreeHeigt(){
        return getNodeHeight(root) ;
    }
}