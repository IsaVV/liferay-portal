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

package com.liferay.dynamic.data.mapping.internal.io;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldOptions;
import com.liferay.dynamic.data.mapping.model.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.model.DDMFormRule;
import com.liferay.dynamic.data.mapping.model.DDMFormSuccessPageSettings;
import com.liferay.dynamic.data.mapping.model.LocalizedValue;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Pablo Carvalho
 */
public abstract class BaseDDMFormDeserializerTestCase extends BaseDDMTestCase {

	@Test
	public void testDDMFormAndFieldsDefaultLocales() throws Exception {
		String serializedDDMForm = read(
			StringBundler.concat(
				"ddm-form-", getDeserializerType(),
				"-deserializer-different-default-locale",
				getTestFileExtension()));

		DDMForm ddmForm = deserialize(serializedDDMForm);

		Assert.assertEquals(LocaleUtil.BRAZIL, ddmForm.getDefaultLocale());

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		DDMFormField selectDDMFormField = ddmFormFieldsMap.get("Select5979");

		LocalizedValue selectLabel = selectDDMFormField.getLabel();

		Assert.assertEquals(LocaleUtil.BRAZIL, selectLabel.getDefaultLocale());

		DDMFormFieldOptions ddmFormFieldOptions =
			selectDDMFormField.getDDMFormFieldOptions();

		for (String optionValue : ddmFormFieldOptions.getOptionsValues()) {
			LocalizedValue optionLabel = ddmFormFieldOptions.getOptionLabels(
				optionValue);

			Assert.assertEquals(
				LocaleUtil.BRAZIL, optionLabel.getDefaultLocale());
		}
	}

	@Test
	public void testDDMFormDeserialization() throws Exception {
		String serializedDDMForm = read(
			StringBundler.concat(
				"ddm-form-", getDeserializerType(), "-deserializer-test-data",
				getTestFileExtension()));

		DDMForm ddmForm = deserialize(serializedDDMForm);

		testAvailableLocales(ddmForm);
		testDDMFormRules(ddmForm.getDDMFormRules());
		testDefaultLocale(ddmForm);

		Map<String, DDMFormField> ddmFormFieldsMap =
			ddmForm.getDDMFormFieldsMap(true);

		testBooleanDDMFormField(ddmFormFieldsMap.get("Boolean2282"));
		testDateDDMFormField(ddmFormFieldsMap.get("Date2510"));
		testDecimalDDMFormField(ddmFormFieldsMap.get("Decimal3479"));
		testDocumentLibraryDDMFormField(
			ddmFormFieldsMap.get("Documents_and_Media4036"));
		testHTMLDDMFormField(ddmFormFieldsMap.get("HTML4512"));
		testNestedDDMFormFields(ddmFormFieldsMap.get("Text6980"));
		testRadioDDMFormField(ddmFormFieldsMap.get("Radio5699"));

		testDDMFormSuccessPageSettings(ddmForm.getDDMFormSuccessPageSettings());
	}

	protected abstract DDMForm deserialize(String serializedDDMForm)
		throws PortalException;

	protected abstract String getDeserializerType();

	protected abstract String getTestFileExtension();

	protected void testAvailableLocales(DDMForm ddmForm) {
		Set<Locale> availableLocales = ddmForm.getAvailableLocales();

		Assert.assertEquals(
			availableLocales.toString(), 2, availableLocales.size());
		Assert.assertTrue(
			availableLocales.toString(),
			availableLocales.contains(LocaleUtil.US));
		Assert.assertTrue(
			availableLocales.toString(),
			availableLocales.contains(LocaleUtil.BRAZIL));
	}

	protected void testBooleanDDMFormField(DDMFormField ddmFormField) {
		Assert.assertNotNull(ddmFormField);
		Assert.assertEquals("boolean", ddmFormField.getDataType());
		Assert.assertEquals("keyword", ddmFormField.getIndexType());

		LocalizedValue label = ddmFormField.getLabel();

		Assert.assertEquals("Boolean", label.getString(LocaleUtil.US));
		Assert.assertEquals("Booleano", label.getString(LocaleUtil.BRAZIL));

		LocalizedValue predefinedValue = ddmFormField.getPredefinedValue();

		Assert.assertEquals("false", predefinedValue.getString(LocaleUtil.US));

		Assert.assertEquals("checkbox", ddmFormField.getType());
		Assert.assertTrue(ddmFormField.isRepeatable());
		Assert.assertFalse(ddmFormField.isRequired());
		Assert.assertFalse(ddmFormField.isShowLabel());
	}

	protected void testDateDDMFormField(DDMFormField ddmFormField) {
		Assert.assertNotNull(ddmFormField);
		Assert.assertEquals("date", ddmFormField.getDataType());
		Assert.assertEquals("ddm", ddmFormField.getFieldNamespace());
		Assert.assertEquals(DDMFormFieldType.DATE, ddmFormField.getType());
		Assert.assertTrue(ddmFormField.isShowLabel());
	}

