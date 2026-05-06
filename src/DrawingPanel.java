import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ChessGame extends JFrame {

    public ChessGame() {
        setTitle("RuChess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessGame());
    }
}

class GamePanel extends JPanel {
    private static final int BOARD_START_X = 600;
    private static final int BOARD_START_Y = 100;

    private static final int TILE_SIZE = 100;

    private static final int BOARD_SIZEX = 8;
    private static final int BOARD_SIZEY = 8;


    private Piece[][] board;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private String currentPlayer = "white";
    private Map<String, BufferedImage> images;
    private Point undoButtonPos = new Point(1500, 200);
    private Dimension undoButtonSize = new Dimension(300, 100);
    private Piece lastCapturedPiece = null;
    private int lastFromRow = -1, lastFromCol = -1;
    private int lastToRow = -1, lastToCol = -1;
    private boolean whiteKingMoved = false;
    private boolean blackKingMoved = false;
    private boolean whiteRookLeftMoved = false;
    private boolean whiteRookRightMoved = false;
    private boolean blackRookLeftMoved = false;
    private boolean blackRookRightMoved = false;
    private boolean lastMoveWasCastling = false;
    private int rookFromCol = -1, rookFromRow = -1;
    private int rookToCol = -1, rookToRow = -1;

    public GamePanel() {
        setBackground(new Color(50, 50, 50));
        loadImages();
        initBoard();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void loadImages() {
        images = new HashMap<>();
        String[] pieces = {"king", "queen", "rook", "bishop", "knight", "pawn"};
        String[] colors = {"white", "black"};

        for (String color : colors) {
            for (String piece : pieces) {
                try {
                    String path = String.format("src/image/%s/%s.png", color, piece);
                    BufferedImage img = ImageIO.read(new File(path));
                    images.put(color + "_" + piece, img);
                }
                catch (IOException e) {
                    System.err.println("Error: " + color + "_" + piece);
                }
            }
        }
    }

    private void initBoard() {
        board = new Piece[BOARD_SIZEX][BOARD_SIZEY];

        for (int i = 0; i < BOARD_SIZEX; i++) {
            for (int j = 0; j < BOARD_SIZEY; j++) {
                board[i][j] = null;
            }
        }
        board[0][0] = new Piece("black", "rook");
        board[7][0] = new Piece("black", "rook");
        board[1][0] = new Piece("black", "knight");
        board[6][0] = new Piece("black", "knight");
        board[2][0] = new Piece("black", "bishop");
        board[5][0] = new Piece("black", "bishop");
        board[3][0] = new Piece("black", "queen");
        board[4][0] = new Piece("black", "king");
        for (int i = 0; i < BOARD_SIZEX; i++) {
            board[i][1] = new Piece("black", "pawn");
        }
        board[0][7] = new Piece("white", "rook");
        board[7][7] = new Piece("white", "rook");
        board[1][7] = new Piece("white", "knight");
        board[6][7] = new Piece("white", "knight");
        board[2][7] = new Piece("white", "bishop");
        board[5][7] = new Piece("white", "bishop");
        board[3][7] = new Piece("white", "queen");
        board[4][7] = new Piece("white", "king");
        for (int i = 0; i < BOARD_SIZEX; i++) {
            board[i][6] = new Piece("white", "pawn");
        }
        resetCastlingFlags();
    }

    private void resetCastlingFlags() {
        whiteKingMoved = false;
        blackKingMoved = false;
        whiteRookLeftMoved = false;
        whiteRookRightMoved = false;
        blackRookLeftMoved = false;
        blackRookRightMoved = false;
        lastMoveWasCastling = false;
    }


    private void handleMouseClick(int x, int y) {
        if (x >= undoButtonPos.x && x <= undoButtonPos.x + undoButtonSize.width &&
                y >= undoButtonPos.y && y <= undoButtonPos.y + undoButtonSize.height) {
            undoMove();
            return;
        }
        if (x < BOARD_START_X || x > BOARD_START_X + BOARD_SIZEX * TILE_SIZE ||
                y < BOARD_START_Y || y > BOARD_START_Y + BOARD_SIZEY * TILE_SIZE) {
            selectedRow = -1;
            selectedCol = -1;
            return;
        }

        int col = (x - BOARD_START_X) / TILE_SIZE;
        int row = (y - BOARD_START_Y) / TILE_SIZE;
        if (selectedRow == -1) {
            if (board[col][row] != null && board[col][row].color.equals(currentPlayer)) {
                selectedRow = row;
                selectedCol = col;
            }
        } else {
            tryMove(selectedCol, selectedRow, col, row);
            selectedRow = -1;
            selectedCol = -1;
        }
    }

    private void tryMove(int fromCol, int fromRow, int toCol, int toRow) {
        Piece piece = board[fromCol][fromRow];
        if (piece == null) return;
        if (!piece.color.equals(currentPlayer)) return;
        if (piece.type.equals("king") && Math.abs(toCol - fromCol) == 2) {
            if (tryCastling(piece, fromCol, fromRow, toCol, toRow)) {
                return;
            }
        }

        if (!isValidMove(piece, fromCol, fromRow, toCol, toRow)) return;
        if (board[toCol][toRow] != null && board[toCol][toRow].color.equals(piece.color)) return;
        saveMoveState(fromCol, fromRow, toCol, toRow);
        lastMoveWasCastling = false;
        if (piece.type.equals("king")) {
            if (piece.color.equals("white")) {
                whiteKingMoved = true;
            } else {
                blackKingMoved = true;
            }
        }
        if (piece.type.equals("rook")) {
            updateRookMovedFlag(piece.color, fromCol, fromRow);
        }

        if(piece.type.equals("pawn")){
            if(piece.color.equals("white")&&toRow==0){
                piece.type="queen";
            }
            if(piece.color.equals("black")&&toRow==7){
                piece.type="queen";
            }
        }
        board[toCol][toRow] = piece;
        board[fromCol][fromRow] = null;

        currentPlayer = currentPlayer.equals("white") ? "black" : "white";
    }

    private boolean tryCastling(Piece king, int fromCol, int fromRow, int toCol, int toRow) {
        int direction = (toCol - fromCol) > 0 ? 1 : -1;
        int rookCol = (direction == 1) ? 7 : 0;
        if (king.color.equals("white") && whiteKingMoved) return false;
        if (king.color.equals("black") && blackKingMoved) return false;
        if (king.color.equals("white")) {
            if (direction == 1 && whiteRookRightMoved) return false;
            if (direction == -1 && whiteRookLeftMoved) return false;
        } else {
            if (direction == 1 && blackRookRightMoved) return false;
            if (direction == -1 && blackRookLeftMoved) return false;
        }
        Piece rook = board[rookCol][fromRow];
        if (rook == null || !rook.type.equals("rook") || !rook.color.equals(king.color)) return false;
        int step = (direction == 1) ? 1 : -1;
        for (int col = fromCol + step; col != rookCol; col += step) {
            if (board[col][fromRow] != null) return false;
        }
        if (isSquareAttacked(fromCol, fromRow, king.color)) return false;
        if (isSquareAttacked(fromCol + direction, fromRow, king.color)) return false;
        if (isSquareAttacked(toCol, toRow, king.color)) return false;
        saveMoveState(fromCol, fromRow, toCol, toRow);
        lastMoveWasCastling = true;
        board[toCol][toRow] = king;
        board[fromCol][fromRow] = null;
        int rookToCol = (direction == 1) ? toCol - 1 : toCol + 1;
        board[rookToCol][fromRow] = rook;
        board[rookCol][fromRow] = null;
        rookFromCol = rookCol;
        rookFromRow = fromRow;
        rookToRow = fromRow;
        if (king.color.equals("white")) {
            whiteKingMoved = true;
        } else {
            blackKingMoved = true;
        }

        currentPlayer = currentPlayer.equals("white") ? "black" : "white";
        return true;
    }

    private boolean isSquareAttacked(int col, int row, String kingColor) {
        String opponent = kingColor.equals("white") ? "black" : "white";

        for (int i = 0; i < BOARD_SIZEX; i++) {
            for (int j = 0; j < BOARD_SIZEY; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.color.equals(opponent)) {
                    if (isValidMove(piece, i, j, col, row)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void updateRookMovedFlag(String color, int col, int row) {
        if (color.equals("white")) {
            if (col == 0 && row == 7) {
                whiteRookLeftMoved = true;
            } else if (col == 7 && row == 7) {
                whiteRookRightMoved = true;
            }
        } else {
            if (col == 0 && row == 0) {
                blackRookLeftMoved = true;
            } else if (col == 7 && row == 0) {
                blackRookRightMoved = true;
            }
        }
    }

    private void saveMoveState(int fromCol, int fromRow, int toCol, int toRow) {
        lastFromCol = fromCol;
        lastFromRow = fromRow;
        lastToCol = toCol;
        lastToRow = toRow;
        lastCapturedPiece = board[toCol][toRow];
    }

    private boolean isValidMove(Piece piece, int fromCol, int fromRow, int toCol, int toRow) {
        int deltaX = Math.abs(toCol - fromCol);
        int deltaY = Math.abs(toRow - fromRow);

        switch (piece.type) {
            case "pawn":
                int direction = piece.color.equals("white") ? -1 : 1;
                if (toCol == fromCol && toRow == fromRow + direction && board[toCol][toRow] == null)
                    return true;
                if (toCol == fromCol && toRow == fromRow + 2 * direction && board[toCol][toRow] == null &&
                        ((piece.color.equals("white") && fromRow == 6) || (piece.color.equals("black") && fromRow == 1)))
                    return true;
                if (Math.abs(toCol - fromCol) == 1 && toRow == fromRow + direction && board[toCol][toRow] != null)
                    return true;
                return false;

            case "knight":
                return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);

            case "bishop":
                if (deltaX != deltaY) return false;
                return isClearDiagonal(fromCol, fromRow, toCol, toRow);

            case "rook":
                if (fromCol != toCol && fromRow != toRow) return false;
                return isClearStraight(fromCol, fromRow, toCol, toRow);

            case "queen":
                if (deltaX == deltaY) {
                    return isClearDiagonal(fromCol, fromRow, toCol, toRow);
                } else if (fromCol == toCol || fromRow == toRow) {
                    return isClearStraight(fromCol, fromRow, toCol, toRow);
                }
                return false;

            case "king":
                if (deltaX <= 1 && deltaY <= 1) {
                    Piece targetPiece = board[toCol][toRow];
                    board[toCol][toRow] = piece;
                    board[fromCol][fromRow] = null;
                    boolean isAttacked = isSquareAttacked(toCol, toRow, piece.color);
                    board[fromCol][fromRow] = piece;
                    board[toCol][toRow] = targetPiece;
                    return !isAttacked;
                }
                return false;
        }
        return false;
    }

    private boolean isClearDiagonal(int fromCol, int fromRow, int toCol, int toRow) {
        int stepX = (toCol - fromCol) > 0 ? 1 : -1;
        int stepY = (toRow - fromRow) > 0 ? 1 : -1;

        int x = fromCol + stepX;
        int y = fromRow + stepY;

        while (x != toCol && y != toRow) {
            if (board[x][y] != null) return false;
            x += stepX;
            y += stepY;
        }
        return true;
    }

    private boolean isClearStraight(int fromCol, int fromRow, int toCol, int toRow) {
        if (fromCol == toCol) {
            int stepY = (toRow - fromRow) > 0 ? 1 : -1;
            for (int y = fromRow + stepY; y != toRow; y += stepY) {
                if (board[fromCol][y] != null) return false;
            }
        } else {
            int stepX = (toCol - fromCol) > 0 ? 1 : -1;
            for (int x = fromCol + stepX; x != toCol; x += stepX) {
                if (board[x][fromRow] != null) return false;
            }
        }
        return true;
    }

    private void undoMove() {
        if (lastFromCol == -1) return;

        if (lastMoveWasCastling) {
            Piece king = board[lastToCol][lastToRow];
            Piece rook = board[rookToCol][rookToRow];
            board[lastFromCol][lastFromRow] = king;
            board[lastToCol][lastToRow] = null;
            board[rookFromCol][rookFromRow] = rook;
            board[rookToCol][rookToRow] = null;
        } else {
            board[lastFromCol][lastFromRow] = board[lastToCol][lastToRow];
            board[lastToCol][lastToRow] = lastCapturedPiece;
        }

        currentPlayer = currentPlayer.equals("white") ? "black" : "white";
        lastFromCol = -1;
        lastMoveWasCastling = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
        if (selectedRow != -1) {
            g.setColor(new Color(100, 255, 100, 100));
            g.fillRect(BOARD_START_X + selectedCol * TILE_SIZE, BOARD_START_Y + selectedRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
        drawUndoButton(g);
        drawGameInfo(g);
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < BOARD_SIZEY; row++) {
            for (int col = 0; col < BOARD_SIZEX; col++) {
                Color color = ((row + col) % 2 == 0) ? new Color(255, 228, 181) : new Color(210, 105, 30);
                g.setColor(color);
                g.fillRect(BOARD_START_X + col * TILE_SIZE,
                        BOARD_START_Y + row * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < BOARD_SIZEY; row++) {
            for (int col = 0; col < BOARD_SIZEX; col++) {
                Piece piece = board[col][row];
                if (piece != null) {
                    BufferedImage img = images.get(piece.color + "_" + piece.type);
                    if (img != null) {
                        int x = BOARD_START_X + col * TILE_SIZE + (TILE_SIZE - img.getWidth()) / 2;
                        int y = BOARD_START_Y + row * TILE_SIZE + (TILE_SIZE - img.getHeight()) / 2;
                        g.drawImage(img, x, y, null);
                    } else {
                        g.setColor(Color.BLACK);
                        g.drawString(piece.color.charAt(0) + " " + piece.type.charAt(0),
                                BOARD_START_X + col * TILE_SIZE + 40,
                                BOARD_START_Y + row * TILE_SIZE + 50);
                    }
                }
            }
        }
    }

    private void drawUndoButton(Graphics g) {
        g.setColor(new Color(100, 100, 200));
        g.fillRect(undoButtonPos.x, undoButtonPos.y, undoButtonSize.width, undoButtonSize.height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Отменить ход", undoButtonPos.x + 70, undoButtonPos.y + 60);
    }
    private void drawGameInfo(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String playerText = "Ход: " + (currentPlayer.equals("white") ? "Белые" : "Чёрные");
        g.drawString(playerText, 50, 50);
    }





    private class Piece {
        String color;
        String type;

        Piece(String color, String type) {
            this.color = color;
            this.type = type;
        }
    }
}