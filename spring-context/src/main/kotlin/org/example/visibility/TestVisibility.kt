package org.example.visibility

//@Component
class TestVisibility {
    private val privateInFile: PrivateInFile = PrivateInFile()


    fun publicFun() {
        println("Public fun!")
        privateInFile.privateFun()
    }

}

//@Component
private class PrivateInFile {

    fun privateFun() {
        println("Private fun!")
    }


}
