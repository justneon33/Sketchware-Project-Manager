package io.sketchware.util

import io.sketchware.model.ExportableItem
import java.io.File

class Exportable {
}

fun MutableList<ExportableItem>.add(internalPath: String, file: File) =
    add(ExportableItem(internalPath, file))