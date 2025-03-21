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
import {
  GQLDiagram,
  Position,
  SingleClickOnTwoDiagramElementsTool,
  Tool,
} from 'diagram/DiagramWebSocketContainer.types';
import { SModelElement } from 'sprotty';
import { Action } from 'sprotty-protocol';
import { Selection } from 'workbench/Workbench.types';

export interface SiriusUpdateModelAction extends Action {
  kind: 'siriusUpdateModel';
  diagram: GQLDiagram;
  readOnly: boolean;
}

export interface SetActiveToolAction extends Action {
  kind: 'activeTool';
  tool: Tool | null;
}

export interface SiriusSelectAction extends Action {
  kind: 'siriusSelectElement';
  selection: Selection;
}

export interface SprottySelectAction extends Action {
  kind: 'sprottySelectElement';
  element: SModelElement;
}

export interface ZoomToAction extends Action {
  kind: 'zoomTo';
  level: string;
}

export interface SourceElementAction extends Action {
  kind: 'sourceElement';
  sourceElement: SourceElement | null;
}

export interface SourceElement {
  element: any;
  position: Position;
}

export interface SetActiveConnectorToolsAction extends Action {
  kind: 'activeConnectorTools';
  tools: SingleClickOnTwoDiagramElementsTool[];
}

export interface ShowContextualMenuAction extends Action {
  kind: 'showContextualMenu';
  element: SModelElement | null;
  tools: Tool[];
  startPosition: Position | null;
  endPosition: Position | null;
}
