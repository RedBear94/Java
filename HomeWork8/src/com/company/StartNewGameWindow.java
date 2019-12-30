package com.company;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;

public class StartNewGameWindow extends JFrame {
    private final GameWindow gameWindow; // Ссылка на GameWindow

    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final int MIN_WIN_LEN = 3;
    private static final int MAX_WIN_LEN = 10;
    private static final String STR_FIELD_SIZE = "Field size: ";
    private static final String STR_WIN_LEN = "Winning length: ";

    private JRadioButton jrbHumVsAi = new JRadioButton("Human vs Ai",true);
    private JRadioButton jrbHumVsHum = new JRadioButton("Human vs Human");
    private ButtonGroup gameMode = new ButtonGroup();

    private JSlider slFieldSize;
    private JSlider slFieldSizeY;
    private JSlider slWinLength;

    public StartNewGameWindow(GameWindow gameWindow) throws HeadlessException {
        this.gameWindow = gameWindow;   // Конструктор передачи ссылки на GameWindow
        setBounds(250, 250, 400, 600);
        setTitle("Game menu");
        setLayout(new GridLayout(13,1));

        addGameControlMode();
        addGameControlFieldAndWinLen();

        JButton btnNewGame = new JButton("Start a game");
        btnNewGame.addActionListener(e -> {
            btnStartGameClick();
        });
        add(btnNewGame);

        setVisible(false);
    }

    void btnStartGameClick(){
        Logic.GAME_STATUS = 0;
        int gameMode;
        if(jrbHumVsAi.isSelected()){
            gameMode = Map.MODE_H_V_A;
            Logic.GAME_MODE = 0;
        } else{
            gameMode = Map.MODE_H_V_H;
            Logic.GAME_MODE = 1;
        }
        int fieldSize = slFieldSize.getValue();
        int fieldSizeY = slFieldSizeY.getValue();
        int winLength = slWinLength.getValue();

        Logic.SIZEX = fieldSize;
        Logic.SIZEY = fieldSizeY;
        Logic.DOTS_TO_WIN = winLength;
        Logic.initMap();
        // Logic.printMap();

        // Вызов метода начала игры (вызывется метод из gameWindow - который передает в метод находящийся в классе Map)
        gameWindow.startNewGame(gameMode, fieldSize, fieldSizeY, winLength);
        setVisible(false);
    }

    void addGameControlMode(){
        add(new JLabel("Choose gaming mode: "));
        gameMode.add(jrbHumVsAi);
        gameMode.add(jrbHumVsHum);
        add(jrbHumVsAi);
        add(jrbHumVsHum);
    }

    void addGameControlFieldAndWinLen(){
        add(new JLabel("Choose field sizeX: "));
        JLabel lblFieldSize = new JLabel(STR_FIELD_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSize);

        // расположение слайдера/от/до/по умолчанию
        slFieldSize = new JSlider(SwingConstants.HORIZONTAL, MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSize.setMajorTickSpacing(1);
        // slFieldSize.setMinorTickSpacing(1);
        slFieldSize.setPaintLabels(true);
        slFieldSize.setPaintTicks(true);
        add(slFieldSize);

        slFieldSize.addChangeListener(e -> {
            int currentFieldSize = slFieldSize.getValue();
            if(currentFieldSize>slFieldSizeY.getValue()){
                slWinLength.setMaximum(currentFieldSize);
            }
            lblFieldSize.setText(STR_FIELD_SIZE + currentFieldSize);
        });

        add(new JLabel("Choose field sizeY: "));
        JLabel lblFieldSizeY = new JLabel(STR_FIELD_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSizeY);

        // расположение слайдера/от/до/по умолчанию
        slFieldSizeY = new JSlider(SwingConstants.HORIZONTAL, MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slFieldSizeY.setMajorTickSpacing(1);
        // slFieldSizeY.setMinorTickSpacing(1);
        slFieldSizeY.setPaintLabels(true);
        slFieldSizeY.setPaintTicks(true);
        add(slFieldSizeY);

        slFieldSizeY.addChangeListener(e -> {
            int currentFieldSize = slFieldSizeY.getValue();
            if(currentFieldSize>slFieldSize.getValue()) {
                lblFieldSizeY.setText(STR_FIELD_SIZE + currentFieldSize);
            }
            slWinLength.setMaximum(currentFieldSize);
        });

        add(new JLabel("Choose winning length: "));
        JLabel lblWinLen = new JLabel(STR_WIN_LEN + MIN_WIN_LEN);
        add(lblWinLen);

        slWinLength = new JSlider(SwingConstants.HORIZONTAL, MIN_WIN_LEN, MIN_WIN_LEN, MIN_WIN_LEN);
        slWinLength.setMajorTickSpacing(1);
        // slWinLength.setMinorTickSpacing(1);
        slWinLength.setPaintLabels(true);
        slWinLength.setPaintTicks(true);
        add(slWinLength);

        slWinLength.addChangeListener(e -> {
            lblWinLen.setText(STR_WIN_LEN + slWinLength.getValue());
        });
    }
}
