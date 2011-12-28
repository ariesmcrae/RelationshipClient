package au.com.ariesmcrae.rel.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * A place object representing a particular state of the UI. A Place can be
 * converted to and from a URL history token by defining a
 * {@link PlaceTokenizer} for each {@link Place}, and the
 * {@link PlaceHistoryHandler} automatically updates the browser URL
 * corresponding to each {@link Place} in your app.
 */
public class RelationshipPlace extends Place {
	
	
	/**
	 * PlaceTokenizer knows how to serialize the Place's state to a URL token.
	 */
	public static class Tokenizer implements PlaceTokenizer<RelationshipPlace> {

		// @Override
		public String getToken(RelationshipPlace place) {
			return null;
		}

		// @Override
		public RelationshipPlace getPlace(String token) {
			return new RelationshipPlace();
		}
	}
}
