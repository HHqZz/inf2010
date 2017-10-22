import java.io.*;
/*************************************************************
* Titre: Travail pratique #2 - INF2010
* Date:  2 Octobre 2017
* Auteur : Constantin Bouis 1783438 et Axel Templier 1837967
**************************************************************/

public class PostfixSolverMain
{
	public static void main(String[] args) throws IOException
	{
        String s1 = "0 1 or";
        String s2 = "0 0 or";
        String s3 = "1 1 or";
        String s4 = "1 0 or";

        try {
            if (!solve(s1) || solve(s2) || !solve(s3) || !solve(s4)) {
                System.out.println("Erreur : résultat de l'opération or invalide.");
                return;
            }
        }
        catch (ParsingErrorException ex) {
            System.out.println("Erreur : le solveur a rencontré un problème.");
        }

        String s5 = "0 0 and";
        String s6 = "0 1 and";
        String s7 = "1 0 and";
        String s8 = "1 1 and";

        try {
            if (solve(s5) || solve(s6) || solve(s7) || !solve(s8)) {
                System.out.println("Erreur : résultat de l'opération and invalide.");
                return;
            }
        }
        catch (ParsingErrorException ex) {
            System.out.println("Erreur : le solveur a rencontré un problème.");
        }

        String s9 = "0 not";
        String s10 = "1 not";

        try {
            if (!solve(s9) || solve(s10)) {
                System.out.println("Erreur : résultat de l'opération not invalide.");
                return;
            }
        }
        catch (ParsingErrorException ex) {
            System.out.println("Erreur : le solveur a rencontré un problème.");
        }

        System.out.print("PostfixSolver: It's all good");
     }

	/*
	* Methode permettant de resoudre une equation postfixe de type booleenne
	*	Capable de traiter les portes AND, OR, NOT
	*/
    public static boolean solve(String input) throws ParsingErrorException
    {
        // À compléter
        ArrayStack<Boolean> stack = new ArrayStack<Boolean>();
        //L'expression est séparée en tokens selon les espaces.
        boolean andSolve = true ;
        for (String token : input.split("\\s")) {

            if (token.equals("and")) {
                if (stack.pop().equals(true) && stack.pop().equals(true))
                    stack.push(true);
                else stack.push(false);
            }

            if (token.equals("or")) {
                if (stack.pop().equals(true) || stack.pop().equals(true))
                    stack.push(true);
                else stack.push(false);
            }

            if (token.equals("not")) {
                if (stack.pop().equals(true))
                    stack.push(false);
                else
                    stack.push(true);
            }

            if (token.equals("1"))
                stack.push(true);

            if (token.equals("0"))
                stack.push(false);


        }
        return stack.peek() ;
    }
}























