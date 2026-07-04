package ar.edu.unahur.obj2.cloud;

public class AsignacionOperacion implements OperacionInfraestructura {
    private ClusterComputo cluster;
    private Double vCPUs;

    public AsignacionOperacion(ClusterComputo cluster, Double vCPUs) {
        if (vCPUs <= 0) {
            throw new ValorInvalidoException("Las vCPUs deben ser mayores a 0");
        }
        this.cluster = cluster;
        this.vCPUs = vCPUs;
    }

    @Override
    public void ejecutar() throws LimiteOverprovisioningException {
        cluster.asignar(vCPUs);
    }

    @Override
    public void deshacer() {
        cluster.liberar(vCPUs);
    }
}