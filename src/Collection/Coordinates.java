package Collection;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x;
    private Long y;

    public Coordinates (Integer x, Long y){
        this.x = x;
        this.y = y;
    }

    public Integer getX() { return x;}
    public Long getY() { return y;}
}
