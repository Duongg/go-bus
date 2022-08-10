package com.example.gobus.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.viewstate.CommonViewState
import com.example.gobus.viewstate.ErrorCase
import com.example.gobus.viewstate.FailCase

class GetBusByRouteIdViewModel: BaseViewModel() {
    var getBusByRouteIdViewModelLiveData = MutableLiveData<CommonViewState<ResponseModel.BusData.Result>>()

    fun getBusByRouteId(busRouteId: String){
        compositeDisposable.add(
            apiRepository.getBusByBusRouteID(busRouteId).doOnSubscribe {
                getBusByRouteIdViewModelLiveData.value = CommonViewState()
            }.subscribe({ response ->
                when(response){
                    is ResponseModel.BusData.ResponseSealed.Success -> {
                        getBusByRouteIdViewModelLiveData.value = CommonViewState(
                            data = response.result
                        )
                    }
                    is ResponseModel.BusData.ResponseSealed.Fail -> {
                        getBusByRouteIdViewModelLiveData.value = CommonViewState(
                            data = null,
                            isFail = FailCase(true, response.responseBody.code())
                        )
                    }

                }
            }){
                getBusByRouteIdViewModelLiveData.value = CommonViewState(
                    data = null,
                    isError = ErrorCase(true, it, it.message)
                )
            }
        )
    }

}