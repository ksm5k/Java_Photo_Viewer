import java.io.File;

/**
 * @author Kristen Shae
 *
 */

public class Photograph implements Comparable<Photograph> {
	private String caption;
	private String filename;
	private String dateTaken;
	private int rating;
	private File imageFile;
	
	/**
	 * constructor for Photograph class with two parameters
	 * @param caption
	 * @param filename
	 */
	public Photograph(String caption, String filename) {
		this.caption = caption;
		this.filename = filename;
		this.imageFile = new File(filename); 
		
	}
	
	/**
	 * @return the imageFile
	 */
	public final File getImageFile() {
		return imageFile;
	}

	/**
	 * @param imageFile the imageFile to set
	 */
	public final void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	/**
	 * constructor for Photograph class with four parameters
	 * @param caption
	 * @param filename
	 * @param dateTaken
	 * @param rating
	 */
	public Photograph(String caption, String filename, String dateTaken, int rating) {
		this.caption = caption;
		this.filename = filename;
		this.dateTaken = dateTaken;
		this.rating = rating;
		this.imageFile = new File(filename);
	}

	/**
	 * @return the caption
	 */
	public final String getCaption() {
		return caption;
	}

	/**
	 * @return the filename
	 */
	public final String getFilename() {
		return filename;
	}
	
	/**
	 * @return the dateTaken
	 */
	public String getDateTaken() {
		return dateTaken;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		if (rating >= 0 && rating <= 5) {
			this.rating = rating;
		}
	}

	
	/** 
	 * @param o
	 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Photograph)) {
			return false;
		}
		
		Photograph otherPhotograph = (Photograph) o;
		return (this.caption.equals(otherPhotograph.caption) && 
			this.filename.equals(otherPhotograph.filename));
	}
	
	
	/**
	 * @return string stating the caption and filename of the photograph
	 */
	public String toString() {
		return "Caption: " + this.caption + " Filename: " + this.filename;
	}
	
	/**
	 * @param p
	 * @return int value given by comparing the dateTaken of two photos or their captions
	 */
	public int compareTo(Photograph p){
		if (this.dateTaken.compareTo(p.getDateTaken()) != 0){
			return this.dateTaken.compareTo(p.getDateTaken());
		}
		return this.caption.compareTo(p.getCaption());
	}
	
    @Override
    public int hashCode() {
        return (this.caption + "---" + this.filename).hashCode();
    }
    
}
