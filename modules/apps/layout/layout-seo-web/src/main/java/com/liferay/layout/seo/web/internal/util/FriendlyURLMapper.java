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

package com.liferay.layout.seo.web.internal.util;

import com.liferay.asset.display.page.constants.AssetDisplayPageWebKeys;
import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.display.page.util.AssetDisplayPageUtil;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public abstract class FriendlyURLMapper {

	public static FriendlyURLMapper create(
			AssetDisplayPageFriendlyURLProvider
				assetDisplayPageFriendlyURLProvider,
			ClassNameLocalService classNameLocalService, Language language,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return Optional.ofNullable(
			(InfoDisplayObjectProvider<?>)httpServletRequest.getAttribute(
				AssetDisplayPageWebKeys.INFO_DISPLAY_OBJECT_PROVIDER)
		).filter(
			infoDisplayObjectProvider -> {
				try {
					return AssetDisplayPageUtil.hasAssetDisplayPage(
						themeDisplay.getScopeGroupId(),
						infoDisplayObjectProvider.getClassNameId(),
						infoDisplayObjectProvider.getClassPK(),
						infoDisplayObjectProvider.getClassTypeId());
				}
				catch (PortalException portalException) {
					_log.error(portalException, portalException);

					return false;
				}
			}
		).map(
			infoDisplayObjectProvider ->
				(FriendlyURLMapper)new AssetDisplayPageFriendlyURLMapper(
					assetDisplayPageFriendlyURLProvider, classNameLocalService,
					infoDisplayObjectProvider, language, themeDisplay)
		).orElseGet(
			() -> new DefaultPageFriendlyURLMapper()
		);
	}

	public abstract String getMappedFriendlyURL(String url, Locale locale)
		throws PortalException;

	public abstract Map<Locale, String> getMappedFriendlyURLs(
			Map<Locale, String> friendlyURLs)
		throws PortalException;

	public static class AssetDisplayPageFriendlyURLMapper
		extends FriendlyURLMapper {

		public String getMappedFriendlyURL(String url, Locale locale)
			throws PortalException {

			if (_infoDisplayObjectProvider == null) {
				return url;
			}

			try {
				ClassName className = _classNameLocalService.getClassName(
					_infoDisplayObjectProvider.getClassNameId());

				ThemeDisplay clonedThemeDisplay =
					(ThemeDisplay)_themeDisplay.clone();

				clonedThemeDisplay.setI18nPath(_getI18nPath(locale));

				String languageId = _language.getLanguageId(locale);

				clonedThemeDisplay.setI18nLanguageId(languageId);
				clonedThemeDisplay.setLanguageId(languageId);

				clonedThemeDisplay.setLocale(locale);

				return _assetDisplayPageFriendlyURLProvider.getFriendlyURL(
					className.getClassName(),
					_infoDisplayObjectProvider.getClassPK(),
					clonedThemeDisplay);
			}
			catch (CloneNotSupportedException cloneNotSupportedException) {
				_log.error(
					cloneNotSupportedException, cloneNotSupportedException);
			}

			return url;
		}

		@Override
		public Map<Locale, String> getMappedFriendlyURLs(
				Map<Locale, String> friendlyURLs)
			throws PortalException {

			if (_infoDisplayObjectProvider == null) {
				return friendlyURLs;
			}

			Map<Locale, String> mappedFriendlyURLs = new HashMap<>();

			for (Map.Entry<Locale, String> entry : friendlyURLs.entrySet()) {
				mappedFriendlyURLs.put(
					entry.getKey(),
					getMappedFriendlyURL(entry.getValue(), entry.getKey()));
			}

			return mappedFriendlyURLs;
		}

		protected AssetDisplayPageFriendlyURLMapper(
			AssetDisplayPageFriendlyURLProvider
				assetDisplayPageFriendlyURLProvider,
			ClassNameLocalService classNameLocalService,
			InfoDisplayObjectProvider<?> infoDisplayObjectProvider,
			Language language, ThemeDisplay themeDisplay) {

			_assetDisplayPageFriendlyURLProvider =
				assetDisplayPageFriendlyURLProvider;
			_classNameLocalService = classNameLocalService;
			_infoDisplayObjectProvider = infoDisplayObjectProvider;
			_language = language;
			_themeDisplay = themeDisplay;
		}

		private String _getI18nPath(Locale locale) {
			Locale defaultLocale = LanguageUtil.getLocale(locale.getLanguage());

			if (LocaleUtil.equals(defaultLocale, locale)) {
				return StringPool.SLASH + defaultLocale.getLanguage();
			}

			return StringPool.SLASH + locale.toLanguageTag();
		}

		private final AssetDisplayPageFriendlyURLProvider
			_assetDisplayPageFriendlyURLProvider;
		private final ClassNameLocalService _classNameLocalService;
		private final InfoDisplayObjectProvider<?> _infoDisplayObjectProvider;
		private final Language _language;
		private final ThemeDisplay _themeDisplay;

	}

	public static class DefaultPageFriendlyURLMapper extends FriendlyURLMapper {

		@Override
		public String getMappedFriendlyURL(String url, Locale locale) {
			return url;
		}

		@Override
		public Map<Locale, String> getMappedFriendlyURLs(
			Map<Locale, String> friendlyURLs) {

			return friendlyURLs;
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(
		FriendlyURLMapper.class);

}