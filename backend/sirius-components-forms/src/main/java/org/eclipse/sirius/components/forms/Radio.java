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
 * The radio widget.
 *
 * @author lfasani
 */
@Immutable
public final class Radio extends AbstractWidget {
    private String label;

    private List<RadioOption> options;

    private Function<String, IStatus> newValueHandler;

    private Radio() {
        // Prevent instantiation
    }

    public String getLabel() {
        return this.label;
    }

    public List<RadioOption> getOptions() {
        return this.options;
    }

    public Function<String, IStatus> getNewValueHandler() {
        return this.newValueHandler;
    }

    public static Builder newRadio(String id) {
        return new Builder(id);
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, label: {2}, options: {3}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.getId(), this.label, this.options);
    }

    /**
     * The builder used to create the radio.
     *
     * @author lfasani
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public static final class Builder {
        private String id;

        private String label;

        private List<RadioOption> options;

        private Function<String, IStatus> newValueHandler;

        private List<Diagnostic> diagnostics;

        private Builder(String id) {
            this.id = Objects.requireNonNull(id);
        }

        public Builder label(String label) {
            this.label = Objects.requireNonNull(label);
            return this;
        }

        public Builder options(List<RadioOption> options) {
            this.options = Objects.requireNonNull(options);
            return this;
        }

        public Builder newValueHandler(Function<String, IStatus> newValueHandler) {
            this.newValueHandler = Objects.requireNonNull(newValueHandler);
            return this;
        }

        public Builder diagnostics(List<Diagnostic> diagnostics) {
            this.diagnostics = Objects.requireNonNull(diagnostics);
            return this;
        }

        public Radio build() {
            Radio radio = new Radio();
            radio.id = Objects.requireNonNull(this.id);
            radio.label = Objects.requireNonNull(this.label);
            radio.options = Objects.requireNonNull(this.options);
            radio.newValueHandler = Objects.requireNonNull(this.newValueHandler);
            radio.diagnostics = Objects.requireNonNull(this.diagnostics);
            return radio;
        }
    }
}
