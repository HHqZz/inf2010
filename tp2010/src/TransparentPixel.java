/*************************************************************
* Titre: Travail pratique #1 - INF2010
* Date:  18 Septembre 2017
* Auteur : Constantin Bouis 1783438 et Axel Templier 1837967
**************************************************************/
/**
 * Classe de pixel transparent
 */

public class TransparentPixel extends AbstractPixel
{
	public int[] rgba; // donnees de l'image

	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	TransparentPixel()
	{
		rgba = new int[4];
		rgba[0] = 255;
		rgba[1] = 255;
		rgba[2] = 255;
		rgba[3] = 255;
	}

	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner
	 */
	TransparentPixel(int[] rgba)
	{
    this.rgba = new int[4];

		this.rgba[0] = rgba[0];
		this.rgba[1] = rgba[1];
		this.rgba[2] = rgba[2];
		this.rgba[3] = rgba[3];
	}

	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
        double moyenne = (this.rgba[0] + this.rgba[1] + this.rgba[2] + this.rgba[3])/4;
        if (moyenne <= 127)
				{
            BWPixel pixelNB = new BWPixel (false);
						return pixelNB;
				}
        else
				{
            BWPixel pixelNB = new BWPixel (true);
						return pixelNB;
				}
	}

	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
        int moyenne = (this.rgba[0] + this.rgba[1] + this.rgba[2] + this.rgba[3])/4;
        GrayPixel pixelGris =  new GrayPixel(moyenne);
        return pixelGris;
	}

	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
        int[] rgb = new int[3];
        rgb[0] = this.rgba[0];
        rgb[1] = this.rgba[1];
        rgb[2] = this.rgba[2];
        ColorPixel pixelCouleur = new ColorPixel( rgb );
				return pixelCouleur;
	}

	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public TransparentPixel Negative()
	{
    this.rgba[0] -= 255;
		this.rgba[1] -= 255;
		this.rgba[2] -= 255;
		return new TransparentPixel(this.rgba);
	}

	//Renvoie un pixel transparent
	public TransparentPixel toTransparentPixel()
	{
		return new TransparentPixel(this.rgba);
	}

	public void setAlpha(int alpha)
	{
		rgba[3] = alpha;
	}

	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier
	 * (avec un espace supplémentaire en fin)
	 */
	public String toString()
	{
		return  ((Integer)rgba[0]).toString() + " " +
				((Integer)rgba[1]).toString() + " " +
				((Integer)rgba[2]).toString() + " " +
				((Integer)rgba[3]).toString() + " ";
	}

	// Permet de comparer le pixel passe en parametre avec le pixel courant
	public int compareTo(AbstractPixel p) {
		if (rgba[0] < ((TransparentPixel) p).rgba[0]
				&& rgba[1] < ((TransparentPixel) p).rgba[1]
				&& rgba[2] < ((TransparentPixel) p).rgba[2]
				&& rgba[3] < ((TransparentPixel) p).rgba[3]) {
			return -1;
		} else {
			if (rgba[0] == ((TransparentPixel) p).rgba[0]
					&& rgba[1] == ((TransparentPixel) p).rgba[1]
					&& rgba[2] == ((TransparentPixel) p).rgba[2]
					&& rgba[3] == ((TransparentPixel) p).rgba[3]) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
