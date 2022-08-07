package com.example.gobus.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.gobus.responsemodel.ResponseModel
import com.example.gobus.viewstate.CommonViewState
import com.example.gobus.viewstate.ErrorCase
import com.example.gobus.viewstate.FailCase

class GetBusStopsViewModel: BaseViewModel() {
    var getBusStopsViewModelLiveData = MutableLiveData<CommonViewState<ResponseModel.BusStop.Result>>()

    fun getBusStops(){
        compositeDisposable.add(
            apiRepository.getBusStops().doOnSubscribe {
                    getBusStopsViewModelLiveData.value = CommonViewState()
            }.subscribe({ response ->
                when(response){
                    is ResponseModel.BusStop.ResponseSealed.Success -> {
                        getBusStopsViewModelLiveData.value = CommonViewState(
                            data = response.result
                        )
                    }
                    is ResponseModel.BusStop.ResponseSealed.Fail -> {
                        getBusStopsViewModelLiveData.value = CommonViewState(
                            data = null,
                            isFail = FailCase(true, response.responseBody.code())
                        )
                    }

                }
            }){
                getBusStopsViewModelLiveData.value = CommonViewState(
                    data = null,
                    isError = ErrorCase(true, it, it.message)
                )
            }
        )
    }
}