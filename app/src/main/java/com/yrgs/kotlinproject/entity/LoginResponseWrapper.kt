package com.yrgs.kotlinproject.entity

data class LoginResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String) {

}