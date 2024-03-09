package assn04;

public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You should add more examples and use this class to debug your code
    */
    BST<Integer> bst = new NonEmptyBST<Integer>(0);

      bst = bst.insert(3);
      bst = bst.insert(4);
      bst = bst.insert(1);
      bst = bst.insert(2);
      bst = bst.insert(5);
      bst = bst.insert(6);
      bst = bst.insert(-1);
      bst = bst.insert(100);
      bst = bst.insert(30);
      // bst = bst.remove(-1);
      // bst = bst.remove(4);
    bst.printPreOrderTraversal();
    System.out.println();

    bst.printPostOrderTraversal();
    System.out.println();

    System.out.println();


    BST<Integer> ssss = new NonEmptyBST<>(1);
    ssss = ssss.insert(-1);
    ssss.printPreOrderTraversal();
    System.out.println();

    // ssss = ssss.remove(1);
    ssss.printPostOrderTraversal();
  }

}
