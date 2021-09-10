package com.asw.rxretrofit.bean;

import java.util.List;

/**
 * Created by castl on 2016/4/23.
 */
public class Weather {


    private String reason; //返回说明
    private ResultBean result;  //结果集
    private int error_code;

    public String getReason() {
        return reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public static class ResultBean {


        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * wind : {"windspeed":"22.0","direct":"东北风","power":"3级","offset":null}
             * time : 11:00:00
             * weather : {"humidity":"40","img":"3","info":"阵雨","temperature":"19"}
             * dataUptime : 1461382083
             * date : 2016-04-23
             * city_code : 101180201
             * city_name : 安阳
             * week : 6
             * moon : 三月十七
             */

            private RealtimeBean realtime;
            /**
             * date : 2016-04-23
             * info : {"night":["1","多云","12","西南风","微风","19:03"],"day":["2","阴","20","东北风","3-4 级","05:38"]}
             * week : 六
             * nongli : 三月十七
             */

            private List<WeatherBean> weather;

            public RealtimeBean getRealtime() {
                return realtime;
            }


            public List<WeatherBean> getWeather() {
                return weather;
            }


            public static class RealtimeBean {
                /**
                 * humidity : 40
                 * img : 3
                 * info : 阵雨
                 * temperature : 19
                 */

                private WeatherBean weather;
                private String date;
                private String city_name;
                private int week;
                private String moon;

                public WeatherBean getWeather() {
                    return weather;
                }


                public String getDate() {
                    return date;
                }


                public String getCity_name() {
                    return city_name;
                }


                public int getWeek() {
                    return week;
                }


                public String getMoon() {
                    return moon;
                }


                public static class WeatherBean {
                    private String humidity;
                    private String img;
                    private String info;
                    private String temperature;

                    public String getHumidity() {
                        return humidity;
                    }


                    public String getImg() {
                        return img;
                    }


                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getTemperature() {
                        return temperature;
                    }


                }
            }

            public static class WeatherBean {
                private String date;
                private InfoBean info;
                private String week;
                private String nongli;

                public String getDate() {
                    return date;
                }


                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public String getWeek() {
                    return week;
                }


                public String getNongli() {
                    return nongli;
                }


                public static class InfoBean {
                    private List<String> night;
                    private List<String> day;

                    public List<String> getNight() {
                        return night;
                    }


                    public List<String> getDay() {
                        return day;
                    }

                }
            }
        }
    }
}