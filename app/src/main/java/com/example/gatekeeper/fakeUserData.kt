package com.example.gatekeeper
import com.example.gatekeeper.model.User

val users = mutableListOf(
    User(
        id="AAA000",
        name = "Sanketh Chandra Shetty",
        phoneNo = "7259172859",
        emailAddress = "shettysankethshetty1@gmail.com",
        password = "Sanketh$1",
        role = "child",
        supporter = mutableListOf("DGC568", "ZKR423"),
        requested = mutableListOf()
    ),

    User(
        id="ABC123",
        name = "Sampritha C Shetty",
        phoneNo = "7022339065",
        emailAddress = "samprithashetty1@gmail.com",
        password = "Sampritha$1",
        role = "child",
        supporter = mutableListOf("DGC568"),
        requested = mutableListOf()
    ),

    User(
        id="DGC568",
        name = "Varija C Shetty",
        phoneNo = "7022339065",
        emailAddress = "sankethshetty001@gmail.com",
        password = "varija",
        role = "parent",
        supporter = mutableListOf("AAA000","ABC123"),
        requested = mutableListOf()
    ),

    User(
        id="ZKR423",
        name = "Chandra Shetty",
        phoneNo = "9844723590",
        emailAddress = "sanketh.chandra.shetty@gmail.com",
        password = "chandra",
        role = "parent",
        supporter = mutableListOf("AAA000"),
        requested = mutableListOf()
    ),

    User(
        id="YAN204",
        name = "Sudeep",
        phoneNo = "7892049845",
        emailAddress = "sudeepaski2004@gmail.com",
        password = "sudeep",
        role = "child",
        supporter = mutableListOf(),
        requested = mutableListOf("ZKR423")
)
)
