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
package com.ariesmcrae.rel.client.view;


import com.ariesmcrae.rel.client.presenter.RelationshipPresenter;
import com.ariesmcrae.rel.model.Participant;
import com.ariesmcrae.rel.model.Relationship;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author ariesmcrae.com
 */
public class RelationshipViewImpl extends Composite implements RelationshipView {

	@UiTemplate("RelationshipView.ui.xml")
	interface RelationshipViewUiBinder extends UiBinder<Widget, RelationshipViewImpl> { }

	private static RelationshipViewUiBinder uiBinder = GWT.create(RelationshipViewUiBinder.class);

	@UiField DivElement spinnerDiv;
	@UiField ListBox listboxServer;
	@UiField ListBox multiBoxNameSpace;
	@UiField (provided = true) FlexTable diffTable;	
	
	private RelationshipPresenter presenter;


	
	public RelationshipViewImpl() {
		diffTable = new FlexTable();		
		initDiffTable();	
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	
	public void setPresenter(RelationshipPresenter newPresenter) {
		presenter = newPresenter;
	}
	
	
	
	private void initDiffTable() {
		diffTable.removeAllRows();		
		diffTable.setText(0, 0, "Key");
		diffTable.setText(0, 1, "CSV Value");
		diffTable.setText(0, 2, "Server Value");
		diffTable.getRowFormatter().addStyleName(0, "diffTableHeader");
		diffTable.setVisible(false);	
	}
	
	


	public void refreshNameSpaceListBox(JsArray<Relationship> relationships) {
		for (int i = 0; i < relationships.length(); i++) {
			Relationship relationship = relationships.get(i);
			multiBoxNameSpace.addItem(relationship.getGroup() + " - " + relationship.getName(), relationship.getNamespace());
		}
	}

	
	@UiHandler("listboxServer")
	void onChangeListboxServer(ChangeEvent event) {
		initDiffTable();		
		
		String selectedServerText = listboxServer.getItemText(listboxServer.getSelectedIndex());				
		
		if (!"DEV".equals(selectedServerText)) {
			Window.alert("Not yet implemented");
		}
		
		//TODO re-populate multiBoxNameSpace
		
	}
	
	
	
	@UiHandler("multiBoxNameSpace")
	void onChangeMultiBoxNameSpace(ChangeEvent event) {
		String selectedServer = retrieveSelectedServer();
		String selectedNameSpace = multiBoxNameSpace.getValue(multiBoxNameSpace.getSelectedIndex());
		
		presenter.onNameSpaceListBoxChange(selectedServer, selectedNameSpace);
	}



	public void populateRelationshipDiffTable(JsArray<Participant> participants) {
		initDiffTable();	
		
		for (int i = 0; i < participants.length(); i++) {
			Participant participant = participants.get(i);
			
			int newRow = i;			
			newRow++; //because we need to start at 1, since diffTable header is on position zero.
			
			diffTable.setText(newRow, 0, participant.getKey());
			diffTable.setText(newRow, 1, participant.getCsvValue());
			diffTable.setText(newRow, 2, participant.getServerValue());					

		}
		
		if (diffTable.getRowCount() > 1) {
			diffTable.setVisible(true);
		}
	}
	
	
	
	public String retrieveSelectedServer() {
		return listboxServer.getValue(listboxServer.getSelectedIndex());			
	}
	
	
	
	public void changeSpinnerVisibility(Visibility visibility) {
		spinnerDiv.getStyle().setVisibility(visibility);
	}


}

