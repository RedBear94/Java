package com.company;

import javax.swing.*;
import java.awt.*;

public class GameEndWindow extends JFrame {

    public GameEndWindow(){
        setBounds(250, 350, 400, 200);
        setTitle("Game over");

        Font font = new Font("Verdana", Font.PLAIN, 20);

        JLabel lbWhoWin = new JLabel("Game Over");
        lbWhoWin.setHorizontalAlignment(SwingConstants.CENTER);
        lbWhoWin.setFont(font);
        add(lbWhoWin, BorderLayout.CENTER);

        switch (Logic.GAME_STATUS){
            case 1:
                lbWhoWin.setText("Crosses won the game");
                break;
            case 2:
                lbWhoWin.setText("Zeroes won the game");
                break;
            case 3:
                lbWhoWin.setText("Draw");
                break;
            default:
                break;
        }
        setVisible(false);
    }
}
