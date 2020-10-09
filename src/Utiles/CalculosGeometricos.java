
package Utiles;

public class CalculosGeometricos {
    public static double areaRectangulo (double altura, double base){
            double areaRec;
            areaRec=base*altura;
            return areaRec;
    }
    public static double perimetroRectangulo(double base,double altura){
        double periRec;
        periRec=base*2+altura*2;
        return periRec;
    }
    public static double areaTriangulo(double lado1, double lado2){
    double areaTri;
    areaTri=(lado1*lado2)/2;
    return areaTri;
} 
    public static double perimetroTriangulo(double lado1,double lado2, double lado3){
        double periTri;
        periTri=lado1+lado2+lado3;
        return periTri;
    }
    public static double areaCirculo(double radio){
        double areaCir;
        areaCir=3.1415*radio*radio;
        return areaCir;
    }
    public static double perimetroCirculo(double radio){
        double periCir;
        periCir=2*3.1415*radio;
        return periCir;
    }
    public static double teoremaPitagoras(double cateto1,double cateto2){
        double hipotenusa;
        hipotenusa=Math.hypot(cateto1,cateto2);
        return hipotenusa;
    }


}