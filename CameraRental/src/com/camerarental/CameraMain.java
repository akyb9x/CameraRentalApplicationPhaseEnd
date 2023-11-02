package com.camerarental;


import java.util.Scanner;

public class CameraMain {

    static Scanner sc = new Scanner ( System.in );



    public static void main ( String[] args ) {

    	System.out.println("--------------------------------");
        System.out.println (" WELCOME TO CAMERA RENTAL APP  " );
        System.out.println("--------------------------------");
        System.out.println ( "PLEASE LOGIN TO CONTINUE - " );
        System.out.println ( "USERNAME - " );
        String userName = sc.next ( );
        System.out.println ( "PASSWORD - " );
        String password = sc.next ( );

        Admin adminObj = new Admin ( );
        if ( adminObj.accountVerify ( userName , password ) ) {
            CameraMenuList camMenuObj = new CameraMenuList ( );
            /*Call the predefined array list*/
            camMenuObj.intialCameraValue();

            camMenuObj.menuList ( );
        } else {
            System.out.println ( "INVALID CREDENTIALS." );
        }
    }
}
