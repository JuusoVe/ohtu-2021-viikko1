package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoNollatilavuudenHuonollaSyotteella() {
        varasto = new Varasto(1.0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void konstruktoriLuoNollatilavuudenHuonoillaSyotteilla() {
        varasto = new Varasto(-1.0, 10.0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void konstruktoriAsettaaNegatiivisenAlkusaldonNollaksi() {
        varasto = new Varasto(10.0, -10.0);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void konstruktoriLuoTaydenVaraston() {
        varasto = new Varasto(10.0,10.0);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void konstruktoriAsettaaSaldoksiKorkeintaanTilavuuden() {
        varasto = new Varasto(10.0,15.0);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        double saldoEnnen = varasto.getSaldo();
        varasto.lisaaVarastoon(-8.0);
        assertEquals(saldoEnnen, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliTilvauudenLisaaminenMeneeHukkaan() {
        varasto.lisaaVarastoon(18.0);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(2);
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiVaikutaSaldoon() {
        double alkuSaldo = varasto.getSaldo();
        varasto.otaVarastosta(-1.0);
        assertEquals(alkuSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliSaldonOttaminenAntaaKaiken() {
        double alkuSaldo = varasto.getSaldo();
        double saatuMaara = varasto.otaVarastosta(100.0);
        assertEquals(alkuSaldo, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
}