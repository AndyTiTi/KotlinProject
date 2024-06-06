package com.yrgs.kotlinproject.net

import android.content.Context
import com.yrgs.kotlinproject.LoadingDialog
import com.yrgs.kotlinproject.entity.LoginResponseWrapper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

// 拦截 自定义操作符 目的：将包装Bean拆分成两份  成功 data -> UI  失败 msg -> UI
abstract class APIResponse<T>(val context: Context) : Observer<LoginResponseWrapper<T>> {
    private var isShow: Boolean = false

    // 次构造
    constructor(context: Context, isShow: Boolean = false) : this(context) {
        this.isShow = isShow
    }

    // success
    abstract fun success(data: T?)

    // fail
    abstract fun failure(msg: String?)


    override fun onSubscribe(t: Disposable) {
        // 可以在这里处理订阅逻辑，例如检查是否已订阅
        if (isShow) {
            LoadingDialog.show(context)
        }
    }

    override fun onNext(t: LoginResponseWrapper<T>) {

        if (t.data == null) {
            failure("登录失败：msg:${t.errorMsg}")
        } else {
            success(t.data)
        }
    }

    override fun onError(t: Throwable) {
        LoadingDialog.cancel()
        failure(t.message)
    }

    override fun onComplete() {
        LoadingDialog.cancel()
    }
}