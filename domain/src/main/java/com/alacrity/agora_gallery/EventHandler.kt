package com.alacrity.agora_gallery

interface EventHandler<T> {
    fun obtainEvent(event: T)
}