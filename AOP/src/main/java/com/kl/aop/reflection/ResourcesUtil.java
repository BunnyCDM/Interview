package com.kl.aop.reflection;

import android.content.res.Resources;

public final class ResourcesUtil {
	private static Resources RESOURCES;

	private static Resources getResources() {
		synchronized (ResourcesUtil.class) {
			if (null == RESOURCES) {
				RESOURCES = ContextUtil.getContext().getResources();
			}
			return RESOURCES;
		}
	}

	public static String getString(int id) {
		return getResources().getString(id);
	}

}
