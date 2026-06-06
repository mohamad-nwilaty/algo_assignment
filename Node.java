class Node{
    Book book;
    Node left;
    Node right;
    int height ;

    public Node(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
        this.height = 1 ;
    }      
}