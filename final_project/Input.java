import javafx.scene.input.KeyCode;

public class Input
{
    public static final int[] FONT = {
        0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
        0x20, 0x60, 0x20, 0x20, 0x70, // 1
        0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
        0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
        0x90, 0x90, 0xF0, 0x10, 0x10, // 4
        0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
        0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
        0xF0, 0x10, 0x20, 0x40, 0x50, // 7
        0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
        0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
        0xF0, 0x90, 0xF0, 0x90, 0x90, // A
        0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
        0xF0, 0x80, 0x80, 0x80, 0xF0, // C
        0xE0, 0x90, 0x90, 0x90, 0xE0, // D
        0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
        0xF0, 0x80, 0xF0, 0x80, 0x80  // F
    };

    /*

    Chip 8 uses a standard font mapped as above

    It is pretty easy to do a web search to find the above list to 
    use in an emulator

    The example below gives an example of how this is represented in
    the actual emulator.  It maps out the character '0'

        Sprite   Binary      Hex    
    .xx..... 0b01100000  0x60    
    x..x.... 0b10010000  0x90    
    x..x.... 0b10010000  0x90    
    x..x.... 0b10010000  0x90    
    .xx..... 0b01100000  0x60    

    */

    private boolean[] controls = new boolean[16];

    /*

    Chip 8 works with a 16 input keypad

    The keypad is HEX based

    Using a boolean array we can store the state of keys
    that are either pressed or not pressed as true or
    false values

    */

    public Input()
    {
        // Initialize all keys as unpressed
        for (int i = 0; i < 15; i++)
        {
            controls[i] = false;
        }
    }

    public setControlDown(KeyCode input)
    {
        switch (input)
        {
            case '1':
                controls[0] = true;
                break;
            case '2':
                controls[1] = true;
                break;
            case '3':
                controls[2] = true;
                break;
            case 'C':
                controls[3] = true;
                break;
            case '4':
                controls[4] = true;
                break;
            case '5':
                controls[5] = true;
                break;
            case '6':
                controls[6] = true;
                break;
            case 'D':
                controls[7] = true;
                break;
            case '7':
                controls[8] = true;
                break;
            case '8':
                controls[9] = true;
                break;
            case '9':
                controls[10] = true;
                break;
            case 'E':
                controls[11] = true;
                break;
            case 'A':
                controls[12] = true;
                break;
            case '0':
                controls[13] = true;
                break;
            case 'B':
                controls[14] = true;
                break;
            case 'F':
                controls[15] = true;
                break;

            return true;
        }
    }

    public setControlUp(KeyCode input)
    {
        switch (input)
        {
            case '1':
                controls[0] = false;
                break;
            case '2':
                controls[1] = false;
                break;
            case '3':
                controls[2] = false;
                break;
            case 'C':
                controls[3] = false;
                break;
            case '4':
                controls[4] = false;
                break;
            case '5':
                controls[5] = false;
                break;
            case '6':
                controls[6] = false;
                break;
            case 'D':
                controls[7] = false;
                break;
            case '7':
                controls[8] = false;
                break;
            case '8':
                controls[9] = false;
                break;
            case '9':
                controls[10] = false;
                break;
            case 'E':
                controls[11] = false;
                break;
            case 'A':
                controls[12] = false;
                break;
            case '0':
                controls[13] = false;
                break;
            case 'B':
                controls[14] = false;
                break;
            case 'F':
                controls[15] = false;
                break;

            return true;
        }
    }

    public boolean isPressed(int input)
    {
        return controls[input];
    }
}