/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.shop.by.diagram.type;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.shop.by.diagram.model.CPDefinitionDiagramSetting;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CPDefinitionDiagramType {

	public String getKey();

	public String getLabel(Locale locale);

	public void render(
			CPDefinitionDiagramSetting cpDefinitionDiagramSetting,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception;

}