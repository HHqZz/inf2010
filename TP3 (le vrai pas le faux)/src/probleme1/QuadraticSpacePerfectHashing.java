package probleme1;
import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		if (items[key] != null)
			return true;
		else
			return false ;

	}

	public boolean containsValue(AnyType x )
	{

      if( Size() == 0 ) return false;
      if( a == 0 ) return ( items[0].equals(x) );
      int m = Size();
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ( ( items[index] != null ) &&
             ( items[index].equals(x) ) );

	}

	public void remove (AnyType x) {
		
		if(containsValue(x)){			
			items[getKey(x)] = null ;
		}
	}

	public int getKey (AnyType x) {
		return ( ( a*x.hashCode() + b ) % p ) % Size(); // On recoit l'index en prenant le hashCode de la valeur passer en parametre.
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );
		int m =  array.size()*array.size();
		if(array == null || array.size() == 0)			// Si l'array ne contient aucun ellement.
		{
			a = 0;										// On initialise les valeurs de "a" et "b" a 0
			b = 0;
			items = null;								// Et le tableau Items est null 
			return;
		}
		if(array.size() == 1)							// Si l'array contient seulement un element
		{
			a = b = 0;									// On initialise les valeurs de "a" et "b" a 0
			items = (AnyType[]) new Object[1];			// On initialise le tableau items a une capacite de 1.
			items[getKey(array.get(0))]= array.get(0);	// Om met l'unique valeur dans le tableau. 		
			return;
		}		
		items = (AnyType[]) new Object[m];				// Si Array contient plusieur valeurs. On l'initialise a une capacitée de m.
		makeEmpty();
		int index;
		AnyType element;
		boolean colision = false;
		
		do {
			
	    	a = generator.nextInt((p-1)+1);				// On genere des valeurs pour a et b.
	        b = generator.nextInt(p);
	        
	        for(int i = 0; i < array.size(); i++) {		// On parcour tout le array.
	        	element = array.get(i);
	        	index =  ( ( a*element.hashCode() + b ) % p ) % m;
	        	if(items[index] == null)				// Si il n'y a pas d'element dans le tabeau (a la position de l'indexe)
	        		items[index] = element;				// alors on y met le nouvel element.
	        	else
	        		colision = true;					// Sinon il y a colision
	        	
	        }
	             
		}while(colision);								// On effecue cette boucle temps qu'il y a une colision.
    	 

      }


	public String toString () {
		String resultat = "";
		boolean premierElement = true;
		for( int i = 0 ; i< Size() ; i++) {
			if(containsKey(i)) {
				if(premierElement) {					
					resultat += "(" + String.valueOf(getKey(items[i])) + ", " + String.valueOf(items[i])+ " )";
					premierElement = false;
				}
				resultat += ", (" + String.valueOf(getKey(items[i])) + "; " + String.valueOf(items[i])+ ")";
			}
				
		}
		resultat += ".";								// On finit l'affichage par un point.
		return resultat; 
	}

	public void makeEmpty () {
		for( int i =0 ; i< Size(); i++){				// On parcourt tout le tableau.
			items[i] = null;							// On supprime chaque element.
		}

   	}
}
