
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
		return ( ( a*x.hashCode() + b ) % p ) % Size();
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );
		int m =  array.size()*array.size();
		if(array == null || array.size() == 0)
		{
			a = 0;
			b = 0;
			items = null;
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			items = (AnyType[]) new Object[1];
			items[getKey(array.get(0))]= array.get(0);		
			return;
		}		
		items = (AnyType[]) new Object[m];
		makeEmpty();
		int index;
		AnyType element;
		boolean colision = false;
		
		do {
			
	    	a = generator.nextInt((p-1)+1);
	        b = generator.nextInt(p);
	        
	        for(int i = 0; i < array.size(); i++) {
	        	element = array.get(i);
	        	index =  ( ( a*element.hashCode() + b ) % p ) % m;
	        	if(items[index] == null)
	        		items[index] = element;
	        	else
	        		colision = true;
	        	
	        }
	             
		}while(colision);
    	 

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
				resultat += "(" + String.valueOf(getKey(items[i])) + "; " + String.valueOf(items[i])+ "), ";
			}
				
		}
		resultat += ".";
		return resultat; 
	}

	public void makeEmpty () {
		for( int i =0 ; i< Size(); i++){
			items[i] = null;
		}

   	}
}
