
package man;

import java.util.*;

public class Solution {

 public static void printNextMove(int player, int player1Mancala, int player1Marbles[], int player2Mancala, int player2Marbles[]){
        
        int yours[] = player1Marbles;
        int opp[] = player2Marbles;

        CheckMoves(yours,opp); 
        
        return;
    }
    
    public static void CheckMoves(int yours[], int opp[]){
        
        double scores[] = new double[6];
        int max = 0;
        int StealDistance = 0;
        int MarblesNeeded = 0;
        
        for(int i = 5; i >= 0; i--){
                      
             if(yours[i] > 0){ 
                 
                int answer = yours[i] % 13;
                
                if(ExtraMove(answer,i) == true){
                    System.out.println(i+1);
                    return;
                }
                if(MancalaScore(i,yours) == true){                    
                    scores[i] = scores[i] + 5;
                } 

                scores[i] = scores[i] - (MarblesLost(yours,i)*0.27);

                if(Unload(yours,i) > 13){
                    scores[i] = scores[i] + yours[i]*0.20;
                } else {
                    scores[i] = scores[i] + yours[i]*0.05;
                }
                
            }
            
            if(StealMarbles(yours,opp,i) == true){
               int k = i;
               while(k <= i && k > 0){
                   k--;  
                   if(yours[k] == i-k){
                       scores[k] = scores[i] + 4;
                   }
               }
            }
           
        }  
            
        for (int i = 0; i < scores.length; i++) {            
                max = scores[i] > scores[max] ? i : max; //Determines the index of the max element in the array           
            }


        System.out.println(max+1);
        return;

    }
    
    public static boolean ExtraMove(int ans,int index){
        
            if(ans == (7-(index+1))){
                return true;
            }                       
        return false;
    }
    
    public static boolean MancalaScore(int index, int[] yours){
   
            int distance = 7 - (index+1);           
            
            if(yours[index] >= distance){
                return true;
            } else {
                return false;
            }
    }
    public static boolean StealMarbles(int[] yours, int[] opp, int i){ // Not done yet
 
           if(yours[i] == 0 && opp[i] > 0){
               return true;
           } 
           return false;
    }
    
    public static double MarblesLost(int[] yours, int i){
        
        int distance = 0;
        int lost = 0;
        
        distance = 7 - i;
        lost = yours[i] - distance;
        
        if(lost > 0){
           return lost;
        } else {
           return 0;
        }
    }
    public static double Unload(int[] yours, int i){
        return yours[i];
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int _player;
        _player = in.nextInt();
        
        int _player1Mancala;
        _player1Mancala = in.nextInt();
        
        
        int _player1Marbles_size = 6;
        int[] _player1Marbles = new int[_player1Marbles_size];
        int _player1Marbles_item;
        for(int _player1Marbles_i = 0; _player1Marbles_i < _player1Marbles_size; _player1Marbles_i++) {
            _player1Marbles_item = in.nextInt();
            _player1Marbles[_player1Marbles_i] = _player1Marbles_item;
        }
        
        int _player2Mancala;
        _player2Mancala = in.nextInt();
        
        
        int _player2Marbles_size = 6;
        int[] _player2Marbles = new int[_player2Marbles_size];
        int _player2Marbles_item;
        for(int _player2Marbles_i = 0; _player2Marbles_i < _player2Marbles_size; _player2Marbles_i++) {
            _player2Marbles_item = in.nextInt();
            _player2Marbles[_player2Marbles_i] = _player2Marbles_item;
        }
        
        if(_player == 1) {
            printNextMove(_player, _player1Mancala, _player1Marbles, _player2Mancala, _player2Marbles);
        } else if(_player == 2) {
            printNextMove(_player, _player2Mancala, _player2Marbles,  _player1Mancala,  _player1Marbles);
        }
    }
}
