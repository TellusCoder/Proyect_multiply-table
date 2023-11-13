/**
 * @(#)NumerosMultiplicar.java
 *
 *
 * @author:
 *         Victor Cerrud
 *         Carlos Fontal 
 * @version 1.00 2020/10/17
 */

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
// creacion de la clase
public class NumerosMultiplicar{
    // atributos
    private int n1,n2,result;
    private int cie=0;
    private int fal=0;
    //metodos
    public void asignarNumeros(int num1, int num2) {
        n1=num1;
        n2=num2;
    }    
    public int calcularMult(){
        result= n1*n2;
        return result;
    }
    public boolean evaluarRes(int iresult){
        if(calcularMult()==iresult )
        {    
            return true;
        }
        else
        {
            return false;
        }
    }
    public void asigCierto(){
        cie=cie+1;
    }
    public void asigFalso(){
        fal=fal+1;
    }
    public float evalCieFal(){
        return(100*cie)/(cie+fal);
    }
    public String mensaje(int message){
        String data=null;
        switch(message){
            case 1: data="Muy Bien!";
                    break;
            case 2: data="Felicidades!";
                    break;
            case 3: data= "Buen Trabajo!";
                    break;
            case 4: data="Continua asi!";
                    break;
            case 5: data="Vas muy bien";
                    break;    
        }
        return data;
    }
}
//Progrma Principal
class Numdemo{
    public static void main (String[]Args){
    	//Declarcion de variable
        String cad;
        int iresult=0,respu;
        float correct;
        boolean eval;
        Byte cont;
        // creación de los iconos
        ImageIcon pres=new ImageIcon("images/pag_pres.png");
        ImageIcon happy= new ImageIcon("images/happy.png");
        ImageIcon sad= new ImageIcon("images/sad.png");
        ImageIcon quest= new ImageIcon("images/quest.png");
        //creamos nuestro objeto
        NumerosMultiplicar objNum = new NumerosMultiplicar();
        do {
            //desde aqui inicia
            cont=1;
            //creamos dos numeros aleatorios y los asignamos al objeto
            int num1=(int)(1+(Math.random()*9));
            int num2=(int)(1+(Math.random()*9));
            objNum.asignarNumeros(num1,num2);
            //
            respu =JOptionPane.CANCEL_OPTION;
            //aqui esta la pagina de presentacion del programa.
            respu = JOptionPane.showConfirmDialog (null,"Hola! vamos a multiplicar \n Cuanto es "+num1+" x "+ num2 +"?\n","Programa De Matematicas Educativo \n",respu,JOptionPane.INFORMATION_MESSAGE,pres);
            // si el usuario pone aceptar se cuenta
            //JOptionPane.CLOSED_OPTION representa el  boton X = -1
            //preguntar
            if (respu !=2 & respu != JOptionPane.CLOSED_OPTION) {
                //evaluamos la primera entrada de los jovenes.
                do{
                    try{
                        eval=true;
                        cad= (String)JOptionPane.showInputDialog(null,"Cual es el resultado de multiplicar: "+num1+" x "+ num2 +" ?\n"+" Ingrese el Resultado: ","Programa De Matematicas Educativo", JOptionPane.QUESTION_MESSAGE,quest,null,null);
                        iresult= Integer.parseInt(cad);
                        if (iresult < 0) {
                            eval =false;
                            JOptionPane.showMessageDialog(null,"Error recuerde que debe ingresar un numeros positivos. Error tipo: ","E r r o t",JOptionPane.ERROR_MESSAGE);  
                        }
                    }
                    catch(NumberFormatException e){
                         eval=false;
                         JOptionPane.showMessageDialog(null,"Error recuerde que debe ingresar un numero. Error tipo: "+e,"E r r o t",JOptionPane.ERROR_MESSAGE);
                     }
                }while(eval==false);
                //aqui se evalua si la respuesta fue correcta
                eval=false;
                //aqui vamos a evaluar lo que ingreso el usuario
                do{
                    //el contador nos ayuda a tener una idea de cual intento es
                    cont++;
                    //de ser correcto
                    if(objNum.evaluarRes(iresult)){
                        eval=true;
                        int message=(int)(1+(Math.random()*5));
                        JOptionPane.showMessageDialog(null,"Es correcto el resultado de "+num1+" x "+ num2 + " es: " +iresult+" \n "+objNum.mensaje(message),"Programa De Matematicas Educativo",JOptionPane.OK_CANCEL_OPTION,happy);
                        objNum.asigCierto();
                    }
                    //de ser incorrecto pero puede ser el 2do o 3er intento
                    else 
                        if((cont<=3)&&(eval==false)){
                            do{
                                try{
                                    eval=true;
                                    JOptionPane.showMessageDialog(null,"Mi respuesta "+num1+" x "+ num2 + " ?   es: "+iresult+ "\n Incorrecto intentalo de nuevo ","Programa De Matematicas Educativo",JOptionPane.OK_CANCEL_OPTION,sad);
                                    cad= (String)JOptionPane.showInputDialog(null,"Cual es el resultado de multiplicar: "+num1+" x "+ num2 +" ?\n"+" Ingrese el Resultado: ","Programa De Matematicas Educativo", JOptionPane.QUESTION_MESSAGE,quest,null,null);
                                    iresult= Integer.parseInt(cad);
                                    if (iresult < 0) {
                                        eval =false;
                                        cont--;
                                        JOptionPane.showMessageDialog(null,"Error recuerde que debe ingresar un numeros positivos. Error tipo: ","E r r o t",JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                 catch(NumberFormatException e){
                                 eval=false;
                                 cont--;
                                 JOptionPane.showMessageDialog(null,"Error recuerde que debe ingresar un numero. Error tipo: "+e,"E r r o t",JOptionPane.ERROR_MESSAGE);
                                 }
                            }while(eval==false );
                            eval=false;
                        }
                        //en caso de que intentara los tres intentos y la respuesta sigue siendo incorrecta
                        if((cont==3)&&(!objNum.evaluarRes(iresult))){
                            eval=true;
                            JOptionPane.showMessageDialog(null,"Incorrecto, el resultado de la multiplicacion entre: "+num1+" x "+ num2 +" es: "+objNum.calcularMult(),"Programa De Matematicas Educativo",JOptionPane.INFORMATION_MESSAGE,sad);
                            objNum.asigFalso(); 
                        }
                }while(eval==false);
            }
        }while (respu != 2 & respu != JOptionPane.CLOSED_OPTION);
        correct = objNum.evalCieFal();
        if ( correct < 75) {
            JOptionPane.showMessageDialog(null,"Por favor pídale ayuda a el instructor","Programa De Matematicas Educativo",JOptionPane.INFORMATION_MESSAGE,sad);  
        }
    }
}