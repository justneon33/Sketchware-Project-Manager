package io.sketchware.models.customs

import io.sketchware.utils.serializers.PathToFileSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class SWCustomSettings(
    @SerialName("blockDir")
    @Serializable(with = PathToFileSerializer::class)
    val blockFile: File,
    @SerialName("palletteDir")
    @Serializable(with = PathToFileSerializer::class)
    val paletteFile: File,
    @SerialName("built-in-blocks")
    val builtInBlocks: Boolean,
    @SerialName("always-show-blocks")
    val alwaysShowBlocks: Boolean
)
