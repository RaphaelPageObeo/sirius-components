/*******************************************************************************
 * Copyright (c) 2019, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.components.emf.configuration;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.components.compatibility.api.ICanCreateDiagramPredicateFactory;
import org.eclipse.sirius.components.compatibility.api.IIdentifierProvider;
import org.eclipse.sirius.components.compatibility.api.IModelOperationHandlerSwitchProvider;
import org.eclipse.sirius.components.compatibility.api.ISemanticCandidatesProviderFactory;
import org.eclipse.sirius.components.compatibility.api.IToolImageProviderFactory;
import org.eclipse.sirius.components.core.api.IObjectService;
import org.eclipse.sirius.components.core.api.IRepresentationMetadataSearchService;
import org.eclipse.sirius.components.emf.compatibility.SemanticCandidatesProvider;
import org.eclipse.sirius.components.emf.compatibility.api.IExternalJavaActionProvider;
import org.eclipse.sirius.components.emf.compatibility.diagrams.CanCreateDiagramPredicate;
import org.eclipse.sirius.components.emf.compatibility.diagrams.ToolImageProvider;
import org.eclipse.sirius.components.emf.compatibility.modeloperations.ModelOperationHandlerSwitch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration used to provide all the necessary beans for the compatibility layer.
 *
 * @author sbegaudeau
 */
@Configuration
public class CompatibilityConfiguration {

    @Bean
    public ISemanticCandidatesProviderFactory semanticCandidatesProviderFactory() {
        return SemanticCandidatesProvider::new;
    }

    @Bean
    public ICanCreateDiagramPredicateFactory canCreateDiagramPredicateFactory() {
        return CanCreateDiagramPredicate::new;
    }

    @Bean
    public IModelOperationHandlerSwitchProvider modelOperationHandlerSwitchProvider(IObjectService objectService, IRepresentationMetadataSearchService representationMetadataSearchService,
            IIdentifierProvider identifierProvider, List<IExternalJavaActionProvider> externalJavaActionProviders) {
        return (interpreter) -> new ModelOperationHandlerSwitch(objectService, representationMetadataSearchService, identifierProvider, externalJavaActionProviders, interpreter);
    }

    @Bean
    public IToolImageProviderFactory toolImageProviderFactory(IObjectService objectService, EPackage.Registry ePackageRegistry) {
        return abstractToolDescription -> new ToolImageProvider(objectService, ePackageRegistry, abstractToolDescription);
    }
}
