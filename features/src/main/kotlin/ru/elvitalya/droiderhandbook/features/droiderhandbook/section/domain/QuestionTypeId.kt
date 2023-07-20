import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class QuestionTypeId(val value: String) : Parcelable

data class QuestionType(val id: QuestionTypeId, val name: String) {
    companion object {
        val Java = QuestionType(id = QuestionTypeId("1"), name = "Java")
        val Kotlin = QuestionType(id = QuestionTypeId("2"), name = "Kotlin")
        val Android = QuestionType(id = QuestionTypeId("3"), name = "Android")
        val Coroutines = QuestionType(id = QuestionTypeId("4"), name = "Coroutines")
    }
}