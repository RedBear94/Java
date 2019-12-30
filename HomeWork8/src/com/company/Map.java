package com.company;

import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    public static int winArray[][];

    int fieldSizeX;
    int fieldSizeY;
    int winLength;
    int gameMode;

    int cellHeight;
    int cellWidth;

    boolean isInitialised = false;

    private static GameEndWindow gameEnd;

    public Map() {
        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(Logic.GAME_STATUS == 0) {
                    super.mouseReleased(e);
                    // Поучение номера ячейки в которую попали
                    int cellX = e.getX() / cellWidth;
                    int cellY = e.getY() / cellHeight;
                    System.out.println(cellX + " " + cellY);
                    if(Logic.whoseMoveIsNow == 0 && gameMode == 1){
                        Logic.humanTurn(cellX, cellY, Logic.DOT_X);
                    } else if(Logic.whoseMoveIsNow == 1 && gameMode == 1){
                        Logic.humanTurn(cellX, cellY, Logic.DOT_O);
                    } else if(gameMode == 0){
                        Logic.humanTurn(cellX, cellY, Logic.DOT_X);
                    }
                    repaint();
                }

                if(Logic.GAME_STATUS > 0 && Logic.GAME_STATUS < 4) {
                    gameEnd = new GameEndWindow();
                    gameEnd.setVisible(true);
                    repaint();
                    if(Logic.GAME_STATUS==3)
                        Logic.GAME_STATUS = 5;  // Сделано чтобы окно окончания выводилось 1 раз
                }

                if(Logic.GAME_STATUS == 1){
                    winArray = Logic.checkWinLineСoordinates(Logic.DOT_X, Logic.DOTS_TO_WIN);
                    Logic.GAME_STATUS = 4;  // Сделано чтобы окно окончания выводилось 1 раз
                }
                if(Logic.GAME_STATUS == 2){
                    winArray = Logic.checkWinLineСoordinates(Logic.DOT_O, Logic.DOTS_TO_WIN);
                    Logic.GAME_STATUS = 4;
                }

            }
        });
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength){
        // System.out.println(mode + " " + fieldSizeX + " " + fieldSizeY + " " + winLength);
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        this.gameMode = mode;
        isInitialised = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        if(!isInitialised){
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellWidth = panelWidth / fieldSizeX;
        cellHeight = panelHeight / fieldSizeY;

        for(int i = 0; i < fieldSizeY; i++){
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);    // x1 y1 x2 y2
        }

        for(int i = 0; i < fieldSizeX; i++){
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for(int i = 0; i < Logic.SIZEY; i++){
            for(int j = 0; j < Logic.SIZEX; j++){
                if(Logic.map[i][j] == Logic.DOT_X){
                    drawX(g, j, i); // DrawX - координты определяются из i и j
                }
                if(Logic.map[i][j] == Logic.DOT_O){
                    drawO(g, j, i);
                }
            }
        }

        // Рисовать победную линию только при победе
        if(Logic.GAME_STATUS == 1 || Logic.GAME_STATUS == 2 || Logic.GAME_STATUS == 4){
                drawWinLine(g,
                        winArray[0][0],
                        winArray[0][1],
                        winArray[winArray.length-1][0],
                        winArray[winArray.length-1][1],
                        winArray[0][2]);
        }
    }

    private void drawWinLine(Graphics g, int cellX1, int cellY1, int cellX2, int cellY2, int howDrawWin){
        g.setColor(new Color(0, 0, 0));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));

        // howDrawWin = 1 - строка | 2 - столбец | 3 - диаганаль слева направо | 4 - диагональ справа налево
        switch (howDrawWin){
            case 1:
                g2d.drawLine(cellX1 * cellWidth + cellWidth,
                        cellY1 * cellHeight  + cellHeight/2,
                        cellX2 * cellWidth,
                        cellY2 * cellHeight + cellHeight/2);
                break;
            case 2:
                g2d.drawLine(cellX1 * cellWidth + cellWidth/2,
                        cellY1 * cellHeight + cellHeight,
                        cellX2 * cellWidth + cellWidth/2,
                        cellY2 * cellHeight);
                break;
            case 3:
                g2d.drawLine(cellX1 * cellWidth + cellWidth,
                        cellY1 * cellHeight + cellHeight,
                        cellX2 * cellWidth,
                        cellY2 * cellHeight);
                break;
            case 4:
                g2d.drawLine(cellX1 * cellWidth,
                        cellY1 * cellHeight + cellHeight,
                        cellX2 * cellWidth + cellWidth,
                        cellY2 * cellHeight);
                break;
            default:
                break;
        }
    }

    private void drawO(Graphics g, int cellX, int cellY){
        g.setColor(new Color(255, 0, 0));
        g.drawOval(cellX * cellWidth, cellY * cellHeight, cellWidth, cellHeight);
    }

    private void drawX(Graphics g, int cellX, int cellY){
        g.setColor(new Color(0, 0, 255));
        g.drawLine(cellX * cellWidth, cellY * cellHeight, cellX * cellWidth + cellWidth, cellY * cellHeight + cellHeight);
        g.drawLine(cellX * cellWidth + cellWidth, cellY * cellHeight, cellX * cellWidth, cellY * cellHeight + cellHeight);
    }
}
