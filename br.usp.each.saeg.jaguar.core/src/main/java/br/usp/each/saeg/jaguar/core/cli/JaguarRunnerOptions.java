package br.usp.each.saeg.jaguar.core.cli;

import java.io.File;

import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.usp.each.saeg.jaguar.core.heuristic.Heuristic;
import br.usp.each.saeg.jaguar.core.heuristic.TarantulaHeuristic;

/**
 * Class to represent the Command Line Interface (CLI) options
 * and respective descriptions.
 * 
 * @author Henrique Ribeiro
 *
 */
public class JaguarRunnerOptions {

	private static Logger logger = LoggerFactory.getLogger("JaguarLogger");
    private Heuristic heuristic = new TarantulaHeuristic();
   
    @Option(name = "-dataflow ", aliases = {"-df"}, 
    		usage = "collect data-flow information\n"
    				+ "when this parameter is not set control-flow information is collected")
	private Boolean dataFlow = false;
    
    @Option(name = "-outputType ", aliases = {"-ot"}, 
    		usage = "the output type\n F = Flat, H = Hierarchical"
    				+ "default is F")
	private String outputType = "F";
    
    @Option(name = "-output ", aliases = {"-o"}, 
    		usage = "the output file name\n"
    				+ "default is codeforest")
	private String outputFileName = "codeforest";
    
    @Option(name = "-projectDir ", aliases = {"-p"}, required = true,
    		usage = "the path where the project is located")
    private File projectPath = new File("");
    
    @Option(name = "-classesDir ", aliases = {"-c"},
            usage = "the path where the compiled classes are located\n"
            		+ "default is .\\target\\classes\\")
	private File sourcePath = new File(".\\target\\classes\\");
	
    @Option(name = "-testsDir ", aliases = {"-t", "-td"},
            usage = "the path where the compiled tests are located\n"
            		+ "default is .\\target\\test-classes\\")
	private File testPath = new File(".\\target\\test-classes\\");
	
    @Option(name = "-testsListFile ", aliases = {"-tf"},
            usage = "the file containing the list of tests\n")
	private File testListFile = new File("\\temp\\junittempfilename.txt");
    
    @Option(name = "-logLevel ", aliases = {"-l"}, 
    		usage = "the log level\n ERROR, INFO, DEBUG, TRACE"
    				+ "default is INFO")
    private String logLevel = "INFO";
    
    @Option(name = "-heuristic", aliases = {"-h"},
    		usage = "heuristic name\n"
    				+ "must be one of the heuristic in the package br.usp.each.saeg.jaguar.core.heuristic\n"
    				+ "must be the exact name of the class without the word Heuristic\n"
		    		+ "default is Tarantura")
    public void setHeuristic(String heuristic){
		try {
			this.heuristic = (Heuristic) Class.forName(
					"br.usp.each.saeg.jaguar.core.heuristic." + heuristic + "Heuristic").newInstance();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
    }
    
    public Heuristic getHeuristic() {
		return heuristic;
	}

	public File getProjectPath() {
		return projectPath;
	}

	public File getSourcePath() {
		return sourcePath;
	}

	public File getTestPath() {
		return testPath;
	}
	
	public File getTestListFile() {
		return testListFile;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public String getOutputType() {
		return outputType;
	}
	
	public Boolean getDataFlow() {
		return dataFlow;
	}
	
	public String getLogLevel() {
		return logLevel;
	}

	@Override
	public String toString() {
		return "JaguarRunnerOptions \n"
				+ "heuristic = " + heuristic.getClass().getName() + "\n"
				+ "projectPath = " + projectPath.getPath() + "\n"
				+ "sourcePath = " + sourcePath.getPath() + "\n"
				+ "testPath = " + testPath.getPath() + "\n"
				+ "testListFile = " + testListFile.getPath() + "\n"
				+ "output = " + outputFileName + "\n"
				+ "outputType = " + outputType + "\n"
				+ "logLevel = " + logLevel + "\n"
				+ "dataflow = " + dataFlow ;
	}
	
}
