public class Table {
    private String[][] table;

    public Table() {
        this.table = new String[3][3];
        for(int i = 0; i <= 2; i++) {
            for(int j = 0; j <= 2; j++) {
                this.table[i][j] = " ";
            }
        }
    }

    public void drawTable() {
        System.out.println("+---+---+---+");
        for(int i = 0; i <= 2; i++) {
            System.out.println("| " + this.table[i][0] + " | " + this.table[i][1] + " | " + this.table[i][2] + " |");
            System.out.println("+---+---+---+");
        }
    }

    public void updateTable(String symbol, int row, int col) {
        if(symbol == "X" && this.table[row][col] == " ") {
            this.table[row][col] = "X";
        }
        else if(symbol == "O" && this.table[row][col] == " ") {
            this.table[row][col] = "O";
        }
    }

    public void clearTable() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                table[i][j] = " ";
            }
        }
    }

    public boolean isTableFull() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(table[i][j] == " ") {
                    return false;
                }
            }
        }
        return true;

    }

    public String getCell(int row, int col) {
        return this.table[row][col];
    }
}
