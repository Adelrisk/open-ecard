/****************************************************************************
 * Copyright (C) 2013 ecsec GmbH.
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

package org.openecard.binding.tctoken;

import org.openecard.common.I18n;


/**
 *
 * @author Tobias Wich <tobias.wich@ecsec.de>
 */
public class ConnectionError extends Exception {

    private static final I18n lang = I18n.getTranslation("tr03112");
    private final String errMsg;
    private Object[] params;

    private static final long serialVersionUID = 1L;

    public ConnectionError(String message, Object ... params) {
	super(message);
	errMsg = message;
	this.params = params;
    }

    public ConnectionError(Throwable cause) {
	super(cause);
	errMsg = "";
    }

    public ConnectionError(String message, Throwable cause, Object ... params) {
	super(message, cause);
	errMsg = message;
	this.params = params;

    }

    @Override
    public String getMessage() {
	if (! errMsg.isEmpty()) {
	    if (params != null) {
		return lang.getOriginalMessage(errMsg, params);
	    } else {
		return lang.getOriginalMessage(errMsg);
	    }
	} else {
	    return super.getMessage();
	}
    }

    @Override
    public String getLocalizedMessage() {
	if (errMsg.isEmpty()) {
	    return super.getLocalizedMessage();
	} else {
	    if (params == null ) {
		return lang.translationForKey(errMsg);
	    } else {
		return lang.translationForKey(errMsg, params);
	    }
	}
    }

}
