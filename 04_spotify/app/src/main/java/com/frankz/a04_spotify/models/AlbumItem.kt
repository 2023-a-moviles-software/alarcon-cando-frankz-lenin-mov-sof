package com.frankz.a04_spotify.models

class AlbumItem {
    var title: String = ""
    var imageResId: Int = -10
    var subtitle: String = ""

    constructor(title: String, subtitle: String, imageResId: Int) {
        this.title = title
        this.subtitle = subtitle
        this.imageResId = imageResId
    }
}