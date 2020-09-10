package sudoku.persistence;

import com.sun.javafx.iio.ios.IosDescriptor;
import sudoku.problemDomain.Storage;
import sudoku.problemDomain.SudokuGame;

import java.io.*;
import java.util.stream.IntStream;

public class LocalStorage implements  Storage {

    private static File GAME_DATA =     new File(System.getProperty("user.home"),"gameData.txt");
    @Override
    public void updateGameData(SudokuGame game) throws IOException {
        try{
            FileOutputStream fileOutputStream= new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
        }catch(IOException e){
            throw new IOException("Unable to access game data");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {
        FileInputStream fileInputStream= new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
        try{
            SudokuGame gameState=(SudokuGame)objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("File Not Found");
        }
        return null;
    }
}
