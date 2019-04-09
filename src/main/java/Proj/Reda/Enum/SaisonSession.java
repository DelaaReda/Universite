package Proj.Reda.Enum;

public enum SaisonSession {
    AUTOMNE (0), HIVER (1), ETE (2);

    private int num;
    SaisonSession (int num) {
        this.num = num;

    }

    private int num() {
        return num;
    }
}
