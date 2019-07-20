package com.example.listview.city;

import java.util.ArrayList;
import java.util.List;

/**
 * 省份
 *
 * @author Test
 */
public class ProvinceBean extends BaseBean {

    private String name;
    private List<CityBean> cityList;

    public ProvinceBean() {
        cityList = new ArrayList<CityBean>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
    }

}
