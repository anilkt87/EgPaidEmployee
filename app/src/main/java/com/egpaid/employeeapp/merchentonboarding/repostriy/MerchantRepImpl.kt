package com.egpaid.employeeapp.merchentonboarding.repostriy

import com.egpaid.employeeapp.base.dataservice.remote.RetrofitService
import javax.inject.Inject

class MerchantRepImpl @Inject constructor(
    val api: RetrofitService
) : MerchantRep {
}