package src;

import javax.swing.*;
import java.awt.event.*;

public class Options extends JFrame {
    final String GAME_TITLE_ITA = "Campo Minato";
    final String GAME_TITLE_ENG = "Minesweeper";
    String language = "ita", difficulty = "easy";
    final String DEFAULT_PATH = "src\\main\\java\\src\\images\\";
    final String ICON_PATH = DEFAULT_PATH + "icon.png";
    final ImageIcon ICON_ICON = new ImageIcon(ICON_PATH);
    final String ENGLISH_PATH = DEFAULT_PATH + "english.png";
    final ImageIcon ENGLISH_ICON = new ImageIcon(ENGLISH_PATH);
    final String ITALIAN_PATH = DEFAULT_PATH + "italian.png";
    final ImageIcon ITALIAN_ICON = new ImageIcon(ITALIAN_PATH);
    JPanel main_panel = new JPanel();
    JLabel title_label = new JLabel();
    JLabel select_difficulty_label = new JLabel();
    JButton easy_mode_button = new JButton();
    JButton medium_mode_button = new JButton();
    JButton hard_mode_button = new JButton();
    JButton extreme_mode_button = new JButton();
    JLabel select_language_label = new JLabel();
    JButton english_button;
    JButton italian_button;
    JButton back_button = new JButton();
    
    public Options(String language) {
        this.language = language;
        switch(language) {
            case "eng":
                setTitle(GAME_TITLE_ENG);
                title_label.setText("Options menu");
                select_difficulty_label.setText("Select the difficulty:");
                easy_mode_button.setText("Easy (10x10)");
                medium_mode_button.setText("Medium (12x12)");
                hard_mode_button.setText("Hard (14x14)");
                extreme_mode_button.setText("Extreme (16x16)");
                select_language_label.setText("Select the language:");
                back_button.setText("Go back");
                break;
            case "ita":
                setTitle(GAME_TITLE_ITA);
                title_label.setText("Menu delle opzioni");
                select_difficulty_label.setText("Seleziona la difficoltà:");
                easy_mode_button.setText("Facile (10x10)");
                medium_mode_button.setText("Media (12x12)");
                hard_mode_button.setText("Difficile (14x14)");
                extreme_mode_button.setText("Estrema (16x16)");
                select_language_label.setText("Seleziona la lingua:");
                back_button.setText("Torna indietro");
                break;
        }
        
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        select_difficulty_label.setHorizontalAlignment(SwingConstants.CENTER);
        easy_mode_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "easy";
            }
        });
        medium_mode_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "medium";
            }
        });
        hard_mode_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "hard";
            }
        });
        extreme_mode_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = "extreme";
            }
        });
        select_language_label.setHorizontalAlignment(SwingConstants.CENTER);
        english_button = new JButton(ENGLISH_ICON);
        english_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLanguage("eng");
                setVisible(false);
                new Options(getLanguage());
            }
        });
        italian_button = new JButton(ITALIAN_ICON);
        italian_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLanguage("ita");
                setVisible(false);
                new Options(getLanguage());
            }
        });
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu(language, difficulty);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(main_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout main_panelLayout = new GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(main_panelLayout.createSequentialGroup()
                        .addGroup(main_panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(medium_mode_button, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(title_label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(easy_mode_button, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hard_mode_button, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(select_difficulty_label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(main_panelLayout.createSequentialGroup()
                                .addComponent(english_button, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(italian_button, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                            .addComponent(extreme_mode_button, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(select_language_label, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(back_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_label, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(select_difficulty_label)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(easy_mode_button)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medium_mode_button)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hard_mode_button)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(extreme_mode_button)
                .addGap(18, 18, 18)
                .addComponent(select_language_label)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(main_panelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(english_button)
                    .addComponent(italian_button))
                .addGap(18, 18, 18)
                .addComponent(back_button)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pack();

        setIconImage(ICON_ICON.getImage());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public String getDifficulty() {
        return this.difficulty;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getLanguage() {
        return this.language;
    }
}
