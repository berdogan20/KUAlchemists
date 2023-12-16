package Screens;

import Business.Ingredient;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.ForageController;

public class ForageIngredientFrame extends FunctionalFrame
{
	JButton button;
	
	public ForageIngredientFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		this.setBackground("/FunctionalBackgroundImages/forageIngredientBackground.png");
		this.setButton();
	}
	private void setButton()
	{
		button = new JButton();
		button.setText("Forage");
		button.setBounds(500, 500, 150, 30);
		button.setForeground(Color.white);
		button.setFont(new Font("Tahoma", Font.ITALIC, 15));
		button.setOpaque(false);
		button.setBorder(new LineBorder(Color.white, 2));
		backgroundPanel.add(button);
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    ForageController controller = new ForageController(game);
			    String takenIngredient = controller.handleForage();
			    if (takenIngredient != null)
			    {
			    	String message = "Ingredient taken: " + takenIngredient;
			    	int index = Ingredient.getIngredientIndex(takenIngredient);
			    	Image image = new ImageIcon(this.getClass().getResource("/Images/ingredient" + index + ".png")).getImage();
			    	Image newImage = image.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
			    	ImageIcon icon = new ImageIcon(newImage);
				    JOptionPane.showMessageDialog(null, message, "Ingredient Taken", JOptionPane.INFORMATION_MESSAGE, icon);
				    
			    }
			    else
			    {
			    	String message = "Ingredient Storage is empty!";
			    	JOptionPane.showMessageDialog(null, message, "Ingredient Taken", JOptionPane.WARNING_MESSAGE);
			    }
			    
			    // Close the frame
			    ForageIngredientFrame.this.dispose();
			    new MainGameFrame(game);
			}


	            @Override
	            public void mousePressed(MouseEvent e) {
	                // Handle mouse press event if needed
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                // Handle mouse release event if needed
	            }

	            @Override
	            public void mouseEntered(MouseEvent e) {
	                // Change button appearance on mouse enter (hover effect)
	            	button.setBorder(new LineBorder(Color.yellow, 2));
	            	button.setForeground(Color.yellow);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                // Restore button appearance on mouse exit
	            	button.setBorder(new LineBorder(Color.white, 2));
	            	button.setForeground(Color.white);
	            }
	        });
		
		backgroundPanel.add(button);
	}


}
