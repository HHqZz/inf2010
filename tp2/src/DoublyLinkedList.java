import org.omg.CORBA.Any;

public class DoublyLinkedList<AnyType>
{
    // Un noeud de la liste.
    @SuppressWarnings("hiding")
    private class Node<AnyType>
    {
        private AnyType value;
        private Node prev;
        private Node next;

        public Node(AnyType value, Node prev, Node next)
        {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public void setPrev(Node prev) { this.prev = prev; }

        public Node<AnyType> getPrev() { return this.prev; }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Node<AnyType> getNext()
        {
            return next;
        }

        public AnyType getValue()
        {
            return value;
        }
    }

    private int size = 0;		 // Nombre d'éléments dans la liste.
    private Node<AnyType> front; // Premier élément de la liste.
    private Node<AnyType> back;  // Dernier élément de la liste.

    // Indique si la liste est vide.
    public boolean empty()
    {
        return size == 0;
    }

    // Retourne la taille de la liste.
    public int size()
    {
        return size;
    }

    // Retourne l'élément à la fin de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekBack()
    {

        if (empty())
            return null;
        else
            return back.getValue();
    }

    // Retourne l'élément au début de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekFront()
    {

        if (empty())
            return null;
        else
            return front.getValue();
    }

    // Retourne le noeud à l'indice donné.
    // Complexité asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
       //On cree une valeur de parcours qui commence au debut de la liste
        Node<AnyType> valeurParcour= front;
        //On parcourt le tableau jusqu'a l'index demande
       for(int i=0 ; i <index ; i++ ){
           // on verifie si notre valeur de parcours n'est pas le dernier noeud de la liste
           if(valeurParcour == back)
                return null;
           //SI ce n'est pas le dernier noeud on passe au prochain
         Node tempNode =  valeurParcour.getNext();
         valeurParcour= tempNode ;
         // Si on est a l'index alors on return le noeud
            if (i == index)
                return valeurParcour;
        }
        return null ;
    }

    // Retourne l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException
    {
        // On verifie si l'index est dans la liste
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
        // On retourne l'element a l'indice donne
        return this.getNodeAt(index).getValue();
    }

    // Retire l'élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
        //On verifie si la pile est vide
        if (empty())
            throw new EmptyListException();

        // Retire l'élément à la fin de la liste.
        if(back.getPrev()!=null)
        {
            back = back.getPrev();
            back.setNext(null);
            size--;
        }

    }

    // Retire l'élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void popFront() throws EmptyListException
    {

        //On verifie si la pile est vide
        if (empty())
            throw new EmptyListException();


        // Retire l'élément au début de la liste.
        if(front.getNext() != null )
        {
            front.getNext().setPrev(null);
            front = front.getNext();
            size--;
        }

    }

    // Retire l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public void removeAt (int index) throws IndexOutOfBoundsException
    {
        // On verifie si l'index est dans la liste
        if (index < 0 || index > size())
           throw  new IndexOutOfBoundsException();

        if (getNodeAt(index).getNext() != null )
        {
            getNodeAt(index).getNext().setPrev(getNodeAt(index-1));
        }

        if(getNodeAt(index).getPrev() != null)
        {
            getNodeAt(index).getPrev().setNext(getNodeAt(index+1));

        }
        if(getNodeAt(index).getNext()== null && getNodeAt(index).getPrev()==null)
        {
            getNodeAt(index).setPrev(null);
            getNodeAt(index).setNext(null);
        }
        size--;
    }

    // Ajoute un élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void pushBack(AnyType item)
    {
        Node <AnyType> newNode = new Node(item,back,null);
        back= newNode;
        size++;
    }

    // Ajoute un élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void pushFront(AnyType item)
    {
        Node <AnyType> newNode = new Node(item,null,front);
        front= newNode ;
        size++;

    }

    // Ajoute un élément à l'indice donné.
    // Complexité asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        Node<AnyType> newNode = new Node(item,null,null);
        getNodeAt(index).setPrev(newNode);
        getNodeAt(index-1).setNext(newNode);
        size++;
    }
}