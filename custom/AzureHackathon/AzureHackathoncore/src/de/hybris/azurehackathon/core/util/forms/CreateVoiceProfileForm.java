/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.azurehackathon.core.util.forms;

import org.springframework.web.multipart.MultipartFile;


public class CreateVoiceProfileForm
{
	private MultipartFile file;

	/**
	 * @return the file
	 */
	public MultipartFile getFile()
	{
		return file;
	}

	/**
	 * @param file
	 *           the file to set
	 */
	public void setFile(final MultipartFile file)
	{
		this.file = file;
	}


}
