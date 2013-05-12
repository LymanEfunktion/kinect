/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package deprecated.model;

/**
 * A generic MVC view, or model listener.
 */
public interface ModelListener <M>
{
  void modelChanged (M model);
}
