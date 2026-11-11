package f2.client.ktor.http

internal class NotJson(val problem: String) : Exception(problem)

// The plain JSON copy of [value], each property read once: what is checked is what is sent, a getter
// answering twice included. Throws NotJson on a class instance at any depth, a cycle through [ancestors],
// or a number JSON.stringify would write as null.
internal fun jsonCopy(value: dynamic, ancestors: dynamic): dynamic = when (jsTypeOf(value)) {
    "string", "boolean", "undefined" -> value
    "number" -> if (js("Number").isFinite(value) as Boolean) value else throw NotJson("holds $value, no finite number")
    "object" -> objectCopy(value, ancestors)
    else -> throw NotJson("is plain JSON, not a ${jsTypeOf(value)}")
}

private fun objectCopy(value: dynamic, ancestors: dynamic): dynamic {
    if (value == null) return null
    if (ancestors.has(value) as Boolean) throw NotJson("holds a cycle, which JSON cannot write")
    val isList = isArray(value)
    if (!isList && isClassInstance(value)) throw NotJson("is plain JSON: no instance of a class, at any depth")
    ancestors.add(value)
    val copy: dynamic = if (isList) arrayCopy(value, ancestors) else recordCopy(value, ancestors)
    ancestors.delete(value)
    return copy
}

// By index, as JSON.stringify reads an array: an element not enumerable is sent too, a hole as null.
private fun arrayCopy(value: dynamic, ancestors: dynamic): dynamic {
    val copy: dynamic = js("[]")
    for (index in 0 until value.length.unsafeCast<Int>()) copy.push(jsonCopy(value[index], ancestors))
    return copy
}

// No prototype: a `__proto__` key is copied as the key it is, not set as the copy's prototype.
private fun recordCopy(value: dynamic, ancestors: dynamic): dynamic {
    val copy: dynamic = jsObject.create(null)
    jsObject.entries(value).unsafeCast<Array<Array<dynamic>>>().forEach { (key, child) ->
        copy[key] = jsonCopy(child, ancestors)
    }
    return copy
}
