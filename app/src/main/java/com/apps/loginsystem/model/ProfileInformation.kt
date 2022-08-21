package com.apps.loginsystem.model

data class ProfileInformation(
    var fullName:String ="",
    var emailAddress:String ="",
    var phoneNumber:Int =0,
    var password:String ="",
) {
}