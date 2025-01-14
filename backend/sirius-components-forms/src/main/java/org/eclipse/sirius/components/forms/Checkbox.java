/*******************************************************************************
 * Copyright (c) 2019, 2022 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.components.forms;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.sirius.components.annotations.Immutable;
import org.eclipse.sirius.components.forms.validation.Diagnostic;
import org.eclipse.sirius.components.representations.IStatus;

/**
 * The checkbox widget.
 *
 * @author sbegaudeau
 */
@Immutable
public final class Checkbox extends AbstractWidget {
    private String label;

    private boolean value;

    private Function<Boolean, IStatus> newValueHandler;

    private Checkbox() {
        // Prevent instantiation
    }

    public String getLabel() {
        return this.label;
    }

    public boolean isValue() {
        return this.value;
    }

    public Function<Boolean, IStatus> getNewValueHandler() {
        return this.newValueHandler;
    }

    public static Builder newCheckbox(String id) {
        return new Builder(id);
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, label: {2}, value: {3}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.getId(), this.label, this.value);
    }

    /**
     * The builder used to create the checkbox.
     *
     * @author sbegaudeau
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public static final class Builder {
        private String id;

        private String label;

        private boolean value;

        private Function<Boolean, IStatus> newValueHandler;

        private List<Diagnostic> diagnostics;

        private Builder(String id) {
            this.id = Objects.requireNonNull(id);
        }

        public Builder label(String label) {
            this.label = Objects.requireNonNull(label);
            return this;
        }

        public Builder value(boolean value) {
            this.value = value;
            return this;
        }

        public Builder newValueHandler(Function<Boolean, IStatus> handler) {
            this.newValueHandler = Objects.requireNonNull(handler);
            return this;
        }

        public Builder diagnostics(List<Diagnostic> diagnostics) {
            this.diagnostics = Objects.requireNonNull(diagnostics);
            return this;
        }

        public Checkbox build() {
            Checkbox checkbox = new Checkbox();
            checkbox.id = Objects.requireNonNull(this.id);
            checkbox.label = Objects.requireNonNull(this.label);
            checkbox.value = Objects.requireNonNull(this.value);
            checkbox.newValueHandler = Objects.requireNonNull(this.newValueHandler);
            checkbox.diagnostics = Objects.requireNonNull(this.diagnostics);
            return checkbox;
        }
    }
}
