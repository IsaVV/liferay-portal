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

package com.liferay.headless.delivery.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.headless.delivery.client.dto.v1_0.MessageBoardMessage;
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class MessageBoardMessageResourceTest
	extends BaseMessageBoardMessageResourceTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(testGroup.getGroupId());

		MBMessage mbMessage = MBMessageLocalServiceUtil.addMessage(
			UserLocalServiceUtil.getDefaultUserId(testGroup.getCompanyId()),
			RandomTestUtil.randomString(), testGroup.getGroupId(), 0,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			new ServiceContext());

		_mbThread = mbMessage.getThread();
	}

	@Ignore
	@Override
	@Test
	public void testGraphQLDeleteMessageBoardMessage() {
	}

	@Ignore
	@Override
	@Test
	public void testGraphQLGetMessageBoardMessage() {
	}

	@Override
	public void testPutMessageBoardMessageSubscribe() throws Exception {
		MessageBoardMessage messageBoardMessage =
			testDeleteMessageBoardMessage_addMessageBoardMessage();

		assertHttpResponseStatusCode(
			204,
			messageBoardMessageResource.
				putMessageBoardMessageSubscribeHttpResponse(
					messageBoardMessage.getId()));

		assertHttpResponseStatusCode(
			404,
			messageBoardMessageResource.
				putMessageBoardMessageSubscribeHttpResponse(0L));
	}

	@Override
	public void testPutMessageBoardMessageUnsubscribe() throws Exception {
		MessageBoardMessage messageBoardMessage =
			testDeleteMessageBoardMessage_addMessageBoardMessage();

		assertHttpResponseStatusCode(
			204,
			messageBoardMessageResource.
				putMessageBoardMessageUnsubscribeHttpResponse(
					messageBoardMessage.getId()));

		assertHttpResponseStatusCode(
			404,
			messageBoardMessageResource.
				putMessageBoardMessageUnsubscribeHttpResponse(0L));
	}

	@Override
	protected String[] getAdditionalAssertFieldNames() {
		return new String[] {"articleBody", "headline"};
	}

	@Override
	protected String[] getIgnoredEntityFieldNames() {
		return new String[] {"creatorId", "messageBoardSectionId"};
	}

	@Override
	protected MessageBoardMessage
			testDeleteMessageBoardMessage_addMessageBoardMessage()
		throws Exception {

		return messageBoardMessageResource.
			postMessageBoardThreadMessageBoardMessage(
				_mbThread.getThreadId(), randomMessageBoardMessage());
	}

	@Override
	protected MessageBoardMessage
			testDeleteMessageBoardMessageMyRating_addMessageBoardMessage()
		throws Exception {

		return messageBoardMessageResource.
			postMessageBoardThreadMessageBoardMessage(
				_mbThread.getThreadId(), randomMessageBoardMessage());
	}

	@Override
	protected MessageBoardMessage
			testGetMessageBoardMessage_addMessageBoardMessage()
		throws Exception {

		return messageBoardMessageResource.
			postMessageBoardThreadMessageBoardMessage(
				_mbThread.getThreadId(), randomMessageBoardMessage());
	}

	@Override
	protected Long
		testGetMessageBoardMessageMessageBoardMessagesPage_getParentMessageBoardMessageId() {

		return _mbThread.getRootMessageId();
	}

	@Override
	protected Long
		testGetMessageBoardThreadMessageBoardMessagesPage_getMessageBoardThreadId() {

		return _mbThread.getThreadId();
	}

	@Override
	protected MessageBoardMessage
			testPatchMessageBoardMessage_addMessageBoardMessage()
		throws Exception {

		return messageBoardMessageResource.
			postMessageBoardThreadMessageBoardMessage(
				testGetMessageBoardThreadMessageBoardMessagesPage_getMessageBoardThreadId(),
				randomMessageBoardMessage());
	}

	@Override
	protected MessageBoardMessage
			testPutMessageBoardMessage_addMessageBoardMessage()
		throws Exception {

		return messageBoardMessageResource.
			postMessageBoardThreadMessageBoardMessage(
				testGetMessageBoardThreadMessageBoardMessagesPage_getMessageBoardThreadId(),
				randomMessageBoardMessage());
	}

	private MBThread _mbThread;

}