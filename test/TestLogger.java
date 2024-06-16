import java.util.ArrayList;
import java.util.List;

public class TestLogger implements Logger{

    List<String> loggedMessages = new ArrayList<>();

    @Override
    public void log(String message){
        loggedMessages.add(message);
    }

    public String messageAt(int pos){
        return loggedMessages.get(pos);
    }

    public int messages(){
        return loggedMessages.size();
    }
}

