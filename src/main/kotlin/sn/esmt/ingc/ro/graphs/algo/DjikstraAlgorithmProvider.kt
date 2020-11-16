package sn.esmt.ingc.ro.graphs.algo

import sn.esmt.ingc.ro.graphs.core.*
import java.util.function.Consumer


class DjikstraAlgorithmProvider(graph: Graph): GraphAlgorithmProvider() {


    private var vertices: Vertices = graph.vertices
    private var edges: Edges = graph.edges


    private lateinit var distance: Distance
    private lateinit var predecessors: Predecessors
    private lateinit var unVisitedVertices: Vertices
    private lateinit var visitedVertices: Vertices


    override fun getShortestPath(src: Vertex, tgt: Vertex): Path {
        initializeGraph(src)
        while (unVisitedVertices.count() > 0) {
            val closestVtx = getClosestVertex(unVisitedVertices)
            visitedVertices.add(closestVtx!!)
            unVisitedVertices.remove(closestVtx)
            updateDistancesFrom(closestVtx)
        }
        return doGetShortestPath(target = tgt)
    }

    private fun initializeGraph(src: Vertex) {
        distance[src] = 0.0
        vertices.forEach { vtx ->
            unVisitedVertices.add(vtx)
            distance.putIfAbsent(vtx, Double.MAX_VALUE)
        }
    }

    private fun getClosestVertex(vertices: Vertices): Vertex? {
        var closestVtx: Vertex? = null
        return vertices.forEach(Consumer { vertex ->
            (
                    if (distanceOf(vertex) < distanceOf(closestVtx!!))
                        closestVtx = vertex)
        }).let { closestVtx }

    }

    private fun distanceOf(destination: Vertex): Double {
        return distance.getOrDefault(destination, Double.MAX_VALUE)
    }

    private fun updateDistancesFrom(thisVertex: Vertex) {
        val adjacentVertices: Vertices = getNeighborsOf(thisVertex)
        adjacentVertices.forEach { successor ->
            if (distanceOf(successor) > (distanceOf(thisVertex) + distanceBetween(thisVertex, successor))) {
                distance[successor] = (distanceOf(thisVertex) + distanceBetween(thisVertex, successor))
                predecessors[successor] = thisVertex

                // TODO: check utility
                //unVisitedVertices.add(successor)
            }
        }
    }

    private fun getNeighborsOf(vertex: Vertex): Vertices {
        val neighbors: Vertices? = null
        return edges.forEach { _ ->
            Consumer<Edge> {
                (
                        if (it.source == vertex && !isVisited(it.destination))
                            neighbors!!.add(it.destination)
                        )
            }
        }.let { neighbors!! }

    }

    private fun isVisited(vertex: Vertex): Boolean {
        return visitedVertices.contains(vertex)
    }

    private fun distanceBetween(start: Vertex, target: Vertex): Double {
        return edges.find { edge -> (edge.source == start && edge.destination == target) }.let { edge -> edge!!.weight }
    }

    private fun doGetShortestPath(target: Vertex): Path {
        val path = Path()
        while (predecessors[target] != null)
            path.addFirst(predecessors[target]!!)
        // TODO: return distance
        return path
    }

}
