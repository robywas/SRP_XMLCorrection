package application;

public class Akt {

    //AU - 6 column,  AM - 7 column, AZ - 7 column;

    private String col1 = "";
    private String col2 = "";
    private String col3 = "";
    private String col4 = "";
    private String col5 = "";
    private String col6 = "";
    private String col7 = "";
    private String rok = "";
    private String rodzaj = "";

    public Akt() {
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
        if (!col1.equals("Numer aktu małżeństwa") && !col1.equals("SUMA")) {
            this.rok = col1.substring(13, 18);
            this.rodzaj = col1.substring(11, 13);
        }
    }

    @Override
    public String toString() {

        return getCol1Xml() + "\n" +
                getCol2Xml() + "\n" +
                getCol3Xml() + "\n" +
                getCol4Xml() + "\n" +
                getCol5Xml() + "\n" +
                getCol6Xml() + "\n" +
                getCol7Xml();
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6;
    }

    public String getCol7() {
        return col7;
    }

    public void setCol7(String col7) {
        this.col7 = col7;
    }

    public String getCol1Xml() {
        return "<column>" + col1 + "</column>";
    }

    public String getCol2Xml() {
        return "<column>" + col2 + "</column>";
    }

    public String getCol3Xml() {
        return "<column>" + col3 + "</column>";
    }

    public String getCol4Xml() {
        return "<column>" + col4 + "</column>";
    }

    public String getCol5Xml() {
        return "<column>" + col5 + "</column>";
    }

    public String getCol6Xml() {
        return "<column>" + col6 + "</column>";
    }

    public String getCol7Xml() {
        if (!rodzaj.equals("AU"))
            return "<column>" + col7 + "</column>\n";
        else
            return "";
    }

    public void naprawaRoku() {

        if (rodzaj.equals("AU"))
                this.col6 = dopiszRok(getCol6(), rok);
            else
                this.col7 = dopiszRok(getCol7(), rok);

    }

    private String dopiszRok(String column, String rok) {
        if (!column.equals("")) {
            if (!(column.contains(rok) || column.contains("/" + rok.substring(3, 4))))
                return column + rok;
        }
        return column;
    }


}
