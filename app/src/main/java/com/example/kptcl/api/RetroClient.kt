package com.example.kptcl.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetroClient(urlService: String) {
    val apiService: RegisterApi
        get() = retrofitInstance.create(RegisterApi::class.java)

    companion object {
        //    private static final String CUSTINFOSERVICE = "http://192.168.100.98:8000/CUSTINFOSERVICE.asmx/";
        //  private static final String CUSTINFOSERVICE = "http://test_bc_service.hescomtrm.com/CUSTINFOSERVICE.asmx/";
        const val CUSTINFOSERVICE = "http://bc_service.hescomtrm1.com/CUSTINFOSERVICE.asmx/"
        const val TRMCARESERVICE = "http://bc_service.hescomtrm1.com/TRMCareService.asmx/"
        private var BASE_URL = CUSTINFOSERVICE
        var TICKET_KEY = ""
        private val retrofitInstance: Retrofit
            private get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    init {
        BASE_URL = urlService
        TICKET_KEY = "TeSt_TvDhEcOmTrM540038TiCkEtInGKeYAnDrOiDdOtNeT"
    }
}