package com.example.gobus.viewstate

data class CommonViewState<T>(
    var showLoading: Boolean = false,
    var data: List<T>? = null,
    var isFail: FailCase = FailCase(),
    var isError: ErrorCase = ErrorCase()
)
data class ErrorCase(
    var isError: Boolean = false,
    var exception: Throwable? = null,
    var errorString: String? = null
)
data class FailCase(
    var isFail: Boolean = false,
    var failCode: Int? = null,
)