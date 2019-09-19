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
package de.hybris.azurehackathon.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdatePasswordForm;


/**
 * Form object for updating the password.
 */
public class MyUpdatePasswordForm extends UpdatePasswordForm
{
	private String currentPassword;
	private String newPassword;
	private String checkNewPassword;
	private String file;

	/**
	 * @return the file
	 */

	public String getFile()
	{
		return file;
	}

	/**
	 * @param file
	 *           the file to set
	 */
	public void setFile(final String file)
	{
		this.file = file;
	}

	@Override
	public String getCurrentPassword()
	{
		return currentPassword;
	}

	@Override
	public void setCurrentPassword(final String currentPassword)
	{
		this.currentPassword = currentPassword;
	}

	@Override
	public String getNewPassword()
	{
		return newPassword;
	}

	@Override
	public void setNewPassword(final String newPassword)
	{
		this.newPassword = newPassword;
	}

	@Override
	public String getCheckNewPassword()
	{
		return checkNewPassword;
	}

	@Override
	public void setCheckNewPassword(final String checkNewPassword)
	{
		this.checkNewPassword = checkNewPassword;
	}
}
