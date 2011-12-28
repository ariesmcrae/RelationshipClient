package au.com.ariesmcrae.rel.client.activity;

import au.com.ariesmcrae.rel.client.ClientFactory;
import au.com.ariesmcrae.rel.client.event.GetAllRelationshipsCallback;
import au.com.ariesmcrae.rel.client.presenter.RelationshipPresenter;
import au.com.ariesmcrae.rel.client.view.RelationshipView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author Aries McRae
 */
public class RelationshipActivity extends AbstractActivity implements RelationshipPresenter {

	private final ClientFactory clientFactory;
	private RelationshipView view;
	private HTML ajaxLoad = new HTML("<img src=\"ajax-loader.gif\"/>");
	
	public RelationshipActivity(ClientFactory newClientFactory) {
		clientFactory = newClientFactory;
	}

	
	
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getRelationshipView();		
		fetchRelationshipNameSpaces();
		panel.setWidget(view);

		bind();		
	}	

	
	
	private void fetchRelationshipNameSpaces() {
		final String GET_RELATIONSHIPS_URL = URL.encode("http://localhost:9081/RelationshipLoader/relationships"); //TODO dev properties file.		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, GET_RELATIONSHIPS_URL);
	    builder.setHeader("Accept", "application/json");
	    builder.setCallback(new GetAllRelationshipsCallback(view)); 	    
	    
	    try {
	    	ajaxLoad.setVisible(true);
	    	
			builder.send();
		} catch (RequestException e) {
			GWT.log("doRequestBuilder exception=" + e.getMessage());	
		}	    
	}



	public void bind() {
		view.setPresenter(this);
	}
	

	
	public void goTo(Place newPlace) {
		clientFactory.getPlaceController().goTo(newPlace);
	}



	public void onNameSpaceListBoxChange(String selectedNameSpace) {
		System.out.println("xxxxxselectedNameSpace=" + selectedNameSpace);
		view.populateRelationshipDiffTable();
	}

}
