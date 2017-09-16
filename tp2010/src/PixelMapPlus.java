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
	 * @paramtype : type de l'image a creer (BW/Gray/Color)
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
		imageType = ImageType.BW;
		for (int i = 0;  i < this.height; i++)
		{
			for (int j = 0;  j < this.width; j++)
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
		imageType = ImageType.Gray;
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
		for (int i = 0;  i < this.getHeight(); i++){
			for (int j = 0;  j < this.getWidth(); j++)
			{
				this.imageData[i][j] = this.imageData[i][j].toColorPixel();
			}
		}

	}

	public void convertToTransparentImage()
	{
		// fait
		for (int i = 0;  i < this.getHeight(); i++)
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
	 * @paramclockWise : Direction de la rotation[i][j] = tableauTemp[i][
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// fait
		// Creation d'un tableau temporaire
		AbstractPixel tableauTemp[][] = new AbstractPixel[this.height][this.width];
		// variable qui permet d'eviter la repetion et fluidifie la lecture du calcul.
		double cosTheta = Math.cos(angleRadian);
		double sinTheta = Math.sin(angleRadian);
		// On Parcour le tableau
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				// On effectue le calcul de la rotation
				int xAncien = (int) (i * cosTheta + j * sinTheta - cosTheta * x - sinTheta * y + x);
				int yAncien = (int) (i * -sinTheta + j * cosTheta + sinTheta * x - cosTheta * y + y);
				// On cr�e un pixel blanc
				AbstractPixel tempPixel = new BWPixel();
				// Si les coordonnees sont bien dans l'interval alors on met le pixel dans le tableau temporaire
				// Sinon on met un Pixel blanc a la place.
				if (xAncien >= 0 && xAncien < this.height && yAncien >= 0 && yAncien < this.width) {
					tableauTemp[i][j] = imageData[xAncien][yAncien];
				}
				
				tableauTemp[i][j] = tempPixel;
			}
		}
		// On copie le tableau temporaire dans le tableau d'origine
		this.imageData = tableauTemp;
	}


	/**
	 * Modifie la longueur et la largeur de l'image
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur[i][j] = tableauTemp[i][
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		// on verifie si les valeurs rentr�e sont corect
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();

		// fait
		// On cr�e un tableau temporaire
		
		AbstractPixel[][] tableauTemp;
		// on copie le tableau existant a l'interieur du tableau temporaire
        tableauTemp= imageData;
        // on sauvegarder l'ancienne hauteur et l'ancienne largeur
		int ancienWidth = width;
		int ancienHeight = height;
		// on clear l'ancien tableau
		clearData();
		// on alloue un espace memoire (remplie de pixel blanc)
		AllocateMemory(imageType, h, w);
		// On effectue la redimension de l'image
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int x = (i * ancienHeight)/h;
				int y = (j * ancienWidth)/w;
				// On place les modifications dans le tableau d'origine 
				imageData[i][j] = tableauTemp[x][y];

			}
		}


	}

	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		
	// On met a jour le type de l'image
		if (pm.imageType == ImageType.Transparent)
			imageType = ImageType.Transparent;
		if(pm.imageType == ImageType.Color && imageType != ImageType.Transparent)
			imageType = ImageType.Color;
		if(pm.imageType == ImageType.Gray && imageType == ImageType.BW)
			imageType = ImageType.Gray;
		
		// On parcour le tableau
		for(int i = 0; (i + row0) < height && i < pm.height; i++)
		{
			for(int j = 0; (j + col0) < width && j < pm.width; j++)
			{
				// On insert le pixel pm a l'endroit voulu
				imageData[i + row0][j + col0] = pm.imageData[i][j];
			}
		}

	}

	/**
	 * Decoupe l'image
	 */
	public void crop(int h, int w)
	{
		// On cr�e un tableau temporaire qui permet de stocker le tableau d'origine
		AbstractPixel[][] tableauTemp;
        tableauTemp= imageData;
        // On sauvegarde la hauteur et la largeur du tableau d'origine
		int tempHeight = height;
		int tempWidth = width;
		// On clear l'ancien tableau
		clearData();
		// on alloue un espace memoire (remplie de pixel blanc)
		AllocateMemory (imageType, h, w);
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				// Si le piexel est dans l'intervalle du tableau d'origine alors on le met dans image data
				// sinon on place un pixel blanc
				if ( i < tempHeight && j < tempWidth){
					imageData[i][j] = tableauTemp[i][j];
				} else {
					imageData[i][j] = new BWPixel();
					} 

			}
		}
	}

	/**
	 * Effectue une translation de l'image
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// On cr�e un tableau temporaire
		AbstractPixel[][] tableauTemp = new AbstractPixel[height][width];
		// On parcour le tableau d'origine
		for(int i = 0 ; i<this.getHeight(); i++)
		{
			for (int j = 0 ; j < this.getWidth(); j++)
			{
				// On effectue les le calcul dans des variables pour fluidifier le reste du programme
				int x = i - rowOffset;
				int y = j - colOffset;
				// Si le pixel est dans l'intervalle alors on place le pixel dans le tableau temp
				// sinon on met un pixel blanc
				if((x < width) && (y < height) && (x >= 0) && (y >= 0)){
					tableauTemp[i][j]= imageData[x][y];
					} else{
						tableauTemp[i][j]= new BWPixel(true);
					}
			}
		}
		// On copie le tableau temporaire dans le tableau d'origine
		imageData = tableauTemp;
	}

	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		// On verifie que les valeurs passer en paraetre soit correcte
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();

		// On a la hauteur et la largeur de l'image zoomer
        int heightZoom = (int)(this.getHeight()/zoomFactor);
        int widthZoom = (int)(this.getWidth()/zoomFactor);
		
        // 	On cr�e un tableau temporaire
		AbstractPixel[][] tableauTemp = new AbstractPixel[heightZoom][widthZoom];
		
		// Si le cadre depasse vers les negatifs, fixer l origine a 0
		int row0 = Math.max(0, y - (heightZoom >> 1));
		int col0 = Math.max(0, x - (widthZoom >> 1));

		// Si le cadre depasse vers les positifs, fixer l aorigine 

		row0 = Math.min(row0, height - heightZoom - 1);
		col0 = Math.min(col0, width - widthZoom - 1);

		if (col0 < 0 ||  row0 < 0)
			throw new IllegalArgumentException();
		

			// On parcour tout le tableau  du debut jusqu'a ca sa nouvelle taille 
			for(int i= 0; i < heightZoom; i++ )
			{
				for(int j = 0; j < widthZoom; j++ )
				{
					//On met dans le tableau temporaire le pixel 
					tableauTemp[i][j] = imageData[i + row0][j + col0];
				}
			}						
		// On parcour le tableau en totaliter
		for(int i=0; i < height; i++ )
        {
            for(int j = 0; j <width; j++ )
            {
            	// On place dans chaque case le nouveau pixel 
				imageData[i][j] = tableauTemp[(i*heightZoom)/ height][(j*widthZoom)/ width];
            }
        }
       

		// fait

	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur
	 * (sa valeur est entre min et max)
	 * 
	 * 
	 *
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max,
			AbstractPixel newPixel) {
		// fait
		
		// On parcour le tableau
		for(int i = 0 ; i<this.getHeight() ; i++)
		{
			for (int j = 0 ; j<this.getWidth(); j++)
			{
				// Remplace les pixels dont la couleur est entre le min et le max avec
				// le nouveau pixel
				if(this.imageData[i][j].compareTo(min) == 1 && this.imageData[i][j].compareTo(max) ==  -1 )
                this.imageData[i][j]=newPixel;
			}
		}

	}

	public void inverser() {
		//  fait
		// On cree un tableau temporaire
		AbstractPixel[][] tableauTemp = new AbstractPixel[height][width];
		// On parcour le tableau d'origine
		for(int i = 0 ; i<this.getHeight() ; i++)
		{
			for(int j = 0 ; j< this.getWidth(); j++)
			{
				// Inverse l'image en mettant les pixels qui sont en haut en bas
				tableauTemp[i][j]= imageData[this.getHeight()-i - 1][j];
			}
		}
		// On copie le tableau temporaire dans le tableau d'origine
		imageData = tableauTemp;
		

	}
}
