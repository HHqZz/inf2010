import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Comparable;
import java.lang.Math;

public class BST<T extends Comparable<T>>
{
    public int height = 0 ;
    protected class Node<T>
    {
        T val; // Valeur du noeud
        Node<T> right; // fils droite
        Node<T> left; // fils gauche

        public Node(T val)
        {
            this.val = val;
        }
    }

    protected Node<T> root = null; // Racine de l'arbre

    public boolean isValid() { return isValid(root); }

    private boolean isValid(Node<T> node)
    {
        if (node == null) {
            return true;
        }
        boolean isLeftValid = node.left == null || node.left.val.compareTo(node.val) < 0 && isValid(node.left);
        boolean isRightValid = node.right == null || node.right.val.compareTo(node.val) > 0 && isValid(node.right);
        return isLeftValid && isRightValid;
    }

    public int getHeight() { return root == null ? 0 : getHeight(root); }

    protected int getHeight(Node<T> node)
    {

        if(node == null)
            return -1 ;  // on decremente pour  la comparaison
        else
            return 1 + Math.max(getHeight(node.left),getHeight(node.right)); // On compare la plus grande hauteur entre le chemin de gauche et celui de droite

    }

	public void insert(T elem) { root = insert(root, elem); }

	//Fonction qui permet dajouter un element dans larbre
	private Node<T> insert(Node<T> node, T elem)
    {

        if(node == null) {  // Si larbre est vide
            return new Node<T>(elem); // On cree une racine
        }
        int compareResult = elem.compareTo(node.val);
        if(compareResult<0 ){
            node.left=insert( node.left, elem);
        }
        if(compareResult >0)
            node.right=insert(node.right, elem);
        else ; // pas de doublons

        return node;
	}

    public boolean contains(T elem) { return contains(root, elem); }

    private boolean contains(Node<T> node, T elem)
    {
        int compareResult = elem.compareTo(node.val);
        if(compareResult == 0)									// Si les valeures comparer sont les memes
            return true ;
        if(compareResult<0 ){									// la valeur du noeud est plus grand que `l element
            if(!(node.left== null))
               return contains( node.left, elem);
        }
        if(compareResult >0) {									// la valeur du noeud est plus petite que `l element
            if (!(node.right == null))
                return contains(node.right, elem);
        }
        if(node == null)										// Si larbre est vide
            return false ;
        else return false;
    }

    public ArrayList<T> traversePreOrder()
	{
		ArrayList<T> list = new ArrayList<T>();
		traversePreOrder(root, list);
		return list;
	}

	private void traversePreOrder(Node<T> node, ArrayList<T> list)
	{
        if (node == null)										// Si larbre est vide
            return;

        list.add(node.val);
        traversePreOrder(node.left,list);
        traversePreOrder(node.right,list);

	}

    public ArrayList<T> traversePostOrder()
	{
		ArrayList<T> list = new ArrayList<T>();
		traversePostOrder(root, list);
		return list;
	}

	private void traversePostOrder(Node<T> node, ArrayList<T> list)
	{
        if(node == null)										// Si larbre est vide
            return;
        traversePostOrder(node.left,list);
        traversePostOrder(node.right,list);
        list.add(node.val);




    }

    public ArrayList<T> traverseInOrder()
    {
        ArrayList<T> list = new ArrayList<T>();
        traverseInOrder(root, list);
        return list;
    }

    private void traverseInOrder(Node<T> node, ArrayList<T> list)
    {
        if(node== null)											  // Si larbre est vide
            return;
        traverseInOrder(node.left,list);
        list.add(node.val);
        traverseInOrder(node.right,list);
    }

    public ArrayList<T> traverseReverseOrder()
    {
        ArrayList<T> list = new ArrayList<T>();
        traverseReverseOrder(root, list);
        return list;
    }

    private void traverseReverseOrder(Node<T> node, ArrayList<T> list)
    {
        if(node == null)											// Si larbre est vide
            return;
        traverseReverseOrder(node.right,list);
        list.add(node.val);
        traverseReverseOrder(node.left,list);
    }

    public ArrayList<T> traverseLevelOrder()
	{
		ArrayList<T> list = new ArrayList<T>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> newElement = queue.poll();
			if(newElement != null){
				list.add(newElement.val);
				if(newElement.left != null)
					queue.add(newElement.left);
				if(newElement.right != null)
					queue.add(newElement.right);
			}
        }

		return list;
	}
}

