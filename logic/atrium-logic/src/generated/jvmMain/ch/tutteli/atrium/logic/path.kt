// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.nio.charset.Charset
import java.nio.file.LinkOption
import java.nio.file.Path
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultPathAssertions

fun <T : Path> AssertionContainer<T>.startsWith(expected: Path): Assertion = impl.startsWith(this, expected)
fun <T : Path> AssertionContainer<T>.startsNotWith(expected: Path): Assertion = impl.startsNotWith(this, expected)
fun <T : Path> AssertionContainer<T>.endsWith(expected: Path): Assertion = impl.endsWith(this, expected)
fun <T : Path> AssertionContainer<T>.endsNotWith(expected: Path): Assertion = impl.endsNotWith(this, expected)

fun <T : Path> AssertionContainer<T>.exists(linkOption: LinkOption? = null): Assertion = impl.exists(this, linkOption)
fun <T : Path> AssertionContainer<T>.existsNot(linkOption: LinkOption? = null): Assertion = impl.existsNot(this, linkOption)

fun <T : Path> AssertionContainer<T>.isReadable(): Assertion = impl.isReadable(this)
fun <T : Path> AssertionContainer<T>.isNotReadable(): Assertion = impl.isNotReadable(this)
fun <T : Path> AssertionContainer<T>.isWritable(): Assertion = impl.isWritable(this)
fun <T : Path> AssertionContainer<T>.isNotWritable(): Assertion = impl.isNotWritable(this)
fun <T : Path> AssertionContainer<T>.isExecutable(): Assertion = impl.isExecutable(this)
fun <T : Path> AssertionContainer<T>.isNotExecutable(): Assertion = impl.isNotExecutable(this)
fun <T : Path> AssertionContainer<T>.isRegularFile(): Assertion = impl.isRegularFile(this)
fun <T : Path> AssertionContainer<T>.isDirectory(): Assertion = impl.isDirectory(this)
fun <T : Path> AssertionContainer<T>.isSymbolicLink(): Assertion = impl.isSymbolicLink(this)
fun <T : Path> AssertionContainer<T>.isAbsolute(): Assertion = impl.isAbsolute(this)
fun <T : Path> AssertionContainer<T>.isRelative(): Assertion = impl.isRelative(this)

fun <T : Path> AssertionContainer<T>.hasDirectoryEntry(entries: List<String>): Assertion = impl.hasDirectoryEntry(this, entries)
fun <T : Path> AssertionContainer<T>.isEmptyDirectory(): Assertion = impl.isEmptyDirectory(this)

fun <T : Path> AssertionContainer<T>.hasSameTextualContentAs(targetPath: Path, sourceCharset: Charset, targetCharset: Charset): Assertion =
    impl.hasSameTextualContentAs(this, targetPath, sourceCharset, targetCharset)

fun <T : Path> AssertionContainer<T>.hasSameBinaryContentAs(targetPath: Path): Assertion = impl.hasSameBinaryContentAs(this, targetPath)

fun <T : Path> AssertionContainer<T>.fileName(): FeatureExtractorBuilder.ExecutionStep<T, String> = impl.fileName(this)
fun <T : Path> AssertionContainer<T>.extension(): FeatureExtractorBuilder.ExecutionStep<T, String> = impl.extension(this)
fun <T : Path> AssertionContainer<T>.fileNameWithoutExtension(): FeatureExtractorBuilder.ExecutionStep<T, String> = impl.fileNameWithoutExtension(this)
fun <T : Path> AssertionContainer<T>.parent(): FeatureExtractorBuilder.ExecutionStep<T, Path> = impl.parent(this)
fun <T : Path> AssertionContainer<T>.resolve(other: String): FeatureExtractorBuilder.ExecutionStep<T, Path> = impl.resolve(this, other)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: PathAssertions
    get() = getImpl(PathAssertions::class) { DefaultPathAssertions() }
