
package ClassesEnum;

public enum ServicoProduto {
    
    PRODUTO("PRODUTO"),
    SERVICO("SERVICO");

    private String ServicoProduto;

    private ServicoProduto(String ServicoProduto) {
        this.ServicoProduto = ServicoProduto;
    }
    @Override
    public String toString() {
        return ServicoProduto;
    }     
    
}
