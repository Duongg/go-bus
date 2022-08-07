package com.example.gobus.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.viewstate.CommonViewState
import com.example.gobus.viewstate.ErrorCase
import com.example.gobus.viewstate.FailCase

class GetBusRoutesViewModel: BaseViewModel() {

    var getBusRoutesViewModelLiveData = MutableLiveData<CommonViewState<ResponseModel.BusRoute.Result>>()

    fun getBusRoutes(){
        compositeDisposable.add(
            apiRepository.getBusRoutes().doOnSubscribe {
                getBusRoutesViewModelLiveData.value = CommonViewState()
            }.subscribe({ response ->
              Log.d("TAG", response.toString())
                when(response){
                    is ResponseModel.BusRoute.ResponseSealed.Success ->{
                        getBusRoutesViewModelLiveData.value = CommonViewState(
                            data = response.result
                        )
                    }
                    is ResponseModel.BusRoute.ResponseSealed.Fail ->{
                        getBusRoutesViewModelLiveData.value = CommonViewState(
                            data = null,
                            isFail = FailCase(true, response.response.code())
                        )
                    }
                    else -> {

                    }
                }
            }){
                getBusRoutesViewModelLiveData.value = CommonViewState(
                    data = null,
                    isError =  ErrorCase(true, it, it.message)
                )
            }
        )
    }
}