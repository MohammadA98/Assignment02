
package interpreter;

import interpreter.virtualmachine.Program;
import interpreter.ByteCode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {

        Program program = new Program();

        StringTokenizer tokenizer = tokenizeNextLine(null);

        while (tokenizer != null) {
            String className = CodeTable.getClassName(tokenizer.nextToken());

            try {
                ByteCode byteCode = (ByteCode)(Class.forName("interpreter.ByteCode."+className).newInstance());

                ArrayList<String> argumentsList = new ArrayList<>();

                while (tokenizer.hasMoreTokens()) {
                    argumentsList.add(tokenizer.nextToken());
                }

                byteCode.init(argumentsList);

                program.addCode(byteCode);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error in loadCodes() -> cannot create byteCode: "+className+". Exiting program.");
                System.exit(-1);
            }

            tokenizer = tokenizeNextLine(tokenizer);
        }

        program.resolveAddress(program);
        return program;
    }

    private StringTokenizer tokenizeNextLine(StringTokenizer tokenizer) {
        try {
            tokenizer = new StringTokenizer(byteSource.readLine());
        } catch (NullPointerException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return tokenizer;
    }

}

