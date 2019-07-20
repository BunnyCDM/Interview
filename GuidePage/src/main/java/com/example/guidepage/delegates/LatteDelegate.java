package com.example.guidepage.delegates;

/**
 * Created by mac on 2019/2/22.
 */

public abstract class LatteDelegate extends BaseDelegate {


    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentDelegate();
    }

}
