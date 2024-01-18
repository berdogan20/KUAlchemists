package Screens;


import javax.swing.*;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.StartGameController;
import DesignSystem.GameButton;
import soundEffects.PlaySong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

public class StartGameFrame extends GeneralFrame {

	/*
	 * Screen displayed after start game is clicked.
	 * Lets the users enter names and avatar, 
	 * then starts the game with the given info.
	 * It takes a game instance as a parameter.
	 */
    private JLabel usernameLabel;
    private JLabel avatarLabel;
    
	private JTextField player1UsernameTexfField = new JTextField();
	private JTextField player2UsernameTexfField = new JTextField();
    private JTextField player3UsernameTextField = new JTextField();
    private JTextField player4UsernameTextField = new JTextField();
	
    private JLabel player1Label = new JLabel("Player 1");
    private JLabel player2Label = new JLabel("Player 2");
    private JLabel player3Label = new JLabel("Player 3");
	private JLabel player4Label = new JLabel("Player 4");
    
    ButtonGroup player1avatarButtonGroup = new ButtonGroup();
    ButtonGroup player2avatarButtonGroup = new ButtonGroup();
    ButtonGroup player3avatarButtonGroup = new ButtonGroup();
    ButtonGroup player4avatarButtonGroup = new ButtonGroup();
    
    private JRadioButton player1Avatar1Button = new JRadioButton("avatar1");
    private JRadioButton player1Avatar2Button = new JRadioButton("avatar2");
    private JRadioButton player1Avatar3Button = new JRadioButton("avatar3");
    private JRadioButton player1Avatar4Button = new JRadioButton("avatar4");
    private JRadioButton player2Avatar1Button = new JRadioButton("avatar1");
    private JRadioButton player2Avatar2Button = new JRadioButton("avatar2");
    private JRadioButton player2Avatar3Button = new JRadioButton("avatar3");
    private JRadioButton player2Avatar4Button = new JRadioButton("avatar4");
    private JRadioButton player3Avatar1Button = new JRadioButton("avatar1");
    private JRadioButton player3Avatar2Button = new JRadioButton("avatar2");
    private JRadioButton player3Avatar3Button = new JRadioButton("avatar3");
    private JRadioButton player3Avatar4Button = new JRadioButton("avatar4");
    private JRadioButton player4Avatar1Button = new JRadioButton("avatar1");
    private JRadioButton player4Avatar2Button = new JRadioButton("avatar2");
    private JRadioButton player4Avatar3Button = new JRadioButton("avatar3");
    private JRadioButton player4Avatar4Button = new JRadioButton("avatar4");
    
    
    private JButton startGameButton;
    private JButton howToPlayButton;
    private JButton exitGameButton;
    
    String p1avatar;
    String p2avatar;
    String p3avatar;
    String p4avatar;
    String p1name;
    String p2name;
    String p3name;
    String p4name;
    
    ImageIcon avatar1;
    ImageIcon avatar2;
    ImageIcon avatar3;
    ImageIcon avatar4;

    
    public StartGameFrame(KUAlchemistsGame game) {
    	
    	super(game);
    	setBackground("/BackgroundImages/startGameBackground.png");

    	this.getAvatarImages();
    	this.setPlayer1Components();
    	this.setPlayer2Components();

    	this.setStartGameButton();
    	this.setHowToPlayButton();
    	this.setExitGameButton();
    	
    	if (game.getNumberOfPlayers() == 3) {
        	this.setPlayer3Components();
    	}
    	
    	else if (game.getNumberOfPlayers() == 4) {
        	this.setPlayer3Components();
    		this.setPlayer4Components();
    	}
    	
                        
    }
    
    private ImageIcon makeCircularAvatar(ImageIcon originalIcon) 
    {
        int diameter = Math.min(originalIcon.getIconWidth(), originalIcon.getIconHeight());
        BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        // Create a circle and clip the original image to the circle
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2.setClip(circle);
        g2.drawImage(originalIcon.getImage(), 0, 0, diameter, diameter, null);
        g2.dispose();

        return new ImageIcon(image);
    }

    private void getAvatarImages() 
    {
        ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/Images/avatar1.png"));
        Image originalImage1 = originalIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar1 = makeCircularAvatar(new ImageIcon(originalImage1));

        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/Images/avatar2.png"));
        Image originalImage2 = originalIcon2.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar2 = makeCircularAvatar(new ImageIcon(originalImage2));

        ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/Images/avatar3.png"));
        Image originalImage3 = originalIcon3.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar3 = makeCircularAvatar(new ImageIcon(originalImage3));

        ImageIcon originalIcon4 = new ImageIcon(getClass().getResource("/Images/avatar4.png"));
        Image originalImage4 = originalIcon4.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        avatar4 = makeCircularAvatar(new ImageIcon(originalImage4));
    }


