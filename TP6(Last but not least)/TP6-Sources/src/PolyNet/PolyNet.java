package PolyNet;

import java.util.HashSet;
import java.util.PriorityQueue;

public class PolyNet {
    private PolyNetNode[] nodes;

    public PolyNet(PolyNetNode[] nodes)
    {
        this.nodes = nodes;
    }

    // MST (using Prim's algorithm).
    public int computeTotalCableLength()
    {
        int totalCableLength = 0;
        PriorityQueue<PolyNetConnection> knownConnections = new PriorityQueue<>();
        HashSet<PolyNetNode> visitedNodes = new HashSet<>();

        // À compléter

        /*
        * But :
        * prendre un noeud Root :
        * Marquer le root comme Connu
        * Obtenir sa liste de noeud voisin et non connu (si list vide = fin)
        * et ajouter a la liste de TOUS les voisins
        * Choisir dans la liste de TOUS les voisins le noeud le plus Proche
        * et incrementer totalcableLegnth avec sa distance
        * Marquerce noeud comme
        *
        * */
        for(PolyNetNode : nodes){

        }



        return totalCableLength;
    }
}
