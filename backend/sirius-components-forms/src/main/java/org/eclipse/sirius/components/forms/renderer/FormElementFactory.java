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
package org.eclipse.sirius.components.forms.renderer;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.components.forms.AbstractWidget;
import org.eclipse.sirius.components.forms.Checkbox;
import org.eclipse.sirius.components.forms.Form;
import org.eclipse.sirius.components.forms.Group;
import org.eclipse.sirius.components.forms.Link;
import org.eclipse.sirius.components.forms.MultiSelect;
import org.eclipse.sirius.components.forms.Page;
import org.eclipse.sirius.components.forms.Radio;
import org.eclipse.sirius.components.forms.Select;
import org.eclipse.sirius.components.forms.Textarea;
import org.eclipse.sirius.components.forms.Textfield;
import org.eclipse.sirius.components.forms.elements.CheckboxElementProps;
import org.eclipse.sirius.components.forms.elements.FormElementProps;
import org.eclipse.sirius.components.forms.elements.GroupElementProps;
import org.eclipse.sirius.components.forms.elements.LinkElementProps;
import org.eclipse.sirius.components.forms.elements.ListElementProps;
import org.eclipse.sirius.components.forms.elements.MultiSelectElementProps;
import org.eclipse.sirius.components.forms.elements.PageElementProps;
import org.eclipse.sirius.components.forms.elements.RadioElementProps;
import org.eclipse.sirius.components.forms.elements.SelectElementProps;
import org.eclipse.sirius.components.forms.elements.TextareaElementProps;
import org.eclipse.sirius.components.forms.elements.TextfieldElementProps;
import org.eclipse.sirius.components.forms.validation.Diagnostic;
import org.eclipse.sirius.components.forms.validation.DiagnosticElementProps;
import org.eclipse.sirius.components.representations.IElementFactory;
import org.eclipse.sirius.components.representations.IProps;

/**
 * Used to instantiate the elements of the form.
 *
 * @author sbegaudeau
 */
public class FormElementFactory implements IElementFactory {

    @Override
    public Object instantiateElement(String type, IProps props, List<Object> children) {
        Object object = null;
        if (FormElementProps.TYPE.equals(type) && props instanceof FormElementProps) {
            object = this.instantiateForm((FormElementProps) props, children);
        } else if (PageElementProps.TYPE.equals(type) && props instanceof PageElementProps) {
            object = this.instantiatePage((PageElementProps) props, children);
        } else if (GroupElementProps.TYPE.equals(type) && props instanceof GroupElementProps) {
            object = this.instantiateGroup((GroupElementProps) props, children);
        } else if (CheckboxElementProps.TYPE.equals(type) && props instanceof CheckboxElementProps) {
            object = this.instantiateCheckbox((CheckboxElementProps) props, children);
        } else if (ListElementProps.TYPE.equals(type) && props instanceof ListElementProps) {
            object = this.instantiateList((ListElementProps) props, children);
        } else if (RadioElementProps.TYPE.equals(type) && props instanceof RadioElementProps) {
            object = this.instantiateRadio((RadioElementProps) props, children);
        } else if (SelectElementProps.TYPE.equals(type) && props instanceof SelectElementProps) {
            object = this.instantiateSelect((SelectElementProps) props, children);
        } else if (MultiSelectElementProps.TYPE.equals(type) && props instanceof MultiSelectElementProps) {
            object = this.instantiateMultiSelect((MultiSelectElementProps) props, children);
        } else if (TextareaElementProps.TYPE.equals(type) && props instanceof TextareaElementProps) {
            object = this.instantiateTextarea((TextareaElementProps) props, children);
        } else if (TextfieldElementProps.TYPE.equals(type) && props instanceof TextfieldElementProps) {
            object = this.instantiateTextfield((TextfieldElementProps) props, children);
        } else if (DiagnosticElementProps.TYPE.equals(type) && props instanceof DiagnosticElementProps) {
            object = this.instantiateDiagnostic((DiagnosticElementProps) props, children);
        } else if (LinkElementProps.TYPE.equals(type) && props instanceof LinkElementProps) {
            object = this.instantiateLink((LinkElementProps) props, children);
        }
        return object;
    }

