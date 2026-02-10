package com.mayada.playscope.domain.model

import com.google.gson.annotations.SerializedName

data class Screenshot(@SerializedName("image") val url: String)