    private void setPlayer1Components()
    {
    	player1Label.setBounds(252, 180, 120, 30);
    	player1Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Label.setForeground(Color.white);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setBounds(182, 240, 200, 30);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setForeground(Color.white);
    	
    	player1UsernameTexfField.setBounds(182, 290, 200, 30);
    	
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setBounds(182, 350, 200, 30);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setForeground(Color.white);
    	
    	player1Avatar1Button.setBounds(182, 421, 89, 89);
    	player1Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar1Button.setForeground(Color.white);
    	player1Avatar1Button.setIcon(avatar1);
    	
    	player1Avatar2Button.setBounds(296, 421, 89, 89);
    	player1Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar2Button.setForeground(Color.white);
    	player1Avatar2Button.setIcon(avatar2);
    	
    	player1Avatar3Button.setBounds(182, 521, 89, 89);
    	player1Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar3Button.setForeground(Color.white);
    	player1Avatar3Button.setIcon(avatar3);
    	
    	player1Avatar4Button.setBounds(296, 521, 89, 89);
    	player1Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player1Avatar4Button.setForeground(Color.white);
    	player1Avatar4Button.setIcon(avatar4);
    	
    	// Add the radio buttons to the ButtonGroup
    	player1avatarButtonGroup.add(player1Avatar1Button);
    	player1avatarButtonGroup.add(player1Avatar2Button);
    	player1avatarButtonGroup.add(player1Avatar3Button);
    	player1avatarButtonGroup.add(player1Avatar4Button);
    	
    	
    	backgroundPanel.add(player1Label);
    	backgroundPanel.add(usernameLabel);
    	backgroundPanel.add(player1UsernameTexfField);
    	backgroundPanel.add(avatarLabel);
    	backgroundPanel.add(player1Avatar1Button);
    	backgroundPanel.add(player1Avatar2Button);
    	backgroundPanel.add(player1Avatar3Button);
    	backgroundPanel.add(player1Avatar4Button);
  
    	
    }
    
    private void setPlayer2Components()
    {
    	player2Label.setBounds(1209, 180, 120, 30);
    	player2Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Label.setForeground(Color.white);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setBounds(1139, 240, 200, 30);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setForeground(Color.white);
    	
    	player2UsernameTexfField.setBounds(1139, 290, 200, 30);
    	
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setBounds(1139, 350, 200, 30);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setForeground(Color.white);
    	
    	player2Avatar1Button.setBounds(1142, 421, 89, 89);
    	player2Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar1Button.setForeground(Color.white);
    	player2Avatar1Button.setIcon(avatar1);
    	
    	player2Avatar2Button.setBounds(1260, 421, 89, 89);
    	player2Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar2Button.setForeground(Color.white);
    	player2Avatar2Button.setIcon(avatar2);
    	
    	player2Avatar3Button.setBounds(1142, 521, 89, 89);
    	player2Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar3Button.setForeground(Color.white);
    	player2Avatar3Button.setIcon(avatar3);
    	
    	player2Avatar4Button.setBounds(1260, 521, 89, 89);
    	player2Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player2Avatar4Button.setForeground(Color.white);
    	player2Avatar4Button.setIcon(avatar4);

    	// Add the radio buttons to the ButtonGroup
    	player2avatarButtonGroup.add(player2Avatar1Button);
    	player2avatarButtonGroup.add(player2Avatar2Button);
    	player2avatarButtonGroup.add(player2Avatar3Button);
    	player2avatarButtonGroup.add(player2Avatar4Button);
    	
    	backgroundPanel.add(player2Label);
    	backgroundPanel.add(usernameLabel);
    	backgroundPanel.add(player2UsernameTexfField);
    	backgroundPanel.add(avatarLabel);
    	backgroundPanel.add(player2Avatar1Button);
    	backgroundPanel.add(player2Avatar2Button);
    	backgroundPanel.add(player2Avatar3Button);
    	backgroundPanel.add(player2Avatar4Button);
    }
    