	protected void testDDMFormRules(List<DDMFormRule> ddmFormRules) {
	}

	protected void testDDMFormSuccessPageSettings(
		DDMFormSuccessPageSettings ddmFormSuccessPageSettings) {

		Assert.assertNotNull(ddmFormSuccessPageSettings);

		LocalizedValue body = ddmFormSuccessPageSettings.getBody();

		Map<Locale, String> bodyValues = body.getValues();

		Assert.assertEquals(bodyValues.toString(), 0, bodyValues.size());

		LocalizedValue title = ddmFormSuccessPageSettings.getBody();

		Map<Locale, String> titleValues = title.getValues();

		Assert.assertEquals(titleValues.toString(), 0, titleValues.size());

		Assert.assertFalse(ddmFormSuccessPageSettings.isEnabled());
	}

	protected void testDecimalDDMFormField(DDMFormField ddmFormField) {
		Assert.assertNotNull(ddmFormField);
		Assert.assertEquals("double", ddmFormField.getDataType());
		Assert.assertEquals("ddm", ddmFormField.getFieldNamespace());
		Assert.assertEquals(DDMFormFieldType.DECIMAL, ddmFormField.getType());
	}

	protected void testDefaultLocale(DDMForm ddmForm) {
		Assert.assertEquals(LocaleUtil.US, ddmForm.getDefaultLocale());
	}

	protected void testDocumentLibraryDDMFormField(DDMFormField ddmFormField) {
		Assert.assertNotNull(ddmFormField);
		Assert.assertEquals("document-library", ddmFormField.getDataType());
		Assert.assertEquals("ddm", ddmFormField.getFieldNamespace());
		Assert.assertEquals(
			DDMFormFieldType.DOCUMENT_LIBRARY, ddmFormField.getType());
		Assert.assertTrue(ddmFormField.isShowLabel());
	}

	protected void testHTMLDDMFormField(DDMFormField ddmFormField) {
		Assert.assertNotNull(ddmFormField);
		Assert.assertEquals("html", ddmFormField.getDataType());
		Assert.assertEquals("ddm", ddmFormField.getFieldNamespace());
		Assert.assertEquals(DDMFormFieldType.TEXT_HTML, ddmFormField.getType());
		Assert.assertFalse(ddmFormField.isReadOnly());
		Assert.assertFalse(ddmFormField.isRepeatable());
		Assert.assertFalse(ddmFormField.isRequired());
		Assert.assertTrue(ddmFormField.isShowLabel());
	}

	protected void testNestedDDMFormFields(DDMFormField ddmFormField) {
		Assert.assertNotNull(ddmFormField);

		List<DDMFormField> nestedDDMFormFields =
			ddmFormField.getNestedDDMFormFields();

		Assert.assertEquals(
			nestedDDMFormFields.toString(), 1, nestedDDMFormFields.size());

		DDMFormField separatorDDMFormField = nestedDDMFormFields.get(0);

		Assert.assertEquals("Separator7211", separatorDDMFormField.getName());

		nestedDDMFormFields = separatorDDMFormField.getNestedDDMFormFields();

		Assert.assertEquals(
			nestedDDMFormFields.toString(), 1, nestedDDMFormFields.size());

		DDMFormField selectDDMFormField = nestedDDMFormFields.get(0);

		Assert.assertEquals("Select7450", selectDDMFormField.getName());

		DDMFormFieldOptions selectDDMFormFieldOptions =
			selectDDMFormField.getDDMFormFieldOptions();

		Set<String> optionsValues =
			selectDDMFormFieldOptions.getOptionsValues();

		Assert.assertEquals(optionsValues.toString(), 3, optionsValues.size());
	}

	protected void testRadioDDMFormField(DDMFormField ddmFormField) {
		Assert.assertNotNull(ddmFormField);
		Assert.assertEquals("string", ddmFormField.getDataType());
		Assert.assertEquals("radio", ddmFormField.getType());

		LocalizedValue predefinedValue = ddmFormField.getPredefinedValue();

		Assert.assertEquals(
			"[\"Value 1\"]", predefinedValue.getString(LocaleUtil.US));

		DDMFormFieldOptions ddmFormFieldOptions =
			ddmFormField.getDDMFormFieldOptions();

		Set<String> optionsValues = ddmFormFieldOptions.getOptionsValues();

		Assert.assertEquals(optionsValues.toString(), 3, optionsValues.size());
		Assert.assertTrue(
			optionsValues.toString(), optionsValues.contains("Value 1"));
		Assert.assertTrue(
			optionsValues.toString(), optionsValues.contains("Value 2"));
		Assert.assertTrue(
			optionsValues.toString(), optionsValues.contains("Value 3"));

		LocalizedValue value1Labels = ddmFormFieldOptions.getOptionLabels(
			"Value 1");

		Assert.assertEquals("Option 1", value1Labels.getString(LocaleUtil.US));
		Assert.assertEquals(
			"Opcao 1", value1Labels.getString(LocaleUtil.BRAZIL));
	}

}