package com.example.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Employee employee = new Employee("Zhai", "Mark");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //通过DataBInding加载布局都会对应的生成Binding对象，如ActivityMainBinding，对象名在布局文件名称后加上了一个后缀Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //方式一，通过binfding.id名称---就相当于获取了改控件对象了
//        binding.firstName.setText(employee.getFirstName());
//        binding.lastName.setText(employee.getLastName());

        //方式二
        binding.setEmployee(employee); //或者binding.setVariable(BR.employee,employee);


        //事件绑定分两种：一种是方法引用、另一种是监听器绑定
        binding.setPresenter(new Presenter());
    }


    public class Presenter {

        //方法引用.必须和原来方法一致
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            employee.setFirstName(s.toString());
//            binding.setEmployee(employee);//通过Employee extends BaseObservable就可以暂且注释掉
        }

        //方法引用
        public void onClick(View view){
            Toast.makeText(MainActivity.this,"触发了1",Toast.LENGTH_SHORT).show();
        }

        //监听器绑定,没有方法引用那么要求严格
        public void onClickListenerBinding(Employee employee){
            //当点击由xml回传到java代码里
            Toast.makeText(MainActivity.this,"触发了2",Toast.LENGTH_SHORT).show();
        }

    }
}
