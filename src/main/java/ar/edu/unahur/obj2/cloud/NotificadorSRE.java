package ar.edu.unahur.obj2.cloud;

public class NotificadorSRE implements SistemaInteresado {

    @Override
    public void actualizar(ClusterComputo cluster, Double vCPUsMovidas) {
        System.out.println("Se han movido " + vCPUsMovidas + " vCPUs en el clúster " + cluster.getIdentificador());
    }
}