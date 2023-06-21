import java.util.ArrayList;

/* Kristen Maggard , ksm5k
*
* Sources : Office hours
*/


public class Album extends PhotographContainer{

	/**
	 * constructor for Album class
	 * @param name
	 */
	public Album(String name) {
		super(name);
		this.photos = new ArrayList<Photograph>();
	}

}
