package com.egpaid.employeeapp.base.basemapper

interface Mapper<in SOURCE, out DESTINATION> {

    fun map(source: SOURCE): DESTINATION
}