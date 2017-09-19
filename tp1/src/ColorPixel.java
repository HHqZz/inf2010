/*************************************************************
* Titre: Travail pratique #1 - INF2010
* Date:  18 Septembre 2017
* Auteur : Constantin Bouis 1783438 et Axel Templier 1837967
**************************************************************/



public class ColorPixel extends AbstractPixel
{
	public int[] rgb; // donnees de l'image

	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel()
	{
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}

	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner
	 */
	ColorPixel(int[] rgb)
	{
		this.rgb = new int[3];
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	}

	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		int moyenne = (this.rgb[0]+ this.rgb[1] + this.rgb[2] )/ 3 ;
		if (moyenne<=127) {
			BWPixel pixelNB = new BWPixel (false) ;
			return  pixelNB;
		}
		else {
			BWPixel pixelNB = new BWPixel(true) ;
			return  pixelNB;
		}
	}

	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		int moyenne = (this.rgb[0]+ this.rgb[1] + this.rgb[2] )/ 3 ;
		GrayPixel pixelGray = new GrayPixel(moyenne);
		return pixelGray;
	}

	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		return new ColorPixel(this.rgb);
	}

	/**
	 * Renvoie un pixel copie de type transparent
	 */
	public TransparentPixel toTransparentPixel()
	{
		int[] rgb = new int[4];
		rgb[0] = this.rgb[0];
		rgb[1] = this.rgb[1];
		rgb[2] = this.rgb[2];
		rgb[3] = 255;
		TransparentPixel pixelTransparent= new TransparentPixel(rgb);
		return pixelTransparent;
	}

	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		int[] temp = new int[3];
		temp[0] = 255-this.rgb[0];
		temp[1] = 255-this.rgb[1];
		temp[2] = 255-this.rgb[2];

		return new ColorPixel(temp);
	}

	public void setAlpha(int alpha)
	{}

	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgb[0]).toString() + " " +
				((Integer)rgb[1]).toString() + " " +
				((Integer)rgb[2]).toString() + " ";
	}

	/**
	*Compare les valeurs du pixel passe
	* en parametre avec le pixel courant
	*/
	public int compareTo(AbstractPixel p) {
		if (rgb[0] < ((ColorPixel) p).rgb[0]
				&& rgb[1] < ((ColorPixel) p).rgb[1]
				&& rgb[2] < ((ColorPixel) p).rgb[2]) {
			return -1;
		} else {
			if (rgb[0] == ((ColorPixel) p).rgb[0]
					&& rgb[1] == ((ColorPixel) p).rgb[1]
					&& rgb[2] == ((ColorPixel) p).rgb[2]) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
