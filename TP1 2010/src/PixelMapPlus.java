import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author :
 * @date   :
 */

public class PixelMapPlus extends PixelMap implements ImageOperations
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}

	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image);
	}

	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image);
	}

	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}

	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// fait
		for (int i = 0;  i < this.getHeight(); i++)
		{
			for (int j = 0;  j < this.getWidth(); j++)
			{
				imageData[i][j]= this.imageData[i][j].Negative();
			}
		}
	}

	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// fait
		for (int i = 0;  i < this.getHeight(); i++)
		{
			for (int j = 0;  j < this.getWidth(); j++)
			{
				this.imageData[i][j] = this.imageData[i][j].toBWPixel();
			}
		}
	}

	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// fait
		for (int i = 0;  i < this.getHeight(); i++)
		{
			for (int j = 0;  j < this.getWidth(); j++)
			{
				this.imageData[i][j] = this.imageData[i][j].toGrayPixel();
			}
		}
	}

	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// fait
		for (int i = 0;  i < this.getHeight(); i++)
		{
			for (int j = 0;  j < this.getWidth(); j++)
			{
				this.imageData[i][j] = this.imageData[i][j].toColorPixel();
			}
		}

	}

	public void convertToTransparentImage()
	{
		// fait
		for (int i = 0;  i < this.getHeigth(); i++)
		{
			for (int j = 0;  j < this.getWidth(); j++)
			{
				this.imageData[i][j] = this.imageData[i][j].toTransparentPixel();
			}
		}

	}

	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// fait
		AbstractPixel[][] tableauTemp = new AbstractPixel[height][width];	//this.getHeight.... ??
			for(int i = 0; i < height, i++) {
				for(int j = 0; j < width, j++) {

					tableauTemp[i][j] = imageData[i][j];

				}
			}

			for(int i = 0; i < height, i++) {
				for(int j = 0; j < width, j++) {
					int a = (int)(0.5 + j * Math.cos(angleRadian) + i * Math.sin(angleRadian) - x * Math.cos(angleRadian) - y * Math.sin(angleRadian) + x);
					int b = (int)(0.5 + (-j) * Math.sin(angleRadian) + i * Math.cos(angleRadian) + x * Math.sin(angleRadian) - y * Math.cos(angleRadian) + y)
					if ( a < width && b < height ){
						imageData[i][j] = tableauTemp[a][b];
					}
				}
			}
	}

	/**
	 * Modifie la longueur et la largeur de l'image
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();

		// fait
		width = w;
		height = h;

	}

	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// fait
		for (int i = row0; (i < pm.getWidth()) || (i < Width); i++)
		{
			for (int j = col0; (j < pm.getHeigth()) || (j < Heigth; j++)
			{
				imageData[i + row0][j + col0] = pm.imageData[i][j];
			}
		}

	}

	/**
	 * Decoupe l'image
	 */
	public void crop(int h, int w)
	{
		// compl�ter
		AbstractPixel[][] tableauTemp = new AbstractPixel[height][width];
		for(int i = 0; i < height, i++) {
			for(int j = 0; j < width, j++) {

				tableauTemp[i][j] = imageData[i][j];

			}
		}
		int tempHeight = height;
		int tempWidth = width;
		clearData();
		AllocateMemory (ImageType, h, w);
		for(int i = 0; i < h, i++) {
			for(int j = 0; j < w, j++) {
				if ( i < tempHeight && j < tempWidth){
					imageData[i][j] = tableauTemp[i][j];
				}

			}
		}
	}

	/**
	 * Effectue une translation de l'image
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter

	}

	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();

		// compl�ter

	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur
	 * (sa valeur est entre min et max)
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max,
			AbstractPixel newPixel) {
		// compl�ter
		for(int i = 0 ; i<this.getHeigth() ; i++)
		{
			for (int j = 0 ; j<this.getWidth(); j++)
			{
				if(this.getPixel()>=min && this.getPixel()<=max)
					this.getPixel()=newPixel;
			}
		}

	}

	public void inverser() {
		// compl�ter

	}
}
