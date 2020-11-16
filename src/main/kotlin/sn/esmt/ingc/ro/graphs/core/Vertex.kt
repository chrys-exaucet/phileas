package sn.esmt.ingc.ro.graphs.core

import java.util.ArrayDeque

data class Vertex(val id: String, var name: String, var isActive: Boolean? = true) {
    override fun toString(): String {
        return this.name
    }
}

typealias Vertices = MutableSet<Vertex>
typealias Path = ArrayDeque<Vertex>
typealias Predecessors = MutableMap<Vertex, Vertex>
typealias Distance = MutableMap<Vertex, Double>


/* ---* ----*  ----*
*
*
*
* */