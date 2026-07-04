package ar.edu.unahur.obj2.cloud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ObserverTest {

    @Test
    public void ElRegistroAuditoriaRegistraElMovimientoAlAsignar() {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        RegistroDeAuditoria cloudTrail = new RegistroDeAuditoria();
        cluster.registrarSistemaInteresado(cloudTrail);

        cluster.liberar(20.0);

        assertEquals(1, cloudTrail.getRegistros().size());
    }

    @Test
    public void alarmaNoSeDisparaSiElClusterNoEstaEnOverprovisioning() {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        AlarmaSaturacionCritica alarma = new AlarmaSaturacionCritica();
        cluster.registrarSistemaInteresado(alarma);

        cluster.liberar(20.0);

        assertFalse(alarma.estaLaAlarmaActiva());

    }

    @Test
    public void alarmaSeDisparaSiElClusterQuedaEnOverprovisioning() throws LimiteOverprovisioningException {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        AlarmaSaturacionCritica alarma = new AlarmaSaturacionCritica();
        cluster.registrarSistemaInteresado(alarma);

        cluster.asignar(150.0);

        assertTrue(alarma.estaLaAlarmaActiva());
    }

    @Test
    public void notificadorSREImprimeAlRecibirUnMovimiento() {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        NotificadorSRE notificador = new NotificadorSRE();
        cluster.registrarSistemaInteresado(notificador);

        cluster.liberar(50.0);
    }

    @Test
    public void unSistemaEliminadoNoRecibeMasAvisos() {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        RegistroDeAuditoria registro = new RegistroDeAuditoria();
        cluster.registrarSistemaInteresado(registro);
        cluster.eliminarSistemaInteresado(registro);

        cluster.liberar(20.0);

        assertEquals(0, registro.getRegistros().size());
    }
}