import java.util.*

private val firstArray = ArrayList<String>()
fun main(args: Array<String>) {
    val i = Scanner(System.`in`)

    val ch = ChangesParser()

    for(a in firstArray){
        println(a)
    }

    ch.selectGroup("ОСАТПиП 18-11-2")
    println(ch.parseChanges())

    firstArray.add("first")


}