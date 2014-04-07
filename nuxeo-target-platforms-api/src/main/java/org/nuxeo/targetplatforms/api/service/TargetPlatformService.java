/*
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Anahide Tchertchian
 */
package org.nuxeo.targetplatforms.api.service;

import java.util.List;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.targetplatforms.api.TargetPackage;
import org.nuxeo.targetplatforms.api.TargetPackageInfo;
import org.nuxeo.targetplatforms.api.TargetPlatform;
import org.nuxeo.targetplatforms.api.TargetPlatformInfo;
import org.nuxeo.targetplatforms.api.TargetPlatformInstance;

/**
 * Service for target platforms and packages management.
 *
 * @since 5.7.1
 */
public interface TargetPlatformService {

    /**
     * Returns the default target platform.
     */
    TargetPlatform getDefaultTargetPlatform() throws ClientException;

    /**
     * Returns the override directory name.
     */
    String getOverrideDirectory();

    /**
     * Returns the target platform with given id, or null if not found.
     */
    TargetPlatform getTargetPlatform(String id) throws ClientException;

    /**
     * Returns the target platform info for given id, or null if not found.
     */
    TargetPlatformInfo getTargetPlatformInfo(String id) throws ClientException;

    /**
     * Returns the target package with given id, or null if not found.
     */
    TargetPackage getTargetPackage(String id);

    /**
     * Returns the target package info for given id, or null if not found.
     */
    TargetPackageInfo getTargetPackageInfo(String id);

    /**
     * Returns a target platform instance with given id and given enabled
     * packages, or null if not found.
     * <p>
     * Ignore target packages that would not be found.
     */
    TargetPlatformInstance getTargetPlatformInstance(String id,
            List<String> packages) throws ClientException;

    /**
     * Returns all target platforms matching given criteria.
     *
     * @param filterRestricted true if restricted target platforms should be
     *            filtered from the resulting list.
     * @param filterDeprecated true if deprecated target platforms should be
     *            filtered from the resulting list.
     * @param type null if no filtering should be done, otherwise filters all
     *            target platforms that would not hold this type.
     */
    List<TargetPlatform> getAvailableTargetPlatforms(boolean filterDisabled,
            boolean filterRestricted, boolean filterDeprecated, String type)
            throws ClientException;

    /**
     * Returns all target platforms info matching given criteria.
     */
    List<TargetPlatformInfo> getAvailableTargetPlatformsInfo(
            boolean filterDisabled, boolean filterRestricted,
            boolean filterDeprecated, String type) throws ClientException;

    /**
     * Deprecates the target platform if given boolean is true (or
     * un-deprecates it if boolean is false), overriding the default value from
     * extension points and adding an entry in the override directory.
     */
    void deprecateTargetPlatform(boolean deprecate, String id)
            throws ClientException;

    /**
     * Enables the target platform if given boolean is true (or disables it
     * boolean is false), overriding the default value from extension points
     * and adding an entry in the override directory.
     */
    void enableTargetPlatform(boolean enable, String id) throws ClientException;

    /**
     * Restricts the target platform if given boolean is true (or un-restricts
     * it if boolean is false), overriding the default value from extension
     * points and adding an entry in the override directory.
     */
    void restrictTargetPlatform(boolean restrict, String id)
            throws ClientException;

    /**
     * Removes overrides for this target platform.
     */
    void restoreTargetPlatform(String id) throws ClientException;

    /**
     * Removes overrides for all target platform.
     */
    void restoreAllTargetPlatforms() throws ClientException;

}