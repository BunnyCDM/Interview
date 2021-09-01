package com.example.iconify.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by mac on 2019/2/22.
 */

public class FontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";//上文复制的字体文件
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
