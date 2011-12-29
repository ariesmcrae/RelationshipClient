/*
 * Copyright 2011 Aries McRae. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.com.ariesmcrae.rel.client.event;

import au.com.ariesmcrae.rel.client.view.RelationshipView;
import au.com.ariesmcrae.rel.model.Relationship;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

/**
 * @author ariesmcrae.com
 */
public class GetAllRelationshipsCallback implements RequestCallback {
	
	private RelationshipView view;
	
	public GetAllRelationshipsCallback(RelationshipView theView) {
		view = theView;
	}
	
	
	public void onResponseReceived(Request request, Response response) {
		if (response.getStatusCode() != Response.SC_OK) {
			GWT.log("Exiting prematurely. Callback is not " + Response.SC_OK + " but statusCode=" + response.getStatusCode());
			Document.get().getBody().getStyle().setCursor( Cursor.DEFAULT );

			return;
		}

		view.refreshNameSpaceListBox(Relationship.parse(response.getText()));
	}

	public void onError(Request request, Throwable e) {
		GWT.log("Callback error=" + e.getMessage());
		Document.get().getBody().getStyle().setCursor( Cursor.DEFAULT );		
	}

}
