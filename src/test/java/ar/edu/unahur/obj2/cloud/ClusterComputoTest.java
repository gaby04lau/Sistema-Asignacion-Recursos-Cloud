package ar.edu.unahur.obj2.cloud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ClusterComputoTest {

    @Test
    public void unClusterNuevoTieneLaCapacidadInicial() {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        assertEquals(100.0, cluster.getVCPUsDisponibles());
    }

    @Test
    public void alAsignarSeReducenLasVCPUsDisponibles() throws LimiteOverprovisioningException {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        cluster.asignar(30.0);
        assertEquals(70.0, cluster.getVCPUsDisponibles());
    }

    @Test
    public void alLiberarSeIncrementanLasVCPUsDisponibles() throws LimiteOverprovisioningException {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        cluster.liberar(20.0);
        assertEquals(120.0, cluster.getVCPUsDisponibles());
    }

    @Test
    public void asignarMasAlladelLimiteLanzaExcepcion() throws LimiteOverprovisioningException {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        assertThrows(LimiteOverprovisioningException.class, () -> {
            cluster.asignar(400.0); 
        });
    }
}

    