package com.example.gatekeeper.model

data class User(
    var id: String,
    var name: String,
    var phoneNo: String,
    var emailAddress: String,
    var password: String,
    var role: String,
    var supporter: MutableList<String> = mutableListOf(),
    var requested: MutableList<String> = mutableListOf()
)
