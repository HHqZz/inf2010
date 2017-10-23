/**
 * Created by cobou on 17-10-21.
 *
 * On reprend le code tire du site suivant :
 * http://users.cis.fiu.edu/~weiss/dsaajava2/code/QuadraticProbingHashTable.java
 * Pour en faire une classe de double hashing.
 *
 */


package probleme2 ;

// QuadraticProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */

//import MyHashMap.Entry;

public class DoubleHashingTable<AnyType>
{
    private int R = 5 ;

    /**
     * Construct the hash table.
     */
    public DoubleHashingTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public DoubleHashingTable( int size )
    {
        allocateArray( size );
        makeEmpty( );
    }

    /**
     * Insert into the hash table.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        // Insert x
        int currentPos = findPos( x );
        array[ currentPos ] = new HashEntry<AnyType>( x, true );
        currentSize++;

        // Rehash; see Section 5.5
        if( ++currentSize > array.length / 2 )
            rehash( );
    }

    /**
     * Expand the hash table.
     */
    private void rehash( )
    {
        HashEntry<AnyType> [ ] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray( nextPrime( 2 * oldArray.length ) );
        currentSize = 0;

        // Copy table over
        for( int i = 0; i < oldArray.length; i++ )
            if( oldArray[ i ] != null && oldArray[ i ].isActive )
                insert( oldArray[ i ].element );
    }

    /**
     * Find the highest pos of x in the table
     * Assumes table is at least half empty and table length is prime.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos( AnyType x )
    {
        int i = 1;
        int currentPos = myhash( x );

        while( array[ currentPos ] != null) // On accepte les doublons
        {
            currentPos = i * myhash2(currentPos);
            i++;
            if( currentPos >= array.length )
                currentPos -= array.length;
        }

        return currentPos;
    }

    private int getKey(AnyType x) {
        int offset = 0;
        int key = myhash(x);
        int longueur = array.length;

        while (array[key] != null && !array[key].element.equals(x)) {
            int hash2 = (R - (x.hashCode() % R));
            key = (myhash(x) + offset * hash2) % longueur;
            offset++;
        }
        return key;
    }

    public AnyType get(AnyType x) {

        if(array[getKey(x)] == null) {
            return null;
        }
        return array[getKey(x)].element;
    }


    public int nbElement() {
        return currentSize;


    }


    /**
     * Remove all occurences of x from the hash table.
     * @param x the item(s) to remove.
     */
    public void remove( AnyType x )
    {
        int temp = nbreOccurence(x);
         while(temp<=1 && temp !=0 ) {
             int currentPos = findPos(x);
             if (isActive(currentPos))
                 array[currentPos].isActive = false;
             temp--;
         }
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( AnyType x )
    {
        int currentPos = findPos( x );
        return isActive( currentPos );
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }

    private int myhash( AnyType x ) //H1(x) = x%N
    {
        int hashVal = x.hashCode( );

        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }

    /*
    * Deuxieme fonction de hashing
    *
    */
    private int myhash2(int pos)
    {
        int R = nextPrime(array.length/2); // Nombre premier R de H2(x)=R-(xmod(R))
        // Entre n/2 et n,  il existe toujours au moins un nombre premier donc R<N
        pos = R-(pos%R);
        return pos ;

    }
    private static class HashEntry<AnyType>
    {
        public AnyType  element;   // the element
        public boolean isActive;  // false if marked deleted

        public HashEntry( AnyType e )
        {
            this( e, true );
        }

        public HashEntry( AnyType e, boolean i )
        {
            element  = e;
            isActive = i;
        }
    }

    public int nbreOccurence(AnyType x)
    {
        int occurence = 0 ;
        int pos = myhash(x);
        if(array[pos].element.equals(x))
        {
            occurence++;

            while (array[pos].element.equals(x)) {
                pos = occurence* myhash2(pos);
                if(array[pos].element.equals(x))
                    occurence++;

            }
        }
        return occurence;

    }



    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<AnyType> [ ] array; // The array of elements
    private int currentSize;              // The number of occupied cells

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    @SuppressWarnings("unchecked")
    private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n <= 0 )
            n = 3;

        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }

}

