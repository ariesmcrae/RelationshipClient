/*
 * Copyright 2005 - 2011 Victorian Workcover Authority
 * 222 Exhibition Street, Melbourne, VIC 3000, Australia.
 * All rights reserved. 
 * This software is the confidential and proprietary information of 
 * Victorian Workcover Authority.
 */
package com.ariesmcrae.rel.client.mvp;


import com.ariesmcrae.rel.client.ClientFactory;
import com.ariesmcrae.rel.client.activity.RelationshipActivity;
import com.ariesmcrae.rel.client.place.RelationshipPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * @author ariesmcrae.com
 */
public class RelationshipActivityMapper implements ActivityMapper {
    private final ClientFactory clientFactory;

    public RelationshipActivityMapper(final ClientFactory theClientFactory) {
        clientFactory = theClientFactory;
    }

    
    
    public Activity getActivity(final Place place) {
        if (place instanceof RelationshipPlace) {
            return new RelationshipActivity(clientFactory);
        } else {
        	return null;
        }
    }
}
