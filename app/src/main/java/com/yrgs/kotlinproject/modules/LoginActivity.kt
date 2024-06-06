package com.yrgs.kotlinproject.modules

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yrgs.kotlinproject.api.WanAndroidAPI
import com.yrgs.kotlinproject.entity.LoginResponse
import com.yrgs.kotlinproject.net.APIClient
import com.yrgs.kotlinproject.net.APIResponse
import com.yrgs.kotlinproject.R
import com.yrgs.kotlinproject.databinding.ActivityLoginBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {
    // 将 binding 声明为成员变量
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var api: WanAndroidAPI = APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
        binding.btnLogin.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            //登录
            R.id.btnLogin -> {
                val userName = binding.etUsername.text.toString()
                var password = binding.etPassword.text.toString()
                Log.d("TTT", "onClickListener: $userName, User pwd: $password")
                // 全部都是RxJava知识了
                APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                    .loginAction(userName, password)  // 起点，开始往下流
                    .subscribeOn(Schedulers.io()) // 给上面请求服务器的操作分配异步线程
                    .observeOn(AndroidSchedulers.mainThread()) // 给下面更新UI操作分配main线程
                    //.subscribe(object : Consumer<LoginResponseWrapper<LoginResponse>> {
                    //    override fun accept(t: LoginResponseWrapper<LoginResponse>?) {
                    //
                    //    }
                    //})
                    .subscribe(object : APIResponse<LoginResponse>(this,true){
                        override fun success(data: LoginResponse?) {
                            Log.d("TTT", "success: $data")
                            Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT).show()
                        }

                        override fun failure(msg: String?) {
                            Log.e("TTT", "failure: $msg")
                            Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
    }
}