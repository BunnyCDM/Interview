package com.example.listview.city;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listview.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityActivity extends Activity {

    private EditText searchEt;
    private ListView mListView;
    private SideBar mSideBar;
    private TextView hintTv;
    private ProgressDialog pd;
    private CityListAdapter mAdapter;
    private List<CityBean> mData = new ArrayList<CityBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initView();
    }

    private void initView() {
        hintTv = (TextView) findViewById(R.id.centerHintTv);
        searchEt = (EditText) findViewById(R.id.searchEt);
        mListView = (ListView) findViewById(R.id.listview);
        mSideBar = (SideBar) findViewById(R.id.sidebar);
        pd = new ProgressDialog(this);
        pd.setTitle("提示");
        pd.setMessage("正在解析数据，请稍等...");
        pd.setCancelable(false);
        new ParseXmlTask().execute();

        searchEt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if (mAdapter != null) {
                    int len = arg0.length();
                    if (len == 0) {
                        mAdapter.resetData();
                    } else if (len > 0) {
                        mAdapter.queryData(arg0.toString());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        //监听SideBar的手指按下和抬起事件
        mSideBar.setOnSelectListener(new SideBar.OnSelectListener() {

            @Override
            public void onSelect(String s) {
                //手指按下时显示中央的字母
                hintTv.setVisibility(View.VISIBLE);
                hintTv.setText(s);
                //如果SideBar按下的是#号，则ListView定位到位置0
                if ("#".equals(s)) {
                    mListView.setSelection(0);
                    return;
                }
                //获取手指按下的字母所在的块索引
                int section = s.toCharArray()[0];
                //根据块索引获取该字母首次在ListView中出现的位置
                int pos = mAdapter.getPositionForSection(section - 65);
                //定位ListView到按下字母首次出现的位置
                mListView.setSelection(pos);
            }

            @Override
            public void onMoveUp(String s) {
                hintTv.setVisibility(View.GONE);
                hintTv.setText(s);
            }
        });
    }

    //显示城市列表
    private void showListView(final List<CityBean> list) {
        mAdapter = new CityListAdapter(this, list, R.layout.list_item);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //点击ListView的某个item后显示当前选择的城市名
                String name = mAdapter.getItem(arg2).getName();
                Toast toast = Toast.makeText(CityActivity.this, name, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

    private class ParseXmlTask extends AsyncTask<Void, Void, Void> {

        private XmlPullParser pullParser;
        private List<ProvinceBean> provinceList;
        private ProvinceBean province;
        private CityBean city;
        private CountyBean county;

        public ParseXmlTask() {
            provinceList = new ArrayList<ProvinceBean>();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //开始解析前，新建解析器
            pullParser = Xml.newPullParser();
            //从raw目录下获取呆解析的数据的输入流
            InputStream is = getResources().openRawResource(R.raw.cities);
            try {
                pullParser.setInput(is, "UTF-8");
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            //显示进度对话框
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            int eventType = 1;
            try {
                eventType = pullParser.getEventType();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            //while循环解析xml数据
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = pullParser.getName();
                        if ("province".equals(startTag)) {//省
                            province = new ProvinceBean();
                            province.setId(pullParser.getAttributeValue(null, "id"));
                            province.setName(pullParser.getAttributeValue(null, "name"));
                        } else if ("city".equals(startTag)) {//市
                            city = new CityBean();
                            city.setId(pullParser.getAttributeValue(null, "id"));
                            city.setName(pullParser.getAttributeValue(null, "name"));
                            String name = city.getName();
                            if (!TextUtils.isEmpty(name) && name.length() > 0) {
                                //获取城市名的首字母（这里获取的是小写）
                                String pinyin = CharacterParser.getInstance().getPinYinSpelling(name.substring(0, 1));
                                //-32获取小写首字母对应的大写字母
                                city.setFirstLetter((char) (pinyin.charAt(0) - 32));
                            }
                        } else if ("county".equals(startTag)) {//区或县
                            county = new CountyBean();
                            county.setId(pullParser.getAttributeValue(null, "id"));
                            county.setName(pullParser.getAttributeValue(null, "name"));
                            county.setWeatherCode(pullParser.getAttributeValue(null, "weatherCode"));
                            if (city != null) {
                                city.getCountyList().add(county);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = pullParser.getName();
                        if ("city".equals(endTag) && province != null) {
                            province.getCityList().add(city);
                        } else if ("province".equals(endTag) && province != null) {
                            provinceList.add(province);
                        }
                        break;
                }
                try {
                    eventType = pullParser.next();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (provinceList != null) {
                //for循环获取所有的城市名
                for (ProvinceBean province : provinceList) {
                    List<CityBean> cityList = province.getCityList();
                    for (CityBean city : cityList) {
                        mData.add(city);
                    }
                }
                Collections.sort(mData, comparator);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pd.dismiss();
            showListView(mData);
        }

    }

    private Comparator<CityBean> comparator = new Comparator<CityBean>() {

        @Override
        public int compare(CityBean arg0, CityBean arg1) {
            //获取城市名对应的拼音，通过比较拼音来确定城市的先后次序
            String pinyin0 = CharacterParser.getInstance().getPinYinSpelling(arg0.getName());
            String pinyin1 = CharacterParser.getInstance().getPinYinSpelling(arg1.getName());
            return pinyin0.compareTo(pinyin1);
        }
    };

}
