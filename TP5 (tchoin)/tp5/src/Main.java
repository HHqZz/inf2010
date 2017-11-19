/**
 * Created by axmas on 17-11-07.
 */

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.Arrays;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args)
    {
        /*
        System.out.println("**********Debut des tests pour NODE.java*********");
        //Creation des NODE
        System.out.println("*******TEST Creation des nodes*******");
        Node noeud1 = new Node(1);
        Node noeud2 = new Node(2);
        Node noeud3 = new Node(3);
        Node noeud4 = new Node(4);
        Node noeud5 = new Node(5);
        Node noeud6 = new Node(6);
        Node noeud7 = new Node(7);
        Node noeud8 = new Node(8);

        if(noeud1.getVal()==1
                && noeud2.getVal() == 2
                && noeud3.getVal()==3
                && noeud4.getVal()==4
                && noeud5.getVal()==5
                && noeud6.getVal()==6
                && noeud7.getVal()==7
                && noeud8.getVal() ==8
                )
        {
            System.out.println("**Tous les noeuds ont ete crees avec succes**");
        }

        else
            System.out.println("---Erreur : les valeurs des noeuds ne correspondent pas---");

        System.out.println("************FIN TEST CREATION NODE ************\n");


        System.out.println("************DEBUT TEST FUSION NODE ************\n");
       try{
           noeud4.fusion(noeud1);
//           noeud1.fusion(noeud4);
            System.out.println("Fusion 1 succes");

  //         noeud7.fusion(noeud8);
           noeud8.fusion(noeud7);
            System.out.println("Fusion 2 succes");

           noeud1.fusion(noeud7);
   //        noeud7.fusion(noeud1);
            System.out.println("Fusion 3 succes");
        }
        catch(DifferentOrderTrees e){
            e.printStackTrace();
            System.out.println("different order tree :: Fusion impossible");
        }






        //Test sur FINDVALUE
        System.out.println("************DEBUT TEST FINDVALUE NODE************");

        Node nodeTest = noeud1.findValue(65);
        Node nodeTest1 = noeud1.findValue(4);
        Node nodeTest2 = noeud1.findValue(8);
        Node nodeTest3 = noeud1.findValue(1);
        Node nodeTest4 = noeud1.findValue(0);

        System.out.println("---------------Noeud 2----------------");
        Node nodeTest5 = noeud2.findValue(65);
        Node nodeTest6 = noeud2.findValue(4);
        Node nodeTest7 = noeud2.findValue(8);
        Node nodeTest8 = noeud2.findValue(1);
        Node nodeTest9 = noeud2.findValue(0);

        System.out.println("---------------Noeud 3----------------");
        Node nodeTest10 = noeud3.findValue(65);
        Node nodeTest11= noeud3.findValue(4);
        Node nodeTest12= noeud3.findValue(8);
        Node nodeTest13= noeud3.findValue(1);
        Node nodeTest14= noeud3.findValue(0);

        System.out.println("---------------Noeud 4----------------");
        Node nodeTest15 = noeud4.findValue(65);
        Node nodeTest16= noeud4.findValue(4);
        Node nodeTest17= noeud4.findValue(8);
        Node nodeTest18= noeud4.findValue(1);
        Node nodeTest19= noeud4.findValue(0);

        System.out.println("************FIN TEST FINDVALUE ************");
        // Fin test findvalue NODE.java

*/

/************************************MONCEAU******************************************/



        System.out.println("**********DEBUT TEST MONCEAU****************");
        System.out.println("Creation arbre monceau");
        Monceau monceau = new Monceau();
        System.out.println("arbre monceau cree avec succes");



        //test insertion
        System.out.println("****Test insertion****");
        monceau.insert(0);
        monceau.insert(1);
        monceau.insert(4);
        monceau.insert(7);
        monceau.insert(84);
        monceau.insert(7);
        monceau.insert(12);
        System.out.println("Fin Des insertions");

        System.out.println("Test findValue ");
       /*monceau.findValue(4);
       monceau.findValue(84);
       monceau.findValue(7);
       monceau.findValue(200);*/
       System.out.println("Fin test FIndValue");

       System.out.println("**Debut test delete Monceau**");
        //monceau.arbres = noeud1.findValue(3).delete();
        //
        monceau.delete(7);
        monceau.print();
      /*  Monceau monceau2 = new Monceau();
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
        System.out.println("DONE");
        monceau.print();
*/
    }




}