
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;




public class decrypt {
    public static void main(String[] args) {
        int i=0,j=0,r=0,g=0,b=0,clr=0,diff=0;

        BufferedImage EncrImg = null;
        try{

            EncrImg = ImageIO.read(new File("saved.png"));
        }catch(IOException e){
            System.out.println(e);
        }

        for(i=0; i<EncrImg.getHeight();i++){
            for(j=0; j<EncrImg.getWidth(); j++){
                clr = EncrImg.getRGB(j,i);
                r = ((clr & 0x00ff0000) >> 16)-180;
                if(r > 255){
                    diff = r-255;
                    r = diff;
                }else if(r < 0){
                    r = 255+r;
                }
                g = ((clr & 0x0000ff00) >> 8)-200;
                if(g > 255){
                    diff = g-255;
                    g = diff;
                }else if(g < 0){
                    g = 255+g;
                }
                b = (clr & 0x000000ff)-220;
                if(b > 255){
                    diff = b-255;
                    b = diff;
                }else if(b < 0){
                    b = 255+b;
                }

                EncrImg.setRGB(j,i,new Color(r,g, b,255).getRGB());
            }
        }




/*        try {
            File outputfile = new File("saved.png");
            ImageIO.write(EncrImg, "png", outputfile);
        }catch (IOException e){
            System.out.println(e);
        }
*/
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
