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
		for( int i = 0 ; i< items.length; i++) {
			if (getKey(items[i]) == key)
				return true;
		}
		return false ;

	}

	public boolean containsValue(AnyType x )
	{

      if( Size() == 0 ) return false;
      if( a == 0 ) return ( items[0].equals(x) );
      int m = items.length * items.length;
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ( ( items[index] != null ) &&
             ( items[index].equals(x) ) );

	}

	public void remove (AnyType x) {
		int key = getKey(x) ;
		if(containsKey(key)){
			items[key] = null ;
		}
	}

	public int getKey (AnyType x) {
		int m = items.length * items.length;
		Random generator = new Random( System.nanoTime() );
		a =  generator.nextInt(p);
		b = generator.nextInt(p);
		int index = ( ( a*x.hashCode() + b ) % p ) % m;
		return index;

		
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

			items[0]= array.get(0);		
			return;
		}
		a =  generator.nextInt((p-1)-1)+1;
		b = generator.nextInt(p);   // SAME HERE
		 int m = items.length;
      for(int i = 0; i < array.size(); i++) {

         int index = ((a * array.get(i).hashCode() + b) % p) % m;

         if () {
            AllocateMemory(array);   //
         }
         items[index] = array.get(i);
         
      //a faire

	}

	
	
	public String toString () {
		String result = "";
		for( int i = 0 ; i< items.length ; i++) {
			result += " (" + String.valueOf(getKey(items[i])) + "; " + String.valueOf(items[i])+ " ) ";
		}
		
		return result; 
	}

	public void makeEmpty () {
		for( int i =0 ; i< items.length ; i++){
			remove(items[i]);
		}

   	}
}
