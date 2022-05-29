package com.example.hw8aberishvili.api

import com.google.gson.annotations.SerializedName

data class User(

    val id: Int?,
    val name: String?,
    val year: Long?,
    val color: String?,

    @SerializedName("pantone_value")
    val pantoneValue: String?

)

data class Support(
    val url: String?,
    val text: String?
)


data class ResourceModel(
    val data: User,
    val support: Support
)

//    "support": {
//    "url": "https://reqres.in/#support-heading",
//    "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//}


data class UserList(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)