import java.util.Stack;

public class StackQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0; // Nombre d'éléments dans la file.
	private Stack<AnyType> inStack;
	private Stack<AnyType> outStack;
   
	@SuppressWarnings("unchecked")
	public StackQueue()
	{
		inStack = new Stack<AnyType>();
		outStack = new Stack<AnyType>();
        
	}
	// methode qui permet de transferer les elements dans l'ordre inverse d'entrer
	// de inStack dans outStack. Cela permet d'avoir l'equivalent d'une file.
	public void transfererPile(){ 
		while (!(inStack.empty()))
			outStack.push(inStack.pop());
			
	}
	
	// Indique si la file est vide.
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	// Retourne la taille de la file.
	public int size() 
	{ 
		return size; 
	}
	
	// Retourne l'élément en tête de file.
	// Retourne null si la file est vide.
	// Complexité asymptotique: O(1) (ammorti)
	public AnyType peek()
    {
		if (empty())
			return null;
		if(outStack.empty())
			transfererPile();
			
		return outStack.peek();
//fait
	}
	
	// Retire l'élément en tête de file.
	// Complexité asymptotique: O(1) (ammorti)
	public void pop() throws EmptyQueueException
	{
		if (empty())
			throw new EmptyQueueException();
		
		if (outStack.empty())
			transfererPile();
		
		outStack.pop();
		size--; 
        //fait
		
	}
	
	// Ajoute un élément a la fin de la file.
	// Complexité asymptotique: O(1)
	public void push(AnyType item)
	{
		//fait
		inStack.push(item);
		size++;
		
	}
}

