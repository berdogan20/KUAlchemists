import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeFrame().setVisible(true);
                System.out.println("Hello12312");
                System.out.println("Soon");
            }
        });
    }

}
