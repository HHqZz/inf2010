
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;

    public Monceau() {
        arbres = new ArrayList<Node>();
    }

    public void fusion(Monceau autre) {

        // On cree des indexs pour larbre courant et larbre en parametre
        int indexCourant=0;
        int indexAutreMonceau = 0;

        // on cree un arbre temporaire
        ArrayList<Node> tempTree = new ArrayList<>();


        while((indexCourant< arbres.size()) || (indexAutreMonceau< autre.arbres.size())){
            if(indexCourant == arbres.size()) {
                tempTree.add(autre.arbres.get(indexAutreMonceau));
                indexAutreMonceau++;
            }
            else if (indexAutreMonceau == autre.arbres.size()){
                tempTree.add(arbres.get(indexCourant));
                indexCourant++;
            }

            else if (arbres.get(indexCourant).ordre <= autre.arbres.get(indexAutreMonceau).ordre){
                tempTree.add(arbres.get(indexCourant));
                indexCourant++;
            }
            else{

                tempTree.add(autre.arbres.get(indexAutreMonceau));
                indexAutreMonceau++;
            }
        }

        int indexChange = 0;
        while(indexChange < tempTree.size() -1){
            Node temp = tempTree.get(indexChange);
            Node compareValue = tempTree.get(indexChange + 1);
            if(temp.ordre > compareValue.ordre){
                tempTree.set(indexChange, compareValue);
                tempTree.set(indexChange + 1, temp);
            } else if (temp.ordre == compareValue.ordre){
                try{
                    tempTree.set(indexChange, temp.fusion(compareValue));
                }catch(DifferentOrderTrees e){}
                tempTree.remove(indexChange + 1);
            } else {
                indexChange++;
            }
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

        for(int i = 0; i < arbres.size(); i++ ){
            if(arbres.get(i).findValue(val) != null){
                ArrayList<Node> arbreTemporaire = arbres.get(i).findValue(val).delete();
                Monceau monceauTemporaire = new Monceau();
                monceauTemporaire.arbres = arbreTemporaire;
                arbres.remove(arbres.get(i));
                fusion(monceauTemporaire);
                return true;



            }
        }



        return false;
    }

    public void print() {
        for(int i = 0; i < arbres.size(); i++){
            Node arbre = arbres.get(i);
            System.out.println("Ordre de l'arbre:" + arbre.ordre);
            arbre.print("   ");
        }
    }

}
