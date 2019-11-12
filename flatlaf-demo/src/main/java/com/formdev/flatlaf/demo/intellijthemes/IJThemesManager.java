/*
 * Copyright 2019 FormDev Software GmbH
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

package com.formdev.flatlaf.demo.intellijthemes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.formdev.flatlaf.util.StringUtils;

/**
 * @author Karl Tauber
 */
class IJThemesManager
{
	final List<IJThemeInfo> bundledThemes = new ArrayList<>();

	void loadBundledThemes() {
		// load themes.properties
		Properties properties = new Properties();
		try( InputStream in = getClass().getResourceAsStream( "themes.properties" ) ) {
			if( in != null )
				properties.load( in );
		} catch( IOException ex ) {
			ex.printStackTrace();
		}

		// add info about bundled themes
		for( Map.Entry<Object, Object> e : properties.entrySet() ) {
			String resourceName = (String) e.getKey();
			List<String> strs = StringUtils.split( (String) e.getValue(), ',' );

			IJThemeInfo themeInfo = new IJThemeInfo();
			themeInfo.name = strs.get( 0 );
			themeInfo.resourceName = resourceName;
			themeInfo.sourceCodeUrl = strs.get( 1 );
			bundledThemes.add( themeInfo );
		}
	}
}