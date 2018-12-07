/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.TextField;

/**
 *
 * @author Kaius
 */
public class Nollaa implements Komento{
    
     private TextField tuloskentta;
    private TextField syotekentta;
    private Sovelluslogiikka sovellus;
    int edellinen;

    public Nollaa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
        this.edellinen = 0;
    }
    
    

    @Override
    public void suorita() {
        edellinen = sovellus.tulos();
        sovellus.nollaa();
        tuloskentta.setText(""+sovellus.tulos());
        syotekentta.setText("");  
    }

    @Override
    public void peru() {
        tuloskentta.setText(""+edellinen);
    }
    
}
