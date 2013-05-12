/*******************************************************************************
 * Copyright (c) 2013, RoCoVoMo
 * Contributors:
 *     RoCoVoMo - Simon Ebner, Volker Werling
 *******************************************************************************/

package deprecated.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic MVC model with generic properties.
 */
public class Model<L extends ModelListener> {
	private final List<L> listeners;

	public Model() {
		this.listeners = new ArrayList<L>();
	}

	public void addModelListener(final L listener) {
		if (!this.listeners.contains(listener)) {
			this.listeners.add(listener);
			notifyModelListener(listener);
		}
	}

	public void removeModelListener(final L listener) {
		this.listeners.remove(listener);
	}

	protected void notifyModelListeners() {
		for (final L listener : this.listeners) {
			notifyModelListener(listener);
		}
	}

	protected void notifyModelListener(final L listener) {
		listener.modelChanged(this);
	}

	public class Property<T> {
		private T value;

		public Property(final T initialValue) {
			this.value = initialValue;
		}

		public void setValue(final T value) {
			this.value = value;
			notifyModelListeners();
		}

		public T getValue() {
			return this.value;
		}
	}
}