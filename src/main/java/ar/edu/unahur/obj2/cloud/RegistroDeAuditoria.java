package ar.edu.unahur.obj2.cloud;

import java.util.ArrayList;
import java.util.List;

public class RegistroDeAuditoria implements SistemaInteresado {
    private List<String> registros = new ArrayList<>();

    @Override
    public void actualizar(ClusterComputo cluster, Double vCPUsMovidas) {
        registros.add("Movimiento de " + vCPUsMovidas + " vCPUs en clúster " + cluster.getIdentificador());
    }

    public List<String> getRegistros() {
        return registros;
    }
}