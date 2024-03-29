package com.example.cityselector02;

public class City {
	/*
	 * 武汉  province=湖北 city=武汉, number=1022211, firstPY=w, allPY=wuhan, allFirstPY = wh
	 * */
	private String province;
	private String city;
	private String number;
	private String firstPY;
	private String allPY;
	private String allFirstPY;
	
	public City(String province, String city, String number, String firstPY,
			String allPY, String allFirstPY) {
		this.province = province;
		this.city = city;
		this.number = number;
		this.firstPY = firstPY;
		this.allPY = allPY;
		this.allFirstPY = allFirstPY;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getFirstPY() {
		return firstPY;
	}
	public void setFirstPY(String firstPY) {
		this.firstPY = firstPY;
	}
	public String getAllPY() {
		return allPY;
	}
	public void setAllPY(String allPY) {
		this.allPY = allPY;
	}
	public String getAllFirstPY() {
		return allFirstPY;
	}
	public void setAllFirstPY(String allFirstPY) {
		this.allFirstPY = allFirstPY;
	}
	@Override
	public String toString() {
		return "City [province=" + province + ", city=" + city + ", number="
				+ number + ", firstPY=" + firstPY + ", allPY=" + allPY
				+ ", allFirstPY=" + allFirstPY + "]";
	}
	
}
