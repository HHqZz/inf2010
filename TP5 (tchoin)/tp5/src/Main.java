/**
 * Created by axmas on 17-11-07.
 */

import java.util.Arrays;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args)
    {
        Node noeud1 = new Node(1);
       /* Node noeud2 = new Node(2);
        Node noeud3 = new Node(3);*/
        Node noeud4 = new Node(4);/*
        Node noeud5 = new Node(5);
        Node noeud6 = new Node(6);
        Node noeud7 = new Node(7);
        Node noeud8 = new Node(8);*/


        //noeud7.print( " ");
        System.out.println("_________________________________________");
       try{
            noeud4.fusion(noeud1);/*
            System.out.println("Fusion 1 succes");
            noeud7.fusion(noeud8);
            System.out.println("Fusion 2 succes");
            noeud1.fusion(noeud7);
            System.out.println("Fusion 3 succes");*/
        }
        catch(DifferentOrderTrees e){
            e.printStackTrace();
            System.out.println("different order tree sa mere");
        }


        noeud4.print( " ");
        System.out.println("print noeud succes");


        System.out.println("_________________________________________");


        //ArrayList<Integer> restants = noeud1.getElementsSorted();

       // System.out.println(restants);


        /*        ArrayList<Node> restants = noeud1.findValue(4).delete();
		for(Node n: restants){
			n.print("  ");
		}
		System.out.println(restants);

        /*Monceau monceau = new Monceau();
        monceau.print();

        //test insertion
        //monceau.insert(0);
        //monceau.insert(1);
        monceau.arbres = noeud1.findValue(3).delete();
        //monceau.insert(4);

        monceau.print();
        Monceau monceau2 = new Monceau();
        try {
            monceau2.arbres.add(noeud5.fusion(noeud6));
        } catch (DifferentOrderTrees e) {
            e.printStackTrace();
        }
        monceau.fusion(monceau2);
        monceau.print();

        Monceau monceau3 = new Monceau();
        try {
            monceau3.arbres.add(noeud7.fusion(noeud8));
        } catch (DifferentOrderTrees e) {
            e.printStackTrace();
        }
        //test fusion
        monceau.fusion(monceau3);

        monceau.print();

        monceau.insert(10);
        monceau.print();
        monceau.insert(4);
        monceau.print();
        monceau.insert(75);
        monceau.print();
        monceau.insert(3);
        monceau.print();
        monceau.insert(9);
        monceau.print();
        monceau.insert(11);
        monceau.print();
        monceau.insert(4);
        monceau.print();
        monceau.insert(75);
        monceau.print();
//		monceau.insert(3);
//		monceau.print();
//		monceau.insert(9);
//		monceau.print();

        monceau.delete(4);
        System.out.println("FUCK YOU!!!");
        monceau.print();
*/
    }




}