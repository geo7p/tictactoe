import java.util.Scanner;

public class Game {
    private Player p;
    private Computer c;
    private Table t;
    private int noRounds;
    private int whoIsFirst;

    public Game(Player p, Computer c, Table t) {
        this.p = p;
        this.c = c;
        this.t = t;
        this.whoIsFirst = (int)Math.abs(Math.random() * 2) + 1;
    }

    public void selectSymbol() {
        String sym;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Selecteaza un simbol (X or O): ");
            sym = s.next();
            if(sym.equals("X")) {
                System.out.println("Ai ales X. Calculatorul va fi O.");
                this.p.setSymbol("X");
                this.c.setSymbol("O");
            }
            else if(sym.equals("O")) {
                System.out.println("Ai ales O. Calculatorul va fi X.");
                this.p.setSymbol("O");
                this.c.setSymbol("X");
            }
            else {
                System.out.println("Error! Try again!");
            }
        } while(!sym.equals("X") && !sym.equals("O"));
    }

    public void setRounds() {
        int noRounds = -1;
        Scanner s = new Scanner(System.in);
        System.out.println("Selecteaza numarul de runde (3, 5, 7): ");
        do {
            noRounds = s.nextInt();
        } while(!(noRounds == 3 || noRounds == 5 || noRounds == 7));
        this.noRounds = noRounds;
    }

    public void playerTurn() {
        int row = -1, col = -1;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Selecteaza o linie (1, 2, 3): ");
            row = s.nextInt();
            System.out.println("Selecteaza o coloana (1, 2, 3): ");
            col = s.nextInt();
        } while(!((row < 1 && row > 3)||(col < 1 && col > 3)) && t.getCell(row - 1, col - 1) != " ");
        t.updateTable(this.p.getSymbol(), row - 1, col - 1);
    }

    public void computerTurn() {
        int row = -1, col = -1;
        Scanner s = new Scanner(System.in);
        do {
            row = (int)Math.abs(Math.random() * 3) + 1;
            col = (int)Math.abs(Math.random() * 3) + 1;
        } while(!((row < 1 && row > 3)||(col < 1 && col > 3)) && t.getCell(row - 1, col - 1) != " ");
        System.out.println("Calculatorul a ales: " + row + " " + col);
        t.updateTable(this.c.getSymbol(), row - 1, col - 1);
    }

    public void round(int cRounds) {
        System.out.println("\n+===============+");
        System.out.println("Runda: " + cRounds);
        System.out.println("+===============+\n");
        while(isWon(this.p, this.c, this.t) == 0) {
            System.out.println("Scor player: " + this.p.getScore() + " || Scor calculator: " + this.c.getScore() + "\n");
            if(this.whoIsFirst == 1) {
                System.out.println("Tu esti primul.");
                this.playerTurn();
                if(!t.isTableFull() && !(isWon(this.p, this.c, this.t) == 1))
                    this.computerTurn();
                this.t.drawTable();
            }
            else {
                System.out.println("Calculatorul este primul.");
                this.computerTurn();
                this.t.drawTable();
                if(!t.isTableFull() && !(isWon(this.p, this.c, this.t) == -1))
                    this.playerTurn();
            }
        }
        if(isWon(this.p, this.c, this.t) == 1) {
            this.p.incrementScore();
            System.out.println("Scor player: " + this.p.getScore() + " || Scor calculator: " + this.c.getScore() + "\n");
            System.out.println("Ati castigat runda: " + cRounds);
            this.t.clearTable();
        }
        else if(isWon(this.p, this.c, this.t) == -1) {
            this.c.incrementScore();
            System.out.println("Scor player: " + this.p.getScore() + " || Scor calculator: " + this.c.getScore() + "\n");
            System.out.println("Calculatorul a castigat runda: " + cRounds);
            this.t.clearTable();
        }
        else if(isWon(this.p, this.c, this.t) == 2) {
            System.out.println("Scor player: " + this.p.getScore() + " || Scor calculator: " + this.c.getScore() + "\n");
            System.out.println("Egalitate!");
            this.t.clearTable();
        }
    }

    public void meniu() {
        Scanner s = new Scanner(System.in);
        String r;
        int cRounds = 1;
        this.selectSymbol();
        this.setRounds();
        while(cRounds <= this.noRounds && this.p.getScore() + this.c.getScore() < (int)(this.noRounds / 2) + 1) {
            round(cRounds);
            cRounds++;
        }
        if(this.p.getScore() > this.c.getScore()) {
            System.out.println("Ati castigat! Felicitari!");
        }
        else if(this.c.getScore() > this.p.getScore()) {
            System.out.println("A castigat calculatorul.");
        }
        else {
            System.out.println("Egalitate.");
        }
        do {
            System.out.println("Mai incerci o data? (Y/N): ");
            r = s.nextLine();
            if(r.equals("Y")) {
                this.p.setScore(0);
                this.c.setScore(0);
                this.t.clearTable();
                meniu();
            }
        }
        while(!r.equals("Y") && !r.equals("N"));
    }

    public static int isWon(Player p, Computer c, Table t) {
        for(int i = 0; i < 3; i++) {
            if(t.getCell(i, 0) == p.getSymbol() && t.getCell(i, 1) == p.getSymbol() && t.getCell(i, 2) == p.getSymbol()) {
                return 1;
            } else if(t.getCell(i, 0) == c.getSymbol() && t.getCell(i, 1) == c.getSymbol() && t.getCell(i, 2) == c.getSymbol()) {
                return -1;
            }
        }
        for(int j = 0; j < 3; j++) {
            if(t.getCell(0, j) == p.getSymbol() && t.getCell(1, j) == p.getSymbol() && t.getCell(2, j) == p.getSymbol()) {
                return 1;
            } else if(t.getCell(0, j) == c.getSymbol() && t.getCell(1, j) == c.getSymbol() && t.getCell(2, j) == c.getSymbol()) {
                return -1;
            }
        }
        if(t.getCell(0, 0) == p.getSymbol() && t.getCell(1, 1) == p.getSymbol() && t.getCell(2, 2) == p.getSymbol()) {
            return 1;
        } else if(t.getCell(0, 0) == c.getSymbol() && t.getCell(1, 1) == c.getSymbol() && t.getCell(2, 2) == c.getSymbol()) {
            return -1;
        }
        if(t.getCell(0, 2) == p.getSymbol() && t.getCell(1, 1) == p.getSymbol() && t.getCell(2, 0) == p.getSymbol()) {
            return 1;
        } else if(t.getCell(0, 2) == c.getSymbol() && t.getCell(1, 1) == c.getSymbol() && t.getCell(2, 0) == c.getSymbol()) {
            return -1;
        }
        if(t.isTableFull())
            return 2;
        return 0;
    }

    public static void main(String[] args) {
        Game j = new Game(new Player(" ", 0), new Computer(" ", 0), new Table());
        j.meniu();
    }
}
