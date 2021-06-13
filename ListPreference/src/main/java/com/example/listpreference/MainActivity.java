package com.example.listpreference;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * 上述程序中需要注意的事项：
 * 1.必须要有android:entryValues="@array/entries_values_str"与android:entries="@array/entries_str"相对应
 * 2.android:defaultValue="@string/default_str"中的default_str必须为entries_str中的一个选项
 * 3.lp=(ListPreference)findPreference(getString(R.string.key_str));这里的获取的字符串的内容必须为key_str，
 * 否则会出来NullPointException的错误（即初始化不成功的错误）。因为android:key="@string/key_str"相当于android:id=""
 */
public class MainActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{


    ListPreference lp;//创建一个ListPreference对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //过滤已经的XML文件资源，并将当前的preference层添加到这个preference层当中
        addPreferencesFromResource(R.xml.preference);

        //初始化这个ListPreference对象
        lp=(ListPreference)findPreference(getString(R.string.key_str));

        //设置获取ListPreference中发生的变化
        lp.setOnPreferenceChangeListener(this);
        /**让ListPreference中的摘要内容（即summary）显示为当前ListPreference中的实体对应的值
         * 这个方法的作用是为了当下一次打开这个程序时会显示上一次的设置的summary(摘要)
         * 如果没有添加这个方法，当再次打开这个程序时，它将不会显示上一次程序设置的值，而
         * 是显示默认值*/
        lp.setSummary(lp.getEntry());
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if(preference instanceof ListPreference){
            //把preference这个Preference强制转化为ListPreference类型
            ListPreference listPreference=(ListPreference)preference;
            //获取ListPreference中的实体内容
            CharSequence[] entries=listPreference.getEntries();
            //获取ListPreference中的实体内容的下标值
            int index=listPreference.findIndexOfValue((String)newValue);
            //把listPreference中的摘要显示为当前ListPreference的实体内容中选择的那个项目
            listPreference.setSummary(entries[index]);
        }
        return true;
    }
}