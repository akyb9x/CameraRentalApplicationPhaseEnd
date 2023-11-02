package com.camerarental;

import java.util.ArrayList;
import java.util.Scanner;

public class CameraMenuList {

    static Scanner sc = new Scanner( System.in );

    static ArrayList< CameraPojo > cameraList = new ArrayList<>( );
    

    static MyWallet wallet = new MyWallet( );
    public static void intialCameraValue(){
    	cameraList.add(new CameraPojo("Canon", "EOS2D", 1000.0));
    	cameraList.add(new CameraPojo("Nikon", "D3500", 1255.0));
    	cameraList.add(new CameraPojo("Kodak", "D2500", 2500.0));
    	cameraList.add(new CameraPojo("Canon", "D6500", 4000.0));
    	cameraList.add(new CameraPojo("Canon", "D3700", 5000.0));
    }
    public static void menuList( ) {
    	
    	System.out.println("---------------------"); 
        System.out.println( "        MENU       " );
        System.out.println("---------------------");
        System.out.println( "1. MY CAMERA" );
        System.out.println( "2. RENT A CAMERA" );
        System.out.println( "3. VIEW ALL CAMERAS" );
        System.out.println( "4. MY WALLET" );
        System.out.println( "5. EXIT" );
        System.out.println("---------------------");
        //System.out.println("Enter Your Choice:-");
        int ch = Integer.parseInt( sc.nextLine( ) );
        switch ( ch ) {
            case 1:
                myCamOpt( );
                break;

            case 2:
                displayCam();
                System.out.println( "ENTER THE CAMERA ID IF YOU WANT TO RENT - " );
                int cId = Integer.parseInt(sc.nextLine( ));
                rentCam( cId, wallet );
                break;

            case 3:
                displayCam();
                menuList();
                break;

            case 4:
                walletCam( wallet );
                break;

            case 5:
            	System.out.println("Thank you !! Come again !!");
                System.exit( 0 );
                break;

            default:
                System.out.println( "WRONG CHOICE!!" );

        }

    }


	private static void myCamOpt( ) {
		System.out.println("---------------------"); 
        System.out.println( "1. ADD" );
        System.out.println( "2. REMOVE" );
        System.out.println( "3. VIEW MY CAMERAS" );
        System.out.println( "4. GO TO PREVIOUS MENU");
        System.out.println("---------------------"); 
        //System.out.println("Enter Choice");
        int choice = Integer.parseInt( sc.nextLine( ) );
        switch ( choice ) {
            case 1:
                addCam( );
                break;

            case 2:
                displayCam();
                System.out.println( "ENTER THE CAMERA ID TO REMOVE - " );
                int cId = Integer.parseInt(sc.nextLine( ));
                removeCam( cId );
                break;

            case 3:
                displayCam();
                myCamOpt( );
                break;

            case 4:
                menuList( );
                break;

            case 5:
                System.exit( 0 );
                break;

            default:
                System.out.println( "WRONG CHOICE!!" );
        }
    }

   /* Add camera in list*/
    private static void addCam( ) {
         try{
             System.out.println( "ENTER THE CAMERA BRAND - " );
             String brand = sc.nextLine( );
             System.out.println( "ENTER THE CAMERA MODEL - " );
             String model = sc.nextLine( );
             System.out.println( "ENTER THE PER DAY PRICE (INR) - " );
             double price = Integer.parseInt( sc.nextLine( ) );
             if ( cameraList.add( new CameraPojo( brand, model, price ) ) ) {
                 System.out.println( "YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST." );
                 myCamOpt( );
             }
         }
         catch (Exception e){
             System.out.println("Enter valid price");
             myCamOpt( );
         }

    }
    /* Add camera in list*/
    private static void displayCam() {
        if ( cameraList.isEmpty( ) ) {
            System.out.println( "NO DATA PRESENT AT THIS MOMENT." );
            return;
        }

        System.out.println( "LIST OF AVAILABLE CAMERA(S) - " );
        System.out.println("---------------------------------------------------------------------------------");
        System.out.print( "  CAMERA ID   "+ "   BRAND   " + "   MODEL   " + "  PRICE(PER DAY)   " + "    STATUS    \n");
        System.out.println("----------------------------------------------------------------------------------");
        for ( CameraPojo camera : cameraList ) {
            if ( camera.getStatus( ) ) {
                System.out.println(""+"    "+    camera.getCamId( )  +"        "+"   " +"   "+ camera.getBrand( ) + "     " + camera.getModel( ) + "    "+"    "+ camera.getPricePerDay( ) +"      "+ "   " + "AVAILABLE"+"   "+"" );
                continue;
            }
            System.out.println(""+"    "+ camera.getCamId( ) +"        "+"   " +"   "+ camera.getBrand( ) +"     " + camera.getModel( )  + "    "+"    "+ camera.getPricePerDay( )+"      "+ "   " +"RENTED"+"    "+"" );
        }
        return;
    }
    /* Remove camera list*/
    private static void removeCam( int cId ) {

        ArrayList< CameraPojo > tempList = new ArrayList<>( );
        for ( CameraPojo c : cameraList ) {
            if ( c.getCamId( ) == cId ) {
                continue;
            }
            tempList.add( c );
        }
        cameraList = tempList;
        System.out.println( "CAMERA SUCCESSFULLY REMOVED FROM THE LIST." );
        myCamOpt( );
    }
    /* Rent Camera list*/
    private static void rentCam( int cId, MyWallet wallet ) {

        for ( CameraPojo c : cameraList ) {
            if ( c.getCamId( ) == cId && c.getPricePerDay( ) <= wallet.getRupees( ) ) {
                c.setStatus( false );

                wallet.setRupees(wallet.getRupees()- c.getPricePerDay());
                System.out.println( "YOUR TRANSACTION FOR CAMERA " + " " + c.getBrand( ) + " " + c.getModel( )+ " " +" with rent INR."
                        + c.getPricePerDay( ) + " " + " HAS SUCCESSFULLY COMPLETED." );
                menuList( );
                return;
            }
        }
        System.out.println( "ERROR : TRANSACTION FAILED DUE TO INSUFFICIENT BALANCE. PLEASE DEPOSIT THE AMOUNT TO YOUR WALLET." );
        menuList( );
    }
    /* Wallet camera list*/
    private static void walletCam( MyWallet wallet ) {
        try {
            System.out.println( "YOUR CURRENT WALLET BALANCE IS - " + wallet.getRupees( ) );

            System.out.println( "DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET?(1.YES 2.NO) - " );
            int depo = Integer.parseInt( sc.nextLine( ) );
            if ( depo == 1 ) {
                System.out.println( "ENTER THE AMOUNT - " );
                int newAmount = Integer.parseInt( sc.nextLine( ) );
                wallet.setRupees( wallet.getRupees( )+newAmount );
                System.out.println( "YOUR WALLET BALANCE UPDATED SUCCESSFULLY. CURRENT WALLET BALANCE - INR." + wallet.getRupees( ) );
            }
            menuList( );
        }
        catch (Exception e){
            System.out.println("Insert Numeric Value");
            menuList( );
        }
        }

}
