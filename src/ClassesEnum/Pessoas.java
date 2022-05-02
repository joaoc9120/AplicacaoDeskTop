
package ClassesEnum;

public enum Pessoas {
    SELECIONE("SELECIONE"),
    FISICA("FÍSICA"),
    JURIDICA("JURÍDICA");

    private String pessoas;

    private Pessoas(String pessoas) {
        this.pessoas = pessoas;
    }
    @Override
    public String toString() {
        return pessoas;
    }        
}
