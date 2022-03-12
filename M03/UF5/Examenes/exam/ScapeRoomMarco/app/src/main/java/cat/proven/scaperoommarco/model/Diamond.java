package cat.proven.scaperoommarco.model;

public class Diamond {
    Color color;
    int value;

    public Diamond(Color c, int v){
        this.color=c;
        this.value=v;
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }

    public void incrementCiclic(){
        int i = getValue();
        if(i<9) i++;
        else i = 0;
        setValue(i);
    }
}
