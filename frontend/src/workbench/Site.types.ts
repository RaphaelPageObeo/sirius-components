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
import React from 'react';
import { Selection } from 'workbench/Workbench.types';

export interface SiteProps {
  editingContextId: string;
  selection: Selection;
  setSelection: (selection: Selection) => void;
  readOnly: boolean;
  contributions: Array<React.ReactElement>;
}
