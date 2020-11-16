package sn.esmt.ingc.ro.graphs.algo

import sn.esmt.ingc.ro.graphs.core.Path
import sn.esmt.ingc.ro.graphs.core.Vertex

abstract class GraphAlgorithmProvider {

    abstract fun getShortestPath(src: Vertex, tgt: Vertex): Path


}