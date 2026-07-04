package ar.edu.unahur.obj2.cloud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PlanDespliegueTest {

    @Test
    public void unPlanEjecutaTodasSusOperacionesEnOrden() throws LimiteOverprovisioningException {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        PlanDespliegue plan = new PlanDespliegue();
        plan.agregar(new AsignacionOperacion(cluster, 30.0));
        plan.agregar(new LiberacionOperacion(cluster, 10.0));

        plan.ejecutar();

        assertEquals(80.0, cluster.getVCPUsDisponibles());
    }

    @Test
    public void siUnaOperacionFallaSeRevierteSoloLoYaEjecutadoDeEsePlan() {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        PlanDespliegue plan = new PlanDespliegue();
        plan.agregar(new AsignacionOperacion(cluster, 50.0));
        plan.agregar(new AsignacionOperacion(cluster, 400.0));

        assertThrows(LimiteOverprovisioningException.class, () -> {
            plan.ejecutar();
        });

        assertEquals(100.0, cluster.getVCPUsDisponibles());
    }

    @Test
    public void deshacerUnPlanCompletoRevierteTodasSusOperaciones() throws LimiteOverprovisioningException {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        PlanDespliegue plan = new PlanDespliegue();
        plan.agregar(new AsignacionOperacion(cluster, 30.0));
        plan.agregar(new LiberacionOperacion(cluster, 10.0));

        plan.ejecutar();
        plan.deshacer();

        assertEquals(100.0, cluster.getVCPUsDisponibles());
    }

    @Test
    public void elPlanificadorEjecutaUnaOperacionInmediatamente() throws LimiteOverprovisioningException {
        ClusterComputo cluster = new ClusterComputo("US-EAST-1", 100.0);
        PlanificadorDespliegues planificador = new PlanificadorDespliegues();
        AsignacionOperacion op = new AsignacionOperacion(cluster, 30.0);

        planificador.ejecutar(op);

        assertEquals(70.0, cluster.getVCPUsDisponibles());
    }
}
