public class Vigener_cipher {
    //start by setting up table
    String letters_for_table = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String message;
    String key;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    String[][] table = new String[27][27];
    {
        System.out.println(letters_for_table.charAt(25));
        table[0][0]=" ";//this sets up the initial columns and rows
        for (int i = 1; i < 27; i++){
            table[0][i] = Character.toString(letters_for_table.charAt(i-1));
            table[i][0] = Character.toString(letters_for_table.charAt(i-1));
        }
        int shifter = 0;
        for (int row = 1; row <= 26; row++){
            for (int col = 1; col <= 26; col++){
                table[row][col] = Character.toString(letters_for_table.charAt(col-1));
            }
            letters_for_table = letters_for_table.substring(1, 26) + Character.toString(letters_for_table.charAt(0));
            //System.out.println(letters_for_table);
        }
        for (int r = 0; r < 27; r++){
            for (int c = 0; c < 27; c++) {
                if (r==0 | c==0)
                    System.out.print(ANSI_RED + "[" + table[r][c] + "]" + ANSI_RESET);
                else
                    System.out.print(ANSI_BLUE + "[" + table[r][c] + "]" + ANSI_RESET);
            }
            System.out.println();
        }
    }
    public Vigener_cipher(String input, String encryption_key, String direction){
        message = input.toUpperCase();
        key = encryption_key.toUpperCase();
    }
    private void table_printer(int[] coords){
        for (int r = 0; r < 27; r++){
            for (int c = 0; c < 27; c++) {
                if (r==0 | c==0)
                    System.out.print(ANSI_RED + "[" + table[r][c] + "]" + ANSI_RESET);
                else if (coords[0] == r && coords[1] == c)
                    System.out.print(ANSI_YELLOW + "[" + table[r][c] + "]" + ANSI_RESET);
                else
                    System.out.print(ANSI_BLUE + "[" + table[r][c] + "]" + ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println();
    }
    public String encoder(){
        return encoder(message, key);
    }

    private String encoder(String message, String key){
        String output="";
        int tracker = 0;
        int[] coords = new int[2];
        while (output.length()<message.length()){
            coords = locater(key.charAt(tracker%key.length()), message.charAt(tracker));
            output = output.concat(table[coords[0]][coords[1]]);
            tracker++;
        }
        System.out.println(output);
        return output;
    }
    private int[] locater(Character key_letter, Character plaintext_letter){
        int[] coords = new int[2];
        coords[0] = key_letter-64;
        coords[1] = plaintext_letter-64;
        //table_printer(coords);
        return coords;
    }

    public String decoder(){
        return decoder(message, key);
    }
    private String decoder(String message, String key){
        String output="";
        int tracker = 0;
        int[] coords = new int[2];
        while (output.length()<message.length()){
            coords = locater(key.charAt(tracker%key.length()), message.charAt(tracker));
            output = output.concat(table[0][coords[1]]);
            tracker++;
        }
        System.out.println(output);
        return output;
    }
    public static void main(String[] args) {
        Vigener_cipher t = new Vigener_cipher("JulianSinger", "DTF", "");
        t.encoder();
        t.decoder();;
    }
}

