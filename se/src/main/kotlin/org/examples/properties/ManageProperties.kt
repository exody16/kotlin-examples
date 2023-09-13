package org.examples.properties

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class ManageProperties {


    val dataProperties = Properties()
    private val propertyFile = File("se/src/main/resources/se.properties")


    init {
        FileInputStream(propertyFile).use { dataProperties.load(it) }
    }

    fun writeToPropertyFile(propertyName: String, propertyValue: String) {
        dataProperties.setProperty(propertyName, propertyValue)

        val out: OutputStream = FileOutputStream(propertyFile)
        dataProperties.store(out, "")
        out.close()
    }

}

fun main() {

    val manageProperties = ManageProperties()
    val value: String = manageProperties.dataProperties.getProperty("mykey")
    println(value)

    val nope: String? = manageProperties.dataProperties.getProperty("nope")
    println(nope)

    manageProperties.writeToPropertyFile("newk", "newV")

}

