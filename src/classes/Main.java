package classes;

import javax.swing.SwingUtilities;

public class Main {

    static WelcomeFrame welcomeFrame;

    public static void main(String[] args) {
        welcomeFrame = new WelcomeFrame();
        
    }

    static void startProgram(int sleep){
        welcomeFrame.dispose();
        SwingUtilities.invokeLater(() -> new MainFrame(sleep));
    }

}
