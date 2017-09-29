import java.util.EmptyStackException;

public class ArrayStack<AnyType>
{
    private static final int INITIAL_SIZE = 10;
    private static final int DEFAULT_RESIZE_FACTOR = 2;

    private int size = 0; // Nombre d'éléments dans la pile.
    private AnyType[] table;

    // Initialisation de la pile.
    public ArrayStack()
    {
        table = (AnyType[]) new Object[INITIAL_SIZE];
    }

    // Enlève l'élément au sommet de la pile et le renvoie.
    // Complexité asymptotique: O(1)
    public AnyType pop() throws EmptyStackException
    {
        if(empty())
            throw new EmptyStackException() ;

        AnyType elePop = table[size-1] ;    // copie de lelement a pop
        table[size -1] = null ;             // suppression de l element a pop
        size= size -1 ;                          // reduit nombre d element
        return elePop ;                  // return element a pop
    }

    // Ajoute un élément au sommet de la pile.
    // Augmente la taille de la pile si nécessaire (utiliser la fonction resize définie plus bas).
    // Complexité asymptotique: O(1) (O(N) lorsqu'un redimensionnement est nécessaire)
    public void push(AnyType element)
    {
        if(size == table.length )   // Si tableau plein ou null
            resize(DEFAULT_RESIZE_FACTOR) ;

        table[size] = element ; // on met lelement a la fin du tableau
        size = size +1 ;  // incremente nombre delement
    }

    // Renvoie l'élément au sommet de la pile sans l'enlever.
    // Retourne null si la pile est vide.
    // Complexité asymptotique: O(1)
    public AnyType peek()
    {
        if(empty())
            return null;

       return table[size-1] ;
    }

    // Renvoie le nombre d'éléments dans la pile.
    public int size() { return size; }

    // Indique si la pile est vide.
    public boolean empty() { return size == 0; }

    // Redimensionne la pile. La capacité est multipliée par un facteur de resizeFactor.
    // Complexité asymptotique: O(N)
    @SuppressWarnings("unchecked")
    private void resize(int resizeFactor)
    {
        int ancienneSize = table.length ;  // stocke ancienne taille
       AnyType[] temp = table ;             // recopie ancien tableau
        table = (AnyType[]) new Object[ancienneSize*resizeFactor];  // cree nouveau tableau avec nouvelle taille
       System.arraycopy(temp,0,table,0,ancienneSize); // recopie tableau temporaire dans le nouveau tableau

    }
}
