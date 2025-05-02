package Classes;

import Classes.Actors.NPC;
import Classes.Actors.Player;
import Classes.Items.Item;
import Classes.Rooms.Rooms;
import Enums.Direction;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UI {
    private static Player mainPlayer;

    private static final Border BORDER = BorderFactory.createLineBorder(Color.BLACK, 2);
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 120;

    public static JFrame root = new JFrame("CHOOSE OWN ADVENTURE");
    private static JPanel mainPanel = new JPanel();
    private static JPanel topPanel = new JPanel(new BorderLayout(20, 20));
    private static JPanel centerPanel = new JPanel();
    private static JPanel bottomPanel = new JPanel();
    private static JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));

    // Components
    private static MapPanel mapPanel = new MapPanel(Rooms.CITY_CENTER);
    private static JPanel inventoryPanel = new JPanel(new BorderLayout());
    private static JLabel healthLabel = new JLabel("");
    private static JTextArea descriptionArea = new JTextArea();
    public static JTextField inputField = new JTextField();
    public static JTextArea inventoryArea = new JTextArea();
    private static JLabel inventoryLabel = new JLabel("Inventory");

    // Buttons
    private static JButton moveBtn = new JButton("Move around");
    private static JButton lookAroundBtn = new JButton("Look around");
    private static JButton interactBtn = new JButton("Interact");
    private static JButton pickupBtn = new JButton("Pick Up");
    private static JButton useItemBtn = new JButton("Use Item");
    private static JButton helpBtn = new JButton("Help");


    private static void setDetails() {
        // Main frame setup
        root.setSize(1600, 1800);
        root.setUndecorated(true);
        root.setExtendedState(JFrame.MAXIMIZED_BOTH);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Setup main panel with BoxLayout for vertical stacking
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        root.add(mainPanel);

        // Configure inventory area
        inventoryArea.setEditable(false);
        inventoryArea.setLineWrap(true);
        inventoryArea.setWrapStyleWord(true);
        JScrollPane inventoryScroll = new JScrollPane(inventoryArea);

        // Top section (Health, Map, and Inventory)
        healthLabel.setBorder(BORDER);
        healthLabel.setPreferredSize(new Dimension(200, 80));

        mapPanel.setPreferredSize(new Dimension(840, 800));
        mapPanel.setBorder(BORDER);

        inventoryPanel.setPreferredSize(new Dimension(260, 600));
        inventoryPanel.setBorder(BORDER);
        inventoryPanel.add(inventoryLabel, BorderLayout.NORTH);
        inventoryPanel.add(inventoryScroll, BorderLayout.CENTER);

        // Arrange top section
        JPanel leftSidePanel = new JPanel(new BorderLayout());
        leftSidePanel.add(healthLabel, BorderLayout.NORTH);
        leftSidePanel.add(inventoryPanel, BorderLayout.CENTER);

        topPanel.add(leftSidePanel, BorderLayout.WEST);
        topPanel.add(mapPanel, BorderLayout.CENTER);


        // Center section (Description)
        descriptionArea.setPreferredSize(new Dimension(860, 200));
        descriptionArea.setBorder(BORDER);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        centerPanel.add(descriptionArea);

        // Button setup
        Dimension buttonSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
        moveBtn.setPreferredSize(buttonSize);
        lookAroundBtn.setPreferredSize(buttonSize);
        interactBtn.setPreferredSize(buttonSize);
        pickupBtn.setPreferredSize(buttonSize);
        useItemBtn.setPreferredSize(buttonSize);

        buttonPanel.add(moveBtn);
        buttonPanel.add(lookAroundBtn);
        buttonPanel.add(interactBtn);
        buttonPanel.add(pickupBtn);
        buttonPanel.add(useItemBtn);

        // Input and help button setup
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField.setBorder(BORDER);
        //inputField.setText("");
        helpBtn.setPreferredSize(new Dimension(140, 100));

        JPanel inputAndHelpPanel = new JPanel(new BorderLayout(20, 0));
        inputAndHelpPanel.add(inputField, BorderLayout.CENTER);
        inputAndHelpPanel.add(helpBtn, BorderLayout.EAST);

        // Add components to bottom panel
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        bottomPanel.add(buttonPanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        bottomPanel.add(inputAndHelpPanel);

        // Add all main sections to the main panel
        mainPanel.add(topPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        mainPanel.add(centerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        mainPanel.add(bottomPanel);
    }

    public static void updateInventory(String newInventory) {
        inventoryArea.setText(newInventory);
    }

    private static void initButtons() {
        updateHealth();
        updateInventory();
        JRootPane rootPane = root.getRootPane();
        KeyStroke escKey = KeyStroke.getKeyStroke("ESCAPE");
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escKey, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        ArrayList<String> validDirections = new ArrayList<>(Arrays.asList("north", "south", "east", "west"));
        moveBtn.addActionListener(e -> {
            String input = inputField.getText().trim().toLowerCase();
            if (validDirections.contains(input)) {
                Direction direction = Direction.valueOf(input.toUpperCase());
                if (!mainPlayer.getLocation().getAdjecent().containsKey(direction)) {
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "You can't move that way.");
                    inputField.setText("");
                    return;
                }
                

                if (mainPlayer.getLocation().isLocked(direction)) {
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "You can't move that way yet.");
                } else {
                    mainPlayer.takeAction(new String[]{"move", input});
                    descriptionArea.setText( "You moved " + input + "!" + "\n"+"📍 " + mainPlayer.getLocation().getDescription());
                    mapPanel.moveIntoRoom(mainPlayer.getLocation());


                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid direction.");
            }
            inputField.setText("");
        });
        lookAroundBtn.addActionListener(e -> {
                    descriptionArea.setText("");
                    mainPlayer.lookAround();
                }
        );
        helpBtn.addActionListener(e -> {
                    descriptionArea.setText("");
                    Utility.explainRules();
                }
        );
        interactBtn.addActionListener(e -> {
                    inputField.requestFocus();
                    descriptionArea.setText("");
                    List<NPC> validNPC = mainPlayer.getLocation().getNPCs();
                    if (validNPC.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There are no NPCs in the room.");
                        inputField.setText("");
                    } else if (validNPC.size() == 1) {
                        mainPlayer.takeAction(new String[]{"interact", validNPC.getFirst().getName()});
                        inputField.setText("");
                    } else if (!inputField.getText().isEmpty()) {
                        mainPlayer.takeAction(new String[]{"interact", inputField.getText()});
                        inputField.setText("");
                    }else {
                        Utility.say("There are the following items in the room:");
                        for (NPC npc : validNPC) {
                            Utility.say("- " + npc.getName());
                        }
                    }
                    updateInventory();
                }
        );
        pickupBtn.addActionListener(e -> {
                    inputField.requestFocus();
                    descriptionArea.setText("");
                    List<Item> validItems = mainPlayer.getLocation().getItems();
                    if (validItems.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "There are no items in the room.");
                        inputField.setText("");
                    } else if (validItems.size() == 1) {
                        mainPlayer.takeAction(new String[]{"pick", "up", validItems.getFirst().getName()});
                        inputField.setText("");
                    } else if (!inputField.getText().isEmpty()) {
                        mainPlayer.takeAction(new String[]{"pick", "up", inputField.getText()});
                        inputField.setText("");
                    } else {
                        Utility.say("There are the following items in the room:");
                        for (Item item : validItems) {
                            Utility.say("- " + item.getName());
                        }
                    }
                    updateInventory();
                }
        );

        useItemBtn.addActionListener(e -> {
                    descriptionArea.setText("");
                    String input = inputField.getText().trim().toLowerCase();
                    mainPlayer.takeAction(new String[]{"use", input});
                    inputField.setText("");
                    updateInventory();
                    updateHealth();
                }

        );

    }

    public static void updateInventory() {
        inventoryArea.setText("");
        for (Item item : mainPlayer.inventory) {
            inventoryArea.append(item.getName() + "\n");

        }
    }

    public static void updateHealth() {
        healthLabel.setText("Health: " + mainPlayer.getHealth());
    }


    public static void runUI(Player player) {
        mainPlayer = player;
        initButtons();
        SwingUtilities.invokeLater(() -> {
            setDetails();
            root.setVisible(true);
        });
    }

    public static JTextArea getDescriptionArea() {
        return descriptionArea;
    }

}