/****************************************************************************
 * Copyright (C) 2012-2015 ecsec GmbH.
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

package org.openecard.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openecard.common.util.FileUtils;


/**
 * Version of the Open eCard Framework.
 * The version is loaded from the file VERSION in this module when the class is loaded.
 * The version string follows <a href="http://semver.org">semantic versioning</a>.
 *
 * @author Tobias Wich
 */
public class Version {

    private static final String appnameFile = "openecard/APPNAME";
    private static final String versionFile = "openecard/VERSION";
    private static final String unknownName = "UNKNOWN";
    private static final String unknownVersion = "UNKNOWN";
    private static final String specFileName = "openecard/EID_CLIENT_SPECIFICATION";
    private static final String versionsFile = "openecard/SUPPORTED_EID_CLIENT_SPEC_VERSIONS";

    private static final String version;
    private static final String name;
    private static final String specName;
    private static final ArrayList<String> specVersions;
    private static final int major;
    private static final int minor;
    private static final int patch;
    private static final String buildId;

    static {
	InputStream inName, inVer, inSpecName, inSpecVer;
	try {
	    inName = FileUtils.resolveResourceAsStream(Version.class, appnameFile);
	} catch (IOException ex) {
	    inName = null;
	}
	try {
	    inVer = FileUtils.resolveResourceAsStream(Version.class, versionFile);
	} catch (IOException ex) {
	    inVer = null;
	}
	try {
	    inSpecName = FileUtils.resolveResourceAsStream(Version.class, specFileName);
	} catch (IOException ex) {
	    inSpecName = null;
	}
	try {
	    inSpecVer = FileUtils.resolveResourceAsStream(Version.class, versionsFile);
	} catch (IOException ex) {
	    inSpecVer = null;
	}
	
	specName = loadName(inSpecName);
	specVersions = loadVersionLine(inSpecVer);
	name = loadName(inName);
	version = loadVersionLine(inVer).get(0);
	String[] groups = splitVersion(version);
	major = Integer.parseInt(groups[0]);
	minor = Integer.parseInt(groups[1]);
	patch = Integer.parseInt(groups[2]);
	buildId = groups[3];
    }

    private static String loadName(InputStream in) {
	if (in == null) {
	    return unknownName;
	} else {
	    Scanner s = new Scanner(in, "UTF-8").useDelimiter("\\A");
	    try {
		String nameStr = s.next();
		return nameStr.trim();
	    } catch (NoSuchElementException ex) {
		// empty file
		return unknownName;
	    }
	}
    }
    
    private static ArrayList<String> loadVersionLine(InputStream in) {
	ArrayList<String> versions = new ArrayList<>();
	if (in == null) {
	    versions.add(unknownVersion);
	} else {
	    BufferedReader r = new BufferedReader(new InputStreamReader(in));
	    try {
		String line = r.readLine();
		do {
		    if (line == null) {
			versions.add(unknownVersion);
		    } else {
			versions.add(line);
		    }

		    line = r.readLine();
		} while (line != null);
	    } catch (IOException ex) {
		versions.add(unknownVersion);
	    }
	}
	return versions;
    }

    private static String[] splitVersion(String version) {
	String[] groups = new String[4];
	Pattern p = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(-.+)?");
	Matcher m = p.matcher(version);
	if (m.matches() && m.groupCount() >= 3) {
	    groups[0] = m.group(1);
	    groups[1] = m.group(2);
	    groups[2] = m.group(3);
	    groups[3] = m.group(4);
	    // correct last match (remove -)
	    if (groups[3] != null) {
		groups[3] = groups[3].substring(1);
	    }
	} else {
	    groups[0] = "0";
	    groups[1] = "0";
	    groups[2] = "0";
	    groups[3] = null;
	}

	return groups;
    }


    /**
     * Gets the name of the application.
     * @return Name of the app or the UNKNOWN if the name is unavailable.
     */
    public static String getName() {
	return name;
    }

    /**
     * Get complete version string with major, minor and patch version separated by dots.
     * If available, the build ID is appended with a dash as seperator.
     * @return Version string or the string UNKNOWN if version is invalid or unavailable.
     */
    public static String getVersion() {
	return version;
    }

    /**
     * Major version.
     * @return Major version number or 0 if version is invalid or unavailable.
     */
    public static int getMajor() {
	return major;
    }

    /**
     * Minor version.
     * @return Major version number or 0 if version is invalid or unavailable.
     */
    public static int getMinor() {
	return minor;
    }

    /**
     * Patch version.
     * @return Major version number or 0 if version is invalid or unavailable.
     */
    public static int getPatch() {
	return patch;
    }

    /**
     * Build ID suffix.
     * @return Build ID without suffix or null when no build suffix is used.
     */
    public static String getBuildId() {
	return buildId;
    }

    /**
     * Get the name of the specification.
     *
     * @return The name of the specification which is {@code BSI-TR-03124}.
     */
    public static String getSpecName() {
	return specName;
    }

    /**
     * Get the versions of specification this application is compatible to.
     *
     * @return A unmodifiable list containing all version this application is compatible to.
     */
    public static List<String> getSpecVersions() {
	return Collections.unmodifiableList(specVersions);
    }

    /**
     * Get the latest version of the specification which is compatible to the application.
     *
     * @return Latest compatible specification version.
     */
    public static String getLatestSpecVersion() {
	return specVersions.get(specVersions.size() - 1);
    }

}
