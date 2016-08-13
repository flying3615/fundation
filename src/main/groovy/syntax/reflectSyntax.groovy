package syntax

/**
 * Created by liuyufei on 8/13/16.
 */
//groovy reflection
println "aaa".dump()
println "aaa".inspect()
//with use "aaa" as the context in the closure
"aaa".with {
    println length()
    println toString()
}