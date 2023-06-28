import java.util.ArrayList;
import java.util.List;

public class VirtualProductCodeManager {
    private static VirtualProductCodeManager instance;
    List<String> usedCodes;

    private VirtualProductCodeManager() {
        usedCodes = new ArrayList<>();
    }

    public static VirtualProductCodeManager getInstance(){
        if(instance == null){
            instance = new VirtualProductCodeManager();
        }
        return instance;
    }

    public void useCode(String useCode){
        usedCodes.add(useCode);
    }

    public boolean isCodeUsed(String useCode){
        return usedCodes.contains(useCode);
    }
}
