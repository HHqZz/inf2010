package PolyNet;

import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes) {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength() {
        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();

        // À compléter

        /*
        * But :
        * prendre un noeud Root :
        * Marquer le root comme Connu
        * Obtenir sa liste de noeud voisin et non connu (si list vide = fin) * et ajouter a la liste de TOUS les voisins
        * Choisir dans la liste de TOUS les voisins le noeud le plus Proche
        * et incrementer totalcableLegnth avec sa distance
        * Marquer ce noeud comme  connu
        *
        * */
        PolyNetNode root = nodes[0]; // on cree un point de depart
        if (root == null)
            System.out.println("Le tableau de node est vide. ");

        // on ajoute aux connections connues toutes les connections qui partent de la racine
        knownConnections.addAll(root.getConnections());

        // On ajoute aux noeuds visités la racine
        visitedNodes.add(root);

        int compteur = 0;
        // Pour toutes les connections possibles
        while(compteur!= nodes.length){

            // on recup la connexion la plus courte
            PolyNetConnection tempConnect = knownConnections.peek();

            // dans le cas ou il n'y a plu de connexion possible
            if(tempConnect == null)
                return totalCableLength;

            // Si la liste de noeud visite ne contient pas le noeud le plus proche
            if(!(visitedNodes.contains(tempConnect.getConnectedNode()))){

                // incremente la distance totale
                totalCableLength+= tempConnect.getDistance() ;

                // ajoute le noeud connecte a la liste de noeud parcouru
                visitedNodes.add(tempConnect.getConnectedNode());

                //Ajoute aux connections possibles les connections du noeud nouvellement parcouru
                knownConnections.addAll(tempConnect.getConnectedNode().getConnections());

                // retire la connexion  des connexions potentielles
                knownConnections.remove(tempConnect);

                // incremente le compteur de noeud parcouru
                compteur++;
            }
            else // on remove de la liste des connexions potentielles
                knownConnections.remove(tempConnect);

        }

        return totalCableLength;


    }
}
