//package probleme1;
//import QuadraticSpacePerfectHashing;

import org.omg.CORBA.Any;

import java.util.Random;
import java.util.ArrayList;



public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );
		int key ;
		int subKey;

		if(array == null || array.size() == 0)
		{

			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			 key = getKey(array.get(0));
			 subKey= data[key].getKey(array.get(0));

			data[key].items[subKey] = array.get(0) ;
			return;
		}
		data = new QuadraticSpacePerfectHashing[array.size()] ; // principal

		for(int i = 0; i< array.size(); i++) {
			for (int j = 0; i < array.size(); j++) {
				key = getKey(array.get(j));
			}
			// Sous tableau, pour chaque index de data
			data[i] = new QuadraticSpacePerfectHashing<AnyType>(new ArrayList<AnyType>(array.size()));
		}
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		int m = data.length;
		int index = ((a*key+b)%p)%m ;
		/*
		* Si l'indexe correspondant a la cle nest pas vide, cest que la cle est deja
		* dans la table
		*/
		if(data[index]!=null) // Si lindexe existe deja
			return true ;		// Cest que la table contient deja la cle
		else return false ;


	}
	
	public int getKey (AnyType x) {
		int key = x.hashCode();
		return key ;
		
	}
	
	public boolean containsValue (AnyType x) {
		return data[getKey(x)].containsValue(x) ;

	}
	
	public void remove (AnyType x) {

		int m = data.length;
		int index = ((a*x.hashCode()+b)%p)%m;
		data[index].remove(x) ;
		
	}

	public String toString () {
		String result = "";

		for (int i=0; i < data.length; i++)
		{
			result = result+ "[ cle "+ i + " ]  -> "+data[i].toString();

		}



		return result; 
	}

	public void makeEmpty () {
		for (int i=0 ; i<data.length; i++)
		{
			data[i].makeEmpty(); // Vider les sous tableaux
			data[i] = null ;	 // Pointer a null chaque element du tableau principal
		}

   	}
	
}
