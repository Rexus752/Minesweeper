package src;

import javax.swing.*;
import java.awt.event.*;

public class Menu extends JFrame {
    final String GAME_TITLE_ITA = "Campo Minato";
    final String GAME_TITLE_ENG = "Minesweeper";
    String language = "ita", difficulty = "easy";
    final String DEFAULT_PATH = "src\\main\\java\\src\\images\\";
    final String ICON_PATH = DEFAULT_PATH + "icon.png";
    final ImageIcon ICON_ICON = new ImageIcon(ICON_PATH);
    final String TITLE_ENG_PATH = DEFAULT_PATH + "title_eng.png";
    final ImageIcon TITLE_ENG_ICON = new ImageIcon(TITLE_ENG_PATH);
    final String TITLE_ITA_PATH = DEFAULT_PATH + "title_ita.png";
    final ImageIcon TITLE_ITA_ICON = new ImageIcon(TITLE_ITA_PATH);
    private JPanel main_panel = new JPanel(); 
    private JLabel logo = new JLabel();
    private JButton play_button = new JButton();
    private JButton options_button = new JButton();
    private JButton exit_button = new JButton();
    
    public Menu(String language, String difficulty) {
        this.difficulty = difficulty;
        this.language = language;
        switch(language) {
            case "eng":
                setTitle(GAME_TITLE_ENG);
                logo.setIcon(TITLE_ENG_ICON);
                play_button.setText("Play");
                options_button.setText("Options");
                exit_button.setText("Exit");
                break;
            case "ita":
                setTitle(GAME_TITLE_ITA);
                logo.setIcon(TITLE_ITA_ICON);
                play_button.setText("Gioca");
                options_button.setText("Opzioni");
                exit_button.setText("Esci");
                break;
        }
        play_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                switch(difficulty) {
                    case "easy":
                        new GameEasy(getLanguage());
                        break;
                    case "medium":
                        new GameMedium(getLanguage());
                        break;
                    case "hard":
                        new GameHard(getLanguage());
                        break;
                    case "extreme":
                        new GameExtreme(getLanguage());
                        break;
                }
            }
        });
        options_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Options(language);
            }
        });
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logo)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(exit_button, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(options_button)
                    .addComponent(play_button, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                .addGap(225, 225, 225))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(logo)
                .addGap(18, 18, 18)
                .addComponent(play_button)
                .addGap(18, 18, 18)
                .addComponent(options_button)
                .addGap(18, 18, 18)
                .addComponent(exit_button)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pack();

        setIconImage(ICON_ICON.getImage());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public String getLanguage() {
        return this.language;
    }
}
