package com.asw.retrofit;

/**
 * Created by mac on 2017/4/14.
 */

public class Tea {

    /**
     * product_cat_id : 2032
     * product_cat_name : 青茶
     * product_cat_parent_id :
     * industry_id : 161
     */

    private String product_cat_id;
    private String product_cat_name;
    private String product_cat_parent_id;
    private String industry_id;

    public String getProduct_cat_id() {
        return product_cat_id;
    }

    public void setProduct_cat_id(String product_cat_id) {
        this.product_cat_id = product_cat_id;
    }

    public String getProduct_cat_name() {
        return product_cat_name;
    }

    public void setProduct_cat_name(String product_cat_name) {
        this.product_cat_name = product_cat_name;
    }

    public String getProduct_cat_parent_id() {
        return product_cat_parent_id;
    }

    public void setProduct_cat_parent_id(String product_cat_parent_id) {
        this.product_cat_parent_id = product_cat_parent_id;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }
}
