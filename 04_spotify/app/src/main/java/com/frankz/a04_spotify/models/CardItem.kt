package com.frankz.a04_spotify.models

class CardItem {
    var title: String = ""
    var imageResId: Int = -10

    constructor(title: String, imageResId: Int) {
        this.title = title
        this.imageResId = imageResId
    }
}