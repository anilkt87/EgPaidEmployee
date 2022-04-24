package com.egpaid.employeeapp.home.view.entities.myprofile

import com.example.example.Education
import com.example.example.Experience
import com.google.gson.annotations.SerializedName


data class MyProfile (

  @SerializedName("empid"         ) var empid         : String?               = null,
  @SerializedName("emptype"       ) var emptype       : String?               = null,
  @SerializedName("emptype2"      ) var emptype2      : String?               = null,
  @SerializedName("designation"   ) var designation   : String?               = null,
  @SerializedName("designationid" ) var designationid : String?               = null,
  @SerializedName("department"    ) var department    : String?               = null,
  @SerializedName("departmentid"  ) var departmentid  : String?               = null,
  @SerializedName("name"          ) var name          : String?               = null,
  @SerializedName("father"        ) var father        : String?               = null,
  @SerializedName("guardian"      ) var guardian      : String?               = null,
  @SerializedName("guardianrel"   ) var guardianrel   : String?               = null,
  @SerializedName("married"       ) var married       : String?               = null,
  @SerializedName("mobile"        ) var mobile        : String?               = null,
  @SerializedName("doj"           ) var doj           : String?               = null,
  @SerializedName("dob"           ) var dob           : String?               = null,
  @SerializedName("gender"        ) var gender        : String?               = null,
  @SerializedName("email"         ) var email         : String?               = null,
  @SerializedName("aadhar"        ) var aadhar        : String?               = null,
  @SerializedName("pan"           ) var pan           : String?               = null,
  @SerializedName("officeemail"   ) var officeemail   : String?               = null,
  @SerializedName("officemobile"  ) var officemobile  : String?               = null,
  @SerializedName("profileimg"    ) var profileimg    : String?               = null,
  @SerializedName("address"       ) var address       : String?               = null,
  @SerializedName("city"          ) var city          : String?               = null,
  @SerializedName("pincode"       ) var pincode       : String?               = null,
  @SerializedName("district"      ) var district      : String?               = null,
  @SerializedName("state"         ) var state         : String?               = null,
  @SerializedName("keyskill"      ) var keyskill      : String?               = null,
  @SerializedName("education"     ) var education     : ArrayList<Education>  = arrayListOf(),
  @SerializedName("experience"    ) var experience    : ArrayList<Experience> = arrayListOf()

)