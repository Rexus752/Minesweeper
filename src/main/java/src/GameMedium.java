package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMedium extends JFrame {
    // Informazioni del programma
    final String GAME_TITLE_ITA = "Campo Minato";
    final String GAME_TITLE_ENG = "Minesweeper";
    String language;
    boolean is_first_cell_discovered = false;
    // Dati di gioco
    // EOE
    final int GRID_DIMENSION = 12;
    final int CELLS_NUMBER = GRID_DIMENSION * GRID_DIMENSION;
    final int BOMBS_NUMBER = CELLS_NUMBER / 4;
    // Percorsi dei file delle immagini
    final String DEFAULT_PATH = "src\\main\\java\\src\\images\\";
    final String ICON_PATH = DEFAULT_PATH + "icon.png";
    final ImageIcon ICON_ICON = new ImageIcon(ICON_PATH);
    final String TITLE_ENG_PATH = DEFAULT_PATH + "title_eng.png";
    final ImageIcon TITLE_ENG_ICON = new ImageIcon(TITLE_ENG_PATH);
    final String TITLE_ITA_PATH = DEFAULT_PATH + "title_ita.png";
    final ImageIcon TITLE_ITA_ICON = new ImageIcon(TITLE_ITA_PATH);
    final String WHITE_BANNER_PATH = DEFAULT_PATH + "white_banner.png";
    final ImageIcon WHITE_BANNER_ICON = new ImageIcon(WHITE_BANNER_PATH);
    final String FLAG_PATH = DEFAULT_PATH + "flag.png";
    final ImageIcon FLAG_ICON = new ImageIcon(FLAG_PATH);
    final String UNDISCOVERED_PATH = DEFAULT_PATH + "undiscovered.png";
    final ImageIcon UNDISCOVERED_ICON = new ImageIcon(UNDISCOVERED_PATH);
    final String EXPLOSION_PATH = DEFAULT_PATH + "explosion.png";
    final ImageIcon EXPLOSION_ICON = new ImageIcon(EXPLOSION_PATH);
    // Dimensioni della finestra
    final int WINDOW_WIDTH = 128 + (GRID_DIMENSION * 32) + 128;  //// banner + grid + banner
    final int WINDOW_HEIGHT = 116 + (GRID_DIMENSION * 32) + 40;  //// logo + grid + buttons
    
    // Pannelli
    JPanel main_panel  = new JPanel(new BorderLayout());
    JPanel grid_panel = new JPanel(new GridLayout(GRID_DIMENSION, GRID_DIMENSION));
    JPanel buttons_panel = new JPanel(new GridLayout(1, 2));
    JButton options_button;
    JButton exit_button;
    // Immagini
    JLabel logo;
    JLabel right_white_banner = new JLabel(WHITE_BANNER_ICON);
    JLabel left_white_banner = new JLabel(WHITE_BANNER_ICON);
    // Array
    JButton[][] cell_buttons;
    String[][] cell_states;
    Boolean[][] discovered_cells;
    
    public GameMedium(String language) {
        // Language Settings
        this.language = language;
        switch(language) {
            case "ita":
                logo = new JLabel(TITLE_ITA_ICON);
                options_button = new JButton("Opzioni");
                exit_button = new JButton("Esci");
                setTitle(GAME_TITLE_ITA);
                break;
            case "eng": 
                logo = new JLabel(TITLE_ENG_ICON);
                options_button = new JButton("Options");
                exit_button = new JButton("Exit");
                setTitle(GAME_TITLE_ENG);
                break;
        }
        // Main Panel
        main_panel.add(logo, BorderLayout.NORTH);
        main_panel.add(left_white_banner, BorderLayout.WEST);
        main_panel.add(right_white_banner, BorderLayout.EAST);
        main_panel.add(grid_panel, BorderLayout.CENTER);
        main_panel.setBackground(Color.WHITE);
        // Buttons Panel
        buttons_panel.add(options_button);
        options_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Options(language);
            }
        });
        buttons_panel.add(exit_button);
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttons_panel.setBackground(Color.WHITE);
        main_panel.add(buttons_panel, BorderLayout.SOUTH);
        // Cell Arrays
        cell_buttons = new JButton[GRID_DIMENSION][GRID_DIMENSION];
        cell_states = new String[GRID_DIMENSION][GRID_DIMENSION];
        discovered_cells = new Boolean[GRID_DIMENSION][GRID_DIMENSION];
        
        set_grid();

        setIconImage(ICON_ICON.getImage());
        setResizable(true);
        setContentPane(main_panel);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public final void set_grid() {
        initialize_cell_state();
        randomize_bombs();
        set_free_cells();
    }
    
    public void initialize_cell_state() {
        int row, col;
        for (row = 0; row < GRID_DIMENSION; row++) {
            for (col = 0; col < GRID_DIMENSION; col++) {
                cell_states[row][col] = "";
            }
        }
    }
    
    public void randomize_bombs() {
        int index, bomb, row, col;
        for (index = 0; index < BOMBS_NUMBER; index++) {
            bomb = (int)(Math.random() * CELLS_NUMBER);
            row = bomb / GRID_DIMENSION;
            col = bomb % GRID_DIMENSION;
            if (cell_states[row][col].equals("bomb")) index--;
            else cell_states[row][col] = "bomb";
        }
    }
    
    public void set_free_cells() {
        int rows, cols;
        for (rows = 0; rows < GRID_DIMENSION; rows++) {
            for (cols = 0; cols < GRID_DIMENSION; cols++) {
                final int row = rows;
                final int col = cols;
                count_bombs(row, col);
                discovered_cells[row][col] = false;
                cell_buttons[row][col] = new JButton(UNDISCOVERED_ICON);
                add_cell_button_listener(row, col);
                grid_panel.add(cell_buttons[row][col]);
            }
        }
    }
    
    public void add_cell_button_listener(int row, int col) {
        JButton button = cell_buttons[row][col];
        String state = cell_states[row][col];
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (is_first_cell_discovered) {
                        button.setIcon(new ImageIcon(DEFAULT_PATH + state + ".png"));
                        set_cell_discovered(row, col);
                        check_end_game();
                        check_if_bomb_discovered(row, col);
                    } else {
                        is_first_cell_discovered = true;
                        // EOE
                        int rowOffset = -2, colOffset = 2;
                        int newRow, newCol;
                        int firstRow = row, firstCol = col;
                        int i, j;
                        for (i = 0; i < 5; i++) {
                            colOffset = -2;
                            for (j = 0; j < 5; j++) {
                                newRow = firstRow + rowOffset;
                                newCol = firstCol + colOffset;
                                colOffset++;
                                try {
                                    cell_buttons[newRow][newCol].setIcon(new ImageIcon(DEFAULT_PATH + cell_states[newRow][newCol] + ".png"));
                                    set_cell_discovered(newRow, newCol);
                                } catch(ArrayIndexOutOfBoundsException ex) {}
                            }
                            rowOffset++;
                        }
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (!discovered_cells[row][col]) {
                        if (button.getIcon() == FLAG_ICON) {
                            button.setIcon(UNDISCOVERED_ICON);
                        } else {
                            button.setIcon(FLAG_ICON);
                        }
                    }
                }
            }
        });
    }
    
    public void check_end_game() {
        int row, col;
        boolean end_game = true;
        for (row = 0; row < GRID_DIMENSION; row++) {
            for (col = 0; col < GRID_DIMENSION; col++) {
                if (!cell_states[row][col].equals("bomb")) {
                    if (!discovered_cells[row][col]) {
                        end_game = false;
                        row = GRID_DIMENSION;
                        col = GRID_DIMENSION;
                    }
                }
            }
        }
        if (end_game) {
            switch(language) {
                case "ita": 
                    JOptionPane.showMessageDialog(null, "Hai vinto!");
                    break;
                case "eng":
                    JOptionPane.showMessageDialog(null, "You won!");
                    break;
            }
            setVisible(false);
            new GameMedium(language);
        }
    }
    
    public void check_if_bomb_discovered(int row, int col) {
        if (cell_states[row][col].equals("bomb")) {
            for (row = 0; row < GRID_DIMENSION; row++) {
                for (col = 0; col < GRID_DIMENSION; col++) {
                    if (cell_states[row][col].equals("bomb")) {
                        cell_buttons[row][col].setIcon(EXPLOSION_ICON);
                    }
                }
            }
            switch(language) {
                case "ita":
                    JOptionPane.showMessageDialog(null, "Hai perso.");
                    break;
                case "eng":
                    JOptionPane.showMessageDialog(null, "You lose.");
                    break;
            }
            setVisible(false);
            new GameMedium(language);
        }
    }
    
    public void count_bombs(int row, int col) {
        if(!cell_states[row][col].equals("bomb")) {
            int bombs_around = 0;
            try {
                if(cell_states[row - 1][col].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            try {
                if(cell_states[row - 1][col - 1].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            try {
                if(cell_states[row][col - 1].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            try {
                if(cell_states[row + 1][col - 1].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            try {
                if(cell_states[row + 1][col].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            try {
                if(cell_states[row - 1][col + 1].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            try {
                if(cell_states[row][col + 1].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            try {
                if(cell_states[row + 1][col + 1].equals("bomb")) bombs_around++;
            } catch(ArrayIndexOutOfBoundsException ex) {}
            cell_states[row][col] = Integer.toString(bombs_around);
        }
    }
    
    public void set_cell_discovered(int row, int col) {
        discovered_cells[row][col] = true;
    }
}
