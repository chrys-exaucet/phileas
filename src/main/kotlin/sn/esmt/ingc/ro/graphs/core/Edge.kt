package sn.esmt.ingc.ro.graphs.core

data class Edge(val id: String, var isDirected: Boolean?=true, var source: Vertex, var destination: Vertex,  var weight: Double) {

    override fun toString(): String {
        return source.name + " " + destination.name
    }
}

typealias Edges = MutableSet<Edge>