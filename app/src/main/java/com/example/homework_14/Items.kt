package com.example.homework_14

data class Items(
    val content: List<Content>
) {
    data class Content(
        val cover: String,
        val descriptionKA: String,
        val id: String,
        val publish_date: String,
        val titleKA: String,
    )
}