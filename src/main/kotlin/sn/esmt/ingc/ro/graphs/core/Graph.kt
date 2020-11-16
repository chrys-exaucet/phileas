package sn.esmt.ingc.ro.graphs.core

import sn.esmt.ingc.ro.graphs.algo.DjikstraAlgorithmProvider

class Graph(var vertices: Vertices, var edges: Edges) {

    private var djikstraAlgorithmProvider = DjikstraAlgorithmProvider(this)

    fun vertex(vtx: Vertex): Graph {
        this.vertices.add(vtx)
        return this
    }


    fun getDjikstraShortestPath(src: Vertex, tgt: Vertex): Path = djikstraAlgorithmProvider.getShortestPath(src, tgt)

}