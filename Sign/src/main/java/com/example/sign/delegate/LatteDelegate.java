package com.example.sign.delegate;

/**
 * Created by mac on 2019/2/26.
 */

public abstract class LatteDelegate extends BaseDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentDelegate();
    }

}
