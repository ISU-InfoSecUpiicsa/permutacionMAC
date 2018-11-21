import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PermutacionMAC{

    public static void main(String[] args) {
        //Pide la MAC hasta que sea validada
        boolean response=false;
        do{
            //Se pide la MAC
            String mac;
            System.out.print("Ingresar MAC: ");
        //se guarda en la variable la cadena ingresada
        mac=new Scanner(System.in).next();
        //Convertir a mayúsculas
        mac=mac.toUpperCase();
        //validar que sean 12 carácteres
        response = validar(mac);
        //se permuta si es aceptado
            if( response == true){
            //modifica la cadena mac con ',', para utilizarlo en un String y permutar
                char[] sintaxis= mac.toCharArray();
                char[] sintaxismac= new char[17];
                short j=0;
                for(short i=0; i<17; i++){
                    if( ((i+1)%3)==0 ){
                        sintaxismac[i]=',';
                    }
                    else{
                        sintaxismac[i]=sintaxis[j];
                        j++;
                    }
                }
                //Termina de stablecer en sintaxis mac la cadena con comas
                //Aqui convierte char[] a una cadena
                String cadena = new StringBuilder().append(sintaxismac).toString();
                //
                String[] elementos= cadena.split(",");
                int n = 6;                  //Tipos para escoger
                int r = elementos.length;   //Elementos elegidos
                Perm2(elementos, "", n, r);
                n=5;
                Perm2(elementos, "", n, r);
                n=4;
                Perm2(elementos, "", n, r);
            }
            else
            {
                //Se manda mesaje de error en caso de que la MAC sea incorrecta
                System.out.println("Error, ingresa de nuevo la MAC.");
            }
        } while (response == false);
        //Fin del programa    
    }

    public static boolean validar(String mac){
        boolean validacion=false;
        char[] arrayChar= mac.toCharArray();
        if(arrayChar.length == 12 ){ 
            validacion=true;
        }
        return validacion;
    }

    public static void Perm2(String[] elem, String act, int n, int r) {
        if (n == 0) {
            /*imprimir*/ diccionario(act);
            /*imprimir*/ diccionario(act.toLowerCase());
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    //Perm2(elem, act + elem[i] + ", ", n - 1, r);-
                    Perm2(elem, act + elem[i],n - 1, r);
                }
            }
        }
    }

    public static void diccionario(String act){
        FileWriter flwriter = null;
        try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
            flwriter = new FileWriter("DiccionarioMAC.txt", true);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            //para escribir se usa bfwriter.write
            bfwriter.write(act);
            //se hace un salto de linea
            bfwriter.write("\r\n");
            //se cierra el documento
            bfwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
