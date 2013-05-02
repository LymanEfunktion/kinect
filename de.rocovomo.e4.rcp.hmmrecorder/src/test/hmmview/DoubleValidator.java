/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package test.hmmview;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class DoubleValidator implements IValidator
{
	@Override
	public IStatus validate(Object value)
	{
		if (value instanceof Double)
		{
			String s = String.valueOf(value);
			if (s.matches("\\d+(\\.\\d*)?"))
			{
				return ValidationStatus.ok();
			}
		}
		return ValidationStatus.error("Not a number");
	};
}
