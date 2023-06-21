import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {
	/**
	 * compares ratings first, captions if ratings are equal
	 * @param a
	 * @param b
	 * @return int value of comparisons
	 */
	public int compare(Photograph a, Photograph b) {
		if (a.getRating() > b.getRating()) {
			return -1;
		}
		if (a.getRating() < b.getRating()) {
			return 1;
		}
		if (a.getRating() == b.getRating()) {
			return a.getCaption().compareTo(b.getCaption());
		}
		return 0;
	}
}