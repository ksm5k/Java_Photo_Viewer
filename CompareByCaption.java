
import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph>{
	/**
	 * compares captions first, ratings if captions equal
	 * @param a, a Photograph
	 * @param b, a Photograph
	 * @return int value of comparison between captions or ratings
	 */
	public int compare(Photograph a, Photograph b) {
		if (a.getCaption().compareTo(b.getCaption()) != 0) {
			return a.getCaption().compareTo(b.getCaption());
		}
		else {
			if (a.getRating() < b.getRating()) {
				return 1;
			}
			else if (a.getRating() > b.getRating()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}

}
