package com.frankz.a04_spotify.models

class CategoryCardItem {
    var title: String = ""
    var imageResId: Int = -10
    var backgroundColor: String = "#ffffff"

    constructor(title: String, backgroundColor: String, imageResId: Int) {
        this.title = title
        this.backgroundColor = backgroundColor
        this.imageResId = imageResId
    }
}