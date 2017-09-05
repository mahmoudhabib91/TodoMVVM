package tv.niceq8i.app.common.domain

/**
 * Created by mahmoud on 6/1/17.
 */
open class Params(val map: Map<String, *>) {
    constructor(vararg pairs: Pair<String, *>) : this(pairs.toMap())

    inline fun <reified T> get(key: String, defaultValue: T? = null): T {
        if (map.containsKey(key)) {
            return map[key].takeIf { it is T } as T
        } else {
            return defaultValue!!
        }
    }
}

fun emptyParams(): Params = Params(mapOf<String, Any>())