    private void setPlayer3Components() {
    	
    	int x;
    	
    	if (game.getNumberOfPlayers() == 3)
    	{
    		 x = 730;
    	}
    	else // 4
    	{
    		 x = 580;
    	}
    	
    	player3Label.setBounds(x, 180, 120, 30);
    	player3Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player3Label.setForeground(Color.WHITE);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setBounds(x - 70, 240, 200, 30);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setForeground(Color.WHITE);    	

    	player3UsernameTextField.setBounds(x - 70, 290, 200, 30);
    		
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setForeground(Color.WHITE);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setBounds(x - 70, 350, 200, 30);  	
    	
    	player3Avatar1Button = new JRadioButton("avatar1");
    	player3Avatar1Button.setForeground(Color.WHITE);
    	player3Avatar1Button.setIcon(avatar1);
    	player3Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player3Avatar1Button.setBounds(x - 70, 421, 89, 89);
    	
    	player3Avatar2Button = new JRadioButton("avatar2");
    	player3Avatar2Button.setForeground(Color.WHITE);
    	player3Avatar2Button.setIcon(avatar2);
    	player3Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player3Avatar2Button.setBounds(x + 43, 421, 89, 89);
    	
    	player3Avatar3Button = new JRadioButton("avatar3");
    	player3Avatar3Button.setForeground(Color.WHITE);
    	player3Avatar3Button.setIcon(avatar3);
    	player3Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player3Avatar3Button.setBounds(x - 70, 521, 89, 89);
    	
    	player3Avatar4Button = new JRadioButton("avatar4");
    	player3Avatar4Button.setForeground(Color.WHITE);
    	player3Avatar4Button.setIcon(avatar4);
    	player3Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player3Avatar4Button.setBounds(x + 43, 521, 89, 89);
    	
    	player3avatarButtonGroup.add(player3Avatar1Button);
    	player3avatarButtonGroup.add(player3Avatar2Button);
    	player3avatarButtonGroup.add(player3Avatar3Button);
    	player3avatarButtonGroup.add(player3Avatar4Button);
    	
    	backgroundPanel.add(player3Label);
    	backgroundPanel.add(player3UsernameTextField);
    	backgroundPanel.add(usernameLabel);
    	backgroundPanel.add(avatarLabel);
    	backgroundPanel.add(player3Avatar1Button);
    	backgroundPanel.add(player3Avatar2Button);
    	backgroundPanel.add(player3Avatar3Button);
    	backgroundPanel.add(player3Avatar4Button);



    	
    }
    
    private void setPlayer4Components() {
   
    	player4Label.setForeground(Color.WHITE);
    	player4Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player4Label.setBounds(904, 180, 120, 30);
    	
    	usernameLabel = new JLabel("Enter a username.");
    	usernameLabel.setForeground(Color.WHITE);
    	usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	usernameLabel.setBounds(834, 240, 200, 30);
    	
    	player4UsernameTextField = new JTextField();
    	player4UsernameTextField.setBounds(834, 290, 200, 30);
    	
    	avatarLabel = new JLabel("Select an avatar.");
    	avatarLabel.setForeground(Color.WHITE);
    	avatarLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	avatarLabel.setBounds(834, 350, 200, 30);
    	
    	player4Avatar1Button = new JRadioButton("avatar1");
    	player4Avatar1Button.setForeground(Color.WHITE);
    	player4Avatar1Button.setIcon(avatar1);
    	player4Avatar1Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player4Avatar1Button.setBounds(834, 421, 89, 89);
    	
    	player4Avatar2Button = new JRadioButton("avatar2");
    	player4Avatar2Button.setForeground(Color.WHITE);
    	player4Avatar2Button.setIcon(avatar2);
    	player4Avatar2Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player4Avatar2Button.setBounds(948, 421, 89, 89);
    	
    	player4Avatar3Button = new JRadioButton("avatar3");
    	player4Avatar3Button.setForeground(Color.WHITE);
    	player4Avatar3Button.setIcon(avatar3);
    	player4Avatar3Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player4Avatar3Button.setBounds(834, 521, 89, 89);
    	
    	player4Avatar4Button = new JRadioButton("avatar4");
    	player4Avatar4Button.setForeground(Color.WHITE);
    	player4Avatar4Button.setIcon(avatar4);
    	player4Avatar4Button.setFont(new Font("Tahoma", Font.PLAIN, 20));
    	player4Avatar4Button.setBounds(948, 521, 89, 89);
    	
    	player4avatarButtonGroup.add(player4Avatar1Button);
    	player4avatarButtonGroup.add(player4Avatar2Button);
    	player4avatarButtonGroup.add(player4Avatar3Button);
    	player4avatarButtonGroup.add(player4Avatar4Button);
    	
    	backgroundPanel.add(player4Label);
    	backgroundPanel.add(player4UsernameTextField);
    	backgroundPanel.add(usernameLabel);
    	backgroundPanel.add(avatarLabel);
    	backgroundPanel.add(player4Avatar1Button);
    	backgroundPanel.add(player4Avatar2Button);
    	backgroundPanel.add(player4Avatar3Button);
    	backgroundPanel.add(player4Avatar4Button);
    }
  	
