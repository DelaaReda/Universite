package Proj.Reda.Enum;

public enum TypeProgramme {
    BACCALAUREAT(0), MAITRISE(1), DOCTORAT(2), MINEUR(3), MAJEUR(4);


    public final int num;   // numero

    TypeProgramme(int num) {
        this.num = num;

    }

    public int num() {
        return num;
    }


}