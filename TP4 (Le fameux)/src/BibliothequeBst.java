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
    }

    // Complexité: O(log(n))
    // Explication:
    public boolean contientLivre(String livre)
    {
        // À compléter
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre/ascendant.
    public String afficherLivresAlpha()
    {
        // À compléter
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre inverse/descendant.
    public String afficherLivresAlphaInverse()
    {
        // À compléter
    }
}