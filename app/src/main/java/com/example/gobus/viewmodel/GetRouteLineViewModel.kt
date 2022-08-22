package com.example.gobus.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gobus.data.local.entity.BusRouteLineEntity
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.viewstate.CommonViewState
import com.example.gobus.viewstate.ErrorCase
import com.example.gobus.viewstate.FailCase

class GetRouteLineViewModel: BaseViewModel() {
    var getBusRouteLineViewModelLiveData = MutableLiveData<CommonViewState<ResponseModel.BusRouteLine.Result>>()

    fun getBusRouteLine(){
        compositeDisposable.add(
            apiRepository.getBusRouteLine().doOnSubscribe {
                getBusRouteLineViewModelLiveData.value = CommonViewState()
            }.subscribe({ response ->
                when(response){
                    is ResponseModel.BusRouteLine.ResponseSealed.Success ->{
                        getBusRouteLineViewModelLiveData.value = CommonViewState(
                            data = response.result
                        )
                    }
                    is ResponseModel.BusRouteLine.ResponseSealed.Fail ->{
                        getBusRouteLineViewModelLiveData.value = CommonViewState(
                            data = null,
                            isFail = FailCase(true, response.responseBody.code())
                        )
                    }
                }

            }){
                getBusRouteLineViewModelLiveData.value = CommonViewState(
                    data = null,
                    isError = ErrorCase(true, it, it.message)
                )
            }
        )
    }
    fun insertBusRouteLine(busRouteLine: ResponseModel.BusRouteLine.Result){
       apiRepository.insertBusRouteLines(busRouteLine)
    }
}