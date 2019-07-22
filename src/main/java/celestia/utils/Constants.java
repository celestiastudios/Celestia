/*
 * Celestia, the next-generation space & tech mod for Minecraft Forge.
 *
 *     Copyright (C) 2019-Onwards Celestia Studios
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package celestia.utils;

public class Constants
{
    public static final String MOD_ID = "celestia";
    public static final String MOD_NAME = "Celestia";

    public static final String ACCEPTED_VERSION = "[1.12.2]";
    public static final String MC_VERSION = "1.12.2";
    public static final String FORGE_VERSION = "14.23.5.2838";

    public static final String VERSION_CAT = "Alpha "; // Alpha, Beta, Etc.
    public static final int VERSION_MAJ = 1; // Major Version
    public static final int VERSION_MIN = 0; // Minor Version
    public static final int VERSION_PAT = 0; // Patch Version
    public static final String VERSION_BLD = " build 0000b"; // Build Number

    public static final String VERSION_SIMPLE = VERSION_MAJ + "." + VERSION_MIN + "." + VERSION_PAT; // Very simple, numbers only
    public static final String VERSION_DISPLAY = VERSION_CAT + VERSION_SIMPLE; // Displays version category too
    public static final String VERSION_COMPLEX = VERSION_DISPLAY + VERSION_BLD; // Also displays the build number
    public static final String VERSION_LOGGER = VERSION_COMPLEX + " for Minecraft " + MC_VERSION; // Displays the MC version the mod was made for
    public static final String VERSION_LOGGER_DEBUG = VERSION_LOGGER + " made with Forge MDK version 14.23.5.2838"; // Also displays the Forge version the mod was made with

    public static final String COMMON_PROXY_PATH = "celestia.proxy.CommonProxy";
    public static final String CLIENT_PROXY_PATH = "celestia.proxy.ClientProxy";
}
