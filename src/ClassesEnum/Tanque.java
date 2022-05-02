
package ClassesEnum;

public enum Tanque {
    NA_RESERVA("NA RESERVA"),
    UM_QUARTO("UM QUARTO"),
    MEIO_TANQUE("MEIO_TANQUE"),
    TRES_QUARTOS("TRÊS QUARTOS"),
    CHEIO("CHEIO");
    
    private String status;

    Tanque(String status) {
        this.status = status;
    }
    
    public String getStatus(){
    
        return this.status;
    }       
    
}
