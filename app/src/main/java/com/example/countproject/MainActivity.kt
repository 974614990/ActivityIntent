package com.example.countproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //给按钮添加点击事件
        mNextbtn.setOnClickListener{
            //跳转到下一个界面DetailActivity 进行计算
            //创建Intent 可以直接设置从那个界面跳转到那个界面 Intent(this,DetailActivity::class.java)
              Intent().apply{
                //设置值
                putExtra("first",mfirst.text.toString().toInt())
                putExtra("second",mSecond.text.toString().toInt())

                //设置从那个页面跳转到那个页面
                setClass(this@MainActivity,DetailActivity::class.java)
                  //启动界面
                 //startActivity(this)
                  startActivityForResult(this,123)
            }
        }
        //分享按钮
        mSharebtn.setOnClickListener{
            //使用隐式跳转到WeChat界面
            Intent().apply {
                action  = "ht.WeChat"
                data = Uri.parse("content: 今天完成了activity的跳转")
            }.also {
                //startActivity(it)
                startActivityForResult(it,456)

            }
        }
    }


    //接收回调的数据
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123){
            //详细页面
            data?.getIntExtra("result",0).also {
                //将结果赋值到mResult上
                mResult.text = it.toString()
            }
        }else if(requestCode == 456){
            //分享页面返回的结果
            data?.getStringExtra("shareResult").also {
                //弹出一个提示框 弹出分享成功提示框
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        }
    }
}