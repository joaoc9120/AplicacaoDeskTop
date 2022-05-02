
package ClassesEnum;

public enum StatusRegistro {    
    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private String statusRegistro;

    private StatusRegistro(String statusRegistro) {
        this.statusRegistro = statusRegistro;
    }
    @Override
    public String toString() {
        return statusRegistro;
    }            
}
