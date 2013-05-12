/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package de.rocovomo.e4.rcp.hmmrecorder.util;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import de.rocovomo.e4.rcp.hmmrecorder.model.Trainer;

public class DoubleValidator implements IValidator
{
	private Trainer trainer;

	public DoubleValidator(Trainer trainer)
	{
		this.trainer = trainer;
	}

	@Override
	public IStatus validate(Object value)
	{
		String s = String.valueOf(value);
		if (s.matches("(\\d+)?(\\.\\d*)?"))
		{
			trainer.setRunnable(true);
			return ValidationStatus.ok();
		}
		trainer.setRunnable(false);
		return ValidationStatus.error("Not a positve double number");
	};
}
