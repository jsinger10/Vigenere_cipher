import java.util.Scanner;

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
//        for (int r = 0; r < 27; r++){
//            for (int c = 0; c < 27; c++) {
//                if (r==0 | c==0)
//                    System.out.print(ANSI_RED + "[" + table[r][c] + "]" + ANSI_RESET);
//                else
//                    System.out.print(ANSI_BLUE + "[" + table[r][c] + "]" + ANSI_RESET);
//            }
//            System.out.println();
//        }
    }
    public Vigener_cipher(String input, String encryption_key, Boolean encode, String visuals){
        message = input.toUpperCase();
        key = encryption_key.toUpperCase();
        if(encode==true){
            message = encoder(visuals);
        }
        else
        {
            message = decoder();
        }
    }
    private void table_printer(int[] coords){
        for (int r = 0; r < 27; r++){
            for (int c = 0; c < 27; c++) {
                if (r==0 | c==0)
                    System.out.print(ANSI_RED + "[" + table[r][c] + "]" + ANSI_RESET);
                else if (coords[0] == r && coords[1] == c)
                    System.out.print(ANSI_YELLOW + "[" + table[r][c] + "]" + ANSI_RESET);
                else if (coords[0] == r && coords[1]!=0)
                    System.out.print(ANSI_GREEN + "[" + table[r][c] + "]" + ANSI_RESET);
                else if (coords[0] !=0 && coords[1]==c)
                    System.out.print(ANSI_GREEN + "[" + table[r][c] + "]" + ANSI_RESET);
                else
                    System.out.print(ANSI_BLUE + "[" + table[r][c] + "]" + ANSI_RESET);
            }
            System.out.println();
        }
        System.out.println();
    }
    public String encoder(String visuals){
        String enc = encoder(message, key, visuals);
        message = enc;
        return enc;
    }

    private String encoder(String message, String key, String visuals){
        String output="";
        int tracker = 0;
        int[] coords = new int[2];
        Scanner e = new Scanner(System.in);
        String cont=visuals;
        while (output.length()<message.length()){
            coords = locater(key.charAt(tracker%key.length()), message.charAt(tracker));
            output = output.concat(table[coords[0]][coords[1]]);
            tracker++;
            if (cont.compareTo("y")==0) {
                System.out.println(ANSI_CYAN+"Enter y to continue, anything else to not show visual"+ANSI_RESET);
                cont = e.nextLine();
                if (cont.compareTo("y")==0) {
                    table_printer(coords);
                    System.out.println(ANSI_PURPLE + output + ANSI_RESET);
                }
            }
        }
        System.out.println(output);
        return output;
    }
    private int[] locater(Character key_letter, Character plaintext_letter){
        int[] coords = new int[2];
        coords[0] = key_letter-64;
        coords[1] = plaintext_letter-64;
        return coords;
    }

    public String decoder(){
        message = decoder(message, key);
        return message;
    }
    private String decoder(String message, String key){
        String output="";
        int tracker = 0;
        int[] coords = new int[2];
        while (output.length()<message.length()){
            coords = decode_locater(key.charAt(tracker%key.length()), message.charAt(tracker));
            output = output.concat(table[0][coords[1]]);
            tracker++;
        }
        System.out.println(output);
        return output;
    }
    private int[] decode_locater(Character keychar, Character plainchar){
        int[] coords = new int[2];
        coords[0] = 0;
        for (int i = 0; i < 26; i++){
            if (table[keychar-64][i].compareTo(Character.toString(plainchar))==0){
                coords[1]=i;
            }
        }
        return coords;
    }
    public static void main(String[] args) {
        Vigener_cipher obj;
        if (args.length!=0){
            if (args[0].compareTo("encode")==0){
                if (args.length==4)
                {
                    if (args[3].compareTo("y")==0)
                        obj= new Vigener_cipher(args[1],args[2],true, "y");
                    else
                        obj= new Vigener_cipher(args[1],args[2],true, "");
                }
                else
                    obj= new Vigener_cipher(args[1],args[2],true, "");
            }
            else
                obj = new Vigener_cipher(args[1],args[2],false, "");
        }
        else {
            Vigener_cipher julian_encode = new Vigener_cipher("JulianSinger", "superb", true, "y");
            Vigener_cipher julian_decode = new Vigener_cipher("BOAMROKCCKVS", "superb", false, "y");
        }
    }
}

