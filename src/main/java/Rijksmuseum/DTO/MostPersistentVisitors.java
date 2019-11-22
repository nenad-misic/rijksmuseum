package Rijksmuseum.DTO;

import java.util.List;

public class MostPersistentVisitors {
    private List<PhonePath> phonePaths;

    public MostPersistentVisitors() {
    }

    public MostPersistentVisitors(List<PhonePath> phonePaths) {
        this.phonePaths = phonePaths;
    }

    public List<PhonePath> getPhonePaths() {
        return phonePaths;
    }

    public void setPhonePaths(List<PhonePath> phonePaths) {
        this.phonePaths = phonePaths;
    }
}
