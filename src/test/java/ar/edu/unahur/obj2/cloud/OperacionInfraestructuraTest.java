package ar.edu.unahur.obj2.cloud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class OperacionInfraestructuraTest {

    @Test

    public void crearAsignacionConVCPUsInvalidasLanzaExcepcion() {

        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);

        assertThrows(ValorInvalidoException.class, () -> {

        new AsignacionOperacion(cluster, -5.0);

        });

    }

    @Test

    public void ejecutarAsignacionReduceCapacidadDelCluster() throws LimiteOverprovisioningException {

        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);

        AsignacionOperacion op = new AsignacionOperacion(cluster, 30.0);

        op.ejecutar();

        assertEquals(70.0, cluster.getVCPUsDisponibles());

    }

    @Test

    public void deshacerAsignacionRestauraCapacidadDelCluster() throws LimiteOverprovisioningException {

        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);

        AsignacionOperacion op = new AsignacionOperacion(cluster, 30.0);

        op.ejecutar();

        op.deshacer();

        assertEquals(100.0, cluster.getVCPUsDisponibles());

    }

}
