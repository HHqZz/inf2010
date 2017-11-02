import java.lang.reflect.Array;
import java.util.ArrayList;

// Implémentation de l'interface IBibliotheque
// à l'aide d'un arbre de recherche binaire.

public class BibliothequeBst implements IBibliotheque
{
    private BST<String> livres = new AvlTree<String>();

    // Complexité: O(log(n))
    // Explication:
    public void ajouterLivre(String livre)
    {
        // À compléter
        livres.insert(livre);

    }

    // Complexité: O(log(n))
    // Explication:
    public boolean contientLivre(String livre)
    {
        return livres.contains(livre);
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre/ascendant.
    public String afficherLivresAlpha()
    {
        // À compléter

        String tempStr="" ; // resultat final
        ArrayList<String> tempListlivres ;  // liste pour stocker larbre dans un certain ordre
        tempListlivres = livres.traverseInOrder();  // On met chacun des elements de la bibliotheques dans dans la liste en suviant un certain ordre

    for( int i = 0 ; i< tempListlivres.size() ; i++ ){
            tempStr+= tempListlivres.get(i) + "\n";
        }
        return tempStr;

       //return livres.traverseInOrder().toString();
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre inverse/descendant.
    public String afficherLivresAlphaInverse()
    {
        // À compléter
        String tempStr="" ; // resultat final
        ArrayList<String> tempListlivres ;  // liste pour stocker larbre dans un certain ordre
        tempListlivres = livres.traverseReverseOrder();  // On met chacun des elements de la bibliotheques dans dans la liste en suviant un certain ordre

        for( int i = 0 ; i< tempListlivres.size() ; i++ ){
        tempStr+= tempListlivres.get(i) + "\n";
        }
        return tempStr;
    }
}

