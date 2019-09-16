package com.qiujuer.library.clink.impl.bridge;

/**
 * Created by mac on 2019/7/16.
 */
public class BridgeIllegalStateException extends IllegalStateException {

    public static void check(boolean status) {
        if (!status) {
            throw new BridgeIllegalStateException();
        }
    }

}

