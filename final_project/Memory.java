public class Memory
{
    /*

    http://devernay.free.fr/hacks/chip8/C8TECH10.HTM

    RAM spans from 0x000 (0) to 0xFFF (4095)
    0x000 through 0x1FF are not used.

    */

    private byte[] memory; // 4K memory
    private byte[] v; // 16 data registers
    private char i; // 16-bit index register
    private char pc;  // 16-bit program counter

    private char[] stack; // 16-bit, 16 location stack
    private byte sp; // 8-bit stack pointer

    private char opcode;

    private int delay_timer;
    private int sound_timer;

    /*
    Build these as seperate classes!    

    private byte keyboard; // 16 key hexidecimal keypad

    private byte display; // 64x32 monochrome display
    */


    public Memory()
    {
        memory = new byte[4096]; // 4k memory
        v = new byte[16]; // data registers
        i = 0x0; //
        pc = 0x200;

        stack = new char[16];
        sp = 0;

        delay_timer = 0;
        sound_timer = 0;
    }

    public void fetch()
    {
        // opcode = decode instructions from registers
    }

    public void decode()
    {
        // opcode table
    }
}