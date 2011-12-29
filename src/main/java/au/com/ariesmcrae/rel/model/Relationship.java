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
package au.com.ariesmcrae.rel.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * @author ariesmcrae.com
 */
public class Relationship extends JavaScriptObject {
	
	protected Relationship() {}
	
	public final native String getGroup() /*-{ return this.group; }-*/;
	public final native String getName() /*-{ return this.name; }-*/;
	public final native String getNamespace() /*-{ return this.namespace; }-*/;	
	
	public static final native JsArray<Relationship> parse(String json) /*-{
    	return eval(json);
  	}-*/;
	
}

