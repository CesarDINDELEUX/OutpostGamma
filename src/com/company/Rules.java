package com.company;

public class Rules {

    public static void attack(Personnage attacker, Personnage defender){

        int atk = attacker.ptAttaque - defender.ptDefense;

        int des = 5; // A MODIFIER

        switch(atk) {
            case 0:
                if (des == 4 || des == 5 || des == 6)
                {
                    defender.isAlive = false;
                }
                break;
            case 1:
                 if (des == 3 || des == 4 || des == 5 || des == 6){
                     defender.isAlive = false;
                 }
                break;
            case 2:
                if ( des != 1 && des != 0){
                    defender.isAlive = false;
                }
            case 3:
                if (des != 0){
                    defender.isAlive = false;
                }
            default:
               defender.isAlive = false;
        }





    }


    public static void deplacement (Personnage perso, int x, int y){
        perso.pos.y = y;
        perso.pos.x = x;
    }

    public static boolean isAttackable(Personnage attacker, Personnage defender)
    {
        // A REVOIR
        if(attacker.porteeAttaque <= attacker.pos.x -  defender.pos.x) {
            return true;
        }
        else {
            return false;
        }
    }









}