    private void setStartGameButton()
    {
    	startGameButton = new GameButton("Start Game");
    	backgroundPanel.add(startGameButton);
    	startGameButton.setBounds(648, 547, 200, 30);
    	startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	 PlaySong.play("ButtonClick");
                 p1name = player1UsernameTexfField.getText();
                 p2name = player2UsernameTexfField.getText();
                 p1avatar = getSelectedButtonText(player1avatarButtonGroup);
                 p2avatar = getSelectedButtonText(player2avatarButtonGroup);
                 boolean startOk = true;
                 
                 // Check for 2 players:
                 if (game.getNumberOfPlayers() == 2) {
                     if (p1avatar == null || p2avatar == null) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please select avatars!",
                                 "Avatar Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     }
                     
                     else if (p1avatar == p2avatar) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please choose different avatars!",
                                 "Avatar Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     }
                     
                     else if ((p1name.isEmpty()) || p2name.isEmpty()) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please enter usernames!",
                                 "Name Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     } 
                 }
                 // Check for 3 players:
                 else if (game.getNumberOfPlayers() == 3) {
                	 
                	 p3name = player3UsernameTextField.getText();
                	 p3avatar = getSelectedButtonText(player3avatarButtonGroup);
                	 
                     if (p1avatar == null || p2avatar == null || p3avatar == null) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please select avatars!",
                                 "Avatar Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     }
                     
                     else if (p1avatar == p2avatar || p1avatar == p3avatar || p2avatar == p3avatar) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please choose different avatars!",
                                 "Avatar Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     }
                     
                     else if ((p1name.isEmpty()) || p2name.isEmpty() || p3name.isEmpty()) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please enter usernames!",
                                 "Name Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     } 
                 }
                 
                 // Check for 4 players:
                 else if (game.getNumberOfPlayers() == 4) {
                	 
                	 p3name = player3UsernameTextField.getText();
                	 p4name = player4UsernameTextField.getText();

                	 p3avatar = getSelectedButtonText(player3avatarButtonGroup);
                	 p4avatar = getSelectedButtonText(player4avatarButtonGroup);
                	 
                     if (p1avatar == null || p2avatar == null || p3avatar == null) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please select avatars!",
                                 "Avatar Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     }
                     
                     else if (p1avatar == p2avatar || p1avatar == p3avatar || p1avatar == p4avatar || 
                    		  p2avatar == p3avatar || p2avatar == p4avatar || p3avatar == p4avatar) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please choose different avatars!",
                                 "Avatar Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     }
                     
                     else if ((p1name.isEmpty()) || p2name.isEmpty() || p3name.isEmpty() || p4name.isEmpty()) {
                    	 JOptionPane.showMessageDialog(new JFrame(), "Please enter usernames!",
                                 "Name Error", JOptionPane.ERROR_MESSAGE);
                    	 startOk = false;
                     } 
                 }
                 
   
                
                 if (startOk) {
                	 System.out.printf("Avatar path: %s", p1avatar);             	 

                     StartGameController startGameController = new StartGameController(game);
                     if (game.getNumberOfPlayers() == 2) {
                         startGameController.handleStartGame(p1name, p2name, p1avatar, p2avatar);
                     }
                     
                     else if (game.getNumberOfPlayers() == 3) {
                         startGameController.handleStartGame(p1name, p2name, p3name, p1avatar, p2avatar, p3avatar);
                     }
                     
                     else { // Number of players = 4
                         startGameController.handleStartGame(p1name, p2name, p3name, p4name, p1avatar, p2avatar, p3avatar, p4avatar);
                     }
                     startGamePressed();
                 }

            }
        });    	
    }

    
    
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    
    private void setHowToPlayButton()
    {
    	howToPlayButton = new GameButton("How to Play?");
    	backgroundPanel.add(howToPlayButton);
    	howToPlayButton.setBounds(648, 597, 200, 30);
    	howToPlayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				howToPlayButtonPressed();
			}
		});
    }
    
    private void setExitGameButton()
    {
    	exitGameButton = new GameButton("Exit Game");
    	backgroundPanel.add(exitGameButton);
    	exitGameButton.setBounds(648, 647, 200, 30);
    	exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
    }
    
    private void startGamePressed() {
    	new MainGameFrame(this.game);
    	this.dispose();
    }
    
    private void howToPlayButtonPressed()
    {
    	new HowToPlayFrame(game);
    }
}