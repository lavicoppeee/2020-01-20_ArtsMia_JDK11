package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	ArtsmiaDAO dao;
	private Graph<Integer,DefaultWeightedEdge> graph;
    List<Integer> vertici;
    List<Arco> aConnessi;
    
    
    List<Integer> bestCammino;
    
	public Model() {
		dao= new ArtsmiaDAO();
	}
	
	public List<String> getRuoli(){
		return dao.getRuoli();
	}
	
	public void creaGrafo(String ruolo) {
		
		this.graph=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		vertici=dao.getVertici(ruolo);
		Graphs.addAllVertices(graph, vertici);
		
		aConnessi=this.dao.getArchi(ruolo);
		
		for(Arco a: aConnessi) {
			if(this.graph.containsVertex(a.getA1()) && this.graph.containsVertex(a.getA2())) {
				Graphs.addEdgeWithVertices(graph, a.getA1(), a.getA2(), a.getPeso());
			
				
			}
		}
		
	}
	
	
	//NUMERO VERTICI:

	public int nVertici() {
			return this.graph.vertexSet().size();
		}

	//NUMERO ARCHI:

		public int nArchi() {
			return this.graph.edgeSet().size();
		}
		
		public List<Arco> getAconnessi(){
			Collections.sort(aConnessi);
			 return aConnessi;
		}
		
		
		public List<Integer> trovaPercorso(Integer partenza){
			
			bestCammino=new LinkedList<Integer>();
			List<Integer> parziale=new ArrayList<Integer>();
			
			parziale.add(partenza);
			ricorsione(parziale,-1);
			
			return bestCammino;
		}
		
		private void ricorsione(List<Integer> parziale, int peso) {
			
			
			Integer last=parziale.get(parziale.size() -1 );
			
			//ottengo i vicini
			List<Integer> vicini= Graphs.neighborListOf(this.graph, last);
			
			//li scorro tutti finche non finiscono tutti i vicini 
			for(Integer vicino: vicini) {
				 if(!parziale.contains(vicino) && peso==-1) {
					 parziale.add(vicino);
					 //devo aggiornare il peso della ricorsione con il peso dell'arco
					 ricorsione(parziale, (int) this.graph.getEdgeWeight(this.graph.getEdge(last, vicino)));
				     ricorsione(parziale,-1); //backtracking
				     
				 }else {
					 if(!parziale.contains(vicino) && this.graph.getEdgeWeight(this.graph.getEdge(last,vicino))==peso) {
						 parziale.add(vicino);
						 ricorsione(parziale,peso);
						 parziale.remove(vicino);
					 }
				 }
			}
			
			if(parziale.size()>bestCammino.size()) {
				this.bestCammino=new ArrayList<>(parziale);
			}
			
			
		}
		
		public boolean esisteId(Integer id) {
			if(this.graph.containsVertex(id))
				return true;
			return false;
		}

		
		
		
}

