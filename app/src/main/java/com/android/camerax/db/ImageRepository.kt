package com.android.camerax.db

class ImageRepository(private val imageDao: ImageDao, private val tag: String) {
    val albums = imageDao.getAlbums()
    val images = imageDao.getImages(tag)

    suspend fun insert(image: Image): Long {
        return imageDao.saveImages(image)
    }
}