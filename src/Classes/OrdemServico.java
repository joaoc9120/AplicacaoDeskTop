package Classes;

//import java.util.Date;

import java.util.Date;
/**
 *
 * @author João Carlos Batista
 */
public class OrdemServico{
   
    private int os_Cod;
    private int vei_Cod;
    private Date os_Dat_Orc;
    private Date os_Dat_Ent;
    private Date os_Dat_Sai;
    private String os_Sit;
    private String os_Def;
    private int os_Km_Ent;
    private String os_Tan;

    
    public int getOs_Cod() {
        return os_Cod;
    }

    public void setOs_Cod(int os_Cod) {
        this.os_Cod = os_Cod;
    }

    public int getVei_Cod() {
        return vei_Cod;
    }

    public void setVei_Cod(int vei_Cod) {
        this.vei_Cod = vei_Cod;
    }

    public Date getOs_Dat_Orc() {
        return os_Dat_Orc;
    }

    public void setOs_Dat_Orc(Date os_Dat_Orc) {
        this.os_Dat_Orc = os_Dat_Orc;
    }

    public Date getOs_Dat_Ent() {
        return os_Dat_Ent;
    }

    public void setOs_Dat_Ent(Date os_Dat_Ent) {
        this.os_Dat_Ent = os_Dat_Ent;
    }

    public Date getOs_Dat_Sai() {
        return os_Dat_Sai;
    }

    public void setOs_Dat_Sai(Date os_Dat_Sai) {
        this.os_Dat_Sai = os_Dat_Sai;
    }

    public String getOs_Sit() {
        return os_Sit;
    }

    public void setOs_Sit(String os_Sit) {
        this.os_Sit = os_Sit;
    }
    
    public String getOs_Def() {
        return os_Def;
    }

    public void setOs_Def(String os_Def) {
        this.os_Def = os_Def;
    }
    
    public int getOs_Km_Ent() {
        return os_Km_Ent;
    }

    public void setOs_Km_Ent(int os_Km_Ent) {
        this.os_Km_Ent = os_Km_Ent;
    }

    public String getOs_Tan() {
        return os_Tan;
    }

    public void setOs_Tan(String os_Tan) {
        this.os_Tan = os_Tan;
    }    
                
}
