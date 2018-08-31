package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;

public class MainController {


    StringBuilder stringBuilder;
    StringBuffer stringBuffer;

    List<File> selectedFiles;
    @FXML
    private Button buttonSelectMultiFile;
    @FXML
    private Button buttonFixFile;
    @FXML
    private Button buttonEnd;
    @FXML
    private Label label1 = new Label();
    @FXML
    private ListView listView;
    @FXML
    private CheckBox naprawRok;
    @FXML
    private Label licznikAkt = new Label();


    private StringProperty spLicznik = new SimpleStringProperty();

    public void buttonSelectMultiFileAction(ActionEvent event) {
        label1.setText("");
        FileChooser fileChooser = new FileChooser();
        this.selectedFiles = fileChooser.showOpenMultipleDialog(null);

        if (selectedFiles != null) {
            selectedFiles.forEach(file -> listView.getItems().add(file.getName()));
            label1.setVisible(true);
            label1.setText(selectedFiles.size() + " pliki/ów");
            buttonFixFile.setDisable(false);

        } else {
            System.out.println("Nic nie wybrano");
        }

    }


    public void buttonFixFileAction(ActionEvent event) throws IOException {
        licznikAkt.textProperty().bind(spLicznik);
        StringBuffer stringBuffer = null;
        label1 = new Label();
        Akt akt = null;
        int licznik = 1;
        int licznik2 = 0;
        Boolean nowyAkt = false;
        File file = selectedFiles.get(0);
        FileReader fileReader = new FileReader(file);
        FileChooser fileChooser = new FileChooser();
        File fileToWrite = fileChooser.showSaveDialog(null);   //.showOpenDialog(null);
        FileWriter fileWriter = new FileWriter(fileToWrite, true);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
//            fileWriter = new FileWriter(fileToWrite, true);
            line = line.substring(line.indexOf('<'));
            if (line.equals("<column>SUMA</column>"))
                    line = line;
            String element = findElement(line);
            if (element.equals("<line>")) {

                spLicznik.setValue(String.valueOf(licznik2++));
                akt = new Akt();
                fileWriter.write(line+"\n");
  //              fileWriter.close();
//                stringBuffer.append(line);
            } else if (element.equals("<column>")) {
                line = line.replace("<column>", "").replace("</column>", "");
                switch (licznik) {
                    case 1:
                        akt.setCol1(line);
                        licznik++;
                        break;
                    case 2:
                        akt.setCol2(line);
                        licznik++;
                        break;
                    case 3:
                        akt.setCol3(line);
                        licznik++;
                        break;
                    case 4:
                        akt.setCol4(line);
                        licznik++;
                        break;
                    case 5:
                        akt.setCol5(line);
                        licznik++;
                        break;
                    case 6:
                        akt.setCol6(line);
                        licznik++;
                        break;
                    case 7:
                        akt.setCol7(line);
                        break;
                }
            } else if (element.equals("</line>")) {
                napraw(akt);
                fileWriter.write(akt.toString()+ line + "\n");
    //            fileWriter.close();
//                stringBuffer.append(akt.toString());
                licznik = 1;
                akt = null;
            } else {
                fileWriter.write(line+"\n");
      //          fileWriter.close();
            }
//                stringBuffer.append(line.toString());
        }

        fileReader.close();
        fileWriter.close();
        listView = null;
        listView.refresh();
        label1.setText("Koniec");
    }

    private String findElement(String line) {
        return line.substring(0,line.indexOf('>')+1);

    }

    private void napraw(Akt akt) {

        if (naprawRok.isSelected())
            akt.naprawaRoku();


    }


    public void buttonEndAction(ActionEvent event) {
        System.exit(0);

    }


}
