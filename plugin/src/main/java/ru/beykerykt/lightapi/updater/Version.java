/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Vladimir Mikhailov <beykerykt@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ru.beykerykt.lightapi.updater;

import java.util.regex.Matcher;

/**
 * Simple major.minor.patch storage system with getters.
 *
 * @author Connor Spencer Harries
 */
public class Version {

	/**
	 * Store the version major.
	 */
	private final int major;

	/**
	 * Store the version minor.
	 */
	private final int minor;

	/**
	 * Store the version patch.
	 */
	private final int patch;

	/**
	 * Create a new instance of the {@link ru.beykerykt.lightapi.updater.Version} class.
	 *
	 * @param major semver major
	 * @param minor semver minor
	 * @param patch semver patch
	 */
	@SuppressWarnings("WeakerAccess")
	public Version(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	/**
	 * Quick method for parsing version strings and matching them using the {@link java.util.regex.Pattern} in {@link Updater}
	 *
	 * @param version semver string to parse
	 * @return {@link ru.beykerykt.lightapi.updater.Version} if valid semver string
	 */
	public static Version parse(String version) {
		Matcher matcher = Updater.regex.matcher(version);

		if (matcher.matches()) {
			int x = Integer.parseInt(matcher.group(1));
			int y = Integer.parseInt(matcher.group(2));
			int z = Integer.parseInt(matcher.group(3));

			return new Version(x, y, z);
		}

		return null;
	}

	/**
	 * @return semver major
	 */
	@SuppressWarnings("WeakerAccess")
	public int getMajor() {
		return major;
	}

	/**
	 * @return semver minor
	 */
	@SuppressWarnings("WeakerAccess")
	public int getMinor() {
		return minor;
	}

	/**
	 * @return semver patch
	 */
	@SuppressWarnings("WeakerAccess")
	public int getPatch() {
		return patch;
	}

	/**
	 * @return joined version string.
	 */
	@Override
	public String toString() {
		return major + "." + minor + "." + patch;
	}

	/**
	 * Little method to see if the input version is greater than ours.
	 *
	 * @param version input {@link ru.beykerykt.lightapi.updater.Version} object
	 * @return true if the version is greater than ours
	 */
	@SuppressWarnings("WeakerAccess")
	public boolean compare(Version version) {
		int result = version.getMajor() - this.getMajor();
		if (result == 0) {
			result = version.getMinor() - this.getMinor();
			if (result == 0) {
				result = version.getPatch() - this.getPatch();
			}
		}
		return result > 0;
	}
}
