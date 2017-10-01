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
    	if(empty())
        	return null;
        return back.getValue();
    }

    // Retourne l'élément au début de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekFront()
    {
        if(empty())
        	return null;
        return front.getValue();
    }

    // Retourne le noeud à l'indice donné.
    // Complexité asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
        Node<AnyType> temporaire = front;
        for (int i = 0; i < index; i++) 
        	temporaire = temporaire.getNext();
        return temporaire;
    }

    // Retourne l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= size())
        	throw new IndexOutOfBoundsException();
        return getNodeAt(index).getValue();
    }

    // Retire l'élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
        if (empty())  
        	throw new EmptyListException();
        
        if (size() > 1) 
        	back = back.getPrev();
        back.setNext(null);
        size--;
    }

    // Retire l'élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void popFront() throws EmptyListException
    {
    	if (empty())  
        	throw new EmptyListException();
    	
    	if (size() > 1) 
        	front = front.getNext();
        front.setPrev(null);
        size--;
    	
    }

    // Retire l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public void removeAt(int index) throws IndexOutOfBoundsException
    {
    	
            if(index < 0 || index >= this.size())
                throw new IndexOutOfBoundsException();

            Node<AnyType> temporaire = getNodeAt(index);

            if(temporaire != back)
            	temporaire.getNext().setPrev(temporaire.getPrev());
            else {
                if(size() > 1)                         
                    back = back.getPrev();  
                back.setNext(null);               
            }

            if(temporaire != front)
            	temporaire.getPrev().setNext(temporaire.getNext());
            else {
                if(size() > 1)                        
                    front = front.getNext();  
                front.setPrev(null);               
            }

            this.size--;
    
    }

    // Ajoute un élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void pushBack(AnyType item)
    {
    	Node <AnyType> newNode = new Node<AnyType>(item,back,null);
        back= newNode ;
        if(empty())
        	front = newNode;
        else
        	back.getPrev().setNext(back);
        	
        size++;
    }

    // Ajoute un élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void pushFront(AnyType item)
    {
    	Node <AnyType> newNode = new Node<AnyType>(item,null,front);
        front= newNode ;
        if (empty())
        	back = newNode;
        else
        	front.getNext().setPrev(front);
        	
        size++;
    }

    // Ajoute un élément à l'indice donné.
    // Complexité asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {
    	if(index < 0 || index >= this.size())
            throw new IndexOutOfBoundsException();

        if(index == 0)
            pushFront(item);                
        else if(index == this.size())
            pushBack(item);                 
        else {                              
            Node<AnyType> temporaire = getNodeAt(index);
            Node<AnyType> newNode = new Node<AnyType>(item, temporaire.prev, temporaire);
            newNode.getPrev().setNext(newNode);
            newNode.getNext().setPrev(newNode);
            size++;
        }
    }
}