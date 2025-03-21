/*******************************************************************************
 * Copyright (c) 2021, 2022 Obeo.
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
package org.eclipse.sirius.components.collaborative.forms.dto;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.components.collaborative.forms.api.IFormInput;

/**
 * The input object for the multi select edition mutation.
 *
 * @author arichard
 */
public final class EditMultiSelectInput implements IFormInput {
    private UUID id;

    private String editingContextId;

    private String representationId;

    private String selectId;

    private List<String> newValues;

    public EditMultiSelectInput() {
        // Used by Jackson
    }

    public EditMultiSelectInput(UUID id, String editingContextId, String representationId, String selectId, List<String> newValues) {
        this.id = Objects.requireNonNull(id);
        this.editingContextId = Objects.requireNonNull(editingContextId);
        this.representationId = Objects.requireNonNull(representationId);
        this.selectId = Objects.requireNonNull(selectId);
        this.newValues = Objects.requireNonNull(newValues);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    public String getEditingContextId() {
        return this.editingContextId;
    }

    @Override
    public String getRepresentationId() {
        return this.representationId;
    }

    public String getSelectId() {
        return this.selectId;
    }

    public List<String> getNewValues() {
        return this.newValues;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, editingContextId: {2}, representationId: {3}, selectId: {4}, newValues: {5}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.editingContextId, this.representationId, this.selectId, this.newValues);
    }
}
