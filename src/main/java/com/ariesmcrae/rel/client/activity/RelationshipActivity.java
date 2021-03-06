package com.ariesmcrae.rel.client.activity;


import com.ariesmcrae.rel.client.ClientFactory;
import com.ariesmcrae.rel.client.event.GetAllRelationshipsCallback;
import com.ariesmcrae.rel.client.event.GetParticipantsCallback;
import com.ariesmcrae.rel.client.presenter.RelationshipPresenter;
import com.ariesmcrae.rel.client.view.RelationshipView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * @author ariesmcrae.com
 */
public class RelationshipActivity extends AbstractActivity implements RelationshipPresenter {

	private final ClientFactory clientFactory;
	private RelationshipView view;
	
	public RelationshipActivity(ClientFactory newClientFactory) {
		clientFactory = newClientFactory;
		view = clientFactory.getRelationshipView();			
	}

	
	
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		fetchRelationshipNameSpaces();
		panel.setWidget(view);
		view.changeNoResultsVisibility(Visibility.HIDDEN);	
		bind();		
	}	

	
	
	public void bind() {
		view.setPresenter(this);
	}
	

	
	public void goTo(Place newPlace) {
		clientFactory.getPlaceController().goTo(newPlace);
	}


	
	private void fetchRelationshipNameSpaces() {
		String selectedServer = view.retrieveSelectedServer();
		final String GET_RELATIONSHIPS_URL = URL.encode(selectedServer + "/relationships"); //e.g. http://relationshipsrvr.appspot.com/relationships. TODO dev properties file.		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, GET_RELATIONSHIPS_URL);
	    builder.setHeader("Accept", "application/json");
	    builder.setCallback(new GetAllRelationshipsCallback(view)); 	    
	    
	    try {
	    	view.changeSpinnerVisibility(Visibility.VISIBLE);
			builder.send();
		} catch (RequestException e) {
	    	view.changeSpinnerVisibility(Visibility.HIDDEN);			
			GWT.log("doRequestBuilder exception=" + e.getMessage());	
		}	    
	}	
	

	
	
	public void onNameSpaceListBoxChange(String selectedServer, String selectedNameSpace) {
		String escapedNamespace = selectedNameSpace.replaceAll("/", "%2F");
		final String GET_PARTICIPANTS_URL = selectedServer + "/relationships/" + escapedNamespace; //e.g. http://relationshipsrvr.appspot.com/relationships/http:%2F%2FLib_Acction_Extract%2Frel%2Fref%2Fv1%2FREVIEW_RSN_CODE. TODO dev properties file.
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, GET_PARTICIPANTS_URL);
	    builder.setHeader("Accept", "application/json");
	    builder.setCallback(new GetParticipantsCallback(view)); 	    
	    
	    try {
	    	view.changeSpinnerVisibility(Visibility.VISIBLE);
			builder.send();
		} catch (RequestException e) {
	    	view.changeSpinnerVisibility(Visibility.HIDDEN);			
			GWT.log("doRequestBuilder exception=" + e.getMessage());	
		}	    
	}
	
	
	

}
