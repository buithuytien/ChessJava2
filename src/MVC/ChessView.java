package MVC;

import ChessPiece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class ChessView extends JPanel implements ChessViewInterface, MouseListener, MouseMotionListener  {
    private IChessControllerUtils controllerUtilsDelegate; // to decouple from chessColtroller class

    private int cellSize = 65;
    private int originX = 30;
    private int originY = 30;

    private int fromCol;
    private int fromRow;
    private int toCol;
    private int toRow;

    private Piece movingPiece;

    HashMap<String, Image> filePath2Img;
    String[] imageFilePaths;

    public ChessView(IChessControllerUtils controllerUtilsDelegate){
        this.controllerUtilsDelegate = controllerUtilsDelegate;

        filePath2Img = new HashMap<String, Image>();
        this.imageFilePaths = ChessConstants.getPieceImgPaths(); // from static class

        try{
            for(String imageName: imageFilePaths){
                Image img = loadImage(imageName);
                filePath2Img.put(imageName, img);
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        addMouseListener(this);
        addMouseMotionListener(this);
    }


    @Override
    protected void paintChildren(Graphics g){
        super.paintChildren(g);

        Graphics2D g2 = (Graphics2D)g;
        drawBoard(g2);
        drawPiecesAll(g2);
    }

    private void drawPiecesAll(Graphics g2){

        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                Piece p = controllerUtilsDelegate.pieceAt(c, r);

                if(p != null){
//                    System.out.println(p);
                    drawPieceAtTile(g2, r, c, p.getImgPath());
                }

            }
        }
    }


    private void drawPieceAtTile(Graphics g2, int row, int col, String imgName){
        Image img = filePath2Img.get(imgName);
        g2.drawImage(img, col*cellSize + originX, row*cellSize + originY, cellSize, cellSize, null);
    }

    private Image loadImage(String imgFileName){
        ClassLoader classLoader = getClass().getClassLoader();
        URL resURL = classLoader.getResource(imgFileName);

        if (resURL == null){
            System.out.println("failed to load image " + imgFileName);
        } else{
//            System.out.println("loaded image imgFileName");
            try {
                File imgFile = new File(resURL.toURI());
                Image img = ImageIO.read(imgFile);
//                g2.drawImage(img, 0, 0, cellSize, cellSize, null);
                return img;
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void drawBoard(Graphics g2){
        for(int j = 0; j < 4; j++){
            for(int i = 0 ; i < 4; i++){
                drawSquare(g2, 2*i, 2*j, true);
                drawSquare(g2, 2*i+1, 2*j+1, true);
            }

            for(int i = 0 ; i < 4; i++){
                drawSquare(g2, 2*i, 2*j, false);
                drawSquare(g2, 2*i+1, 2*j+1, false);
            }
        }
    }

    private void drawSquare(Graphics g, int col, int row, boolean isWhite){
        g.setColor(isWhite ? Color.lightGray : Color.darkGray);
        g.fillRect(originX + col*cellSize, originY + row*cellSize, cellSize, cellSize );

    }

    // mouse event handling
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("from " + e.getPoint());
        fromCol = (e.getPoint().x - originX) / cellSize;
        fromRow = (e.getPoint().y - originY) / cellSize;
//        System.out.println("from col = " + fromCol + " row = " + fromRow);

        movingPiece = controllerUtilsDelegate.pieceAt(fromCol, fromRow);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("to " + e.getPoint());
        int col = (e.getPoint().x - originX) / cellSize;
        int row = (e.getPoint().y - originY) / cellSize;

//        System.out.println("to col = " + col + " row = " + row);

        if (fromCol != col || fromRow != row) {
            controllerUtilsDelegate.movePiece(fromCol, fromRow, col, row);
        }

        movingPiece = null;
//        controllerUtilsDelegate = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
