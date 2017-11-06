import java.lang.Comparable;

public class AvlTree<T extends Comparable<T>> extends BST<T>
{
    public boolean isBalanced() { return isBalanced(root); }

    private boolean isBalanced(Node<T> node)
    {
        if (node == null) {
            return true;
        }

        boolean childsBalanced = isBalanced(node.left)
                              && isBalanced(node.right);

        int heightDiff = Math.abs(getHeight(node.left) - getHeight(node.right));

        return childsBalanced && heightDiff <= 1;
    }

    public void insert(T elem) { root = insert(root, elem); }

    private Node<T> insert(Node<T> node, T elem)
    {
        if(node == null) {  // Si larbre est vide
            return new Node<T>(elem); // On cree une racine
        }
        int compareResult = elem.compareTo(node.val);
        if(compareResult<0 ){	// Si l'element est plus petit que la valeur du noeud
            node.left=insert( node.left, elem);
            if(getHeight(node.left) - getHeight(node.right) == 2)
                if(elem.compareTo(node.left.val) < 0) // Si l`element est plus petit que la valeur du noeuds de gauche
                    node = balanceLeftLeft(node);
                else									// Si l`element est plus grand que la valeur du noeuds de gauche
                    node = balanceLeftRight(node);
        }
        if(compareResult >0){ // Si l'element est plus grand que la valeur du noeud
            node.right=insert(node.right, elem);
            if(getHeight(node.right) - getHeight(node.left) == 2)
                if(elem.compareTo(node.right.val) > 0) // Si l`element est plus grand que la valeur du noeuds de droite
                    node = balanceRightRight(node);
                else									// Si l`element est plus petit que la valeur du noeuds de droite
                    node = balanceRightLeft(node);
            }
        else ; // pas de doublons

        return node;
    }
	//balancement droite-droite
    private Node<T> balanceRightRight(Node<T> node)
    {
        Node<T> node2 = node.right ;

        node.right = node2.left;
        node2.left = node ;


        return node2;

    }
	//balancement droite-gauche
    private Node<T> balanceRightLeft(Node<T> node)
    {
        node.right = balanceLeftLeft(node.right);
        return balanceRightRight(node);
    }
	//balancement gauche-gauche
    private Node<T> balanceLeftLeft(Node<T> node)
    {
        Node<T> node2 = node.left ;

        node.left = node2.right;
        node2.right = node ;


        return node2;
    }
	//balancement gauche-droite
    private Node<T> balanceLeftRight(Node<T> node)
    {
        node.left = balanceRightRight(node.left);
        return balanceLeftLeft(node);
    }
}