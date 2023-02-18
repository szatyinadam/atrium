@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import java.nio.file.Path

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Path].
 */
enum class DescriptionPathAssertion(override val value: String) : StringBasedTranslatable {
    ABSOLUTE_PATH("ein absoluter Pfad"),
    DOES_NOT_HAVE_PARENT("!! hat keinen Elternpfad"),
    ENDS_NOT_WITH("endet nicht mit"),
    ENDS_WITH("endet mit"),
    EXIST("existieren"),
    PARENT("Elternpfad"),
    EXTENSION("Dateiendung"),
    FILE_NAME("Dateiname"),
    FILE_NAME_WITHOUT_EXTENSION("Dateiname ohne Endung"),
    STARTS_WITH("beginnt mit"),
    STARTS_NOT_WITH("beginnt nicht mit"),
    READABLE("lesbar"),
    WRITABLE("schreibbar"),
    EXECUTABLE("ausführbar"),
    A_FILE("eine Datei"),
    A_DIRECTORY("ein Verzeichnis"),
    AN_EMPTY_DIRECTORY("ein leeres Verzeichnis"),
    DIRECTORY_CONTAINS("Verzeichnis enthält"),
    A_SYMBOLIC_LINK("eine symbolische Verknüpfung"),
    A_UNKNOWN_FILE_TYPE("ein unbekannter Dateityp"),
    FAILURE_DUE_TO_NO_SUCH_FILE("es exisitiert kein Dateisystemeintrag an diesem Ort"),
    FAILURE_DUE_TO_PERMISSION_FILE_TYPE_HINT("%s exisitiert an diesem Ort, ist aber nicht %s"),
    HINT_OWNER("der Besitzer ist %s"),
    HINT_OWNER_AND_GROUP("der Besitzer ist %s, die Gruppe ist %s"),
    HINT_ACTUAL_POSIX_PERMISSIONS("die POSIX-Berechtigungen lauten %s"),
    HINT_ACTUAL_ACL_PERMISSIONS("die Access Control List lautet:"),
    FAILURE_DUE_TO_PARENT("Problem am Elternpfad"),
    FAILURE_DUE_TO_ACCESS_DENIED("der Zugriff wurde verweigert"),
    FAILURE_DUE_TO_ACCESS_EXCEPTION("beim Zugriff wurde eine %s geworfen:"),
    FAILURE_DUE_TO_WRONG_FILE_TYPE("war %s anstatt %s"),
    FAILURE_DUE_TO_LINK_LOOP("Zykel von symbolischen Verknüpfungen gefunden: %s"),
    HINT_CLOSEST_EXISTING_PARENT_DIRECTORY("das nächste, existierende Elternverzeichnis ist %s"),
    HINT_FOLLOWED_SYMBOLIC_LINK("folgte der symbolischen Verknüpfung %s nach %s"),
    HAS_SAME_TEXTUAL_CONTENT("hat denselben textlichen Inhalt mit Kodierung %s, %s"),
    HAS_SAME_BINARY_CONTENT("hat denselben binären Inhalt"),
    RELATIVE_PATH("ein relativer Pfad")
}
