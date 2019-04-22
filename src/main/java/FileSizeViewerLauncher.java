import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSizeViewerLauncher {

    @Option(name = "-h", metaVar = "ReadableVersion", usage = "Making file's size readable")
    private boolean hSize;

    @Option(name = "-c", metaVar = "TotalSize", usage = "Total size counting")
    private boolean cSize;

    @Option(name = "--si", metaVar = "DecimalBase", usage = "Changing base from 1024 to 1000")
    private boolean siSize;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        new FileSizeViewerLauncher().doMain(args);
    }

    private void doMain(String[] args) throws IOException {

        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
            if (arguments.isEmpty()) throw new CmdLineException(parser, new Exception());
        }
        catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar du.jar [-h] [-c] [--si] file1 file2 file3 …");
            parser.printUsage(System.err);
            return;
        }

        FileSizeViewer FSV = new FileSizeViewer(arguments);

        FSV.size();

        if (cSize)
            FSV.getSum();


        if (siSize)
            FSV.output(1000, cSize, hSize);
        else
            FSV.output(1024, cSize, hSize);

    }


}
