
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;

    public Monceau() {
        arbres = new ArrayList<Node>();
    }

    public void fusion(Monceau autre) {

        // On cree des indexs pour larbre courant et larbre en parametre
        int indexCourant = 0;
        int indexAutreMonceau = 0;
        int indexChange = 0;
        // on cree un arbre temporaire
        ArrayList<Node> tempTree = new ArrayList<>();


        while ((indexCourant < this.arbres.size()) || (indexAutreMonceau < autre.arbres.size())) { // Tant qu on a pas parcouru les deux arbres entierement ( pour ne pas oublier delements durant la fusion )
            if (indexAutreMonceau == autre.arbres.size()) { // si on arrive a la fin du parcours de larbre en parametre
                tempTree.add(arbres.get(indexCourant)); // on ajoute a larbre fuisonne les nodes de larbre courant
                indexCourant = indexCourant + 1; // on incremente lindex de larbre courant
              }
			else if (indexCourant == arbres.size()) {    // Si lindex de larbre courant arrive a la fin de larbre courant
                tempTree.add(autre.arbres.get(indexAutreMonceau));  //On ajoute a larbre fusionne les nodes de larbre en parametre
                indexAutreMonceau = indexAutreMonceau + 1;    // On incremente lindice de larbre en parametre
            } 
      
			else if (this.arbres.get(indexCourant).ordre <= autre.arbres.get(indexAutreMonceau).ordre) { // On
                tempTree.add(arbres.get(indexCourant));
                indexCourant = indexCourant + 1;
            } 
			else {
                tempTree.add(autre.arbres.get(indexAutreMonceau));
                indexAutreMonceau++;
            }
        }

        
        while (indexChange < tempTree.size() - 1) {
            Node temp = tempTree.get(indexChange);
            Node compareValue = tempTree.get(indexChange + 1);
            if (temp.ordre == compareValue.ordre) {
                try {
                    tempTree.set(indexChange, temp.fusion(compareValue));
                } catch (DifferentOrderTrees e) {
                }
                tempTree.remove(indexChange + 1);
                
            } else if (temp.ordre > compareValue.ordre) {
                tempTree.set(indexChange, compareValue);
                tempTree.set(indexChange + 1, temp);
            } else 
                indexChange++;
         
        }

        arbres = tempTree;
        autre.arbres = tempTree;

    }


    public void insert(int val) {
        Node newTree = new Node(val); // on cree un arbre bin a partir de la valeur en parametre

        Monceau newMonceau = new Monceau(); // on cree un monceau
        newMonceau.arbres.add(newTree);     // on lui passe larbre cree
        this.fusion(newMonceau); // on fusionne larbre cree avec le monceau courant
    }

    public boolean delete (int val) {

        boolean isDeleted = false ;
        for(int node = 0; node < arbres.size(); node++){
            Monceau monceauTemp = new Monceau();

            if(this.arbres.get(node).findValue(val) == null)
            { /* ne fait rien*/}
            else {
                ArrayList<Node> arbreTemp = this.arbres.get(node).findValue(val).delete(); // recupere la liste de noeud a delete
                monceauTemp.arbres = arbreTemp; // on stocke dans larbre temporaire les noeud a supprimer
                this.arbres.remove(arbres.get(node)); // retire de la liste le noeud a supprimer
                this.fusion(monceauTemp); // on fusionne les deux monceaux
                node = 0; // Reinitialise la boucle en cas de doublons et +
                isDeleted = true; // On a delete au moins une fois
            }
        }
        return isDeleted;
    }


    public void print() {
        for (int i = 0; i < arbres.size(); i++) {
            Node arbre = arbres.get(i);
            System.out.println("Ordre de l'arbre:" + arbre.ordre);
            arbre.print("\t");
        }
    }




}