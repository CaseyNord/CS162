import java.util.Random;

public class Memory
{
    /*

    http://devernay.free.fr/hacks/chip8/C8TECH10.HTM

    RAM spans from 0x000 (0) to 0xFFF (4095)
    0x000 through 0x1FF are not used.

    */

    private byte[] memory; // 4K memory
    private byte[] register; // 16 data registers
    private char index; // 16-bit index register
    private char programCounter;  // 16-bit program counter

    private char[] stack; // 16-bit, 16 location stack
    private byte stackPointer; // 8-bit stack pointer

    private char opCode;

    private int delayTimer;  
    private int soundTimer;

    /*

    We need to use a timers because the timer register and sound register
    runs at 60hz

    To emulate this we set a variable above zero as a delay, and when it
    counts down to zero we trigger the action

    */

    private boolean drawFlag = false;
    private final Random RANDOM = new Random();

    private Display display;
    private Input input;


    public Memory(Display display, Input input)
    {
        this.display = display;
        this.input = input;

        index = 0x0; // Set index register at 0
        programCounter = 0x200; // The program counter always starts here
        stackPointer = 0; // Set stack pointer at 0

        this.delayTimer = 0;
        this.soundTimer = 0;
        this.drawFlag = true;

        // Set up and 0 out memory
        memory = new byte[4096]; // 4k memory
        for (int i = 0; i < memory.length; i++)
        {
            memory[i] = 0;
        }

        // Set up and 0 out register
        register = new byte[16]; // Data registers
        for (int i = 0; i < register.length; i++)
        {
            register[i] = 0;
        }

        // Set up and 0 out stack
        stack = new char[16];
        for (int i = 0; i < stack.length; i++)
        {
            stack[i] = 0;
        }

        // Load font into memory
        for (int i = 0; i < Input.FONT.length; i++)
        {
            memory[i] = Input.FONT[i];
        }
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