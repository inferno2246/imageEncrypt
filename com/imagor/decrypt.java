package com.imagor;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;




public class decrypt {
    public static void main(String[] args) {
        int i=0,j=0,r=0,g=0,b=0,clr=0,diff=0;
        char store;
        BufferedImage EncrImg = null;
        try{

            EncrImg = ImageIO.read(new File("saved.jpg"));
        }catch(IOException e){
            System.out.println(e);
        }

        boolean r_mat[][]=new boolean[EncrImg.getWidth()][EncrImg.getHeight()];
        boolean g_mat[][]=new boolean[EncrImg.getWidth()][EncrImg.getHeight()];
        boolean b_mat[][]=new boolean[EncrImg.getWidth()][EncrImg.getHeight()];


        try {
            FileInputStream stream = new FileInputStream(new File("rvalues.txt"));
            while(stream.available()>0){
                for(i=0;i<EncrImg.getHeight();i++){
                    for(j=0;j<EncrImg.getWidth();j++) {
                        store = (char) stream.read();
                        if(store=='1'){
                            r_mat[j][i]=true;
                        }else if(store=='0'){
                            r_mat[j][i]=false;
                        }
                    }
                }

            }
        }catch (IOException e){}

        try {
            FileInputStream stream = new FileInputStream(new File("gvalues.txt"));
            while(stream.available()>0){
                for(i=0;i<EncrImg.getHeight();i++){
                    for(j=0;j<EncrImg.getWidth();j++) {
                        store = (char) stream.read();
                        if(store=='1'){
                            g_mat[j][i]=true;
                        }else if(store=='0'){
                            g_mat[j][i]=false;
                        }
                    }
                }

            }
        }catch (IOException e){}

        try {
            FileInputStream stream = new FileInputStream(new File("bvalues.txt"));
            while(stream.available()>0){
                for(i=0;i<EncrImg.getHeight();i++){
                    for(j=0;j<EncrImg.getWidth();j++) {
                        store = (char) stream.read();
                        if(store=='1'){
                            b_mat[j][i]=true;
                        }else if(store=='0'){
                            b_mat[j][i]=false;
                        }
                    }
                }

            }
        }catch (IOException e){}

        for(i=0; i<EncrImg.getHeight();i++){
            for(j=0; j<EncrImg.getWidth(); j++){
                clr = EncrImg.getRGB(j,i);
                if(r_mat[j][i]){
                    r = ((clr & 0x00ff0000) >> 16)+255-180;
                }else{
                    r = ((clr & 0x00ff0000) >> 16)-180;
                }

                if (g_mat[j][i]) {
                    g = ((clr & 0x0000ff00) >> 8)+255-200;
                } else {
                    g = ((clr & 0x0000ff00) >> 8) - 200;
                }

                if (b_mat[j][i]) {
                    b = (clr & 0x000000ff)+255-220;

                } else {
                    b = (clr & 0x000000ff)-220;
                }

                EncrImg.setRGB(j,i,new Color(r,g, b,255).getRGB());
            }
        }




        try {
            File outputfile = new File("decrypted.jpg");
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
