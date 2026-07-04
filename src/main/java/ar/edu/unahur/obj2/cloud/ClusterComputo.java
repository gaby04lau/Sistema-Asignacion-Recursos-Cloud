package ar.edu.unahur.obj2.cloud;

import java.util.ArrayList;
import java.util.List;

public class ClusterComputo {
    private String identificador;
    private Double vCPUsDisponibles;
    private static final Double limite = -200.0;
    private List<SistemaInteresado> sistemasInteresados = new ArrayList<>();

    public ClusterComputo(String identificador, Double vCPUsIniciales) {
        this.identificador = identificador;
        this.vCPUsDisponibles = vCPUsIniciales;
    }

    public Double getVCPUsDisponibles() {
        return vCPUsDisponibles;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void registrarSistemaInteresado(SistemaInteresado sistema) {
        sistemasInteresados.add(sistema);
    }

    public void eliminarSistemaInteresado(SistemaInteresado sistema) {
        sistemasInteresados.remove(sistema);
    }

    private void avisarATodos(Double vCPUsMovidas) {
        for (SistemaInteresado sistema : sistemasInteresados) {
            sistema.actualizar(this, vCPUsMovidas);
        }
    }

    public void liberar(Double vCPUs) {
        vCPUsDisponibles += vCPUs;
        avisarATodos(vCPUs);
    }

    public void asignar(Double vCPUs) throws LimiteOverprovisioningException {
        if (vCPUsDisponibles - vCPUs < limite) {
            throw new LimiteOverprovisioningException(
                "Se superó el límite de overprovisioning del clúster " + identificador);
        }
        vCPUsDisponibles -= vCPUs;
        avisarATodos(vCPUs);
    }
}