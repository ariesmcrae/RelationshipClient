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
package au.com.ariesmcrae.rel.client.view;

import au.com.ariesmcrae.rel.client.presenter.RelationshipPresenter;
import au.com.ariesmcrae.rel.model.Relationship;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author ariesmcrae.com
 */
public class RelationshipViewImpl extends Composite implements RelationshipView {

	@UiTemplate("RelationshipView.ui.xml")
	interface RelationshipViewUiBinder extends UiBinder<Widget, RelationshipViewImpl> { }

	private static RelationshipViewUiBinder uiBinder = GWT.create(RelationshipViewUiBinder.class);

	@UiField ListBox multiBoxNameSpace;
	
	private RelationshipPresenter presenter;


	
	public RelationshipViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	
	public void setPresenter(RelationshipPresenter newPresenter) {
		presenter = newPresenter;
		
	}
	


	public void refreshNameSpaceListBox(JsArray<Relationship> relationships) {
		for (int i = 0; i < relationships.length(); i++) {
			Relationship relationship = relationships.get(i);
			multiBoxNameSpace.addItem(relationship.getGroup() + " - " + relationship.getName(), relationship.getNamespace());
		}
	}
	
	
	
	@UiHandler("multiBoxNameSpace")
	void onChange(ChangeEvent event) {
		String selectedNameSpace = multiBoxNameSpace.getValue(multiBoxNameSpace.getSelectedIndex());
		presenter.onNameSpaceListBoxChange(selectedNameSpace);
	}



	public void populateRelationshipDiffTable() {
		Window.alert("not yet implemented");
	}
	

}

