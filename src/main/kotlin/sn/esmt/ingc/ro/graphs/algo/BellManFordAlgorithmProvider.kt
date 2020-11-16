package sn.esmt.ingc.ro.graphs.algo

import sn.esmt.ingc.ro.graphs.core.*

data class BellManFordAlgorithmProvider(var graph: Graph) {


    private var vertices: Vertices = graph.vertices
    private var edges: Edges = graph.edges

    private lateinit var distance: Distance
    private lateinit var predecessors: Predecessors
    private lateinit var unVisitedVertices: Vertices
    private lateinit var visitedVertices: Vertices


    /*
  * Computes the shortest distance to all vertices
  *  If there is a negative weight cycle, then shortest distances are not calculated, negative weight cycle is reported.
  * @param: src : the given source node
  * @return: distance to all vertices
  * */
    fun getShortestPath(src: Vertex) {
        initializeGraph(src)

        update()

        hasNegativeCycle()

        doGetShortestPath(src)

    }

    private fun initializeGraph(src: Vertex) {
        distance[src] = 0.0
        vertices.forEach { vtx ->
            unVisitedVertices.add(vtx)
            distance.putIfAbsent(vtx, Double.MAX_VALUE)
        }
    }

    private fun update(){}


    private fun hasNegativeCycle(): Boolean {
        return true
    }

    private fun doGetShortestPath(target: Vertex){}

}