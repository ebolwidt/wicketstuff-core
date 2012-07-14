package org.wicketstuff.openlayers.event;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.wicketstuff.openlayers.api.LonLat;

public abstract class ClickEventListenerBehavior extends EventListenerBehavior {

	private static final long serialVersionUID = 1L;

	// TODO: whenever any type of event arrives, update the map object (the component of this behavior),
	// with the default event information, which has the map center, bounds and zoom, normal and converted.
	
	@Override
	public String getJSaddListener() {
		return getOpenLayersMap().getJSinvoke("addClickListener('" + getCallbackUrl() + "')");
	}

	@Override
	protected String getEvent() {
		return "click";
	}

	@Override
	protected void onEvent(AjaxRequestTarget target) {
		Request request = RequestCycle.get().getRequest();
		double lon = request.getRequestParameters().getParameterValue("lonConverted").toDouble();
		double lat = request.getRequestParameters().getParameterValue("latConverted").toDouble();
		int zoom = request.getRequestParameters().getParameterValue("zoom").toInt();
		LonLat lonLat = new LonLat(lon, lat);
		onClick(lonLat, zoom, target);
		onClick(lonLat, target);

	}

	/**
	 * To be implemented when the zoom level is not of interest.
	 */
	protected void onClick(LonLat lonLat, AjaxRequestTarget target) {
	}

	/**
	 * To be implemented when the zoom level is of interest.
	 */
	protected void onClick(LonLat lonLat, int zoom, AjaxRequestTarget target) {
	}

}
