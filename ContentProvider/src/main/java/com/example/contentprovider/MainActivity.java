package com.example.contentprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private DBManager mDBManager;
	private EditText mUserId;
	private EditText mUserName;
	private EditText mPassword;
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mUserId = (EditText)findViewById(R.id.id);
		mUserName = (EditText)findViewById(R.id.username);
		mPassword = (EditText)findViewById(R.id.password);
		mListView = (ListView)findViewById(R.id.allusers);
		
		mDBManager = new DBManager(this);
		findViewById(R.id.add).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				User user = new User(mUserId.getText().toString(),
						mUserName.getText().toString(), mPassword.getText().toString());
				mDBManager.add(user);
			}
		});

		findViewById(R.id.del).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDBManager.deleteWithId(mUserId.getText().toString());
			}
		});
		
		findViewById(R.id.del_with_username).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDBManager.deleteWithUserName(mUserName.getText().toString());
			}
		});

		findViewById(R.id.chg).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				User user = new User(mUserId.getText().toString(),
						mUserName.getText().toString(), mPassword.getText().toString());
				mDBManager.update(user);
			}
		});

		findViewById(R.id.quy).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showQueryList();
			}
		});
		
	}

	private void showQueryList() {
		List<User> userlist = mDBManager.getAllUserList();
		
		List<HashMap<String, Object>> lists = new ArrayList<HashMap<String, Object>>();
		for(User user : userlist){
			HashMap<String, Object> obj = new HashMap<String, Object>();
			obj.put("id", user.getId());
			obj.put("username", user.getUsername());
			obj.put("password", user.getPassword());
			lists.add(obj);
		}
	
		mListView.setAdapter(new SimpleAdapter(this, lists, R.layout.query_list_item,
				new String[]{"id", "username", "password"}, 
				new int[]{R.id.myid, R.id.myusername, R.id.mypasswd}));
	}

}
