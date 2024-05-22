package com.example.myapiretrofit

import com.google.gson.annotations.SerializedName

data class Response(
	@field:SerializedName("hasil")
	val hasil: String
)
