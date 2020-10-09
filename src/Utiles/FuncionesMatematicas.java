
package Utiles;

public class FuncionesMatematicas {
    public static double Potencia (double numero,double exponente){
        double resultado;
        resultado=1;
        for(int i=0;i<exponente;i++){
            resultado=resultado*numero;
        }
        
        return resultado;
    }
    
    public static double PromedioEntreTres(double valor1,double valor2, double valor3){
        double resultado;
        resultado=(valor1+valor2+valor3)/3;
        return resultado;
    }
    
    public static double Porcentaje(double porcentaje, double numero){
        double resultado;
        resultado=numero*(porcentaje/100);
        return resultado;
    }
    
    public static double Redondear(double numero){
        //Este modulo redondea los decimales
        double resultado;
        int numeroInt;
        numeroInt=(int)numero;
        if(numero>=0){
            numero=numero-numeroInt;
            if(numero>=0.5){
                resultado=(int)numero+numeroInt+1;
            }
            else{
                resultado=numeroInt;
            }
        }
        else{
            numero=numero+numeroInt;
            if(numero<=-0.5){
               resultado=(int)numero-numeroInt-1; 
            }
            else{
                resultado=numero-numeroInt;
            }
        }
        return resultado;
    }
    
}
