
import com.sun.xml.internal.bind.AnyTypeAdapter;

import java.awt.*;
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

        // si this est plus grand que parametre alors bug
        // prendre la valeur de this et la comparer avec celle en parametre
        // si this est plus gfrand que param alors on rappelle la fonction mais a lenvers
        if(this.valeur> autre.valeur){
            return autre.fusion(this);
        }



        if(parent == null && autre.parent == null) {// si larbe courant est vide
            if (valeur < autre.valeur){  // On compare les deux arbres
                enfants.add(autre);
                autre.parent = this;
                ordre++;
                return this;
            }

            else{
                autre.addEnfant(autre);
                autre.ordre++;
                return autre;
            }
        }
        else
            return null;


    }

    // Permet dechanger deux noeuds par valeur (si relation parent enfant)
    private void moveUp() {

        int tempVal = this.parent.getVal(); // copie du parent

        if(this.parent!= null){
            this.parent.valeur = this.getVal() ;
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
       if(this == null)
           System.out.println("Arbre vide");

        System.out.print(tabulation);
        System.out.print(this.getVal());

        for(int i = 0 ; i< this.enfants.size(); i++) { // Pour tous les enfants du noeud courant
            Node enfant = enfants.get(i);
            enfant.print(tabulation+ "\t");

        }
       System.out.println("\n");
    }



    // Fonction qui permet de trouver un noeud contenant la valeur passee en parametre
    public Node findValue(int valeur) {

        ArrayList<Node> listNode= new ArrayList<>();
        listNode.add(this);
        fillArrayNode(listNode);

        if(this.valeur == valeur) {
            System.out.println("Valeur presente dans larbre binomial"+this.getVal());
            return this;
        }
        for(Node node : listNode){
            if(node.valeur==valeur) {
                System.out.println("Valeur presente dans larbre binomial"+node.getVal());
                return node;
            }
        }
        System.out.println("Valeur absente dans larbre");
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

    //Methode qui retourne une arraylist contenant les noeud de larbre
    private ArrayList<Node> fillArrayNode(ArrayList liste){

        for(int i=0 ; i<this.enfants.size() ; i++){ // pour chaque enfant du noeud courant
            liste.add(this.enfants.get(i)); // on les ajoute a la liste
            this.enfants.get(i).fillArrayNode(liste);   // et on rappelle la fonction sur chacun d'eux

        }
        return liste;
    }

    //Renvoie une liste de noeud triee en ordre croissant
    public ArrayList<Integer> getElementsSorted() {
        ArrayList<Integer> listeTriee = new ArrayList<Integer>(); //nouvelle liste pour accueillir les elements tries
        listeTriee.add(this.valeur); // on ajoute la racine de larbre
        fillArray(listeTriee); // on remplit la liste avec les noeuds de larbre sans la racine qui y est deja
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