    private Form instantiateForm(FormElementProps props, List<Object> children) {
        // @formatter:off
        List<Page> pages = children.stream()
                .filter(Page.class::isInstance)
                .map(Page.class::cast)
                .collect(Collectors.toList());

        return Form.newForm(props.getId())
                .label(props.getLabel())
                .targetObjectId(props.getTargetObjectId())
                .descriptionId(props.getDescriptionId())
                .pages(pages)
                .build();
        // @formatter:on
    }

    private Page instantiatePage(PageElementProps props, List<Object> children) {
        // @formatter:off
        List<Group> groups = children.stream()
                .filter(Group.class::isInstance)
                .map(Group.class::cast)
                .collect(Collectors.toList());

        return Page.newPage(props.getId())
                .label(props.getLabel())
                .groups(groups)
                .build();
        //@formatter:on
    }

    private Group instantiateGroup(GroupElementProps props, List<Object> children) {
        // @formatter:off
        List<AbstractWidget> widgets = children.stream()
                .filter(AbstractWidget.class::isInstance)
                .map(AbstractWidget.class::cast)
                .collect(Collectors.toList());

        return Group.newGroup(props.getId())
                .label(props.getLabel())
                .widgets(widgets)
                .build();
        // @formatter:on
    }

    private Checkbox instantiateCheckbox(CheckboxElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return Checkbox.newCheckbox(props.getId())
                .label(props.getLabel())
                .value(props.isValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics)
                .build();
        // @formatter:on
    }

    private org.eclipse.sirius.components.forms.List instantiateList(ListElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return org.eclipse.sirius.components.forms.List.newList(props.getId())
                .label(props.getLabel())
                .items(props.getItems())
                .diagnostics(diagnostics)
                .build();
        // @formatter:on
    }

    private Radio instantiateRadio(RadioElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return Radio.newRadio(props.getId())
                .label(props.getLabel())
                .options(props.getOptions())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics)
                .build();
        // @formatter:on
    }

    private Select instantiateSelect(SelectElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return Select.newSelect(props.getId())
                .label(props.getLabel())
                .options(props.getOptions())
                .value(props.getValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics)
                .build();
        // @formatter:on
    }

    private MultiSelect instantiateMultiSelect(MultiSelectElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return MultiSelect.newMultiSelect(props.getId())
                .label(props.getLabel())
                .options(props.getOptions())
                .values(props.getValues())
                .newValuesHandler(props.getNewValuesHandler())
                .diagnostics(diagnostics)
                .build();
        // @formatter:on
    }

    private Textarea instantiateTextarea(TextareaElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return Textarea.newTextarea(props.getId())
                .label(props.getLabel())
                .value(props.getValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics)
                .build();
        // @formatter:on
    }

    private Textfield instantiateTextfield(TextfieldElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return Textfield.newTextfield(props.getId())
                .label(props.getLabel())
                .value(props.getValue())
                .newValueHandler(props.getNewValueHandler())
                .diagnostics(diagnostics)
                .build();
        // @formatter:on
    }

    private Object instantiateDiagnostic(DiagnosticElementProps props, List<Object> children) {
        // @formatter:off
        return Diagnostic.newDiagnostic(props.getId())
                .kind(props.getKind())
                .message(props.getMessage())
                .build();
        // @formatter:on
    }

    private Link instantiateLink(LinkElementProps props, List<Object> children) {
        List<Diagnostic> diagnostics = this.getDiagnosticsFromChildren(children);

        // @formatter:off
        return Link.newLink(props.getId())
                 .label(props.getLabel())
                 .url(props.getUrl())
                 .diagnostics(diagnostics)
                 .build();
       // @formatter:on
    }

    private List<Diagnostic> getDiagnosticsFromChildren(List<Object> children) {
        // @formatter:off
        return children.stream()
                .filter(Diagnostic.class::isInstance)
                .map(Diagnostic.class::cast)
                .collect(Collectors.toList());
        // @formatter:on
    }

}
