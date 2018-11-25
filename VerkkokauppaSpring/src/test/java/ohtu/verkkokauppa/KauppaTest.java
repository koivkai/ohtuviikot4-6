package ohtu.verkkokauppa;

import org.junit.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);
    
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
    }
    
    @Test
    public void kahdellaEriOstoksellaKorissaOstaminenKutsuuTilisiirtoaOikeallaArvoilla() {
        Pankki pankki = mock(Pankki.class);
    
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(100);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "pepsi", 7));
        
        Kauppa k = new Kauppa(varasto, pankki, viite); 
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2); 
        
        k.tilimaksu("keijo", "67890");
        
        verify(pankki).tilisiirto("keijo", 42, "67890", "33333-44455", 12);
    }
    
    @Test
    public void kahdellaSamallaOstoksellaKorissaOstaminenKutsuuTilisiirtoaOikeallaArvoilla() {
        Pankki pankki = mock(Pankki.class);
    
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10).thenReturn(9);
        
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        
        Kauppa k = new Kauppa(varasto, pankki, viite); 
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(1); 
        
        k.tilimaksu("meijo", "09876");
        
        verify(pankki).tilisiirto("meijo", 42, "09876", "33333-44455", 10);
    }
    
    @Test
    public void kunYritetaanOStaaLoppunutTuoteJaTuoteJotaOnVainOikeastaTuotteestaVeloitetaan() {
        Pankki pankki = mock(Pankki.class);
    
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);
        
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "pepsi", 7));
        
        Kauppa k = new Kauppa(varasto, pankki, viite); 
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2); 
        
        k.tilimaksu("kekka", "54321");
        
        verify(pankki).tilisiirto("kekka", 42, "54321", "33333-44455", 5);
    }
    
}
