
import com.sun.xml.internal.bind.AnyTypeAdapter;

import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }


    // permet de fusionner deux arbres de meme ordre
    public Node fusion(Node autre) throws DifferentOrderTrees {

        if(this.ordre != autre.ordre)   // sils ne sont pas du meme ordre
             throw new DifferentOrderTrees(); //  on lance l'exception

        if(parent == null) {// si larbe courant est vide
            if(autre.parent == null)
            {
                return null;
            }
            return autre ;// On retourne le nouvel arbre qui est le noeud passe en parametre
        }
        else
            if(autre.getVal()<this.getVal()){ // On compare les deux arbres
                autre.addEnfant(autre);      // le plus grand devient l'enfant du deuxieme arbre
                autre.ordre++;
                return autre;
            }
            else {
                this.addEnfant(this); // Dans le cas dune egalite alors le deuxieme arbre devient
                this.ordre++;
                return this;

            }


    }

    // Permet dechanger deux noeuds par valeur (si relation parent enfant)
    private void moveUp() {

        int tempVal = parent.getVal(); // on copie le parent

        if(parent!= null){
            parent.valeur = this.getVal() ;
            this.valeur = tempVal ;
        }

    }

    public ArrayList<Node> delete() {
        if(parent != null) {    // si noeud a des parents
            this.moveUp();         // move up
            return this.parent.delete(); // return sur son parent avec la nouvelle valeur
        }
        //sinon
        else
        {
            // on met tous les enfants de la racine a null
            for(int i = 0; i< this.getEnfants().size() ; i++){
                this.getEnfants().get(i).parent = null ;
            }
            return this.getEnfants();
        }

    }

    public void print(String tabulation) {
        // on affiche la valeur du noeud courant
        System.out.println(tabulation);
        System.out.println(this.getVal());

        for(int i = 0 ; i< this.getEnfants().size() ; i++) { // Pour tous les enfants du noeud courant
            this.getEnfants().get(i).print(tabulation); // on rappelle print
            System.out.println("\n");

        }
    }
    
    public Node findValue(int valeur) {
        //
        return null;
    }

    //Methode qui retourne une arraylist contenant les valeurs de larbre non triees
    private ArrayList<Integer> fillArray(ArrayList liste){

        for(int i=0 ; i<this.enfants.size() ; i++){ // pour chaque enfant du noeud courant
            liste.add(this.enfants.get(i).getVal()); // on les ajoute a la liste
            this.enfants.get(i).fillArray(liste);   // et on rappelle la fonction sur chacun d'eux

        }
        return liste;
    }

    //Renvoie une liste de noeud triee en ordre croissant
    public ArrayList<Integer> getElementsSorted() {
        ArrayList<Integer> listeTriee = new ArrayList<Integer>(); //nouvelle liste pour accueillir les elements tries
        fillArray(listeTriee); // on remplit la liste avec les noeuds de larbre
        Collections.sort(listeTriee); // trie la liste dentier en ordre croissant
        return listeTriee; // renvoie la list triee
    }



    // Retourne une liste qui contient les valeurs tries des enfants dun noeud
    public static int minIndex (ArrayList<Node> list) {
        ArrayList listVal = new ArrayList(); // cree nouvelle liste
        for(int i = 0 ; i<list.size() ; i++) { // pour chaque enfant de la liste de noeud
            listVal.add(list.get(i).getVal()); // on ajoute la valeur dans la liste de valeur
        }
        return listVal.indexOf (Collections.min(listVal));
    }
}
