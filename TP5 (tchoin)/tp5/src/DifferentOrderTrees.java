/*
***
*
 */
public class DifferentOrderTrees extends Exception {

    public DifferentOrderTrees() {
        super("Erreur : Fusion impossible : ordres differents");
    }
    public DifferentOrderTrees(String string) {
        super(string);
    }
}
