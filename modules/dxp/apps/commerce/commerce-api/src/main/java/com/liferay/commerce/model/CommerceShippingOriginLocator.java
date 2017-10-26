/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.model;

import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Locale;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Andrea Di Giorgi
 */
public interface CommerceShippingOriginLocator {

	public String getDescription(Locale locale);

	public String getName(Locale locale);

	public Map<CommerceAddress, Iterable<CommerceCartItem>> getOriginAddresses(
			CommerceCart commerceCart)
		throws Exception;

	public void renderConfiguration(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception;

	public void updateConfiguration(
			Map<String, String> parameterMap, ServiceContext serviceContext)
		throws Exception;

}