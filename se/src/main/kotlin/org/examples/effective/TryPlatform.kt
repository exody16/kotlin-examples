package org.examples.effective

import org.examples.PlatformClass


fun main() {
    val notNull = PlatformClass().notNullPlatform
    val nullable = PlatformClass().nullablePlatform

    // NPE getAnyPlatform(...) must not be null
    val anyPlatform: String = PlatformClass().anyPlatform

    // By default String!
    val anyPlatform2 = PlatformClass().anyPlatform
}
