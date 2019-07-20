package com.example.bottomtab.delegate;

/**
 * Created by mac on 2019/2/25.
 */

public abstract class LatteDelegate extends BaseDelegate{


    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentDelegate();
    }

}
