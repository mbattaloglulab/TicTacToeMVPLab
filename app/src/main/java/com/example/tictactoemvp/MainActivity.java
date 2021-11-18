package com.example.tictactoemvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BoardView{

    BoardPresenter presenter;
    TableLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = findViewById(R.id.board);
        presenter = new BoardPresenter(this);

        for (byte i = 0; i < 3; i++) {
            TableRow tableRow = (TableRow) board.getChildAt(i);
            for (byte j = 0; j < 3; j++) {
                Button btn = (Button) tableRow.getChildAt(j);
                btn.setOnClickListener(new BoardPresenter.CellClickListener(presenter,i,j));
            }
        }
    }

    @Override
    public void newGame() {
        for (byte i = 0; i < 3; i++) {
            TableRow tableRow = (TableRow) board.getChildAt(i);
            for (byte j = 0; j < 3; j++) {
                Button btn = (Button) tableRow.getChildAt(j);
                btn.setText("");
                btn.setEnabled(true);
            }
        }
    }

    @Override
    public void putSymbol(char symbol, byte row, byte col) {
        TableRow tableRow = (TableRow) board.getChildAt(row);
        Button btn = (Button) tableRow.getChildAt(col);
        btn.setText(Character.toString(symbol));
    }

    @Override
    public void gameEnded(byte winner) {
        for (byte i = 0; i < 3; i++) {
            TableRow tableRow = (TableRow) board.getChildAt(i);
            for (byte j = 0; j < 3; j++) {
                Button btn = (Button) tableRow.getChildAt(j);
                btn.setEnabled(false);
            }
        }
        String msg;
        if (winner != 0)
            msg = "Player" + winner + "won the game";
        else
            msg = "Draw";
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void invalidPlay(byte row, byte col) {
        Toast.makeText(this, "Invalid Move", Toast.LENGTH_LONG).show();

    }
}