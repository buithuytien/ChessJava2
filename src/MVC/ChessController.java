package MVC;

import ChessPiece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessController implements IChessControllerUtils {
    ChessModel chessModel;
    ChessView chessView;
    JFrame frame;

    public ChessController(){
        chessModel = new ChessModel();
        chessModel.reset();

        chessView = new ChessView(this);

        frame = new JFrame("MVC.ChessController");
        frame.setSize(600, 630);
        frame.setLocation(400, 1500);
        frame.add(chessView);

        // add layout
        frame.add(chessView, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton resetButton = createResetButton();
        bottomPanel.add(resetButton);
        frame.add(bottomPanel, BorderLayout.PAGE_END);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public Piece pieceAt(int col, int row) {
        return chessModel.pieceAt(col, row);
    }

    @Override
    public void movePiece(int fromCol, int fromRow, int toCol, int toRow) {
        chessModel.movePiece(fromCol, fromRow, toCol, toRow);
        chessView.repaint(); // refresh the view to move the chess piece

        // if game is over, display a message.

        Player winner = chessModel.getWinner();
        System.out.println("from controller. Get winner " + winner);
        if(winner != null ){

            // TODO: controller tell view to display a message that "white/ black win".
            // Reset game? (OK).

            JOptionPane.showMessageDialog(frame,  winner.toString() + " wins");
        }
    }

    @Override
    public boolean isGameOver() {
        return chessModel.isGameOver();
    }

    private JButton createResetButton(){
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chessModel.reset();
                chessView.repaint();
            }
        });

        return resetButton;
    }
}
