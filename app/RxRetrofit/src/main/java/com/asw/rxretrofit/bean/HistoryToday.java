package com.asw.rxretrofit.bean;

import java.util.List;

/**
 * Created by castl on 2016/4/24.
 */
public class HistoryToday {

    /**
     * reason : success
     * result : [{"day":"4/24","date":"1192年04月24日","title":"卢沟桥建成","e_id":"4905"},{"day":"4/24","date":"1731年04月24日","title":"英国作家丹尼尔·笛福逝世","e_id":"4906"},{"day":"4/24","date":"1792年04月24日","title":"里勒创作《马赛曲》","e_id":"4907"},{"day":"4/24","date":"1800年04月24日","title":"美国国会图书馆成立","e_id":"4908"},{"day":"4/24","date":"1856年04月24日","title":"法国陆军将领、政治家贝当出生","e_id":"4909"},{"day":"4/24","date":"1891年04月24日","title":"普鲁士和德意志总参谋长，著名军事家毛奇逝世","e_id":"4910"},{"day":"4/24","date":"1905年04月24日","title":"清廷废除死刑中的凌迟、枭首、戮尸","e_id":"4911"},{"day":"4/24","date":"1911年04月24日","title":"林觉民挥泪写遗书","e_id":"4912"},{"day":"4/24","date":"1916年04月24日","title":"爱尔兰爆发复活节起义","e_id":"4913"},{"day":"4/24","date":"1918年04月24日","title":"世界历史上第一次坦克战爆发","e_id":"4914"},{"day":"4/24","date":"1919年04月24日","title":"詹天佑病逝","e_id":"4915"},{"day":"4/24","date":"1925年04月24日","title":"莽原社成立","e_id":"4916"},{"day":"4/24","date":"1925年04月24日","title":"陈诚等发起孙中山主义学会","e_id":"4917"},{"day":"4/24","date":"1931年04月24日","title":"顾顺章叛变，中共中央机关险遭破坏","e_id":"4918"},{"day":"4/24","date":"1941年04月24日","title":"抗战英雄谢晋元遇难","e_id":"4919"},{"day":"4/24","date":"1945年04月24日","title":"毛泽东作《论联合政府》报告","e_id":"4920"},{"day":"4/24","date":"1947年04月24日","title":"民国时期文物损失严重","e_id":"4921"},{"day":"4/24","date":"1948年04月24日","title":"苏联战斗机与英国货机相撞","e_id":"4922"},{"day":"4/24","date":"1949年04月24日","title":"解放军攻破太原","e_id":"4923"},{"day":"4/24","date":"1950年04月24日","title":"外约旦改名为约旦","e_id":"4924"},{"day":"4/24","date":"1970年04月24日","title":"我国第一颗人造地球卫星上天","e_id":"4925"},{"day":"4/24","date":"1972年04月24日","title":"《人民日报》发表社论《惩前毖后，治病救人》","e_id":"4926"},{"day":"4/24","date":"1980年04月24日","title":"美武装营救扣押在伊朗的人质失败","e_id":"4927"},{"day":"4/24","date":"1981年04月24日","title":"IBM公司推出首部个人电脑","e_id":"4928"},{"day":"4/24","date":"1986年04月24日","title":"温莎公爵夫人去世","e_id":"4929"},{"day":"4/24","date":"1986年04月24日","title":"世界最大的飞机安-124起飞","e_id":"4930"},{"day":"4/24","date":"1991年04月24日","title":"我停止对金门等岛屿国民党军官兵广播喊话","e_id":"4931"},{"day":"4/24","date":"1991年04月24日","title":"日本向海湾派遣扫雷舰队","e_id":"4932"},{"day":"4/24","date":"1992年04月24日","title":"山西省曲沃出土一大批西周文物","e_id":"4933"},{"day":"4/24","date":"1993年04月24日","title":"南非非国大全国主席坦博去世","e_id":"4934"},{"day":"4/24","date":"1994年04月24日","title":"南非大选前发生严重爆炸事件","e_id":"4935"},{"day":"4/24","date":"1995年04月24日","title":"我国卫星数字广播开播","e_id":"4936"},{"day":"4/24","date":"1995年04月24日","title":"孔繁森被追认为全国先进工作者","e_id":"4937"},{"day":"4/24","date":"1996年04月24日","title":"星光工程正式启动","e_id":"4938"},{"day":"4/24","date":"1997年04月24日","title":"中俄哈吉塔五国边境裁军协定签署","e_id":"4939"},{"day":"4/24","date":"1997年04月24日","title":"著名画家黄胄逝世","e_id":"4940"},{"day":"4/24","date":"1997年04月24日","title":"中国青年五四奖章首次颁奖","e_id":"4941"},{"day":"4/24","date":"1998年04月24日","title":"基里延科就任俄国斯总理","e_id":"4942"},{"day":"4/24","date":"1999年04月24日","title":"中国企业联合会组建","e_id":"4943"},{"day":"4/24","date":"2003年04月24日","title":"全国防治非典型肺炎指挥部在京成立","e_id":"4944"},{"day":"4/24","date":"2012年04月24日","title":"干露露事件","e_id":"4945"},{"day":"4/24","date":"2014年04月24日","title":"青海省委原常委、西宁市委原书记毛小兵被查","e_id":"4946"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;

    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public static class ResultBean {
        private String day;
        private String date;
        private String title;
        private String e_id;

        public String getDay() {
            return day;
        }


        public String getDate() {
            return date;
        }


        public String getTitle() {
            return title;
        }


    }
}
