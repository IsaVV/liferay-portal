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

import getAlloyEditorProcessor from './getAlloyEditorProcessor';

export default getAlloyEditorProcessor(
	'text',

	element => {
		const anchor =
			element instanceof HTMLAnchorElement
				? element
				: element.querySelector('a');

		if (anchor) {
			const innerWrapper = document.createElement('div');
			innerWrapper.innerHTML = anchor.innerHTML;
			anchor.innerHTML = '';
			anchor.appendChild(innerWrapper);

			return innerWrapper;
		}

		return element;
	},

	(element, value, config) => {
		const anchor =
			element instanceof HTMLAnchorElement
				? element
				: element.querySelector('a');

		if (anchor) {
			anchor.href = config.href;
			anchor.innerHTML = value;
			anchor.target = config.target;
		}
	}
);
