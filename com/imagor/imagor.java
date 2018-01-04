package com.imagor;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

class imagor {

    public static void main(String[] args) {
        int i=0,j=0,r=0,g=0,b=0,clr=0,diff=0;

        BufferedImage EncrImg = null;
        try{

           EncrImg = ImageIO.read(new File("storm.jpg"));
        }catch(IOException e){
            System.out.println(e);
        }

        boolean r_ind[][] = new boolean[EncrImg.getWidth()][EncrImg.getHeight()];
        boolean g_ind[][] = new boolean[EncrImg.getWidth()][EncrImg.getHeight()];
        boolean b_ind[][] = new boolean[EncrImg.getWidth()][EncrImg.getHeight()];
        for(i=0; i<EncrImg.getHeight();i++){
            for(j=0; j<EncrImg.getWidth(); j++){
                clr = EncrImg.getRGB(j,i);
                r = ((clr & 0x00ff0000) >> 16)+180;
                if(r > 255){
                    //System.out.println("Exceed r: "+r);
                    diff = r-255;
                    r = diff;
                    r_ind[j][i]=true;
                    //System.out.println("Changed r: "+r);
                }
                g = ((clr & 0x0000ff00) >> 8)+200;
                if(g > 255){
                    //System.out.println("Exceed g: "+g);
                    diff = g-255;
                    g = diff;
                    g_ind[j][i]=true;
                    //System.out.println("Changed g: "+g);
                }
                b = (clr & 0x000000ff)+220;
                if(b > 255){
                    //System.out.println("Exceed b: "+b);
                    diff = b-255;
                    b = diff;
                    b_ind[j][i]=true;
                   // System.out.println("Changed b: "+b);
                }

                EncrImg.setRGB(j,i,new Color(r,g, b,255).getRGB());
            }
        }

/*        for(i=0; i<EncrImg.getHeight();i++) {
            for (j = 0; j < EncrImg.getWidth(); j++) {
                if (g_ind[j][i])
                    System.out.println("j: " + j + " i: " + i);
            }
        }
*/
        try{
            File file = new File("rvalues.txt");
            PrintWriter writer = new PrintWriter(file);
            for(i=0; i<EncrImg.getHeight();i++) {
                for (j = 0; j < EncrImg.getWidth(); j++){
                    if(r_ind[j][i]){
                        writer.print(1);
                    }else{
                        writer.print(0);
                    }
                }
            }

        writer.close();
        }catch (IOException e){}

        try{
            File file = new File("gvalues.txt");
            PrintWriter writer = new PrintWriter(file);
            for(i=0; i<EncrImg.getHeight();i++) {
                for (j = 0; j < EncrImg.getWidth(); j++){
                    if(g_ind[j][i]){
                        writer.print(1);
                    }else{
                        writer.print(0);
                    }
                }
            }

            writer.close();
        }catch (IOException e){}

        try{
            File file = new File("bvalues.txt");
            PrintWriter writer = new PrintWriter(file);
            for(i=0; i<EncrImg.getHeight();i++) {
                for (j = 0; j < EncrImg.getWidth(); j++){
                    if(b_ind[j][i]){
                        writer.print(1);
                    }else{
                        writer.print(0);
                    }
                }
            }

            writer.close();
        }catch (IOException e){}

        try {
            File outputfile = new File("saved.jpg");
            ImageIO.write(EncrImg, "jpg", outputfile);
        }catch (IOException e){
            System.out.println(e);
        }

        ImageIcon image = new ImageIcon(EncrImg);
        JLabel label1 = new JLabel(image);
        JFrame window = new JFrame("SuperWindow");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500,500);
        window.getContentPane().add(label1,BorderLayout.CENTER);
        window.pack();
        window.setVisible(true);

    }


}
