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
package au.com.ariesmcrae.rel.client;


import au.com.ariesmcrae.rel.client.mvp.AppPlaceHistoryMapper;
import au.com.ariesmcrae.rel.client.mvp.RelationshipActivityMapper;
import au.com.ariesmcrae.rel.client.place.RelationshipPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;


/**
 * @author Aries McRae
 */
public class RelationshipClient implements EntryPoint {


	public void onModuleLoad() {
		SimplePanel appWidget = new SimplePanel();		
        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        
        new ActivityManager(new RelationshipActivityMapper(clientFactory), eventBus).setDisplay(appWidget);        

		AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(clientFactory.getPlaceController(), eventBus, new RelationshipPlace());
		
		RootPanel.get().add(appWidget);
		
		historyHandler.handleCurrentHistory();        
	}

}
