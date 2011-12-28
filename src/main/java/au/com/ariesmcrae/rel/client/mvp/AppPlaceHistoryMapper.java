/*
 * Copyright 2005 - 2011 Victorian Workcover Authority
 * 222 Exhibition Street, Melbourne, VIC 3000, Australia.
 * All rights reserved. 
 * This software is the confidential and proprietary information of 
 * Victorian Workcover Authority.
 */
package au.com.ariesmcrae.rel.client.mvp;

import au.com.ariesmcrae.rel.client.place.RelationshipPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;


@WithTokenizers({RelationshipPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}