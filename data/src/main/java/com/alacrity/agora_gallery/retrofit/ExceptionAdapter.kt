package com.alacrity.agora_gallery.retrofit

import com.alacrity.agora_gallery.exceptions.AgoraGalleryException
import com.squareup.moshi.*

class ExceptionAdapter : JsonAdapter<AgoraGalleryException>() {

    @FromJson
    override fun fromJson(reader: JsonReader): AgoraGalleryException? {
        return try {
            val dateAsString = reader.nextString()
            AgoraGalleryException(dateAsString)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: AgoraGalleryException?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }
}