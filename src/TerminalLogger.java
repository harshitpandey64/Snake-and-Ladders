public class TerminalLogger implements Logger {
    @Override
    public void log(String logMessage) {
        System.out.print(logMessage);
    }
}