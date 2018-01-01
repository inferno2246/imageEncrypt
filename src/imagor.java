
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;




public class imagor {
    public static void main(String[] args) {
        int i=0,j=0,r=0,g=0,b=0;

        BufferedImage EncrImg = null;
        try{

           EncrImg = ImageIO.read(new File("apple.bmp"));
        }catch(IOException e){
            System.out.println(e);
        }

        for(i=0; i<EncrImg.getHeight();i++){
            for(j=0; j<EncrImg.getWidth(); j++){
                r=(Math.abs(i+j));
                g=(Math.abs(10+i));
                b=(Math.abs(10+j));
                if(r > 255 || r < 0){
                    r = 255;

                }
                if(g > 255 || g < 0){
                    g = 255;

                }
                if(b>255 || b < 0){
                    b = 255;

                }
                System.out.println("r: "+r+" g: "+g+"b: "+b);
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
