/*
 * Created 2005/09/17
 * Copyright (C) 2003-2009  Naoki Iwami (naoki@limy.org)
 *
 * This file is part of Limy Eclipse Plugin.
 *
 * Limy Eclipse Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Limy Eclipse Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Limy Eclipse Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.limy.eclipse.code;

import java.io.File;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.limy.eclipse.common.LimyEclipsePluginUtils;

/**
 * 
 * @author Naoki Iwami
 */
public class LimyCodePlugin extends AbstractUIPlugin {
    
    // ------------------------ Static Fields

    /**
     * 唯一のLimyCodePluginインスタンス
     */
    private static LimyCodePlugin plugin;

    // ------------------------ Fields
    
    /** Pluginルートディレクトリ */
    private File rootDir;

    /**
     * リソースバンドル
     */
    private ResourceBundle resourceBundle;
    
    // ------------------------ Constructors
    
    /**
     * The constructor.
     */
    public LimyCodePlugin() {
        super();
        plugin = this;
        try {
            resourceBundle = ResourceBundle.getBundle(
                    "org.limy.eclipse.code.LimyCodePluginResources");
        } catch (MissingResourceException e) {
            LimyEclipsePluginUtils.log(e);
        }
    }

    // ------------------------ Override Methods

    @Override
    public IPreferenceStore getPreferenceStore() {
        IPreferenceStore store = super.getPreferenceStore();
        store.setDefault(LimyCodeConstants.PREF_GETTER_DESC, "を取得します。");
        store.setDefault(LimyCodeConstants.PREF_SETTER_DESC, "を設定します。");
        return store;
    }
    
    // ------------------------ Public Methods
    
    /**
     * Returns the shared instance.
     * @return 唯一のLimyCodePluginインスタンス
     */
    public static LimyCodePlugin getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given
     * plug-in relative path.
     *
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin("org.limy.eclipse.code", path);
    }
    
    /**
     * リソース文字列を返します。
     * @param key リソースキー
     * @return リソース文字列
     */
    public static String getResourceString(String key) {
        ResourceBundle bundle = getDefault().getResourceBundle();
        try {
            return (bundle != null) ? bundle.getString(key) : key;
        } catch (MissingResourceException e) {
            return key;
        }
    }
    
    public File getPluginRoot() {
        if (rootDir == null) {
            rootDir = LimyEclipsePluginUtils.getPluginRoot(plugin); // この処理に4秒くらい掛かる
        }
        return rootDir;
    }

    // ------------------------ Private Methods
    
    /**
     * リソースバンドルを返します。
     * @return リソースバンドル
     */
    private ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
