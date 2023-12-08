package Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import Business.Ingredient;
import Business.KUAlchemistsGame;
import Business.Player;
import Controllers.TransmuteController;

public class TransmuteIngredientFrame extends FunctionalFrame{
	
	public TransmuteIngredientFrame(KUAlchemistsGame game, Player player) {
		super(game, player);
		this.setBackground("transmuteIngredientBackground");
		this.setTransmuteLabel();
		this.setIngredients();
	}
	
	private void setTransmuteLabel()
	{
		JLabel forageLabel = new JLabel("Choose the ingredient you would like to transmute");
        forageLabel.setForeground(Color.WHITE); // Set text color to white
        forageLabel.setBounds(270, 260, 550, 30); // Adjust the position as needed
        forageLabel.setOpaque(false); // Make the label transparent
        forageLabel.setFont(new Font("Tahoma",Font.BOLD | Font.ITALIC, 20));
        backgroundPanel.add(forageLabel);
	}
	
	private void setIngredients() {
	    TransmuteController controller = new TransmuteController(game);
	    List<Ingredient> currentIngredients = controller.getPlayersCurrentIngredients();

	    JPanel ingredientPanel = new JPanel(null);
	    ingredientPanel.setBounds(70, 320, 1050, 320);
	    ingredientPanel.setOpaque(false);

	    ButtonGroup ingredientGroup = new ButtonGroup(); // Create a button group

	    int buttonWidth = 100;
	    int buttonHeight = 150;
	    int xOffset = 5;
	    int yOffset = 5;

	    for (int i = 0; i < currentIngredients.size(); i++) {
	    	
	    	Ingredient ingredient = currentIngredients.get(i);
	    	int ingredientIndex = Ingredient.getIngredientIndex(ingredient.getName());
	    	
	        JToggleButton ingredientButton = new JToggleButton(); // Use JToggleButton for radio button behavior
	        ingredientButton.setBackground(null);
	        ingredientButton.setOpaque(false);
	        ingredientButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/ingredient" + ingredientIndex + ".png"));
	        Image image = imageIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
	        ingredientButton.setIcon(new ImageIcon(image));

	        int xPosition = (buttonWidth + xOffset) * (i % 8);
	        int yPosition = (buttonHeight + yOffset) * (i / 8);

	        ingredientButton.setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
	        ingredientPanel.add(ingredientButton);

	        ingredientGroup.add(ingredientButton); // Add buttons to the group
	        ingredientButton.setActionCommand(String.valueOf(ingredient.getName())); // Set action command for identification
	        ingredientButton.addActionListener(e -> handleIngredientSelection(ingredientButton)); // Add action listener
	    }

	    backgroundPanel.setLayout(null);
	    backgroundPanel.add(ingredientPanel);
	}

	private void handleIngredientSelection(JToggleButton selectedButton) {
	    // Access the selected button through the action command
	    String selectedIngredientName = selectedButton.getActionCommand();

	    System.out.println(selectedIngredientName);
	    
	    TransmuteController controller = new TransmuteController(game);
        String transmutedIngredient = controller.handleTransmute(selectedIngredientName);
        
	    if (transmutedIngredient != null)
	    {
	    	String message = "Ingredient transmuted: " + transmutedIngredient;
		    JOptionPane.showMessageDialog(null, message, "Ingredient Transmuted", JOptionPane.INFORMATION_MESSAGE);
	    }
	    else
	    {
	    	String message = "Ingredient Storage is empty!";
	    	JOptionPane.showMessageDialog(null, message, "Ingredient Taken", JOptionPane.WARNING_MESSAGE);
	    }
	    
	    // Close the frame
	    TransmuteIngredientFrame.this.dispose();
	}


}
