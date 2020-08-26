package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Arco;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	this.txtResult.clear();
    	String ruolo=this.boxRuolo.getValue();
    	
    	if(ruolo==null) {
    		txtResult.clear();
        	txtResult.appendText("Seleziona un ruolo e crea il grafo!\n");
        	return ;
    	}
    	List<Arco> archi=this.model.getAconnessi();
    	for(Arco a: archi) {
    		txtResult.appendText(a.toString()+"\n");
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
     String idS=this.txtArtista.getText();
    	
        Integer id;
    	
    	try {
    		id=Integer.parseInt(idS);
    	}catch(NumberFormatException e) {
    		
    		txtResult.setText("Devi inserire solo numeri");
    		return ;
    	}
    	
    	if(!this.model.esisteId(id)) {
    		txtResult.appendText("L'ARTISTA NON E' NEL GRAFO!\n");
    		return ;
    	}
    	
    	List<Integer> percorso = this.model.trovaPercorso(id);
    	
    	txtResult.appendText("PERCORSO PIU' LUNGO: " + percorso.size() + " \n");
    	for(Integer v : percorso) {
    		txtResult.appendText(v + " ");
    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	
    	String ruolo=this.boxRuolo.getValue();
    	if(ruolo==null) {
    		txtResult.clear();
        	txtResult.appendText("Seleziona un ruolo!\n");
        	return ;
    	}
    	
    	this.model.creaGrafo(ruolo);
    	
    	txtResult.appendText("Grafo Creato!\n");
      	txtResult.appendText("# Vertici: " + model.nVertici()+ "\n");
      	txtResult.appendText("# Archi: " + model.nArchi() + "\n");
      	
      	
    }

    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxRuolo.getItems().addAll(this.model.getRuoli());
	}
}

