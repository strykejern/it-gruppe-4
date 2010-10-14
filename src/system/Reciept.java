/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.StringTokenizer;



/**
 * Creating printable reciept
 * @author Lars
 */
public class Reciept {
    static double mva = 0.25;
    static int delivery;
    static int deliveryPrice = 50;
    static int maxtot = 500;
    public int nr;
    public int customer;
    public int[] dishes;
    public int[] amount;
    public int[] price;
    public int total;
    public String orders;

    public Reciept(){
        nr=0;
        customer=0;
        
        total=0;
        orders="";
        

    }

    public Reciept(int ordernr){
        Reciept reciept=new Reciept();
        StringTokenizer st;
        int numTokens;
        int token;
        
        //gets orderinformation from order-DB
        try {
            reciept= OrderDB.buildReciept(ordernr);
        } catch (SQLException ex) {
            Logger.getLogger(Reciept.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
        st = new StringTokenizer(orders);
        numTokens=st.countTokens();
        dishes = new int[numTokens];
        
        /**
         * places dishes in array with corresponding price and amount arrays
         */
        for(int i =0; i<dishes.length;i++){
            while(st.hasMoreTokens()){
            token = Integer.parseInt(st.nextToken());
            if(dishes[i]==token){
                amount[i]+=1;
            }
            else if(dishes[i]==0){
                dishes[i]=token;
                amount[i]+=1;
                try {
                    price[i] = OrderDB.getDish(token).price;
               } catch (SQLException ex) {
                    Logger.getLogger(Reciept.class.getName()).log(Level.
                            SEVERE, null, ex);
                }
            }
            else if(!st.hasMoreTokens()){
                i=dishes.length;
            }
            }
        }
        for(int i =0; i<dishes.length;i++){
            total+=price[i]*amount[i];
            if(dishes[i]==0){
                i=dishes.length;
            }
        }
        if(total<maxtot && delivery==1){
            total+=deliveryPrice;
        }

    }

    @Override
    public String toString(){
        String print="\t" + this.customer +"\n"+"Dish"+"\t amount \t price \t "
                + "total \n";
        for(int i=0;i<dishes.length;i++){
            if(dishes[i]!=0){
            print+=dishes[i] + "\t\t" + amount[i] + "\t" + price[i] + "\t" +
                    price[i]*amount[i] + "\n";
            }
            else{i=dishes.length;}
        }
        if(delivery==1){
            print+="Delivery: \t\t\t\t" + deliveryPrice;
        }
        print+="\t mva: " + (total/(1+mva)) + "\n\t total: " + total;;

                return print;
         }
    }



//