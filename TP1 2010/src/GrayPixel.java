/**
 * Classe de pixel en tons de gris
 * @author :
 * @date : 
 */

public class GrayPixel  extends AbstractPixel 
{
	int pixel; // donnee du pixel
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	GrayPixel()
	{
		this.pixel = 255;
	}
	
	/**
	 * Constructeur par parametre
	 * @param pixel : valeur du pixel
	 */
	GrayPixel(int pixel)
	{
		this.pixel = pixel; //fait
		
	}
	
	/**
	 * Renvoie la valeur du pixel
	 */
	public int getPixel()
	{
		return pixel;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param pixel: valeur a assigner 
	 */
	public void setPixel(int pixel)
	{
		this.pixel = pixel;
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
        
        if (this.pixel <= 127){
            BWPixel pixelNB= new BWPixel (false);
			return pixelNB;
        } else {
            BWPixel pixelNB= new BWPixel (true);
			return pixelNB;
        
        }

		 //fait
		
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		return new GrayPixel(this.pixel); //fait
		
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
	
		int[] rgb = new int[3];
		rgb[0] = rgb[1] = rgb[2] = this.pixel;
		ColorPixel pixelCouleur = new ColorPixel( rgb );
		return pixelCouleur;
		
		// fait
		
	}
	
	public TransparentPixel toTransparentPixel()
	{
		
		int[] rgba = new int[4];
		rgba[0] = rgba[1] = rgba[2] = this.pixel;
		rgba[3] = 255;
		TransparentPixel pixelTransparent = new TransparentPixel( rgba );
		return pixelTransparent;
		
		// fait
		
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
        return new GrayPixel(this.pixel -= 255);
	
		// fait
	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return ((Integer)(pixel)).toString() + " ";
	}
	
	public int compareTo(AbstractPixel p) {
		if (this.pixel < ((GrayPixel) p).pixel) {
			return -1;
		} else {
			if (this.pixel == ((GrayPixel) p).pixel) {
				return 0;
			} else {
				return 1;
			}
		}
		
	}
}
