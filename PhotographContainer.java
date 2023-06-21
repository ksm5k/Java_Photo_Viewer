import java.util.ArrayList;

/**
 * @author Kristen Shae
 *
 */
public abstract class PhotographContainer {
	protected String name;
	protected ArrayList<Photograph> photos;
	
	public PhotographContainer(String name) {
		this.name = name;// ask about this
		this.photos = new ArrayList<Photograph>();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the photos
	 */
	public ArrayList<Photograph> getPhotos() {
		return photos;
	}
	/**
	 * @param photograph called p
	 * @return whether or not photograph was added
	 */
	public boolean addPhoto(Photograph p) {
		if (!photos.contains(p)){
			photos.add(p);
			return true;
		} 
		return false;
	}
	
	/**
	 * @param photograph called p
	 * @return if the photo was in array list photos
	 */
	public boolean hasPhoto(Photograph p) {
		return photos.contains(p);
	}
	
	/**
	 * @param p
	 * @return true if photos contains p and p is thus removed, otherwise false
	 */
	public boolean removePhoto(Photograph p) {
		if (photos.contains(p)) {
			photos.remove(p);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @return number of photos
	 */	
	public int numPhotographs() {
		return photos.size();
	}
	
	/**
	 * @param o
	 * @return true or false
	 */
	public boolean equals(Object o) {
		if (o == null ) {
			return false;
		}
		if (!(o instanceof PhotographContainer)) {
			return false;
		}
		
		PhotographContainer otherPhotographContainer = (PhotographContainer) o;
		return this.name.equals(otherPhotographContainer.getName());
	}
	
	/**
	 * @return string that says the name of the PhotographContainer and the filenames
	 */
	public String toString() {
		return "Name: " + this.name + "\n" + "Photo filenames: " + this.photos;
	}
	
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
	
	/**
	 * @param rating
	 * @return new array list with photos that have a rating equal to or greater the given
	 */
	public ArrayList<Photograph> getPhotos(int rating){
		ArrayList<Photograph> ratedPhotos = new ArrayList<Photograph>();
		if ( rating > 5 || rating < 0) {
			return null;
		} for (Photograph photo : photos) {
			if (photo.getRating() >= rating) {
				ratedPhotos.add(photo);
			}
		}
		return ratedPhotos;
	}
	
	/**
	 * @param year
	 * @return ArrayList of photos taken in the same year or null if year is invalid
	 */
	public ArrayList<Photograph> getPhotosInYear(int year) {
		ArrayList<Photograph> sameYear = new ArrayList<Photograph>();
		if (year >= 1000 && year <= 9999) {
			for (Photograph photo : photos) {
				int photoYear = Integer.valueOf(photo.getDateTaken().substring(0, 4));
				if (photoYear == year) {
					sameYear.add(photo);
				return sameYear; }
			else {
			return null;
			}
		}
		}
		return sameYear;
	}
	
	/**
	 * @param month
	 * @param year
	 * @return ArrayList of photos taken in the same year and same month, or null if year and month values are invalid
	 */
	public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
		ArrayList<Photograph> sameYearMonth = new ArrayList<Photograph>();
		sameYearMonth.clear();
		if (year >= 1000 && year <= 9999 && month <= 12 && month >= 1) {
			for (int i = 0; i < photos.size(); i++) {
				if (Integer.valueOf(this.photos.get(i).getDateTaken().substring(0,4)) == year && Integer.valueOf(this.photos.get(i).getDateTaken().substring(5,7)) == month) {
					sameYearMonth.add(this.photos.get(i));
				}
			}
			return sameYearMonth;
		} else {
			return null;
		}
	}
	
	/**
	 * @param date
	 * @return true if year, month, and day dates are valid, false if not
	 */
	public boolean validDate(String date) {
		
		if (date.length() < 10) {
			return false;
		}
		
		else {
			int dateYear = Integer.valueOf(date.substring(0,4));
			int dateMonth = Integer.valueOf(date.substring(5,7));
			int dateDay = Integer.valueOf(date.substring(8,10));
			
			if (dateYear < 1000 || dateYear > 9999 ) {
				return false;
			}
			if (dateMonth < 1 || dateMonth > 12) {
				return false;
			}
			if (dateDay < 1 || dateDay > 31) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * @param strings beginDate and endDate
	 * @return new ArrayList, betweenDates, that contains photos with dateTaken between beginDate and endDate or null
	 */	
	public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
		ArrayList<Photograph> betweenDates = new ArrayList<Photograph>();
		
		if (!validDate(beginDate)) {
			return null;
		}
		
		if (!validDate(endDate)) {
			return null;
		}
		
		if (beginDate.compareTo(endDate) > 0) {
			return null;
		}
		
		for (Photograph photo:photos) {
			
			if (beginDate.compareTo(photo.getDateTaken()) <= 0 && endDate.compareTo(photo.getDateTaken()) >= 0) {
				betweenDates.add(photo);
			}
		}
		return betweenDates;
	}
}
