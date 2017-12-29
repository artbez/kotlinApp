package com.iwings.fly

import kotlinx.serialization.Serializable

@Serializable
data class TwitterPostDto(val text: String, val id: Long) {
    override fun toString(): String {
        return "msg: $text"
    }
}

@Serializable
data class Wall(val author: String, val tweets: List<TwitterPostDto>)