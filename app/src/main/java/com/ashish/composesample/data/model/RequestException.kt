package com.ashish.composesample.data.model

class RequestException(val code: Int, message: String) : Throwable(message)