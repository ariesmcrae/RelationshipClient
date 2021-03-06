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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author ariesmcrae.com
 */
public interface RelationshipView  extends IsWidget{

	void setPresenter(RelationshipPresenter presenter);
	
	void refreshNameSpaceListBox(JsArray<Relationship> relationships);
	
	void populateRelationshipDiffTable(JsArray<Participant> participants);
	
	String retrieveSelectedServer();	
	
	void changeSpinnerVisibility(Visibility visibility);
	
	void changeNoResultsVisibility(Visibility visibility);
}
