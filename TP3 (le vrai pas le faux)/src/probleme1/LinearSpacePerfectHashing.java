package probleme1;
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
	/*private void AllocateMemory(ArrayList<AnyType> array)
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
			data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
			return;
		}
		data = new QuadraticSpacePerfectHashing[array.size()] ; // principal

		for(int i = 0; i< array.size(); i++) {
			for (int j = 0; j < array.size(); j++) {
				key = getKey(array.get(j));
			}
			// Sous tableau, pour chaque index de data
			data[i] = new QuadraticSpacePerfectHashing<AnyType>(new ArrayList<AnyType>(array.size()));
		}
	}*/
	
	
	private void AllocateMemory(ArrayList<AnyType> array) {
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0) {
			return;
		}

		//Tableau de taille m = n .
		int m = array.size();
		this.data = new QuadraticSpacePerfectHashing[m];		//Tableau de taille m = n.
		this.makeEmpty();

		if(array.size() == 1) {
			this.a = this.b = 0;
			this.data[0] = new QuadraticSpacePerfectHashing<>(array);
			return;
		}

		//Bornes pour max et min de a.
		int max = this.p - 1;
		int min = 1;
		this.a = generator.nextInt(max + 1 - min) + min;    	//Génération d'un nombre se situant dans l'intervalle [1 , p - 1] .

		//Bornes pour min et max de b.
		max = this.p - 1;
		min = 0;
		this.b = generator.nextInt(max + 1 - min) + min;    	//Génération d'un nombre se situant dans l'intervalle [0 , p - 1] .

		//Déclaration des variables utiles à l'intérieur de la boucle suivante.
		int key;
		AnyType element, elementTmp;
		ArrayList<AnyType> tmpArray = new ArrayList<>();

		for(int i  = 0 ; i < m ; i++) {
			element = array.get(i);					//On récupère l'élément à la position i du array.
			key = this.getKey(element);				//On va chercher la clé pour l'élément.
			if(!containsKey(key)) {					//Si la clé n'est pas déjà présente dans le HashTable, on l'ajoute.
				tmpArray.clear();
				tmpArray.add(element);
				for(int j = i + 1 ; j < m ; j++) {	//Récupération de tous les éléments du array qui ont la même clé que celle ci-haut.
					elementTmp = array.get(j);
					int indexTmp = this.getKey(elementTmp);
					if(indexTmp == key)				//Si la clé est la même,
						tmpArray.add(elementTmp);	//on l'ajoute au tableau temporaire.
				}
				this.data[key] = new QuadraticSpacePerfectHashing<>(tmpArray); //À la position de clé, on insère le tableau temporaire.
			}
		}
	}

	
	
	public int Size()
	{
		if( data == null )
			return 0;

		int size = 0;

		for(int i=0; i<data.length; ++i)
		{
			if(data[i]==null)
				size++;
			else size+= data[i].Size();
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
			return true ;		// Cest que la table contient deja la cle donc return true
		else return false ;


	}
	
	public int getKey (AnyType x) {
		int key = x.hashCode();
		return key ;
		
	}
	
	public boolean containsValue (AnyType x) {

		/* Gerons les differents cas pour la taille du tableau
		*	vide
		*	a=0
		*	key inexistante
	    */

		if(Size() == 0)	//cas table vide
			return false;

		if(a == 0) // a de lequation index = ((a*key+b)%p)%m : cas index 0
			return data[0].containsValue(x);

		int key = getKey(x);

		if(data[key] == null) //cas ou la cle nexiste pas
			return false;

		return data[key].containsValue(x); //cas normal

	}
	
	public void remove (AnyType x) { //

		int m = data.length;
		int index = ((a*x.hashCode()+b)%p)%m; // Fonction de hashing
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
