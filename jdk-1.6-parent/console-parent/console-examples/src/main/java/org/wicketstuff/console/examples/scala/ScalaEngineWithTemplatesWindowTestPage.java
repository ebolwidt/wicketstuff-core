/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wicketstuff.console.examples.scala;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.wicketstuff.console.engine.Lang;
import org.wicketstuff.console.examples.ConsoleBasePage;
import org.wicketstuff.console.scala.ScalaScriptEngineWithTemplatesWindow;
import org.wicketstuff.console.templates.PackagedScriptTemplates;
import org.wicketstuff.console.templates.ScriptTemplate;

public class ScalaEngineWithTemplatesWindowTestPage extends ConsoleBasePage
{

	private static final class OpenLink extends AjaxLink<Void>
	{
		private final ScalaScriptEngineWithTemplatesWindow window;
		private static final long serialVersionUID = 1L;

		private OpenLink(final String id, final ScalaScriptEngineWithTemplatesWindow window)
		{
			super(id);
			this.window = window;
		}

		@Override
		public void onClick(final AjaxRequestTarget target)
		{
			window.show(target);
		}
	}

	private static final long serialVersionUID = 1L;
	private ScalaScriptEngineWithTemplatesWindow window;
	private final OpenLink openLink;

	public ScalaEngineWithTemplatesWindowTestPage()
	{

		final IDataProvider<ScriptTemplate> dataProvider = new ListDataProvider<ScriptTemplate>(
			new PackagedScriptTemplates().findAll(Lang.SCALA));
		add(window = new ScalaScriptEngineWithTemplatesWindow("window", null, dataProvider));
		openLink = new OpenLink("link", window);
		add(openLink);
	}

	public OpenLink getOpenLink()
	{
		return openLink;
	}

}
