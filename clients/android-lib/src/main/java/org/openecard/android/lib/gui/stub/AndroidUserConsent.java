/****************************************************************************
 * Copyright (C) 2017 ecsec GmbH.
 * All rights reserved.
 * Contact: ecsec GmbH (info@ecsec.de)
 *
 * This file is part of the Open eCard App.
 *
 * GNU General Public License Usage
 * This file may be used under the terms of the GNU General Public
 * License version 3.0 as published by the Free Software Foundation
 * and appearing in the file LICENSE.GPL included in the packaging of
 * this file. Please review the following information to ensure the
 * GNU General Public License version 3.0 requirements will be met:
 * http://www.gnu.org/copyleft/gpl.html.
 *
 * Other Usage
 * Alternatively, this file may be used in accordance with the terms
 * and conditions contained in a signed written agreement between
 * you and ecsec GmbH.
 *
 ***************************************************************************/

package org.openecard.android.lib.gui.stub;

import org.openecard.android.lib.ServiceContext;
import org.openecard.gui.FileDialog;
import org.openecard.gui.MessageDialog;
import org.openecard.gui.UserConsent;
import org.openecard.gui.UserConsentNavigator;
import org.openecard.gui.definition.UserConsentDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Mike Prechtl
 */
public class AndroidUserConsent implements UserConsent {

    private static final Logger LOG = LoggerFactory.getLogger(AndroidUserConsent.class);

    private final ServiceContext ctx;

    public AndroidUserConsent(ServiceContext ctx) {
	this.ctx = ctx;
    }

    @Override
    public UserConsentNavigator obtainNavigator(UserConsentDescription userConsentDescription) {
	return null;
    }

    @Override
    public FileDialog obtainFileDialog() {
	return null;
    }

    @Override
    public MessageDialog obtainMessageDialog() {
	return new AndroidMessageDialog();
    }

}