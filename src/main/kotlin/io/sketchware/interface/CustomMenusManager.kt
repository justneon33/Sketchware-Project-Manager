package io.sketchware.`interface`

import io.sketchware.`interface`.listener.ActionFinishListener
import io.sketchware.model.custom.SWProMenu
import kotlinx.coroutines.Job
import java.io.File

interface CustomMenusManager : FileExportable, Editor {

    /**
     * @return list of custom menus.
     */
    val menus: List<SWProMenu>

    /**
     * Adds custom menu.
     * @param menu menu to add
     */
    fun addCustomMenu(menu: SWProMenu)

    /**
     * Removes custom menu by id.
     * @param id menu's string id.
     */
    fun removeMenuById(id: String)

    /**
     * Edits custom menu.
     * @param id menu string id.
     * @param builder Lambda with [SWProMenu] in context to edit already exists menu data.
     */
    fun editMenu(id: String, builder: SWProMenu.() -> Unit)

    /**
     * Edits custom menu.
     * @param id menu string id.
     * @param menu new menu data.
     */
    fun editMenu(id: String, menu: SWProMenu)

    /**
     * Imports custom menus from the [file].
     * @param file - file with data about menus.
     * @param conflictProvider - provider for conflict names.
     * If provider isn't specified, prime menu will be removed.
     */
    suspend fun import(file: File, conflictProvider: ((conflictId: String) -> String)? = null)

    /**
     * Imports custom menus from the [file].
     * @param file - file with data about menus.
     * @param conflictProvider - provider for conflict names.
     * If provider isn't specified, prime menu will be removed.
     */
    fun import(
        file: File,
        conflictProvider: ((conflictId: String) -> String)? = null,
        callback: ActionFinishListener
    ): Job

}