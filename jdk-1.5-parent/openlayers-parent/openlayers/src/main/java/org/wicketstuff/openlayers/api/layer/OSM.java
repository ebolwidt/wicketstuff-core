package org.wicketstuff.openlayers.api.layer;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.wicketstuff.openlayers.js.JSUtils;

public class OSM extends Layer implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private static final String OSMARENDER_URL = "http://tah.openstreetmap.org/Tiles/tile/${z}/${x}/${y}.png";

	public static enum OSMLayer
	{
		Mapnik, TilesAtHome, CycleMap
	}

	private OSMLayer layer;

	public OSM(String name, OSMLayer layer)
	{
		setName(name);
		this.layer = layer;
	}


	@Override
	protected void bindHeaderContributors(IHeaderResponse response)
	{

		response.renderJavaScriptReference("http://www.openstreetmap.org/openlayers/OpenStreetMap.js");
	}

	@Override
	public String getJSconstructor()
	{

		String quotedName = JSUtils.getQuotedString(getName());

		switch (layer)
		{
			case Mapnik :
				return getJSconstructor("OpenLayers.Layer.OSM.Mapnik", Arrays.asList(quotedName));

			case TilesAtHome :
				return getJSconstructor("OpenLayers.Layer.OSM",
					Arrays.asList(quotedName, JSUtils.getQuotedString(OSMARENDER_URL)));
			case CycleMap :
				return getJSconstructor("OpenLayers.Layer.OSM.CycleMap", Arrays.asList(quotedName));
			default :
				return getJSconstructor("OpenLayers.Layer.OSM.Mapnik", Arrays.asList(quotedName));
		}
	}
}
