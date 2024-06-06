package com.yrgs.kotlinproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yrgs.kotlinproject.modules.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 检查用户是否登录
        if (!isLoggedIn()) {
            // 如果用户未登录，则跳转到登录页面
            redirectToLoginActivity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // 模拟检查用户登录状态的方法
    private fun isLoggedIn(): Boolean {
        // 这里应该包含检查用户登录状态的逻辑
        // 例如，检查SharedPreferences中的标记，或调用服务器API检查会话状态
        // 这里我们假设用户未登录，返回false
        return false
    }

    // 跳转到登录页面的方法
    private fun redirectToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        // 如果你不希望用户返回MainActivity，可以添加以下代码
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        // finish()
    }
}