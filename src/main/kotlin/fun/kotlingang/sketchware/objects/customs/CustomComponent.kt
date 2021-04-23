package `fun`.kotlingang.sketchware.objects.customs

import `fun`.kotlingang.sketchware.internal.json.serializers.StringNumberConvertor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomComponent(
    @Serializable(StringNumberConvertor::class)
    var icon: Int,
    @SerialName("class")
    var `class`: String,
    var description: String,
    var defineAdditionalVar: String,
    var typeName: String,
    @Serializable(StringNumberConvertor::class)
    var id: Int,
    var url: String,
    var name: String,
    var additionalVar: String,
    var varName: String,
    var imports: String,
    var buildClass: String
)