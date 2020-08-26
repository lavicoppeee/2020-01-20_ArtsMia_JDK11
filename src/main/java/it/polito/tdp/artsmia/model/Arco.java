package it.polito.tdp.artsmia.model;

public class Arco implements Comparable<Arco>{
	
	Integer a1;
	Integer a2;
	Double peso;
	
	
	
	
	public Arco(Integer a1, Integer a2, Double peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}
	public Integer getA1() {
		return a1;
	}
	public void setA1(Integer a1) {
		this.a1 = a1;
	}
	public Integer getA2() {
		return a2;
	}
	public void setA2(Integer a2) {
		this.a2 = a2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return a1+"  "+a2+"  "+peso;
	}
	@Override
	public int compareTo(Arco o) {
		// TODO Auto-generated method stub
		return -(this.peso.compareTo(o.getPeso()));
	}
	
	
	
	

}
