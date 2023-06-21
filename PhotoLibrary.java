/**
* Homework 3
* Kristen Maggard , ksm5k

*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Kristen Shae
 *
 */

public class PhotoLibrary extends PhotographContainer{ 
	private int id; 
	private HashSet<Album> albums;
	
	/**
	 * constructor for PhotoLibrary
	 * @param name
	 * @param id
	 */
	public PhotoLibrary(String name, int id) {
		super(name);
		this.id = id;
		this.albums = new HashSet<Album>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the albums
	 */
	public HashSet<Album> getAlbums() {
		return albums;
	}
	
	
	/**
	 * @param p
	 * @return true or false depending on whether a photo was deleted from the account
	 */
	public boolean removePhoto(Photograph p) {
		if (photos.contains(p)){
			photos.remove(p);
			for (Album album: this.getAlbums()) {
				if (album.hasPhoto(p)) {
					album.removePhoto(p);
				}
			}
			return true;
		}
			return false;
	}
	
	
	/**
	 * @param o
	 * @return if the album names are the same
	 */
	public boolean equals(Object o) {
		if (o == null ) {
			return false;
		}
		if (!(o instanceof PhotoLibrary)) {
			return false;
		}
		PhotoLibrary otherPhotoLibrary = (PhotoLibrary) o;
		
		return (this.id == otherPhotoLibrary.getId());
	}
	
	/**
	 * @return the name of the album and an array list of photos in the album
	 */
	public String toString() {
		return "Name: " + this.name + " ID: " + this.id + " Photos: " + this.photos + "Albums: " + this.albums; // need to fix
	}
	

	static ArrayList<Photograph> commPhotographs = new ArrayList<Photograph>();
	
	/**
	 * @param a
	 * @param b
	 * @return array list with mutual photos
	 */
	public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b){
		for (int i = 0; i < a.photos.size(); i ++) {
			for (int k = 0; k < b.photos.size(); k ++) {
				if (a.photos.get(i).equals(b.photos.get(k))) {
					commPhotographs.add(a.photos.get(i));
				}
			}
		}
		return commPhotographs;
	}
	
	/**
	 * @param a
	 * @param b
	 * @return indicates percent similarity between a and b photos
	 */
	public static double similarity(PhotoLibrary a, PhotoLibrary b) {
		if (a.photos.isEmpty() || b.photos.isEmpty()) {
			return 0.0;
		} else {
			if (a.photos.size() > b.photos.size()) {
				return commPhotographs.size()/a.photos.size();
			} else {
				return commPhotographs.size()/b.photos.size();
			}
		}
		
	}
	
	/**
	 * @param albumName
	 * @return false if album in the list of albums has the given name, true if album was not and was thus added to albums
	 */
	public boolean createAlbum(String albumName) {
		Album newAlbum = new Album(albumName);
		
		for (Album album : albums)
		{
			if (album.equals(this.getAlbumByName(albumName))) {
				return false; }
			}
			albums.add(newAlbum);
			return true;
		}
	
	/**
	 * @param albumName
	 * @return true if the album by name albumName was in the list of albums and thus was removed, false if not
	 */
	public boolean removeAlbum(String albumName) {
		for (Album album : albums)
		{
			if (album.equals(this.getAlbumByName(albumName))) {
				albums.remove(album);
				return true; }
			} 
		return false;
	}
	
	/**
	 * @param p
	 * @param albumName
	 * @return true if photo was added to album by name albumName, false if not
	 */
	public boolean addPhotoToAlbum(Photograph p, String albumName) {
		if (p == null) {
			return false;
		}
		if (this.photos.contains(p) && this.albums.contains(getAlbumByName(albumName)) && !getAlbumByName(albumName).hasPhoto(p)) {
				this.getAlbumByName(albumName).addPhoto(p); 
				return true;
		}
		return false;
	}
	
	/**
	 * @param p
	 * @param albumName
	 * @return true if photo was removed, false if not
	 */
	public boolean removePhotoFromAlbum(Photograph p, String albumName) {
		Iterator<Album> itr = albums.iterator();
		if (photos.contains(p) && getAlbumByName(albumName) != null) {
			itr.next().getPhotos().remove(p); 
			return true;
		}
		return false;
	}
	
	/**
	 * @param albumName
	 * @return album if name of album in albums equals given albumName, null if not
	 */
	private Album getAlbumByName(String albumName) {
		for (Album album:albums) {
			if (album.getName().equals(albumName)){
				return album;
			}
		}
		return null;
	}
}
	