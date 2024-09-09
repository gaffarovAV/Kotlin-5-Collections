package ru.otus.homework.mapswap

/**
 * Меняет местами ключи и значения
 */
fun <K, V> Map<K, V>.swap(): Map<V, K>
    {
        val tempMap: MutableMap<V, K> = mutableMapOf<V, K>()

        for ((key, value) in entries) {
            if(!containsKey(key))
                tempMap.put(value,key)
            else
                tempMap.set(value,key)  // is it needed?
        }
        return tempMap.toMap()
    }
