package com.asw.rxretrofit.bean;

import java.util.List;

/**
 * Created by castl on 2016/4/24.
 */
public class TrainTimeByStation {

    /**
     * reason : 查询成功
     * result : {"title":"郑州到福州_火车时刻表及余票查询","list":[{"train_no":"K32","m_train_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fcheci_K32%3Futm_source%3D360&m=416f36&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b","train_type":"快速","start_station":"郑州","start_station_type":"过","end_station":"福州","end_station_type":"终","start_time":"16:06","end_time":"13:35","run_time":"21小时29分","run_distance":"","price_list":[{"price_type":"硬座","price":"192"},{"price_type":"硬卧","price":"351"}],"m_chaxun_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fbuy%2Fhome.html%3Ffrom%3D%E9%83%91%E5%B7%9E%26to%3D%E7%A6%8F%E5%B7%9E%26checi%3DK32%26utm_source%3Dh5260%26channel%3Dm&m=4942cb&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b"},{"train_no":"D295","m_train_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fcheci_D295%3Futm_source%3D360&m=8af04d&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b","train_type":"动车","start_station":"郑州","start_station_type":"始","end_station":"福州","end_station_type":"终","start_time":"08:24","end_time":"18:47","run_time":"10小时23分","run_distance":"","price_list":[{"price_type":"二等座","price":"441"},{"price_type":"一等座","price":"593"}],"m_chaxun_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fbuy%2Fhome.html%3Ffrom%3D%E9%83%91%E5%B7%9E%26to%3D%E7%A6%8F%E5%B7%9E%26checi%3DD295%26utm_source%3Dh5260%26channel%3Dm&m=eb628d&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b"},{"train_no":"K32","m_train_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fcheci_K32%3Futm_source%3D360&m=416f36&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b","train_type":"快速","start_station":"郑州","start_station_type":"过","end_station":"福州","end_station_type":"终","start_time":"15:42","end_time":"13:28","run_time":"21小时46分","run_distance":"","price_list":[{"price_type":"硬座","price":"192"},{"price_type":"硬卧","price":"371"}],"m_chaxun_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fbuy%2Fhome.html%3Ffrom%3D%E9%83%91%E5%B7%9E%26to%3D%E7%A6%8F%E5%B7%9E%26checi%3DK32%26utm_source%3Dh5260%26channel%3Dm&m=4942cb&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b"}]}
     * error_code : 0
     */

    private String reason;
    /**
     * title : 郑州到福州_火车时刻表及余票查询
     * list : [{"train_no":"K32","m_train_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fcheci_K32%3Futm_source%3D360&m=416f36&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b","train_type":"快速","start_station":"郑州","start_station_type":"过","end_station":"福州","end_station_type":"终","start_time":"16:06","end_time":"13:35","run_time":"21小时29分","run_distance":"","price_list":[{"price_type":"硬座","price":"192"},{"price_type":"硬卧","price":"351"}],"m_chaxun_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fbuy%2Fhome.html%3Ffrom%3D%E9%83%91%E5%B7%9E%26to%3D%E7%A6%8F%E5%B7%9E%26checi%3DK32%26utm_source%3Dh5260%26channel%3Dm&m=4942cb&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b"},{"train_no":"D295","m_train_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fcheci_D295%3Futm_source%3D360&m=8af04d&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b","train_type":"动车","start_station":"郑州","start_station_type":"始","end_station":"福州","end_station_type":"终","start_time":"08:24","end_time":"18:47","run_time":"10小时23分","run_distance":"","price_list":[{"price_type":"二等座","price":"441"},{"price_type":"一等座","price":"593"}],"m_chaxun_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fbuy%2Fhome.html%3Ffrom%3D%E9%83%91%E5%B7%9E%26to%3D%E7%A6%8F%E5%B7%9E%26checi%3DD295%26utm_source%3Dh5260%26channel%3Dm&m=eb628d&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b"},{"train_no":"K32","m_train_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fcheci_K32%3Futm_source%3D360&m=416f36&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b","train_type":"快速","start_station":"郑州","start_station_type":"过","end_station":"福州","end_station_type":"终","start_time":"15:42","end_time":"13:28","run_time":"21小时46分","run_distance":"","price_list":[{"price_type":"硬座","price":"192"},{"price_type":"硬卧","price":"371"}],"m_chaxun_url":"http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fbuy%2Fhome.html%3Ffrom%3D%E9%83%91%E5%B7%9E%26to%3D%E7%A6%8F%E5%B7%9E%26checi%3DK32%26utm_source%3Dh5260%26channel%3Dm&m=4942cb&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b"}]
     */

    private ResultBean result;
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
        private String title;
        /**
         * train_no : K32
         * m_train_url : http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fcheci_K32%3Futm_source%3D360&m=416f36&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b
         * train_type : 快速
         * start_station : 郑州
         * start_station_type : 过
         * end_station : 福州
         * end_station_type : 终
         * start_time : 16:06
         * end_time : 13:35
         * run_time : 21小时29分
         * run_distance :
         * price_list : [{"price_type":"硬座","price":"192"},{"price_type":"硬卧","price":"351"}]
         * m_chaxun_url : http://j.www.haosou.com/?u=http%3A%2F%2Fm.tieyou.com%2Fbuy%2Fhome.html%3Ffrom%3D%E9%83%91%E5%B7%9E%26to%3D%E7%A6%8F%E5%B7%9E%26checi%3DK32%26utm_source%3Dh5260%26channel%3Dm&m=4942cb&from=juhe&type=train_ab&juid=JH7cd5bd8c8847e29aee25849a0579540b
         */

        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String train_no;
            private String m_train_url;
            private String train_type;
            private String start_station;
            private String start_station_type;
            private String end_station;
            private String end_station_type;
            private String start_time;
            private String end_time;
            private String run_time;
            private String run_distance;
            private String m_chaxun_url;
            /**
             * price_type : 硬座
             * price : 192
             */



            public String getTrain_no() {
                return train_no;
            }



            public String getM_train_url() {
                return m_train_url;
            }


            public String getTrain_type() {
                return train_type;
            }


            public String getStart_station() {
                return start_station;
            }



            public String getStart_station_type() {
                return start_station_type;
            }



            public String getEnd_station() {
                return end_station;
            }



            public String getEnd_station_type() {
                return end_station_type;
            }



            public String getStart_time() {
                return start_time;
            }


            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getRun_time() {
                return run_time;
            }



            public String getRun_distance() {
                return run_distance;
            }



            public String getM_chaxun_url() {
                return m_chaxun_url;
            }


        }
    }
}
