package io.sketchware.manager.collections.managers

import io.sketchware.interfaces.BaseCollectionManager
import io.sketchware.interfaces.Editor
import io.sketchware.interfaces.listeners.ActionFinishListener
import io.sketchware.utils.SketchwareEncryptor.decrypt
import io.sketchware.utils.SketchwareEncryptor.encrypt
import io.sketchware.utils.delegates.lazyInit
import io.sketchware.utils.internal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.KSerializer
import java.io.File

open class CollectionManager<Item>(
    private var value: String,
    private val file: File,
    private val serializer: KSerializer<Item>
) : BaseCollectionManager<Item>, Editor, CoroutineScope {

    private val allProperty = lazyInit {
        TagFormatter.parseAsArray(value, serializer)
    }

    /**
     * @return list of all collection items.
     */
    override val all: List<Item> by allProperty

    /**
     * Adds item to collection
     * @param entity - Entity to add.
     */
    override fun addItem(entity: Item) = saveItems(
        all.toMutableList().apply { add(entity) }
    )

    /**
     * Removes item which matches [entity].
     */
    override fun removeItem(entity: Item) = saveItems(
        all.toMutableList().apply { remove(entity) }
    )

    /**
     * Saves [items] locally to [value].
     */
    private fun saveItems(items: List<Item>) {
        value = items.joinToString("\n") { it.deserialize(serializer) }
        allProperty.reset()
    }

    /**
     * Updates data in Editor async.
     */
    override fun fetch(callback: ActionFinishListener?) = launch {
        fetch()
        callback?.onFinish()
    }

    /**
     * Updates data in Editor.
     */
    override suspend fun fetch() {
        value = file.read().decrypt().byteArrayToString()
        allProperty.reset()
    }

    /**
     * Saves data which was edited async.
     */
    override fun save(callback: ActionFinishListener?) = launch {
        save()
        callback?.onFinish()
    }

    /**
     * Saves data which was edited.
     */
    override suspend fun save() {
        file.write(value.toByteArray().encrypt())
    }

    override val coroutineContext = Dispatchers.Main

}