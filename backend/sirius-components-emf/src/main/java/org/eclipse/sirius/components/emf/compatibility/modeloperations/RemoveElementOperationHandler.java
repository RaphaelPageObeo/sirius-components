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
package org.eclipse.sirius.components.emf.compatibility.modeloperations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.components.compatibility.api.IIdentifierProvider;
import org.eclipse.sirius.components.compatibility.api.IModelOperationHandler;
import org.eclipse.sirius.components.core.api.IObjectService;
import org.eclipse.sirius.components.core.api.IRepresentationMetadataSearchService;
import org.eclipse.sirius.components.interpreter.AQLInterpreter;
import org.eclipse.sirius.components.representations.IStatus;
import org.eclipse.sirius.components.representations.VariableManager;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EcoreIntrinsicExtender;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle the {@link RemoveElement} model operation.
 *
 * @author lfasani
 */
public class RemoveElementOperationHandler implements IModelOperationHandler {

    private final IObjectService objectService;

    private final IRepresentationMetadataSearchService representationMetadataSearchService;

    private final IIdentifierProvider identifierProvider;

    private final AQLInterpreter interpreter;

    private final ChildModelOperationHandler childModelOperationHandler;

    private final RemoveElement removeElementOperation;

    private final Logger logger = LoggerFactory.getLogger(RemoveElementOperationHandler.class);

    public RemoveElementOperationHandler(IObjectService objectService, IRepresentationMetadataSearchService representationMetadataSearchService, IIdentifierProvider identifierProvider,
            AQLInterpreter interpreter, ChildModelOperationHandler childModelOperationHandler, RemoveElement removeElementOperation) {
        this.objectService = Objects.requireNonNull(objectService);
        this.representationMetadataSearchService = Objects.requireNonNull(representationMetadataSearchService);
        this.identifierProvider = Objects.requireNonNull(identifierProvider);
        this.interpreter = Objects.requireNonNull(interpreter);
        this.childModelOperationHandler = Objects.requireNonNull(childModelOperationHandler);
        this.removeElementOperation = Objects.requireNonNull(removeElementOperation);
    }

    @Override
    public IStatus handle(Map<String, Object> variables) {
        // @formatter:off
        Optional<EObject> optionalObjectToRemove = Optional.ofNullable(variables.get(VariableManager.SELF))
             .filter(EObject.class::isInstance)
             .map(EObject.class::cast);
        // @formatter:on

        if (optionalObjectToRemove.isPresent()) {
            EObject objectToRemove = optionalObjectToRemove.get();

            EcoreIntrinsicExtender ecoreIntrinsicExtender = new EcoreIntrinsicExtender();
            String containingFeatureName = ecoreIntrinsicExtender.eContainingFeatureName(objectToRemove);
            EObject container = ecoreIntrinsicExtender.eContainer(objectToRemove);
            Object removedElement = ecoreIntrinsicExtender.eRemove(container, containingFeatureName, objectToRemove);
            if (removedElement == null) {
                this.logger.warn("The removal of the object {} from the instance {} on the feature {} failed.", objectToRemove, container, containingFeatureName); //$NON-NLS-1$
            }
        }

        Map<String, Object> childVariables = new HashMap<>(variables);
        List<ModelOperation> subModelOperations = this.removeElementOperation.getSubModelOperations();
        return this.childModelOperationHandler.handle(this.objectService, this.representationMetadataSearchService, this.identifierProvider, this.interpreter, childVariables, subModelOperations);
    }

}
