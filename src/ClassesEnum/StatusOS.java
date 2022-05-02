
package ClassesEnum;

public enum StatusOS {
    SEF("EM FILA DE ESPERA"),
    SEA("EM ANDAMENTO"),
    OR("ORÇAMENTO"),
    OC("ORÇ. CANCELADO"),
    SF("SERV. FINALIZADO"),
    SC("SERV. CANCELADO");

    private String descricao;
    
    StatusOS(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return descricao;
    }
}
