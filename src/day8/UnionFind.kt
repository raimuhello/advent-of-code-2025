package day8

// Lifted from my C# dungeon gen algorithm
class UnionFind(size: Int) {

    private val parent = IntArray(size)

    init {
        for (i in 0 until size) {
            parent[i] = i
        }
    }

    fun find(x: Int): Int {
        if (parent[x] != x) {
           parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(a: Int, b: Int): Boolean {
        val rootA: Int = find(a)
        val rootB: Int = find(b)
        if (rootA == rootB)
        {
            return false
        }
        parent[rootB] = rootA
        return true
    }
}
