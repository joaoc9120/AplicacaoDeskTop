package formularios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MegaSena {

    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>();

        for (int i = 1; i <= 60; i++) {
            numeros.add(i);
        }

        for (int i = 1; i <= 100; i++) {
            for (long j = 1; j <= 900000; j++) {
                Collections.shuffle(numeros);
            }
        }  
            
            
            
            List<Integer> escolhidos = numeros.subList(0, 6);

            Collections.sort(escolhidos);

            System.out.println(escolhidos);        
    }
}
